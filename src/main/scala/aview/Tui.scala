package aview

import model.{Mesh, Field, House, Player, Dice}

class Tui {
    var playernumber = 1
    var playerturn = 1
    val dice1 = new Dice
    def startgame(input: String) = {
        input match {
            case "1" => playeramount(1)
            case "2" => playeramount(2)
            case "3" => playeramount(3)
            case "4" => playeramount(4)
        }
    }

    def processInputLine(input: String): Int = {
        input match {
            case "r" => dice1.diceRandom()
        }
    }  
    def playeramount(playeramount: Int) = {
        playernumber = playeramount
    }
    def checkinput(input: String, output: Int) = {
        if (input == "r") {
            turn(input, output)
            if(playerturn == 1) println("It is Player A's turn")
            if(playerturn == 2) println("It is Player B's turn")
            if(playerturn == 3) println("It is Player C's turn")
            if(playerturn == 4) println("It is Player D's turn")
        }
    }
    def turn(input: String, output: Int) = {
        if(output == 6) {
            println("Player A can roll the dice once more\n")
            println("Player A can move out one Piece\n")
        } else if(output != 6) {
            if(playerturn == playernumber)
                playerturn = 1
            else playerturn += 1
        }
    }
}