// 22100259 Final Project Assignment
package ch.makery.game.model

import scala.collection.mutable
import java.time.LocalDateTime

class Game {
  private var _score: Int = 0 // Change when the game is started
  private var _timer: Int = 180 // Default timer is 3 minutes - also change
  private var _level: String = "" // Change according to player's choice
  private val _history: mutable.ListBuffer[GameHistory] = mutable.ListBuffer()

  // Accessor
  def score: Int = _score
  def timer: Int = _timer
  def level: String = _level
  def history: Seq[GameHistory] = _history.toSeq // Immutable history view

  def startGame(level: String): Unit = {
    _level = level
    _score = 0
    _timer = 180
  }

  def updateScore(points: Int): Unit = {
    _score = Math.max(0, _score + points) // so score not negative
  }

  def decrementTimer(): Unit = {
    _timer = Math.max(0, _timer - 1) // so timer not negative
  }

  def isGameOver: Boolean = _timer <= 0

  def saveHistory(): Unit = {
    _history += GameHistory(java.time.LocalDateTime.now(), _level, _score)
  }

  def deleteHistory(index: Int): Unit = {
    if (index >= 0 && index < history.size) _history.remove(index)
  }

  def reset(): Unit = {
    _score = 0
    _timer = 180
  }
}

case class GameHistory(dateTime: java.time.LocalDateTime, level: String, score: Int)
