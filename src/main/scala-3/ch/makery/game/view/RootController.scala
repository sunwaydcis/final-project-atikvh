// 22100259 Final Project Assignment
package ch.makery.game.view

import javafx.fxml.{FXML, FXMLLoader}
import scalafx.scene.control.{Menu, MenuBar, MenuItem}
import scalafx.scene.layout.{BorderPane, StackPane}

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
    viewHistory.setOnAction(_=> showHistory())
    howTo.setOnAction(_=> showTutorial())
  }
  
  private def showHistory(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("/ch/makery/game/view/HistoryView.fxml"))
    val historyView = loader.load[StackPane]()
    rootPane.center = historyView
  }
  
  private def showTutorial(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("/ch/makery/game/view/TutorialView.fxml"))
    val tutorialView = loader.load[StackPane]()
    rootPane.center = tutorialView
  }
}
