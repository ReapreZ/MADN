
package model.diceComponent.diceBase
import scala.util.{Try,Success,Failure}


final case class Dice() extends DiceStrategy{
    override def diceRandom(num: Int=5): Int = (1 + scala.util.Random.nextInt(num))
    override def magicDice(num: Int=6): Int = num
    

}
