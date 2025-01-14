// 22100259 Final Project Assignment
package ch.makery.game.view

import javafx.fxml.FXML
import scalafx.scene.control.{Menu, MenuBar, MenuItem}
import scalafx.scene.layout.BorderPane

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
  
  private var gameInProgress: Boolean = false
  private var gameOver: Boolean = false

  def initialize(): Unit = {
    setupMenuActions()
    updateMenuPrivileges()
  }

  private def setupMenuActions(): Unit = {
    playOpt.setOnAction(_ => resumeGame())
    pauseOpt.setOnAction(_ => pauseGame())
    endOpt.setOnAction(_ => endGame())
    playAgainOpt.setOnAction(_ => playAgain())
    viewHistory.setOnAction(_ => showHistory())
    character.setOnAction(_ => showTutorial("Characters.png"))
    howTo.setOnAction(_ => showTutorial("Tutorial.png"))
  }
  
  private def resumeGame(): Unit = {
    if (gameInProgress){
      println("Game Resumed")
      updateMenuPrivileges()
    }
  }
  
  private def pauseGame(): Unit = {
    if (gameInProgress) {
      println("Game Resumed")
      updateMenuPrivileges()
    }
  }
  
  private def endGame() : Unit ={
    if (gameInProgress) {
      println("Game Ended")
      gameInProgress = false
      gameOver = true
      updateMenuPrivileges()
    }
  }
  
  private def playAgain(): Unit = {
    if (gameOver) {
      println("Game restarted.")
      gameInProgress = true
      gameOver = false
      updateMenuPrivileges()
    }
  }
  
  private def updateMenuPrivileges(): Unit = {
    playOpt.setDisable(!gameInProgress)
    pauseOpt.setDisable(!gameInProgress)
    endOpt.setDisable(!gameInProgress)
    playAgainOpt.setDisable(!gameOver)
  }
  
  private def showHistory(): Unit = {
    println("Displaying game history...")
  }
  
  private def showTutorial(imagePath: String): Unit = {
    println(f"$imagePath")
  }
}
