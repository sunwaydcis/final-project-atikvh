package ch.makery.game

import javafx.fxml.FXMLLoader
import javafx.scene as jfxs
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene as sfxs
import scalafx.scene.Scene
import scalafx.scene.layout.*

object MainApp extends JFXApp3 {
  var roots: Option[BorderPane] = None

  override def start(): Unit = {
    val rootResource = getClass.getResource("src/main/resources/ch/makery/game/view/RootLayout.fxml")
    val loader = new FXMLLoader(rootResource)
    loader.load()

    roots = Option(loader.getRoot[jfxs.layout.BorderPane])

    stage = new PrimaryStage():
      title = "Whack-a-mole Game"
      scene = new Scene(roots.get) {
        maxWidth = 960.0
        maxHeight = 540.0
      }
    showMain()
  }

  def showMain(): Unit = {
    val resource = getClass.getResource("view/MainView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.get.center = roots
  }
}
