package model

import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class HouseSpec() extends AnyWordSpec with Matchers {
    "A House" when {
        val eol = sys.props("line.separator")
        "it is not set to any value" should {
            val house0 = new House()
            "have an empty field" in {
                house0.housefield(0,0) should be ("" + eol)
            }
        }
        "it is a small field" should {
            val house1 = new House()
            "be scalable" in {
                house1.housefield(1,1) should be ("H  " + eol)
                house1.housefield(1,2) should be ("H  H  " + eol)
                house1.housefield(2,1) should be ("HH  " + eol)
            }
        }
    }
}