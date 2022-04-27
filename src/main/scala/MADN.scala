package MADN

import model._
import aview._
import scala.io.StdIn.readLine
import scala.sys.process.processInternal

object MADN {
    def main(args: Array[String]): Unit = {
        var input: String = ""
        val tui = new Tui
        val mesh1: Mesh = (tui.startgame())
        while (input != "q")
            input = readLine()
            val output = tui.processInputLine(input)
            if(output == 0)
                println("Game over!")
            else
                println("\nYou rolled a " + output + "\n")
            val mesh = tui.checkinput(input, output, mesh1)
            println(mesh)
    }
}