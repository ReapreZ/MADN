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
        val piecesOutList:List[Int]=List(0,0,0,0)
        val playerturn = (json \ "game" \ "playerturn").get.toString.toInt
        val fieldArr = (json \ "game" \ "mesh" \ "mesh-String").get.toString.toCharArray
        val playeramount = (json \ "game" \ "mesh" \ "playeramount").get.toString.toInt
        val houseArr = (json \ "game" \ "mesh" \ "house-array").get.toString.toArray
        val finishArr = (json \ "game" \ "mesh" \ "finish-array").get.toString.toArray
        val piecepos = (json \ "game" \ "mesh" \ "piecepos").get.toString.toCharArray
        val piecepos2 = fillArr(piecepos, playeramount)
        val stepsdone = (json \ "game" \ "mesh" \ "stepsdone").get.toString.toCharArray
        val stepsdone2 = fillArr(stepsdone, playeramount)
        val mesh:Mesh = new Mesh(playeramount)
        mesh.field1.Arr = changeMeshArr(fieldArr)
        mesh.house1.Arr = changeArr(houseArr, playeramount)
        mesh.finish1.Arr = changeArr(finishArr, playeramount)
        val game: GameInterface = Game(playerturn, mesh, piecesOutList)
        game.mesh.piecepos = piecepos2
        game.mesh.stepsdone = stepsdone2
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
                "piecesOutMap0" -> JsNumber(game.piecesOutList(0)),
                "piecesOutMap1" -> JsNumber(game.piecesOutList(1)),
                "piecesOutMap2" -> JsNumber(game.piecesOutList(2)),
                "piecesOutMap3" -> JsNumber(game.piecesOutList(3)),
                "mesh" -> Json.obj(
                    "mesh-String" -> game.mesh.field1.toString(),
                    "playeramount" -> JsNumber(game.mesh.Player),
                    "house-array" -> game.mesh.house1.Arr.mkString(""),
                    "finish-array" -> game.mesh.finish1.Arr.mkString(""),
                    "piecepos" -> game.mesh.piecepos.map(_.mkString).mkString("!"),
                    "stepsdone" -> game.mesh.stepsdone.map(_.mkString).mkString("!")
                )
            )
        )
    }
    def fillArr(values:Array[Char], playeramount: Int): Array[Array[Int]] = {
        val arr = Array.ofDim[Int](playeramount, 4)
        var temp = 0
        var temp2 = 0
        var temp3 = 0
        while(temp < playeramount)
            temp2 = 0
            temp3 = 0
            while(values(temp2) != '!')
                if(values(temp2) != '-' && values(temp2) != '!' && values(temp2) != '"')
                    arr(temp)(temp3) = values(temp2).toString.toInt
                    temp3 = temp3 + 1
                temp2 = temp2 + 1
            temp = temp + 1
        return arr
    }
    def changeArr(arr:Array[Char], playeramount:Int): Array[Char] = {
        var temp = 1
        val max = playeramount* 4 + (playeramount*2)
        var newArr = new Array[Char](max)
        while(arr(temp) != '"' && (temp - 1) != max)
            newArr(temp - 1) = arr(temp)
            temp = temp + 1
        return newArr
    }
    def changeMeshArr(arr:Array[Char]): Array[Char] = {
        val newArr = new Array[Char](40)
        var temp = 1
        while(arr(temp) != '"' && (temp - 1) != 40)
            newArr(temp - 1) = arr(temp)
            temp = temp + 1
        newArr
    }
}
