// 22100259 Final Project Assignment
package ch.makery.game

import ch.makery.game.model.{Bomb, BrownMole, Character, Game, GreyMole, PinkMole, GameLevel}
import scalafx.application.Platform
import scalafx.scene.control.{Button,Label}
import scalafx.scene.layout.{AnchorPane, GridPane}
import scalafx.scene.image.ImageView

class GameController {
  var game: Game = new Game()
  var currentLevel: GameLevel = _

  // UI Components
  var rootPane: AnchorPane = _
  var scoreLabel: Label = _
  var timerLabel: Label = _
  var cellGrid: GridPane = _
  var playButton: Button = _
  var levelButtons: Map[String, Button] = Map()

  var backgroundImageView: ImageView = _

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
    // Map cell to character
    val character: Character = generateRandomCharacter()
    character.applyEffect(game)
    updateUI()
  }

  private def generateRandomCharacter(): Character = {
    // based on character probability
    val probabilities = game.level.characterProbability
    val randomValue = scala.util.Random.nextInt(100)
    probabilities.collectFirst {
      case (character, threshold) if randomValue < threshold => character match {
        case "Brown Mole" => BrownMole()
        case "Pink Mole" => PinkMole()
        case "Grey Mole" => GreyMole()
        case "Bomb" => Bomb()
      }
    }.getOrElse(BrownMole()) // -> default character
  }
}
