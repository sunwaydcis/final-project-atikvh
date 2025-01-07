//22100259 Final Project Assignment
package ch.makery.game.model

object GameLevelFactory {
  def getLevel(level: String): GameLevel = {
    level match {
      case "Easy" => EasyLevel()
      case "Medium" => MediumLevel()
      case "Hard" => HardLevel()
      case _ => throw new IllegalArgumentException(f"Invalid level: $level")
    }
  }
}