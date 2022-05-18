package model.DiceComponent.DiceBase
import model.DiceComponent.DiceBase.Dice


trait  DiceStrategy {
    
    def dicestra(diceread:Int): Int =
        if(diceread == 1 ) diceRandom() else magicDice()

    def diceRandom(num: Int=6): Int

    def magicDice(num: Int=6): Int
}
