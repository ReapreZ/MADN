package model

import org.scalatest.*
import matchers.should.Matchers
import wordspec.AnyWordSpec

class DiceSpec extends AnyWordSpec with Matchers {
    "A Dice " when {
        "it is rolled" should {
            val dice0 = new Dice()
            "roll a number below 7" in {
                dice0.diceRandom() should be < 7
            }
        }
    }
}


