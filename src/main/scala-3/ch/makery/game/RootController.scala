// 22100259 Final Project Assignment
package ch.makery.game

import javafx.fxml.FXML
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.{Menu, MenuBar, MenuItem}

class RootController {
  @FXML var rootPane: BorderPane = _
  @FXML var menuBar: MenuBar = _
  @FXML var menu: Menu = _
  @FXML var playOpt: MenuItem = _
  @FXML var pauseOpt: MenuItem = _
  @FXML var endOpt: MenuItem = _
  @FXML var playAgainOpt: MenuItem = _
  @FXML var history: Menu = _
  @FXML var viewHistory: MenuItem = _
  @FXML var tutorial: Menu = _
  @FXML var character: MenuItem = _
  @FXML var howTo: MenuItem = _
  

  def initialize(): Unit = {
    // Initialize menu actions
    setupMenuActions()
  }

  private def setupMenuActions(): Unit = {
    
  }

  def navigateToMain(): Unit = {
    // Logic to switch to MainView
  }

  def navigateToGame(): Unit = {
    // Logic to switch to GameView
  }
  
}
