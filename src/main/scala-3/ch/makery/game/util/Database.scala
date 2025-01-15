package ch.makery.game.util

import scalikejdbc._
import ch.makery.game.model.Game

trait Database:
  val ClassHistory =
    "org.apache.derby.jdbc.myDB;create=true"
  val dbURL = "jdbc:derby:myDB;create=true"
  Class.forName(ClassHistory)
  ConnectionPool.singleton(dbURL, "me", "mine")
  given AutoSession = AutoSession
  
object Database extends Database:
  def setupDB() =
    if (!hasDBInitialize) then
      Game.initializeTable()
  def hasDBInitialize: Boolean =
    DB getTable "Game" match
      case Some(x) => true
      case None => false
