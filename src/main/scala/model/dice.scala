package model

class Dice {
    val r = scala.util.Random
    def diceRandom(num: Int=6) = 
        1 + r.nextInt(num)

}
