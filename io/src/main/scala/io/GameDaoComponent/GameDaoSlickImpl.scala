package io.GameDaoComponent

import slick.jdbc.PostgresProfile.api.*
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.{Await, Future}
import slick.lifted.Tag
import scala.concurrent.duration.{Duration, DurationInt}
import io.GameDaoInterface
import io.GameDaoComponent.GamesTable
import scala.io.StdIn
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import java.sql.{Connection, DriverManager, ResultSet}

class GameDaoSlickImpl extends GameDaoInterface{

  val connectIP = sys.env.getOrElse("POSTGRES_IP", "localhost").toString
  val connectPort = sys.env.getOrElse("POSTGRES_PORT", 5432).toString.toInt
  val database_user = sys.env.getOrElse("POSTGRES_USER", "postgres").toString
  val database_pw = sys.env.getOrElse("POSTGRES_PASSWORD", "postgres").toString
  val database_name = sys.env.getOrElse("POSTGRES_DB", "postgres").toString

  val database =
    Database.forURL(
      url = "jdbc:postgresql://" + connectIP + ":" + connectPort + "/" + database_name + "?serverTimezone=UTC",
      user = database_user,
      password = database_pw,
      driver = "org.postgresql.Driver")

  val gamesTable = TableQuery(new GamesTable(_))
  
  def create: Unit = {
    val running = Future(Await.result(database.run(DBIO.seq(
      gamesTable.schema.createIfNotExists,
    )), Duration.Inf))
    running.onComplete{
      case Success(_) => println("Connection successful")
      case Failure(e) => println(e.getMessage())
    }
  }
  def read: Future[String] = Future {
    val conn = DriverManager.getConnection("jdbc:postgresql://" + connectIP + ":" + connectPort + "/" + database_name + "?user=postgres&password=postgres")
    val sb = new StringBuilder("")
    try {
      val stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

      val rs = stm.executeQuery("SELECT * FROM \"GamesTable\"")

      while(rs.next) {
        sb++= rs.getString("mesh")
      }
      sb.toString()
    } finally {
      conn.close()
  }
  }

  def update(id: Int, playerturn: Int, mesh: String, piecesOut: String, timesPlayerRolled: String): Unit = {
    val insertAction = gamesTable returning gamesTable.map(_.id)
      += (id, playerturn, mesh, piecesOut, timesPlayerRolled)
      database.run(insertAction)
      println("Update successful")
  }

  def delete: Unit = {
    val deleteAction = gamesTable.delete
    val resultFuture = database.run(deleteAction)

    resultFuture.onComplete {
      case Success(numRowsDeleted) => println(s"Deleted $numRowsDeleted rows from table.")
      case Failure(e) => println(e.getMessage())
    }
  }
}