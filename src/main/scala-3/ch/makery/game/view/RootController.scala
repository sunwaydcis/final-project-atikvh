// 22100259 Final Project Assignment
package ch.makery.game.view

import javafx.fxml.FXML
import scalafx.scene.control.{Menu, MenuBar, MenuItem}
import scalafx.scene.layout.BorderPane

class RootController {
  @FXML var rootPane: BorderPane = _
  @FXML var menuBar: MenuBar = _
  @FXML var history: Menu = _
  @FXML var viewHistory: MenuItem = _
  @FXML var tutorial: Menu = _
  @FXML var howTo: MenuItem = _
  

  def initialize(): Unit = {
    setupMenuActions()
  }

  private def setupMenuActions(): Unit = {
    
  }
  
  private def showHistory(): Unit = {
    //open History upon 'view history' menuitem chosen in history
  }
  
  private def showTutorial(imagePath: String): Unit = {
    //open howTo upon 'how to play' menuitem chosen in tutorial
  }
}
