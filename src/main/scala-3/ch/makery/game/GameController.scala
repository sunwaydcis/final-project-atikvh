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

  }

  def startGame(level: String): Unit = {
    game.startGame(level)
  }

  def cellClicked(cellIndex: Int): Unit = {

  }
  
  def generateRandomCharacter(): Character = {

  }
}
