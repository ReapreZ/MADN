package model
import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec with Matchers {
  "A Field" when {
        val eol = sys.props("line.separator")
        "it is not set to any value" should {
            val field0 = new Field(0,0)
            "have an empty field" in {
                field0.toString() should be("" + eol + eol)
            }
        }
        "it is a small field" should {
            val field1 = new Field(1,1)
            "be scalable" in {
                field1.toString() should be ("x_" + eol + eol)
            }
        }
    }
}