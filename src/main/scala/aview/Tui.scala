package aview

import model.MeshComponent.MeshBase.Mesh
import model.DiceComponent.DiceBase.Dice
import scala.io.StdIn.readLine
import model.DiceComponent.DiceBase.DiceStrategy

class Tui {
    
    val dice1 = new Dice

    def startgame(): Mesh = {
        //println("Which Dice? 1 = RandomDice 2 = MagicDice")
        //val diceread = readLine()
        //dice1.dicestra(diceread.toInt)
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
            case "r" =>
                println("Which Dice? 1 = RandomDice 2 = MagicDice")
                val diceread = readLine()
                return dice1.dicestra(diceread.toInt)

            case "q" => return 0
            case _ => return -1
        }
    }

}
