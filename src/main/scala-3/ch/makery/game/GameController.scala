// 22100259 Final Project Assignment
package ch.makery.game

import ch.makery.game.model.*
import scalafx.application.Platform
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{AnchorPane, GridPane}
import scalafx.scene.image.ImageView
import javafx.scene.input.{KeyEvent, KeyCode}

class GameController {
  var game: Game = new Game()
  var currentLevel: GameLevel = EasyLevel()

  // UI Components
  var rootPane: AnchorPane = _
  var scoreLabel: Label = _
  var timerLabel: Label = _
  var cellGrid: GridPane = _
  var playButton: Button = _
  var levelButtons: Map[String, Button] = Map()
  var backgroundImageView: ImageView = _

  def initialize(): Unit = {
    currentLevel = EasyLevel()
    updateBackground()
    scoreLabel.text = f"Score: ${game.score}"
    timerLabel.text = f"Time: ${game.timer}"
    levelButtons("Easy").onAction = _ => selectLevel(EasyLevel())
    levelButtons("Medium").onAction = _ => selectLevel(MediumLevel())
    levelButtons("Hard").onAction = _ => selectLevel(HardLevel())
    playButton.onAction = _ => startGame()

    rootPane.onKeyPressed = (event: KeyEvent) => handleKeyPress(event)
    rootPane.requestFocus()
  }
  
  private def updateBackground(): Unit = {
    val background = new Background(currentLevel)
    backgroundImageView.image = background.getImage
  }
  
  private def selectLevel(level: GameLevel): Unit = {
    currentLevel = level
    game.startGame(level)
    updateBackground()
  }

  def startGame(): Unit = {
    game.startGame(currentLevel)
    startTimer()
  }

  private def startTimer(): Unit = {
    new Thread(() => {
      while (!game.isGameOver) {
        Platform.runLater(() => updateUI())
        Thread.sleep(1000)
        game.decrementTimer()
      }
      Platform.runLater(()=> endGame())
    }).start()
  }
  
  private def endGame(): Unit = {
    game.saveHistory()
  }

  def cellClicked(cellIndex: Int): Unit = {
    val character: Character = generateRandomCharacter()
    character.applyEffect(game)
    updateUI()
  }

  private def generateRandomCharacter(): Character = {
    val probabilities = currentLevel.characterProbability
    val randomValue = scala.util.Random.nextInt(100)
    probabilities.collectFirst {
      case (character, threshold) if randomValue < threshold => character match {
        case "Brown Mole" => BrownMole()
        case "Pink Mole" => PinkMole()
        case "Grey Mole" => GreyMole()
        case "Bomb" => Bomb()
      }
    }.getOrElse(BrownMole())
  }
  
  private def updateUI(): Unit = {
    scoreLabel.text = f"Score: ${game.score}"
    timerLabel.text = f"Time: ${game.timer}"
  }

  private def handleKeyPress(event: KeyEvent): Unit = {
    event.getCode match {
      case KeyCode.DIGIT1 => cellClicked(0) // top left
      case KeyCode.DIGIT2 => cellClicked(1) // top center
      case KeyCode.DIGIT3 => cellClicked(2) // top right
      case KeyCode.DIGIT4 => cellClicked(3) // middle left
      case KeyCode.DIGIT5 => cellClicked(4) // middle center
      case KeyCode.DIGIT6 => cellClicked(5) // middle right
      case KeyCode.DIGIT7 => cellClicked(6) // bottom left
      case KeyCode.DIGIT8 => cellClicked(7) // bottom center
      case KeyCode.DIGIT9 => cellClicked(8) // bottom right
      case _ => // ignore
    }
  }
}
