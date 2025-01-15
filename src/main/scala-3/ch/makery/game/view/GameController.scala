// 22100259 Final Project Assignment
package ch.makery.game.view

import ch.makery.game.model.*
import javafx.fxml.FXML
import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.application.Platform
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{AnchorPane, GridPane, Pane}

class GameController {
  @FXML var rootPane: AnchorPane =_
  @FXML var scoreLabel: Label = _
  @FXML var timerLabel: Label = _
  @FXML var cellGrid: GridPane = _
  @FXML var backgroundImageView: ImageView = _
  @FXML var cell0: Pane = _
  @FXML var cell1: Pane = _
  @FXML var cell2: Pane = _
  @FXML var cell3: Pane = _
  @FXML var cell4: Pane = _
  @FXML var cell5: Pane = _
  @FXML var cell6: Pane = _
  @FXML var cell7: Pane = _
  @FXML var cell8: Pane = _
  @FXML var cellImage0: ImageView = _
  @FXML var cellImage1: ImageView = _
  @FXML var cellImage2: ImageView = _
  @FXML var cellImage3: ImageView = _
  @FXML var cellImage4: ImageView = _
  @FXML var cellImage5: ImageView = _
  @FXML var cellImage6: ImageView = _
  @FXML var cellImage7: ImageView = _
  @FXML var cellImage8: ImageView = _


  private var game: Game = new Game()
  private var currentLevel: GameLevel = EasyLevel()

  def initialize(): Unit = {
    startGame(currentLevel)
  }

  def startGame(level: GameLevel): Unit = {
    currentLevel = level
    game.startGame(level)
    updateBackground()
    game.startGameLoop()
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
    val character: Character = game.generateRandomCharacter()
    placeCharInCell(character, cellIndex)
    character.applyEffect(game)
    updateUI()
  }
  
  private def placeCharInCell(character: Character, cellIndex: Int): Unit = {
    val characterImage = cellImages(cellIndex)
    characterImage.image = new Image(character.imagePath)
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
      case _ => 
    }
  }

  private def updateUI(): Unit = {
    scoreLabel.text = f"Score: ${game.score}"
    timerLabel.text = f"Time: ${game.timer}"
  }