//22100259 Final Project Assignment
package ch.makery.game.view

import ch.makery.game.model.Game
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.util.Callback
import scalafx.scene.control.{TableColumn, TableView}
import scalafx.scene.layout.StackPane

class HistoryController {
  @FXML var rootPane: StackPane = _
  @FXML var gameHistory: TableView[(Int, String, String, Int)] = _
  @FXML var ID: TableColumn[(Int, String, String, Int), Int] = _
  @FXML var DateTime: TableColumn[(Int, String, String, Int), String] = _
  @FXML var Level: TableColumn[(Int, String, String, Int), String] = _
  @FXML var Score: TableColumn[(Int, String, String, Int), Int] = _

  @FXML
  def initialize(): Unit = {
    val games = Game.getAllGamesWithId 
    val data = FXCollections.observableArrayList(
      games.map { case (id, dateTime, level, score) =>
        (id, dateTime.toString, level, score)
      }: _*
    )

    gameHistory.setItems(data)

    ID.setCellValueFactory(cellData =>
      new javafx.beans.property.SimpleIntegerProperty(cellData.getValue._1).asInstanceOf[javafx.beans.value.ObservableValue[Int]]
    )

    DateTime.setCellValueFactory(cellData =>
      new javafx.beans.property.SimpleStringProperty(cellData.getValue._2)
    )

    Level.setCellValueFactory(cellData =>
      new javafx.beans.property.SimpleStringProperty(cellData.getValue._3)
    )

    Score.setCellValueFactory(cellData =>
      new javafx.beans.property.SimpleIntegerProperty(cellData.getValue._4).asInstanceOf[javafx.beans.value.ObservableValue[Int]]
    )

    Score.setComparator((score1, score2) => score2.compareTo(score1))

    gameHistory.getSortOrder.add(Score)
  }
}