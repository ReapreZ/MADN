package model

import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class finishSpec() extends AnyWordSpec with Matchers {
    "A Finish" when {
        val eol = sys.props("line.separator")
        "not set any value" should {
            val finish0 = new finish()
            "have an empty field" in {
                finish0.finishfield(0,0) should be ("" + eol)
            }
        }
        "it is a small field" should {
            val finish1 = new finish()
            "be scalable" in {
                finish1.finishfield(1,1) should be ("-  " + eol)
                finish1.finishfield(1,2) should be ("-  -  " + eol)
                finish1.finishfield(2,1) should be ("--  " + eol)
            }
        }
    }
}
