package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model._

class field extends WordSpec with Matchers {

  "A Field" when {
    "not set any value" should {
      val emptyField = Field(0)
      "have value 0" in {
        emptyField.value should be(0)
      }
      "not be set" in {
        emptyField.isSet should be(false)
      }
    }
    "set to a specific value" should {
      val nonEmptyField = Field(2)
      "return that value" in {
        nonEmptyField.value should be(2)
      }
      "be set" in {
        nonEmptyField.isSet should be(true)
      }
    }
  }
}
