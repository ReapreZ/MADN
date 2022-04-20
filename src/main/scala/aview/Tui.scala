package aview

import model.{Mesh, Field, House, Player, Dice}

class Tui {
    var playernumber = 0
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
        }
    }
    def turn(input: String, output: Int) = {
        if(output == 6) {
            println("Player A can roll the dice once more")
            println("Player A can move out one Piece")
        } else if(output != 6) {
            if(playerturn == playernumber)
                playerturn = 0
            else playerturn += 1
        }
    }
}
