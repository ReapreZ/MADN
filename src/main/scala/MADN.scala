package MADN

import Controller._
import model._
import aview._
import scala.io.StdIn.readLine
import scala.sys.process.processInternal

object MADN {
    def main(args: Array[String]): Unit = {
        var input: String = ""
        val tui = new Tui
        val controller = new Controller(new Mesh(0,0,0))
        val mesh1: Mesh = tui.startgame()
        controller.checkinput1(0, mesh1)
        controller.notifyObservers
        while (input != "q")
            input = readLine()
            val output = tui.processInputLine(input)
            if(output == 0)
                println("Game over!\n")
            else if(output == -1)
                println("Wrong input")
            else
                println("\nYou rolled a " + output + "\n")
                //val mesh = controller.doAndPublish(controller.checkinput1, controller.move1)
                controller.checkinput1(output, mesh1)
                println(mesh1.mesh()) 
    }
}