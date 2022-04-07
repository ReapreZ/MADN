package model
import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class fieldSpec extends AnyWordSpec with Matchers {
  "A Field" when {
    val eol = sys.props("line.separator")
    "not set any value" should {
      val field0 = new field()
      "have an empty field" in {
        field0.cell(0,0) should be("" + eol)
      }
    }
    "it is a small field" should {
      val field1 = new field()
      "be scalable" in {
        field1.cell(1,1) should be ("x_" + eol)
        field1.cell(1,2) should be ("x__x__" + eol)
        field1.cell(2,1) should be ("x__" + eol)
      }
    }
  }
}