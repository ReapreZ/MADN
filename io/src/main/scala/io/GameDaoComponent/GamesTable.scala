package io.GameDaoComponent


import slick.jdbc.JdbcProfile
import scala.concurrent.Future
import scala.util.{Try,Success,Failure}
import play.api.libs.json._
import slick.jdbc.MySQLProfile._
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery
import scala.annotation.targetName

class GamesTable(tag: Tag) extends Table[(Int, Int, String, String, String)](tag, "GamesTable") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def playerturn = column[Int]("playerturn")
    def mesh = column[String]("mesh")
    def piecesOutList = column[String]("piecesOutList")
    def timesPlayerRolledList = column[String]("timesPlayerRolledList")

    /*
    implicit val listIntColumnType: BaseColumnType[List[Int]] = MappedColumnType.base[List[Int], String](
      list => Json.stringify(Json.toJson(list)),
      string => Json.fromJson[List[Int]](Json.parse(string)).get
    )*/

    override def * = (id, playerturn, mesh, piecesOutList, timesPlayerRolledList)
}