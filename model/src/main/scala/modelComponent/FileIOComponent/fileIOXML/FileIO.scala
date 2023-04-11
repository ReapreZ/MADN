package modelComponent.fileIOComponent.fileIOXML

import scala.io.Source
import scala.xml.{NodeSeq, PrettyPrinter}

class FileIO extends FileIOInterface{
  
    override def load: GameInterface = {
        val file = scala.xml.XML.loadFile("game.xml")
        val piecesOutMap0 = (file \\ "game" \\ "piecesOutMap0").text.toString.toInt
        val piecesOutMap1 = (file \\ "game" \\ "piecesOutMap1").text.toString.toInt
        val piecesOutMap2 = (file \\ "game" \\ "piecesOutMap2").text.toString.toInt
        val piecesOutMap3 = (file \\ "game" \\ "piecesOutMap3").text.toString.toInt
        var piecesOutMap4:Map[Int,Int]=Map(0 -> piecesOutMap0, 1 -> piecesOutMap1, 2 -> piecesOutMap2, 3 -> piecesOutMap3)
        val playerturn = (file \\ "game" \\ "playerturn").text.toString.toInt
        val fieldArr = (file \\ "game" \\ "mesh" \\ "mesh-String").text.toString.toCharArray
        val playeramount = (file \\ "game" \\ "mesh" \\ "playeramount").text.toString.toInt
        val houseArr = (file \\ "game" \\ "mesh" \\ "house-array").text.toString.toArray
        val finishArr = (file \\ "game" \\ "mesh" \\ "finish-array").text.toString.toArray
        val piecepos = (file \\ "game" \\ "mesh" \\ "piecepos").text.toString.toCharArray
        //println(piecepos.mkString + "<---- \n")
        val piecepos2 = fillArr(piecepos, playeramount)
        //piecepos2 foreach { row => row foreach print; println }
        val stepsdone = (file \\ "game" \\ "mesh" \\ "stepsdone").text.toString.toCharArray
        val stepsdone2 = fillArr(stepsdone, playeramount)
        //stepsdone2 foreach { row => row foreach print; println }
        var mesh10:Mesh = new Mesh(playeramount)
        mesh10.field1.Arr = fieldArr
        mesh10.house1.Arr = houseArr
        mesh10.finish1.Arr = finishArr
        var game: GameInterface = new Game(playerturn, mesh10, piecesOutMap4)
        game.mesh10.piecepos = piecepos2
        game.mesh10.stepsdone = stepsdone2
        game
    }

    override def save(game:GameInterface): Unit = {
        import java.io._
        val pw = new PrintWriter(new File("game.xml"))
        val prettyPrinter = new PrettyPrinter(120, 4)
        val xml = prettyPrinter.format(gameToXML(game))
        pw.write(xml)
        pw.close
    }

    def gameToXML(game: GameInterface) = {
        <game>
            <playerturn>{game.playerturn}</playerturn>
            <piecesOutMap0>{game.piecesOutMap(0)}</piecesOutMap0>
            <piecesOutMap1>{game.piecesOutMap(1)}</piecesOutMap1>
            <piecesOutMap2>{game.piecesOutMap(2)}</piecesOutMap2>
            <piecesOutMap3>{game.piecesOutMap(3)}</piecesOutMap3>
            <mesh>
                <mesh-String>{game.mesh10.field1.toString()}</mesh-String>
                <playeramount>{game.mesh10.Player}</playeramount>
                <house-array>{game.mesh10.house1.Arr.mkString("")}</house-array>
                <finish-array>{game.mesh10.finish1.Arr.mkString("")}</finish-array>
                <piecepos>{game.mesh10.piecepos.map(_.mkString).mkString("!")}</piecepos>
                <stepsdone>{game.mesh10.stepsdone.map(_.mkString).mkString("!")}</stepsdone>
            </mesh>
        </game>
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
                if(values(temp2) != '-' && values(temp2) != '!')
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
