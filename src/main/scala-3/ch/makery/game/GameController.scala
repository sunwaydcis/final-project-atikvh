// 22100259 Final Project Assignment
package ch.makery.game

import ch.makery.game.model._
import scalafx.application.Platform
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{AnchorPane, GridPane}
import scalafx.scene.image.ImageView

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
    // based on character probability
    val probabilities = currentLevel.characterProbability
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
  
  private def updateUI(): Unit = {
    scoreLabel.text = f"Score: ${game.score}"
    timerLabel.text = f"Time: ${game.timer}"
  }
}
