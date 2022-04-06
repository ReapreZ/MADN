package model
import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class fieldSpec extends AnyWordSpec with Matchers {

  "A Field" when {
    "not set any value" should {
      val emptyField = field(0)
      "have value 0" in {
        emptyField.value should be(0)
      }
      "not be set" in {
        emptyField.isSet should be(false)
      }
    }
    "set to a specific value" should {
      val nonEmptyField = field(2)
      "return that value" in {
        nonEmptyField.value should be(2)
      }
      "be set" in {
        nonEmptyField.isSet should be(true)
      }
    }
  }
}
