// 22100259 Final Project Assignment
package ch.makery.game.model

abstract class GameLevel(
                          val name: String,
                          val initialTimer: Int,
                          val characterProbability: Map[String, Int],
                          val speed: Int)

case class EasyLevel() extends GameLevel(
  name = "Easy",
  initialTimer = 180,
  characterProbability = Map(
    "Brown Mole" -> 100
  ),
  speed = 1000 //milliseconds
)

case class MediumLevel() extends GameLevel(
  name = "Medium",
  initialTimer = 180,
  characterProbability = Map(
    "Brown Mole" -> 70,
    "Bomb" -> 30
  ),
  speed = 800 
)

case class HardLevel() extends GameLevel(
  name = "Hard",
  initialTimer = 180,
  characterProbability = Map(
    "Brown Mole" -> 50,
    "Pink Mole" -> 25,
    "Grey Mole" -> 15,
    "Bomb" -> 10
  ),
  speed = 500
)