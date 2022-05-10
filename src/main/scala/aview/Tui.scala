package aview

import model.{Mesh, Field, House, Dice}
import scala.io.StdIn.readLine

class Tui {
    
    val dice1 = new Dice

    def startgame(): Mesh = {
        println("Amount of Players:")
        val input = readLine()
        val playeramount = input.toInt
        println("Amount of Houses:")
        val houseamount = readLine()
        println("Amount of Cells per Player:")
        val cellamount = readLine()
        var mesh1 = Mesh(cellamount.toInt, playeramount.toInt, houseamount.toInt)
        println("Press 'r' to roll the dice\n")
        mesh1
    }

    def processInputLine(input: String): Int = {

        input match {
            case "r" => dice1.diceRandom()
            case "q" => return 0
            case _ => return -1
        }
    }

}
