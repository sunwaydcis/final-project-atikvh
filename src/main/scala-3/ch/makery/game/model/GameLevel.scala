// 22100259 Final Project Assignment
package ch.makery.game.model

abstract class GameLevel(
                          val name: String,
                          val initialTimer: Int,
                          val characterProbability: Map[String, Int],
                          val backgroundImagePath: String)

case class EasyLevel() extends GameLevel(
  name = "Easy",
  initialTimer = 180,
  characterProbability = Map(
    "Brown Mole" -> 100
  ),
  backgroundImagePath = "/ch/makery/game/assets/Easy.png"
)

case class MediumLevel() extends GameLevel(
  name = "Medium",
  initialTimer = 180,
  characterProbability = Map(
    "Brown Mole" -> 70,
    "Bomb" -> 30
  ),
  backgroundImagePath = "/ch/makery/game/assets/Medium.png"
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
  backgroundImagePath = "/ch/makery/game/assets/Hard.png"
)