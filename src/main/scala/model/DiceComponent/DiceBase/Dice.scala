
package model.diceComponent.diceBase
import scala.util.{Try,Success,Failure}


final case class Dice() extends DiceStrategy{
    override def diceRandom(num: Int=5): Int = {
        val r = scala.util.Random
        (1 + r.nextInt(num))
    }

    override def magicDice(num: Int=6): Int = {
        return num
    }

}
