package aview

import model.{Mesh, Field, House, Dice}
import scala.io.StdIn.readLine

class Tui {
    var mesh1 = new Mesh(0,0,0)
    var playernumber = 1
    var playerturn = 1
    val dice1 = new Dice
    var input: String = ""


    def startgame() = {
        println("Amount of Players:")
        input = readLine()
        input match {
            case "1" => playernumber = 1
            case "2" => playernumber = 2
            case "3" => playernumber = 3
            case "4" => playernumber = 4
        }
        println("Amount of Houses:")
        val houseamount = readLine()
        println("Amount of Cells per Player:\n")
        val cellamount = readLine()
        mesh1 = Mesh(cellamount.toInt, playernumber.toInt, houseamount.toInt)
        println(mesh1.mesh())
        println("Press 'r' to roll the dice\n")
    }

    def processInputLine(input: String): Int = {

        input match {
            case "r" => dice1.diceRandom()
            case "q" => return 0
        }
    }  

    def checkinput(input: String, output: Int) = {
        if (input == "r") {
            turn(output)
            if(playerturn == 1) println("It is Player A's turn\n")
            if(playerturn == 2) println("It is Player B's turn\n")
            if(playerturn == 3) println("It is Player C's turn\n")
            if(playerturn == 4) println("It is Player D's turn\n")
        }
        println(mesh1.mesh())
    }
    def turn(output: Int) = {
        move(output)
        if(output == 6) {
            val player2 = mesh1.field1.Player * mesh1.field1.Cell + 1
            //println("Player " + mesh1.house1.houses(playerturn) + " can roll the dice once more\n")
            //println("Player A can move out one Piece\n")
            println("You can roll again")
            playerturn match {
                case 1 => mesh1.field1.cArr(0) = 'A'
                    //mesh1.house1.hArr(0) = 'H'
                case 2 => mesh1.field1.cArr(player2) = 'B'
                    //mesh1.house1.hArr(4) = 'H'
                case 3 => mesh1.field1.cArr(player2 * 2) = 'C'
                    //mesh1.house1.hArr(8) = 'H'
                case 4 => mesh1.field1.cArr(player2 * 3) = 'D'
                    //mesh1.house1.hArr(12) = 'H'
            }
        } else if(output != 6) {
            if(playerturn == playernumber)
                playerturn = 1
            else playerturn += 1
        }
    }
    def move(output: Int) = {
        var letterturn = ' '
        playerturn match {
            case 1 => letterturn = 'A'
            case 2 => letterturn = 'B'
            case 3 => letterturn = 'C'
            case 4 => letterturn = 'C'
        }
        var out = mesh1.field1.cArr.indexOf(letterturn)
        if(out != -1)
            mesh1.field1.cArr(out) = ('-')
            mesh1.field1.cArr(out + output) = (letterturn)
        
    }
}