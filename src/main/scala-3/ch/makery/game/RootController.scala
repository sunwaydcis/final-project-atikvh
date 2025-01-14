// 22100259 Final Project Assignment
package ch.makery.game

import javafx.fxml.FXML
import scalafx.scene.layout.AnchorPane
import scalafx.scene.control.MenuBar

class RootController {
  @FXML var rootPane: AnchorPane = _
  @FXML var menuBar: MenuBar = _

  def initialize(): Unit = {
    // Initialize menu actions
    setupMenuActions()
  }

  private def setupMenuActions(): Unit = {
    // Bind menu items (e.g., Pause, Restart, etc.)
  }

  def navigateToMain(): Unit = {
    // Logic to switch to MainView
  }

  def navigateToGame(): Unit = {
    // Logic to switch to GameView
  }

  def navigateToTutorial(): Unit = {
    // Logic to switch to TutorialView
  }
}
