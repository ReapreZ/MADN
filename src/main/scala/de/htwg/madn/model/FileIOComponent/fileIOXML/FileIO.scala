package de.htwg.madn.model.fileIOComponent.fileIOXML

import de.htwg.madn.model.fileIOComponent.FileIOInterface
import de.htwg.madn.model.gameComponent.GameInterface
import de.htwg.madn.model.gameComponent.gameBase.Game
import de.htwg.madn.model.meshComponent.MeshInterface
import de.htwg.madn.model.meshComponent.meshBase.Mesh
import de.htwg.madn.model.FieldFactory
import scala.xml.{NodeSeq, PrettyPrinter}
import scala.io.Source

class FileIO extends FileIOInterface{
  
    override def load: GameInterface = {
        val file = scala.xml.XML.loadFile("game.xml")
//        val piecesOutMap0 = (file \\ "game" \\ "piecesOutMap0").text.toString.toInt
//        val piecesOutMap1 = (file \\ "game" \\ "piecesOutMap1").text.toString.toInt
//        val piecesOutMap2 = (file \\ "game" \\ "piecesOutMap2").text.toString.toInt
//        val piecesOutMap3 = (file \\ "game" \\ "piecesOutMap3").text.toString.toInt
        val piecesOutList:List[Int]=List(0,0,0,0)
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
        val mesh: Mesh = new Mesh(playeramount)
        mesh.field1.Arr = fieldArr
        mesh.house1.Arr = houseArr
        mesh.finish1.Arr = finishArr
        val game: GameInterface = new Game(playerturn, mesh, piecesOutList)
        game.mesh.piecepos = piecepos2
        game.mesh.stepsdone = stepsdone2
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
            <piecesOutMap0>{game.piecesOutList(0)}</piecesOutMap0>
            <piecesOutMap1>{game.piecesOutList(1)}</piecesOutMap1>
            <piecesOutMap2>{game.piecesOutList(2)}</piecesOutMap2>
            <piecesOutMap3>{game.piecesOutList(3)}</piecesOutMap3>
            <mesh>
                <mesh-String>{game.mesh.field1.toString()}</mesh-String>
                <playeramount>{game.mesh.Player}</playeramount>
                <house-array>{game.mesh.house1.Arr.mkString("")}</house-array>
                <finish-array>{game.mesh.finish1.Arr.mkString("")}</finish-array>
                <piecepos>{game.mesh.piecepos.map(_.mkString).mkString("!")}</piecepos>
                <stepsdone>{game.mesh.stepsdone.map(_.mkString).mkString("!")}</stepsdone>
            </mesh>
        </game>
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
                if(values(temp2) != '-' && values(temp2) != '!')
                    arr(temp)(temp3) = values(temp2).toString.toInt
                    temp3 = temp3 + 1
                temp2 = temp2 + 1
            temp = temp + 1
        arr
    }
    def changeArr(arr:Array[Char], playeramount:Int): Array[Char] = {
        var temp = 1
        val max = playeramount* 4 + (playeramount*2)
        val newArr = new Array[Char](max)
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
        return newArr
    }
}
