package ch.makery.game.model

abstract class Character(val points: Int, val effect: String) {
  def applyEffect(game: Game): Unit
}

class BrownMole extends Character(3, "Gain points") {
  override def applyEffect(game: Game): Unit = game.updateScore(points)
}

class PinkMole extends Character(6, "Gain extra points") {
  override def applyEffect(game: Game): Unit = game.updateScore(points)
}

class GreyMole extends Character(-2, "Lose points") {
  override def applyEffect(game: Game): Unit = game.updateScore(points)
}

class Bomb extends Character(0, "Lose time") {
  override def applyEffect(game: Game): Unit = game.decrementTimer()
}
