// 22100259 Final Project Assignment
package ch.makery.game.model

import scala.collection.mutable
import java.time.LocalDateTime

class Game {
  private var _score: Int = 0
  private var _timer: Int = 180 
  private var _level: GameLevel = EasyLevel() 
  private val _history: mutable.ListBuffer[GameHistory] = mutable.ListBuffer()
  private var _speed: Int = _level.speed
  
  def score: Int = _score
  def timer: Int = _timer
  def level: GameLevel = _level
  def history: Seq[GameHistory] = _history.toSeq // Immutable history view
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

  def saveHistory(): Unit = {
    _history += GameHistory(java.time.LocalDateTime.now(), _level.name, _score)
  }

  def deleteHistory(index: Int): Unit = {
    if (index >= 0 && index < history.size) _history.remove(index)
  }

  def reset(): Unit = {
    _score = 0
    _timer = _level.initialTimer
  }
  
  private def startGameLoop(): Unit = {
    new Thread(()=> {
      while (!isGameOver) {
        spawnCharacter()
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
  
  private def spawnCharacter(): Unit = {
    val character = generateRandomCharacter()
  }
  
  private def generateRandomCharacter(): Unit = {
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

case class GameHistory(dateTime: java.time.LocalDateTime, level: String, score: Int)
