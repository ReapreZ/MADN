package model

import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class MeshSpec() extends AnyWordSpec with Matchers {
    "A Mesh" when {
        val eol = sys.props("line.separator")
        "it is not set to any value" should {
            val mesh0 = new Mesh(0,0,0)
            "have an empty mesh" in {
                mesh0.mesh() should be ("" + eol + eol + "" + eol + "" + eol)
            }
        }
        "it is a small mesh" should {
            val mesh1 = new Mesh(1,1,1)
            "be scalable" in {
                mesh1.mesh() should be ("x_" + eol + eol + "A  " + eol + "-  " + eol)
            }
        }
    }
}
