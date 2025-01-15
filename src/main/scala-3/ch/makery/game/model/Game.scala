// 22100259 Final Project Assignment
package ch.makery.game.model

import ch.makery.game.util.Database
import scalikejdbc.*
import java.time.LocalDateTime
import scala.collection.mutable

class Game(
            private var _score: Int = 0,
            private var _timer: Int = 180,
            private var _level: GameLevel = EasyLevel()
          ) {
  private val cellCharacters: mutable.Map[Int, Character] = mutable.Map()
  def score: Int = _score
  def timer: Int = _timer
  def level: GameLevel = _level

  def startGame(level: GameLevel): Unit = {
    _level = level
    _score = 0
    _timer = _level.initialTimer
    startGameLoop()
  }

  def updateScore(points: Int): Unit = {
    _score = Math.max(0, _score + points) // so score not negative
  }

  def decrementTimer(): Unit = {
    _timer = Math.max(0, _timer - 1) // so timer not negative
  }
  
  def decrementTimerBy(seconds: Int): Unit = {
    _timer = Math.max(0, _timer - seconds)
  }

  def isGameOver: Boolean = _timer == 0

  def onCharacterHit(character: Character): Unit = {
    character.applyEffect(this)
  }

  def startGameLoop(): Unit = {
    new Thread(() => {
      while (_timer > 0) {
        val randomCell = scala.util.Random.nextInt(9)
        val character = generateRandomCharacter()
        placeCharacterInCell(character, randomCell)

        Thread.sleep(_level.speed)

        if (cellCharacters.contains(randomCell)) {
          cellCharacters.remove(randomCell)
        }
      }
    }).start()

    new Thread(() => {
      while (_timer > 0) {
        Thread.sleep(1000)
        decrementTimer()
      }
    }).start()
  }

  def placeCharacterInCell(character: Character, cellIndex: Int): Unit = {
    cellCharacters(cellIndex) = character
  }

  def removeCharacterFromCell(cellIndex: Int): Unit = {
    cellCharacters.remove(cellIndex)
  }
  
  def generateRandomCharacter(): Character = {
    val randomValue = scala.util.Random.nextInt(100)
    _level.characterProbability.collectFirst {
      case (character, threshold) if randomValue < threshold => character match {
        case "Brown Mole" => new BrownMole()
        case "Pink Mole" => new PinkMole()
        case "Grey Mole" => new GreyMole()
        case "Bomb" => new Bomb()
      }
    }.getOrElse(new BrownMole())
  }

  def generateRandomCharacterAtCell(cellIndex: Int): Option[Character] = {
    val character = generateRandomCharacter()
    placeCharacterInCell(character, cellIndex)
    Some(character)
  }

  def getCurrentCharacterAtCell(cellIndex: Int): Option[Character] = {
    cellCharacters.get(cellIndex)
  }
}

object Game extends Database {
  def apply(dateTime: LocalDateTime, levelS: String, scoreS: Int): Game =
    new Game(scoreS, 0, GameLevel.fromString(levelS))

  def initializeTable(): Unit = {
    DB autoCommit { implicit session =>
      sql"""
          CREATE TABLE gameHistory (
            id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
            dateTime TIMESTAMP NOT NULL,
            level VARCHAR(10) NOT NULL,
            score INT NOT NULL
          )
          """.execute.apply()
    }
  }

  def saveGameToDatabase(dateTime: LocalDateTime, level: String, score: Int): Unit = {
    DB autoCommit { implicit session =>
      sql"""
          INSERT INTO gameHistory (dateTime, level, score)
          VALUES (${dateTime}, ${level}, ${score})
          """.update.apply()
    }
  }

  def getAllGamesWithId: List[(Int, LocalDateTime, String, Int)] = {
    DB readOnly { implicit session =>
      sql"SELECT id, dateTime, level, score FROM game".map(rs => (
        rs.int("id"),
        rs.localDateTime("dateTime"),
        rs.string("level"),
        rs.int("score")
      )).list.apply()
    }
  }
}
