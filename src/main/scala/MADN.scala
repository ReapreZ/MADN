package MADN

import model._
import aview._
import scala.io.StdIn.readLine
import scala.sys.process.processInternal

object MADN {
    def main(args: Array[String]): Unit = {
        val eol = sys.props("line.separator")
        val student = Player('A', 3)
        var input: String = ""
        val tui = new Tui
        println("Welcome to Mensch aergere dich nicht Player " + student.name + eol)
        println("Amount of Players:")
        input = readLine()
        val playeramount = input
        tui.startgame(input)
        println("Amount of Houses:")
        val houseamount = readLine()
        println("Amount of Cells per Player:")
        val cellamount = readLine()
        val mesh1 = Mesh(cellamount.toInt, playeramount.toInt, houseamount.toInt)
        println(mesh1.mesh())
        println("It is Player A's turn")
        while (input != "q")
            input = readLine()
            val output = tui.processInputLine(input, mesh1)
            if(output == 0)
                println("Game over!")
            else
                println("You rolled a " + output)
            tui.checkinput(input, output)
    }
}