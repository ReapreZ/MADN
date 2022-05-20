package MADN

import model.MeshComponent.MeshBase.Mesh
import model.MeshComponent.MeshBase._
import model.GameComponent.GameBase.Game
import aview._
import scala.io.StdIn.readLine
import controller._

object MADN {
    def main(args: Array[String]): Unit = {
        var input: String = ""
        //val test: Mesh = new SimpleMesh with twoPtwoHtenF
        val tui = new Tui()
        val mesh1: Mesh = tui.startgame()
        val controller = new Controller(new Game(0, mesh1,0,0,0,0))
        //val game: Game = new Game(0, mesh1,0,0,0,0)
        controller.doAndPublish(controller.checkinput1, 0)
        while (input != "q")
            input = readLine()
            val output = tui.processInputLine(input)
            if(output == 0)
                println("Game over!\n")
            else if(output == -1)
                println("Wrong input")
            else if(output == 10)
                controller.doAndPublish(controller.undo)
            else
                println("\nYou rolled a " + output + "\n")
                controller.doAndPublish(controller.checkinput1, output)
    }

}