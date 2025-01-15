//22100250 Final Project Assignment
package ch.makery.game.view

import ch.makery.game.model.{EasyLevel, GameLevel, HardLevel, MediumLevel}
import javafx.fxml.FXML
import scalafx.scene.control.Button
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.AnchorPane
import scalafx.scene.text.Text

class MainController {
  @FXML var rootPane: AnchorPane = _
  @FXML var playButton: Button = _
  @FXML var easyButton: Button = _
  @FXML var mediumButton: Button = _
  @FXML var hardButton: Button = _
  @FXML var backgroundImageView: ImageView = _
  @FXML var titleLabel: Text = _

  private var currentLevel: GameLevel = EasyLevel()

  def initialize(): Unit = {
    setInitialBackground()
    setUpButtonActions()
  }

  private def setInitialBackground(): Unit = {
    backgroundImageView.setImage(new Image("assets/Easy 1.png"))
  }
  
  private def setUpButtonActions(): Unit = {
    easyButton.setOnAction(_ => setLevel(EasyLevel()))
    mediumButton.setOnAction(_ => setLevel(MediumLevel()))
    hardButton.setOnAction(_ => setLevel(HardLevel()))
    playButton.setOnAction(_ => startGame())
  }
  
  private def setLevel(level: GameLevel): Unit = {
    currentLevel = level
    val backgroundImagePath = level match {
      case EasyLevel() => "assets/Easy 1.png"
      case MediumLevel() => "assets/Medium 1.png"
      case HardLevel() => "assets/Hard 1.png"
    }
    backgroundImageView.setImage(new Image(backgroundImagePath))
  }

  private def startGame(): Unit = {
    println(f"Starting game with level: $currentLevel")
    val gameController = new GameController()
    gameController.startGame(currentLevel)
  }
}
