//22100259 Final Project Assignment
package ch.makery.game.view

import ch.makery.game.model.Game
import javafx.fxml.FXML
import scalafx.scene.control.{TableColumn, TableView}
import scalafx.scene.layout.StackPane

class HistoryController {
  @FXML var rootPane: StackPane=_
  @FXML var gameHistory: TableView[Game] =_
  @FXML var ID: TableColumn[] =_
  @FXML var DateTime: TableColumn[] =_
  @FXML var Level: TableColumn[] =_
  @FXML var Score: TableColumn[] =_
}