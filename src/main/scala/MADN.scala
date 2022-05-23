package MADN

import model.MeshComponent.MeshBase.Mesh
import model.MeshComponent.MeshBase._
import model.GameComponent.GameBase.Game
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
        var mesh = new Mesh(0,0,0)
        val meshtry = mesh.startgame()
        meshtry match {
            case Success(v) => mesh = v
            case Failure(e) => print(e.getMessage)
        }
        val controller = new Controller(new Game(1, mesh,0,0,0,0))
        val tui = new Tui(controller)
        val gui = new GuiSwing(controller)
        //controller.doAndPublish(controller.checkinput1, 0)
        while (input != "q")
            input = readLine()
            val output = tui.processInputLine(input)
            if(output == 0)
                println("Game over!\n")
                gui.closeOperation()
            else if(output == -1)
                println("Wrong input")
            else if(output == 10)
                controller.doAndPublish(controller.undo)
                //controller.setGame(controller.doAndPublish(controller.undo))
            else
                println("\nYou rolled a " + output + "\n")
                controller.doAndPublish(controller.checkinput1, output)
    }

}