package scala

import scala._
import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class MADNSpec() extends AnyWordSpec with Matchers {
    "MADN" when {
        val eol = sys.props("line.separator")
        "newly created" should {
            "print out" in {
                println("Welcome to Mensch aergere dich nicht " + "Jeff" + eol)
            }
        }
        "mesh " should {
        val mesh1 = new mesh()
        "print out" in {
            mesh1.mesh(2,2,2) should be ("x____x____" + eol + "HH  HH  " + eol + "--  --  " + eol)
        }
    }
    }
}