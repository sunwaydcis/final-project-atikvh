// 22100259 Final Project Assignment
package ch.makery.game.model

import ch.makery.game.util.Database
import scalikejdbc.*
import ch.makery.game.model.GameLevel
import scala.collection.mutable
import java.time.LocalDateTime
import java.sql.*

class Game(
            private var _score: Int = 0,
            private var _timer: Int = 180,
            private var _level: GameLevel = EasyLevel()
          ) {
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


  def reset(): Unit = {
    _score = 0
    _timer = _level.initialTimer
  }
  
  def startGameLoop(): Unit = {
    new Thread(()=> {
      while (!isGameOver) {
        generateRandomCharacter()
        Thread.sleep(_level.speed)
      }
    }).start()
    new Thread(()=> {
      while(!isGameOver) {
        Thread.sleep(1000)
        decrementTimer()
      }
    }).start()
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
