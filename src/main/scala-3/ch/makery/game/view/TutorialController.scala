// 22100259 Final Project Assignment
package ch.makery.game.view

import javafx.fxml.FXML
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, StackPane}

class TutorialController {
  @FXML var rootPane: StackPane =_
  @FXML var border: BorderPane =_
  @FXML var tutorial: ImageView =_
  @FXML var character: ImageView =_
  
  def initialize(): Unit = {
    character.setImage(new Image("assets/Characters.png"))
    tutorial.setImage(new Image("assets/Tutorial.png"))
  }
}