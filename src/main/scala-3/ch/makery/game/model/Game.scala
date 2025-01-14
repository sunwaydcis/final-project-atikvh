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
  

  def startGame(level: GameLevel): Unit = {
    _level = level
    _score = 0
    _timer = _level.initialTimer
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
}

case class GameHistory(dateTime: java.time.LocalDateTime, level: String, score: Int)
