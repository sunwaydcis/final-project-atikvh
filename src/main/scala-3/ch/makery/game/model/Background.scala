// 22100259 Final Project Assignment
package ch.makery.game.model

import scalafx.scene.image.Image

class Background(level: GameLevel) {
  val image: Image = new Image(getClass.getResource(level.backgroundImagePath).toExternalForm)
  
  def getImage: Image = image
}