package aview

import model.{Mesh, Field, House, Player, Dice}

class Tui {
    val dice1 = new Dice
    def processInputLine(input: String): Int = {
        input match {
            case "r" => dice1.diceRandom()

        }
    }  
    
    def checkinput(input: String, output: Int) = {
        if (input == "r") {
            getout(input, output)
        }
    }
    def getout(input: String, output: Int) = {
        if(output == 6)
            println("Player A can roll the dice once more")
            println("Player A can move out one Piece")
    }
}
