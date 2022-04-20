package aview

import model.{Mesh, Field, House, Player, Dice}

class Tui {
    val dice1 = new Dice
    def processInputLine(input: String): Int = {
        input match {
            case "r" => dice1.diceRandom()

        }
    }   
}
