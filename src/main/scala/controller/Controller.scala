package Controller

import model.{Mesh, Field, House, Dice}
import scala.io.StdIn.readLine


class Controller {

    val dice1 = new Dice
    var playerturn = 1


    def getOut(rolledDice: Int, mesh: Mesh): Mesh = {
        val mesh1 = move(rolledDice, mesh)
        if(rolledDice == 6) {
            val nextPlayer = mesh1.field1.Player * mesh1.field1.Cell + 1
            //println("Player " + mesh1.house1.houses(playerturn) + " can roll the dice once more\n")
            //println("Player A can move out one Piece\n")
            println("You can roll again\n")
            playerturn match {
                case 1 => mesh1.field1.cArr(0) = 'A'
                    //mesh1.house1.hArr(0) = 'H'
                case 2 => mesh1.field1.cArr(nextPlayer) = 'B'
                    //mesh1.house1.hArr(4) = 'H'
                case 3 => mesh1.field1.cArr(nextPlayer * 2) = 'C'
                    //mesh1.house1.hArr(8) = 'H'
                case 4 => mesh1.field1.cArr(nextPlayer * 3) = 'D'
                    //mesh1.house1.hArr(12) = 'H'
            }
        } else if(rolledDice != 6) {
            if(playerturn == mesh1.playeramount)
                playerturn = 1
            else playerturn += 1
        }
        mesh1
    }

    def checkinput(input: String, rolledDice: Int, mesh: Mesh): Mesh = {
        if (input == "r") {
            val playerturnC = getTurnC(playerturn)
            println("It is Player " + playerturnC + "'s turn\n")
            return getOut(rolledDice, mesh)
        }
        mesh
    }

    def move(rolledDice: Int, mesh1: Mesh): Mesh = {
        val playerTurnC = getTurnC(playerturn)
        val out = mesh1.field1.cArr.indexOf(playerTurnC)
        if(out != -1)
            mesh1.field1.cArr(out) = ('-')
            mesh1.field1.cArr(out + rolledDice) = (playerTurnC)
        mesh1
    }

    def getTurnC(playerturn: Int): Char = {
        playerturn match {
            case 1 => 'A'
            case 2 => 'B'
            case 3 => 'C'
            case 4 => 'D'
        }
    }
}