package MADN

import model.meshComponent.meshBase.Mesh
import model.gameComponent.gameBase.Game
import aview._
import scala.io.StdIn.readLine
import controller._
import model.Move
import aview.Gui.GuiSwing
import scala.util.Success
import org.scalactic.Fail
import scala.util.{Try,Success,Failure}

object MADN {
    def main(args: Array[String]): Unit = {
        var input: String = ""
        var mesh = new Mesh(0)
        val piecesOutMap:Map[Int,Int]=Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)
        val meshtry = mesh.startgame()
        meshtry match {
            case Success(v) => mesh = v
            case Failure(e) => print(e.getMessage)
        }
        val controller = new Controller(new Game(1, mesh,piecesOutMap))
        val tui = new Tui(controller) 
        val gui = new GuiSwing(controller)
        tui.inputLoop()
        gui.closeOperation()
    }
}