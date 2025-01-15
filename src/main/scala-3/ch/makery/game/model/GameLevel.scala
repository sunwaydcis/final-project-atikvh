// 22100259 Final Project Assignment
package ch.makery.game.model

abstract class GameLevel(
                          val name: String,
                          val initialTimer: Int = 180,
                          val characterProbability: Map[String, Int],
                          val speed: Int,
                          val imagePath: String)

case class EasyLevel() extends GameLevel(
  name = "Easy",
  characterProbability = Map(
    "Brown Mole" -> 100
  ),
  speed = 1000,
  imagePath = "assets/Easy.png"
)

case class MediumLevel() extends GameLevel(
  name = "Medium",
  characterProbability = Map(
    "Brown Mole" -> 70,
    "Bomb" -> 30
  ),
  speed = 800,
  imagePath = "assets/Medium.png"
)

case class HardLevel() extends GameLevel(
  name = "Hard",
  characterProbability = Map(
    "Brown Mole" -> 50,
    "Pink Mole" -> 25,
    "Grey Mole" -> 15,
    "Bomb" -> 10
  ),
  speed = 500,
  imagePath = "assets/Easy.png"
)