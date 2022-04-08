package model

import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class PlayerSpec() extends AnyWordSpec with Matchers {
    "A Player" when {
        "newly created" should {
            val player = Player("Jeff")
            "have a name" in {
                player.name should be("Jeff")
            }
            "have a nice String representation" in {
                player.toString should be("Jeff")
            }
        }
    }
}