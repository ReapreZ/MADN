package model.dicecomponent.dicebase

import scala.util.{Try,Success,Failure}


trait DiceStrategy {


    def dicestra(diceread:Int): Int = if(diceread == 1) diceRandom(5) else magicDice(6)     

    def diceRandom(num: Int=6): Int

    def magicDice(num: Int=6): Int
}
