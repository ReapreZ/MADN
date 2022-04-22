package aview

import model.{Mesh, Field, House, Dice}
import scala.io.StdIn.readLine

class Tui {
    
    val dice1 = new Dice
    var input: String = ""
    var playerturn = 1
    var playeramount = 1

    def processInputLine(input: String): Int = {

        input match {
            case "r" => dice1.diceRandom()   
            case "q" => return 0
        }
    }

    def startgame(): Mesh = {
        println("Amount of Players:")
        input = readLine()
        playeramount = getTurnI(input)
        println("Amount of Houses:")
        val houseamount = readLine()
        println("Amount of Cells per Player:\n")
        val cellamount = readLine()
        var mesh1 = Mesh(cellamount.toInt, playeramount.toInt, houseamount.toInt)
        println("Press 'r' to roll the dice\n")
        mesh1
    }

    def getOut(rolledDice: Int, mesh: Mesh): Mesh = {
        val mesh1: Mesh = move(rolledDice, mesh)
        if(rolledDice == 6) {
            val nextPlayer = mesh.field1.Player * mesh.field1.Cell + 1
            //println("Player " + mesh1.house1.houses(playerturn) + " can roll the dice once more\n")
            //println("Player A can move out one Piece\n")
            println("You can roll again")
            playerturn match {
                case 1 => mesh.field1.cArr(0) = 'A'
                    //mesh1.house1.hArr(0) = 'H'
                case 2 => mesh.field1.cArr(nextPlayer) = 'B'
                    //mesh1.house1.hArr(4) = 'H'
                case 3 => mesh.field1.cArr(nextPlayer * 2) = 'C'
                    //mesh1.house1.hArr(8) = 'H'
                case 4 => mesh.field1.cArr(nextPlayer * 3) = 'D'
                    //mesh1.house1.hArr(12) = 'H'
            }
        } else if(rolledDice != 6) {
            if(playerturn == playeramount)
                playerturn = 1
            else playerturn += 1
        }
        mesh
    }

    def checkinput(input: String, rolledDice: Int, mesh: Mesh) = {
        if (input == "r") {
            val playerturnC = getTurnC(playerturn)
            val mesh1 =  getOut(rolledDice, mesh)
            println("It is Player" + playerturnC + "'s turn\n")

        }
        println(mesh.mesh())
    }

    def move(output: Int, mesh1: Mesh): Mesh = {
        val playerTurnC = getTurnC(playerturn)

        var out = mesh1.field1.cArr.indexOf(playerTurnC)
        if(out != -1)
            mesh1.field1.cArr(out) = ('-')
            mesh1.field1.cArr(out + output) = (playerTurnC)    
        mesh1
    }

    def getTurnC(input: Int): Char = {
        playerturn match {
            case 1 => 'A'
            case 2 => 'B'
            case 3 => 'C'
            case 4 => 'D' 
        }
    }
    
    def getTurnI(input: String): Int = {
        input match {
            case "1" => 1
            case "2" => 2
            case "3" => 3
            case "4" => 4
        }
    }
}
