
package model.DiceComponent.DiceBase

final case class Dice(){
    def diceRandom(num: Int=6): Int = {
        val r = scala.util.Random
        1 + r.nextInt(num)
    }

}
