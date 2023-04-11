package modelComponent.fileIOComponent.fileIOJsonImpl

import scala.io.Source
import play.api.libs.json._


class FileIO extends FileIOInterface {

    override def load: GameInterface = {
        val source: String = Source.fromFile("game.json").getLines.mkString
        val json: JsValue = Json.parse(source)
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
        val piecepos2 = fillArr(piecepos, playeramount)
        val stepsdone = (json \ "game" \ "mesh" \ "stepsdone").get.toString.toCharArray
        val stepsdone2 = fillArr(stepsdone, playeramount)
        var mesh10:Mesh = new Mesh(playeramount)
        mesh10.field1.Arr = changeMeshArr(fieldArr)
        mesh10.house1.Arr = changeArr(houseArr, playeramount)
        mesh10.finish1.Arr = changeArr(finishArr, playeramount)
        var game: GameInterface = new Game(playerturn, mesh10, piecesOutMap4)
        game.mesh10.piecepos = piecepos2
        game.mesh10.stepsdone = stepsdone2
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
                "mesh" -> Json.obj(
                    "mesh-String" -> game.mesh10.field1.toString(),
                    "playeramount" -> JsNumber(game.mesh10.Player),
                    "house-array" -> game.mesh10.house1.Arr.mkString(""),
                    "finish-array" -> game.mesh10.finish1.Arr.mkString(""),
                    "piecepos" -> game.mesh10.piecepos.map(_.mkString).mkString("!"),
                    "stepsdone" -> game.mesh10.stepsdone.map(_.mkString).mkString("!")
                )
            )
        )
    }
    def fillArr(values:Array[Char], playeramount: Int): Array[Array[Int]] = {
        var arr = Array.ofDim[Int](playeramount, 4)
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
        var newArr = new Array[Char](40)
        var temp = 1
        while(arr(temp) != '"' && (temp - 1) != 40)
            newArr(temp - 1) = arr(temp)
            temp = temp + 1
        return newArr
    }
}
