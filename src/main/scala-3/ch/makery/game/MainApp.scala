package ch.makery.game

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import javafx.fxml.FXMLLoader
import scalafx.scene as sfxs
import javafx.scene as jfxs
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.Includes.*

object MainApp extends JFXApp3 {
  var roots: Option[sfxs.layout.BorderPane] = None
  override def start(): Unit = {
    val rootResource = getClass.getResource("view/RootLayout.fxml")
    val loader = new FXMLLoader(rootResource)
    loader.load()
    roots = Option(loader.getRoot[jfxs.layout.BorderPane])

    stage = new PrimaryStage():
      title = "Whack-a-mole Game"
      scene = new Scene():
        root = roots.get
  }
}
