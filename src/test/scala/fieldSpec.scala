package model
import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class fieldSpec extends AnyWordSpec with Matchers {
  "A Field" when {
    val eol = sys.props("line.separator")
    "not set any value" should {
      val field0 = new field()
      "should have an empty field" in {
        field0.cell should be("" + eol)
      }
    }
  }
}