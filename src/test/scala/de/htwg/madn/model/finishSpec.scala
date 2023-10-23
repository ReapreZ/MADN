package de.htwg.madn.model

import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec
import model.FinishComponent.FinishBase.Finish

class FinishSpec() extends AnyWordSpec with Matchers {
    "A Finish" when {
        val eol = sys.props("line.separator")
        "it is not set to any value" should {
            val finish0 = new Finish(0,0)
            "have an empty field" in {
                finish0.toString() should be ("" + eol)
            }
        }
        "it is a small field" should {
            val finish1 = new Finish(1,1)
            "be scalable" in {
                finish1.toString() should be ("-  " + eol)
            }
        }
    }
}