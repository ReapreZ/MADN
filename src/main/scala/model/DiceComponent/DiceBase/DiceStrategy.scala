package model.diceComponent.diceBase
import model.diceComponent.diceBase.Dice
import scala.util.{Try,Success,Failure}


trait DiceStrategy {
    
    /*def dicestra(diceread:Int): Try[Int] =
        if diceread > 2 && diceread < 1 then return Failure(NoSuchMethodException("Something went wrong"))
        else if(diceread == 1)
         (diceRandom()) else Success(magicDice())*/

    def dicestra(diceread:Int): Int =
        if(diceread == 1) diceRandom(6) else magicDice(6)     

    //def diceRandom(num: Try[Int]): Try[Int]
    def diceRandom(num: Int=6): Int

    def magicDice(num: Int=6): Int
}
