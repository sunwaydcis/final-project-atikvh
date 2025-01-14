// 22100259 Final Project Assignment
package ch.makery.game.model

abstract class Character(
                          val name: String,
                          val points: Int = 0,
                          val penalty: Int = 0,
                          val imagePath: String) {
  def applyEffect(game: Game): Unit
}

class BrownMole extends Character("Brown Mole", points = 3, imagePath = "assets/Brown Mole.png") {
  override def applyEffect(game: Game): Unit = {
    game.updateScore(points)
  }
}

class PinkMole extends Character("Pink Mole", points = 6, imagePath = "assets/Pink Mole.png") {
  override def applyEffect(game: Game): Unit = {
    game.updateScore(points)
  }
}

class GreyMole extends Character("Grey Mole", points = -2, imagePath = "assets/Grey Mole.png") {
  override def applyEffect(game: Game): Unit = {
    game.updateScore(points)
  }
}

class Bomb extends Character("Bomb", penalty = 5, imagePath = "assets/Bomb.png") {
  override def applyEffect(game: Game): Unit = {
    game.decrementTimerBy(penalty)
  }
}