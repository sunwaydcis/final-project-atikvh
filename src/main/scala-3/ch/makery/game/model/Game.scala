package ch.makery.game.model

import scala.collection.mutable

class Game {
  var score: Int = 0
  var timer: Int = 180 // 3 minutes in seconds
  var level: String = "Easy"
  val history: mutable.ListBuffer[GameHistory] = mutable.ListBuffer()

  def startGame(level: String): Unit = {
    this.level = level
    score = 0
    timer = if (level == "Easy") 180 else if (level == "Medium") 180 else 180
  }

  def updateScore(points: Int): Unit = {
    score = Math.max(0, score + points)
  }

  def decrementTimer(): Unit = {
    timer = Math.max(0, timer - 1)
  }

  def saveHistory(): Unit = {
    history += GameHistory(java.time.LocalDateTime.now(), level, score)
  }

  def deleteHistory(index: Int): Unit = {
    if (index >= 0 && index < history.size) history.remove(index)
  }
}

case class GameHistory(dateTime: java.time.LocalDateTime, level: String, score: Int)
