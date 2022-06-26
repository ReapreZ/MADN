package model.fileIOComponent.fileIOJsonImpl

import scala.io.Source
import model.fileIOComponent.FileIOInterface
import model.gameComponent.GameInterface
import play.api.libs.json._
import model.gameComponent.gameBase.Game
import model.meshComponent.MeshInterface
import model.meshComponent.meshBase.Mesh
import model.FieldFactory


class FileIO extends FileIOInterface {

    override def load: GameInterface = {
        val source: String = Source.fromFile("game.json").getLines.mkString
        val json: JsValue = Json.parse(source)

        //var game: GameInterface = null
        val piecesOutMap0 = (json \ "game" \ "piecesOutMap0").get.toString.toInt
        val piecesOutMap1 = (json \ "game" \ "piecesOutMap1").get.toString.toInt
        val piecesOutMap2 = (json \ "game" \ "piecesOutMap2").get.toString.toInt
        val piecesOutMap3 = (json \ "game" \ "piecesOutMap3").get.toString.toInt
        var piecesOutMap4:Map[Int,Int]=Map(0 -> piecesOutMap0, 1 -> piecesOutMap1, 2 -> piecesOutMap2, 3 -> piecesOutMap3)
        val playerturn = (json \ "game" \ "playerturn").get.toString.toInt
        val fieldArr = (json \ "game" \ "mesh" \ "mesh-String").get.toString.toCharArray
        val playeramount = (json \ "game" \ "mesh" \ "playeramount").get.toString.toInt
        val houseArr = (json \ "game" \ "mesh" \ "house-array").get.toString.toArray
        val finishArr = (json \ "game" \ "mesh" \ "finish-array").get.toString.toArray
        val piecepos = (json \ "game" \ "mesh" \ "piecepos").get.toString.toCharArray
        val stepsdone = (json \ "game" \ "mesh" \ "stepsdone").get.toString.toCharArray
        var mesh10:Mesh = new Mesh(playeramount)
        mesh10.field1.Arr = fieldArr
        mesh10.house1.Arr = houseArr
        mesh10.finish1.Arr = finishArr
        var game: GameInterface = new Game(playerturn, mesh10, piecesOutMap4)
        game
    }

    override def save(game:GameInterface): Unit = {
        import java.io._
        val pw = new PrintWriter(new File("game.json"))
        pw.write(Json.prettyPrint(gameToJson(game)))
        pw.close
    }

    def gameToJson(game: GameInterface) = {
        Json.obj(
            "game" -> Json.obj(
                "playerturn" -> JsNumber(game.playerturn),
                "piecesOutMap0" -> JsNumber(game.piecesOutMap(0)),
                "piecesOutMap1" -> JsNumber(game.piecesOutMap(1)),
                "piecesOutMap2" -> JsNumber(game.piecesOutMap(2)),
                "piecesOutMap3" -> JsNumber(game.piecesOutMap(3)),
                "mesh" -> Json.toJson(
                    "mesh-String" -> game.mesh10.field1.toString(),
                    "playeramount" -> JsNumber(game.mesh10.Player),
                    "house-array" -> game.mesh10.house1.Arr.toString(),
                    "finish-array" -> game.mesh10.finish1.Arr.toString(),
                    "piecepos" -> game.mesh10.piecepos.toString(),
                    "stepsdone" -> game.mesh10.stepsdone.toString()
                )
            )
        )
    }
    //playerturn:Int,mesh10:Mesh,piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)
  //mesh: val field1 = FieldFactory("field", Player)
    //val house1 = FieldFactory("house", Player)
    //val finish1 = FieldFactory("finish", Player)
    //val piecepos = fillArr(Player, 4)
    //val stepsdone = fillArr(Player, 4)
    // Mesh parameter Player
}
