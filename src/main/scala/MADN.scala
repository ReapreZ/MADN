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
        val controller = new Controller
        val mesh1: Mesh= (controller.startgame())
        while (input != "q")
            input = readLine()
            val output = tui.processInputLine(input)
            if(output == 0)
                println("Game over!\n")
            else if(output == -1)
                println("Wrong input")
            else
                println("\nYou rolled a " + output + "\n")
            val mesh = controller.checkinput(input, output, mesh1)
            println(mesh.mesh())
    }
}