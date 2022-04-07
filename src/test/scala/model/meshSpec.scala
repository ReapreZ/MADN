package model

import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class meshSpec() extends AnyWordSpec with Matchers {
  "A Mesh" when {
      val eol = sys.props("line.separator")
      "not set any value" should {
          val mesh0 = new mesh()
          "have an empty mesh" in {
              mesh0.mesh(0,0,0) should be ("" + eol + "" + eol + "" + eol)
          }
      }
      "it is a small mesh" should {
          val mesh1 = new mesh()
          "be scalable" in {
              mesh1.mesh(1,1,1) should be ("x_" + eol + "H  " + eol + "-  " + eol)
              mesh1.mesh(1,1,2) should be ("x_" + eol + "HH  " + eol + "--  " + eol)
              mesh1.mesh(3,2,3) should be ("x______x______" + eol + "HHH  HHH  " + eol + "---  ---  " + eol)
          }
      }
  }
}
