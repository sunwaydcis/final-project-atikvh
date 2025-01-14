//22100250 Final Project Assignment
package ch.makery.game

import ch.makery.game.model.{EasyLevel, GameLevel, HardLevel, MediumLevel}
import javafx.fxml.FXML
import scalafx.scene.control.{Button, Label}
import scalafx.scene.image.{Image, ImageView}

class MainController {
  @FXML var playButton: Button = _
  @FXML var levelButtons: Map[String, Button] = Map()
  @FXML var backgroundImageView: ImageView = _
  @FXML var titleLabel: Label = _

  private var currentLevel: GameLevel = EasyLevel()

  def initialize(): Unit = {
    setupLevelButtons()
    playButton.onAction = _ => startGame()
  }

  private def setupLevelButtons(): Unit = {
    levelButtons("Easy").onAction = _ => selectLevel(EasyLevel())
    levelButtons("Medium").onAction = _ => selectLevel(MediumLevel())
    levelButtons("Hard").onAction = _ => selectLevel(HardLevel())
  }

  private def selectLevel(level: GameLevel): Unit = {
    currentLevel = level
    updateBackground()
  }

  private def updateBackground(): Unit = {
    val backgroundImagePath = currentLevel match {
      case EasyLevel() => "assets/Easy 1.png"
      case MediumLevel() => "assets/Medium 1.png"
      case HardLevel() => "assets/Hard 1.png"
    }
    backgroundImageView.image = new Image(backgroundImagePath)
  }

  private def startGame(): Unit = {
    // Navigate to GameView and pass the selected level
  }
}
