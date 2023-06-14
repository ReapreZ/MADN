package io.GameDaoComponent

import io.GameDaoInterface
import org.mongodb.scala.*
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.result.{DeleteResult, InsertOneResult, UpdateResult}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

class GameDAOMongoImpl extends GameDaoInterface {

  val uri: String = s"mongodb://localhost:27017/admin?authSource=admin"
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("admin")
  val gameCollection: MongoCollection[Document] = db.getCollection("game")

  def create: Unit = {
    val document: Document = Document("_id" -> 1, "playerturn" -> 0, "mesh" -> "mesh", "piecesOut" -> "piecesOut", "timesPlayerRolled" -> "timesPlayerRolled")
    observerInsertion(gameCollection.insertOne(document))
  }

  def read:Future[String] = Future {
    val document: Document = Await.result(gameCollection.find(equal("_id", 1)).first().head(), Duration.Inf)

    document("mesh").asString().getValue.toString
  }

  def update(id: Int, playerturn: Int, mesh: String, piecesOut: String, timesPlayerRolled: String): Unit = {
    observerUpdate(gameCollection.updateOne(equal("_id", 1), set("playerturn", playerturn)))
    observerUpdate(gameCollection.updateOne(equal("_id", 1), set("mesh", mesh)))
    observerUpdate(gameCollection.updateOne(equal("_id", 1), set("piecesOut", piecesOut)))
    observerUpdate(gameCollection.updateOne(equal("_id", 1), set("timesPlayerRolled", timesPlayerRolled)))
  }

  def delete:Unit = {
    Await.result(deleteFuture, Duration.Inf)
  }

  private def deleteFuture:Future[String] = {
    gameCollection.deleteMany(equal("_id", 1)).subscribe(
      (dr: DeleteResult) => println("Deleted gameDocument"),
      (e: Throwable) => println(e.getMessage())
    )
    Future {
      "Finished deleting!"
    }
  }

  private def observerInsertion(insertObservable: SingleObservable[InsertOneResult]): Unit = {
    insertObservable.subscribe(new Observer[InsertOneResult] {
      override def onNext(result: InsertOneResult): Unit = println(s"inserted: $result")

      override def onError(e: Throwable): Unit = println(s"insert onError: $e")

      override def onComplete(): Unit = println("completed insert")
    })
  }

  private def observerUpdate(insertObservable: SingleObservable[UpdateResult]): Unit = {
    insertObservable.subscribe(new Observer[UpdateResult] {
      override def onNext(result: UpdateResult): Unit = println(s"updated: $result")

      override def onError(e: Throwable): Unit = println(s"update onError: $e")

      override def onComplete(): Unit = println("completed update")
    })
  }


}
