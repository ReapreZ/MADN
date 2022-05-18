
package model.DiceComponent.DiceBase



final case class Dice() extends DiceStrategy{
    override def diceRandom(num: Int=6): Int = {
        val r = scala.util.Random
        1 + r.nextInt(num)
    }

    override def magicDice(num: Int=6): Int = {
        return num
    }

}
