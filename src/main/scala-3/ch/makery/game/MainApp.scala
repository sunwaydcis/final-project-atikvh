//22100259 Final Project Assignment
package ch.makery.game

import ch.makery.game.MainApp.{getClass, stage}
import javafx.fxml.FXMLLoader
import javafx.scene as jfxs
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene as sfxs
import scalafx.scene.Scene

object MainApp extends JFXApp3 {
  var roots: Option[sfxs.layout.BorderPane] = None
  override def start(): Unit = {
    val rootResource = getClass.getResource("view/RootLayout.fxml")
    val loader = new FXMLLoader(rootResource)
    loader.load()
    roots = Option(loader.getRoot[jfxs.layout.BorderPane])

    stage = new PrimaryStage():
      title = "Whack-a-mole Game"
      scene = new Scene(){
        root = roots.get
        maxWidth = 960.0
        maxHeight = 540.0
      }

    showGame()
  }
  
  def showMain(): Unit = {
    
  }
  
  def showGame(): Unit = {
    val resource = getClass.getResource("view/MainView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.get.center = roots
  }
  
  def showHistory(): Unit = {
    
  }
  
  def showTutorial(): Unit = {
    
  }
}
