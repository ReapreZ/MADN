package MADN

import model._
import aview._
import scala.io.StdIn.readLine
import scala.sys.process.processInternal


object MADN {
    def main(args: Array[String]): Unit = {
        val eol = sys.props("line.separator")
        val student = Player('A')
        var input: String = ""
        val tui = new Tui
        println("Welcome to Mensch aergere dich nicht " + student.name + eol)
        val mesh1 = Mesh()
        println("Amount of Players:")
        input = readLine()
        val playeramount = input
        println("Amount of Houses:")
        val houseamount = readLine()
        println("Amount of Cells per Player:")
        val cellamount = readLine()
        println(mesh1.mesh(cellamount.toInt,playeramount.toInt,houseamount.toInt))
        println("Spieler A ist an der Reihe!")
        while (input != "q")
            input = readLine()
            val output = tui.processInputLine(input)
            tui.checkinput(input, output)
            println(output)


            
        
    }
}