// 22100259 Final Project Assignment
package ch.makery.game.view

import ch.makery.game.model.*
import javafx.fxml.FXML
import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.application.Platform
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.GridPane

class GameController {
  @FXML var scoreLabel: Label = _
  @FXML var timerLabel: Label = _
  @FXML var cellGrid: GridPane = _
  @FXML var backgroundImageView: ImageView = _

  private var game: Game = new Game()
  private var currentLevel: GameLevel = EasyLevel()
  private val cells: Array[GameCell] = Array.ofDim(9)

  def initialize(): Unit = {
    setupGrid()
    startGame(currentLevel)
  }

  private def setupGrid(): Unit = {
    cellGrid.getChildren.clear()
    for (i <- 0 until 9) {
      val cell = new GameCell()
      cells(i) = cell
      cellGrid.add(cell, i % 3, i / 3)
      cell.onClick = () => cellClicked(i)
    }
    cellGrid.sceneProperty().addListener((_, _, newScene) => {
      if (newScene != null) {
        newScene.addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => handleKeyPress(event))
      }
    })
  }

  def startGame(level: GameLevel): Unit = {
    currentLevel = level
    game.startGame(level)
    updateBackground()
    startTimer()
  }

  private def startTimer(): Unit = {
    new Thread(() => {
      while (!game.isGameOver) {
        Platform.runLater(() => updateUI())
        Thread.sleep(1000)
        game.decrementTimer()
      }
      Platform.runLater(() => endGame())
    }).start()
  }

  private def updateBackground(): Unit = {
    val gameBackgroundImagePath = currentLevel match {
      case EasyLevel() => "assets/Easy.png"
      case MediumLevel() => "assets/Medium.png"
      case HardLevel() => "assets/Hard.png"
    }
    backgroundImageView.image = new Image(gameBackgroundImagePath)
  }

  def cellClicked(cellIndex: Int): Unit = {
    val character = generateRandomCharacter()
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

  private def handleKeyPress(event: KeyEvent): Unit = {
    event.getCode match {
      case KeyCode.Q => cellClicked(0)
      case KeyCode.W => cellClicked(1)
      case KeyCode.E => cellClicked(2)
      case KeyCode.A => cellClicked(3)
      case KeyCode.S => cellClicked(4)
      case KeyCode.D => cellClicked(5)
      case KeyCode.Z => cellClicked(6)
      case KeyCode.X => cellClicked(7)
      case KeyCode.C => cellClicked(8)
      case _ => // Ignore
    }
  }

  private def updateUI(): Unit = {
    scoreLabel.text = f"Score: ${game.score}"
    timerLabel.text = f"Time: ${game.timer}"
  }

  private def endGame(): Unit = {
    game.saveHistory()
  }
}
