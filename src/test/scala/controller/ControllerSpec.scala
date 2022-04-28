package controller

import util.Observable
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ControllerSpec extends AnyWordSpec with Matchers {

"A Controller" when {
    "observed by an Observer" should {
        val observer = new Observer {
            var updated: Boolean = false
            def isUpdated: Boolean = updated
            override def update: Unit = updated = true
        }


    }
}

}
  

