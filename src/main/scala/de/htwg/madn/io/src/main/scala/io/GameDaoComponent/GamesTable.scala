package de.htwg.madn.io.GameDaoComponent

import slick.jdbc.JdbcProfile
import scala.concurrent.Future
import scala.util.{Try,Success,Failure}
import play.api.libs.json._
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery
import scala.annotation._

class GamesTable(tag: Tag) extends Table[(Int, Int, String, String, String)](tag, "GamesTable") {
    def id = column[Int]("id", O.PrimaryKey)
    def playerturn = column[Int]("playerturn")
    def mesh = column[String]("mesh")
    def piecesOutList = column[String]("piecesOutList")
    def timesPlayerRolledList = column[String]("timesPlayerRolledList")

    override def * = (id, playerturn, mesh, piecesOutList, timesPlayerRolledList)
}