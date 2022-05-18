package MADN

import Controller._
import model.MeshComponent.MeshBase.Mesh
import model.GameComponent.GameBase.Game
import aview._
import scala.io.StdIn.readLine
import scala.sys.process.processInternal

object MADN {
    def main(args: Array[String]): Unit = {
        var input: String = ""
        val controller = new Controller(new Mesh(0,0,0))
        val tui = new Tui
        val mesh1: Mesh = tui.startgame()
        val game: Game = new Game(0, mesh1,0,0,0,0)
        controller.doAndPublish(controller.checkinput1, 0, mesh1)
        while (input != "q")
            input = readLine()
            val output = tui.processInputLine(input)
            if(output == 0)
                println("Game over!\n")
            else if(output == -1)
                println("Wrong input")
            else
                println("\nYou rolled a " + output + "\n")
                controller.doAndPublish(controller.checkinput1, output, mesh1) 
    }
}