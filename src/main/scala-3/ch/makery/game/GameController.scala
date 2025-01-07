// 22100259 Final Project Assignment
package ch.makery.game

import ch.makery.game.model.Game
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
    scoreLabel.text = s"Score: ${game.score}"
    timerLabel.text = s"Time: ${game.timer}"
  }

  def startGame(level: String): Unit = {
    game.startGame(level)
    initialize()
    // Start timer
    new Thread(() => {
      while (game.timer > 0) {
        Platform.runLater(() => timerLabel.text = s"Time: ${game.timer}")
        Thread.sleep(1000)
        game.decrementTimer()
      }
    }).start()
  }

  def cellClicked(cellIndex: Int): Unit = {
    //Generate random character
    val character: Character = generateRandomCharacter()
    
    //Apply character effect on the game
    character.applyEffect(game)
    
    //Update the score label
    scoreLabel.text = f"Score: ${game.score}"
  }
  
  def generateRandomCharacter(): Character = {
    val random = scala.util.Random.nextInt(100)
    random match {
      case r if r < 50 => new BrownMole() //50% chance
      case r if r < 75 => new PinkMole() //25% chance
      case r if r < 90 => new GreyMole() //15% chance
      case _ => new Bomb() //10% chance
    }
  }
}
