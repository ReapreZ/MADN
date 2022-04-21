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
        val mesh1 = Mesh()
        println("Welcome to Mensch aergere dich nicht Player " + student.name + eol)
        println("Amount of Players:")
        input = readLine()
        val playeramount = input
        tui.startgame(input)
        println("Amount of Houses:")
        val houseamount = readLine()
        println("Amount of Cells per Player:")
        val cellamount = readLine()

        println(mesh1.mesh(cellamount.toInt,playeramount.toInt,houseamount.toInt))
        println("Spieler A ist an der Reihe!")
        while (input != "q")
            input = readLine()
            val output = tui.processInputLine(input)
            println("You rolled a " + output)
            tui.checkinput(input, output)
    }
}