package io.GameDaoComponent

import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.util.{Try,Success,Failure}
import play.api.libs.json._
import model.Games
import model.meshComponent.meshBase._
import io.GameDaoInterface
import io.GameDaoComponent.GamesTable
import slick.lifted.TableQuery

class GameDaoSlickImpl(val profile: JdbcProfile)(implicit val executionContext: ExecutionContext) extends GameDaoInterface {
  import slick.jdbc.PostgresProfile.api._

  private val db = Database.forConfig("slick.db")

  //val games: TableQuery[GamesTable] = new TableQuery(new GamesTable(_))


}

  /*private lazy val games = TableQuery[GamesTable]

  override def getById(id: Int): Future[Option[Games]] = {
    sql"""
    select id, playerturn, mesh, piecesOutList, timesPlayerRolledList
    from games
    where id = ${id}
    """.as[Games].headOption
  }

  override def getAll: Future[List[Games]] = {
    sql"""
    select id, playerturn, mesh, piecesOutList, timesPlayerRolledList
    from games
    """.as[Games]
  }

  override def create(game: Games): Future[Games] = {
    sqlu"""
    insert into games(id, playerturn, mesh, piecesOutList, timesPlayerRolledList)
    values (${GameDAOSlickImpl.id}, ${game.playerturn}, ${game.mesh}, ${game.piecesOutList}, ${game.timesPlayerRolledList})
    """.andThen(DBIOAction.successful(()))
  }

  override def update(game: Games): Future[Option[Games]] = {
    db.run(sqlu"""
    update games
    set playerturn = ${game.playerturn},
      mesh = ${game.mesh},
      piecesOutList = ${game.piecesOutList},
      timesPlayerRolledList = ${game.timesPlayerRolledList}
    where id = ${game.id}
    """.void)
  }

  override def delete(id: Int): Future[Boolean] = {
    db.run(sqlu"""
    delete from games
    where id = ${game.id}
    """.void)
  }*/