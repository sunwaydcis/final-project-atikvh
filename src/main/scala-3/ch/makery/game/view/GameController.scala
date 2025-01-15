//22100259 Final Assignment Project
package ch.makery.game.view

import ch.makery.game.model.{Character, EasyLevel, Game, GameLevel}
import javafx.fxml.FXML
import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.application.Platform
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{AnchorPane, GridPane, Pane}

import java.time.LocalDateTime
import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}

class GameController {
  @FXML var rootPane: AnchorPane = _
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

  private val cellImages = Array(
    cellImage0, cellImage1, cellImage2, cellImage3,
    cellImage4, cellImage5, cellImage6, cellImage7, cellImage8
  )

  private val game = new Game()
  private var currentLevel: GameLevel = EasyLevel()
  private implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  def initialize(): Unit = {
    startGame(currentLevel)
    attachTapHandlers()
  }

  def startGame(level: GameLevel): Unit = {
    currentLevel = level
    game.startGame(level)
    updateBackground()
    game.startGameLoop()

    Future {
      while (!game.isGameOver) {
        Platform.runLater(updateUI())
        Thread.sleep(500)
      }

      Platform.runLater(() => {
        Game.saveGameToDatabase(LocalDateTime.now(), currentLevel.name, game.score)
        updateUI()
      })
    }
  }

  private def updateBackground(): Unit = {
    backgroundImageView.image = new Image(currentLevel.imagePath)
  }

  def cellClicked(cellIndex: Int): Unit = {
    val character = game.generateRandomCharacterAtCell(cellIndex)
    if (character.isDefined) {
      character.get.applyEffect(game)
      removeCharacterFromCell(cellIndex)
      updateUI()
    }
  }

  private def attachTapHandlers(): Unit = {
    cellImages.zipWithIndex.foreach { case (imageView, index) =>
      imageView.onMouseClicked = (_: MouseEvent) => {
        cellClicked(index)
      }
    }
  }

  def spawnCharacterAtRandomCell(): Unit = {
    val randomCell = scala.util.Random.nextInt(cellImages.length)
    val character = game.generateRandomCharacter()
    placeCharInCell(character, randomCell)

    // Remove the character after a timeout if not clicked
    Future {
      Thread.sleep(currentLevel.speed)
      if (game.getCurrentCharacterAtCell(randomCell).contains(character)) {
        removeCharacterFromCell(randomCell)
        Platform.runLater(updateUI())
      }
    }
  }

  private def placeCharInCell(character: Character, cellIndex: Int): Unit = {
    val characterImage = cellImages(cellIndex)
    game.placeCharacterInCell(character, cellIndex)
    Platform.runLater {
      characterImage.setImage(new Image(character.imagePath))
    }
  }

  private def removeCharacterFromCell(cellIndex: Int): Unit = {
    val characterImage = cellImages(cellIndex)
    game.removeCharacterFromCell(cellIndex)
    Platform.runLater {
      characterImage.setImage(null)
    }
  }

  def handleKeyPress(event: KeyEvent): Unit = {
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
      case _ => // ignore
    }
  }

  private def updateUI(): Unit = {
    scoreLabel.text = f"Score: ${game.score}"
    timerLabel.text = f"Time: ${game.timer}"
    if (game.isGameOver) {
      timerLabel.text = "Game Over!"
    }
  }
}
