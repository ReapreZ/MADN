package aview

import model.{Mesh, Field, House, Dice}
import scala.io.StdIn.readLine

class Tui {
    
    val dice1 = new Dice

    def processInputLine(input: String): Int = {

        input match {
            case "r" => dice1.diceRandom()
            case "q" => return 0
            case _ => return -1
        }
    }

}
