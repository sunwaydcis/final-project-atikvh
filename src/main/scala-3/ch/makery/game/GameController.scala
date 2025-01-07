// 22100259 Final Project Assignment
package ch.makery.game

import ch.makery.game.model.{Character, Game, GameLevelFactory}
import ch.makery.game.model.{Character, BrownMole, PinkMole, GreyMole, Bomb}
import scalafx.application.Platform
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.GridPane

class GameController {
  var game: Game = new Game()

  // UI Components
  var scoreLabel: Label = _
  var timerLabel: Label = _
  var cellGrid: GridPane = _

  def initialize(): Unit = {
    updateUI()
  }

  def startGame(level: String): Unit = {
    game.startGame(level)
    updateUI()
    startTimer()
  }

  private def updateUI(): Unit = {
    scoreLabel.text = f"Score: ${game.score}"
    timerLabel.text = f"Time: ${game.timer}"
  }

  private def startTimer(): Unit = {
    new Thread(() => {
      while (!game.isGameOver) {
        Platform.runLater(() => updateUI())
        Thread.sleep(1000)
        game.decrementTimer()
      }
    }).start()
  }

  def cellClicked(cellIndex: Int): Unit = {

  }

  def generateRandomCharacter(): Unit = {
  }
  
}
