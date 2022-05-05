package MADN

import model._
import Controller._
import util.Observer
import scala.language.reflectiveCalls
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ControllerSpec extends AnyWordSpec with Matchers {

    "A Controller" when {
        "observed by an Observer" should {
            val mesh = new Mesh(1,1,1)
            val controller = new Controller(mesh)
            val observer = new Obs
            controller.add(observer)
            "notify its Observer after getting out" in {
                controller.getOut1(1, mesh)
                observer.updated should be(true)
            }
            "notify its Observer after moving" in {
                controller.move1(1,mesh)
                observer.updated should be(true)
            }
            "notify its Observer after the input check" in {
                controller.checkinput1(1, mesh)
                observer.updated should be(true)
            }
        }
    }
}
case class Obs() extends Observer:
    var updated = false
    override def update: Unit = updated = true