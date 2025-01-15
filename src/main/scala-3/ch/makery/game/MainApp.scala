package ch.makery.game

import ch.makery.game.view.{MainController, RootController}
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.layout.BorderPane

object MainApp extends JFXApp3 {
  var roots: Option[BorderPane] = None
  var mainController: Option[MainController] = None
  var rootController: Option[RootController] = None

  override def start(): Unit = {
    val rootResource = getClass.getResource("/ch/makery/game/view/RootLayout.fxml")
    val loader = new FXMLLoader(rootResource)

    // Load the RootLayout.fxml for the menu bar
    roots = Option(loader.load())

    // Get the RootController instance from FXML
    rootController = Option(loader.getController[RootController])

    // Setup the primary stage and scene
    stage = new PrimaryStage():
      title = "Whack-a-mole Game"
      scene = new Scene() {
        root = roots.get
        maxWidth = 960.0
        maxHeight = 540.0
      }
    showMain()
  }

  def showMain(): Unit = {
    val mainViewResource = getClass.getResource("/ch/makery/game/view/MainView.fxml")
    val mainLoader = new FXMLLoader(mainViewResource)
    
    val mainRoot = mainLoader.load()
    
    mainController = Option(mainLoader.getController[MainController])
    
    rootController.foreach { controller =>
      controller.rootPane.setCenter(mainRoot) 
    }

    mainController.foreach { controller =>
      controller.initialize() 
    }
    println("Main game screen is now visible!")
  }
}
