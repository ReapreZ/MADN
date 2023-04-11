package model

import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class HouseSpec() extends AnyWordSpec with Matchers {
    "A House" when {
        val eol = sys.props("line.separator")
        "it is not set to any value" should {
            val house0 = new House(0,0)
            "have an empty field" in {
                house0.toString() should be ("" + eol)
            }
        }
        "it is a small field" should {
            val house1 = new House(1,1)
            "be scalable" in {
                house1.toString() should be ("A  " + eol)
            }
        }
    }
}