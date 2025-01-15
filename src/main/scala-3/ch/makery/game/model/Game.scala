// 22100259 Final Project Assignment
package ch.makery.game.model

import ch.makery.game.util.Database
import scalikejdbc.*
import ch.makery.game.model.GameLevel
import scala.collection.mutable
import java.time.LocalDateTime
import java.sql.*

class Game {
  private var _score: Int = 0
  private var _timer: Int = 180 
  private var _level: GameLevel = EasyLevel()
  private var _speed: Int = _level.speed
  
  def score: Int = _score
  def timer: Int = _timer
  def level: GameLevel = _level
  def speed: Int = _speed

  def startGame(level: GameLevel): Unit = {
    _level = level
    _score = 0
    _timer = _level.initialTimer
    _speed = _level.speed
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
        Thread.sleep(_speed)
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

object Game extends Database{
  def apply(dateTime: LocalDateTime, levelS: GameLevel, scoreS: Int): Game =
    new Game(scoreS, levelS.initialTimer, levelS, levelS.speed, dateTime)

  def initializeTable() = Unit 
    DB autoCommit { implicit session =>
      sql"""
          CREATE TABLE game (
          id int NOT NULL GENERATED ALWAYS AS IDENTITY
          (START WITH 1, INCREMENT BY 1),
          dateTime timestamp,
          level varchar(5),
          score int
          )
          """.execute.apply()
    }
  
  
  def getAllGames: List[Game] = {
    DB readOnly { implicit session =>
      sql"SELECT * FROM game".map(rs => Game(
        rs.localDateTime("dateTime"),
        GameLevel.fromString(rs.string("level")),
        rs.int("Score")
      )).list.apply()
    }
  } 
}
