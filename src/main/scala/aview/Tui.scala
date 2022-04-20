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
        if (input == 'r') getout(tui.processInputLine(input,output))
    } 
}
