/*package scala
import model.mesh
import scala._
import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class MADNSpec() extends AnyWordSpec with Matchers {
    "MADN" when {
        val eol = sys.props("line.separator")
        "newly created" should {
            val welcome = "Welcome to Mensch aergere dich nicht " + "Jeff" + eol
            "print out" in {
                welcome should be ("Welcome to Mensch aergere dich nicht Jeff" + eol)
            }
        }
        "mesh " should {
        val mesh1 = new mesh()
        "print out" in {
            mesh1.mesh(2,2,2) should be ("x____x____" + eol + "HH  HH  " + eol + "--  --  " + eol)
            }
        }
    }  
}*/