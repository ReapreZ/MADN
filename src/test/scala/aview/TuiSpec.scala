package aview

import model.{Mesh, Field, House, Dice}
import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class TuiSpec extends AnyWordSpec with Matchers{
  "A TUI" when {
    var mesh1 = new Mesh(0,0,0)
    var playernumber = 1
    var playerturn = 1
    val dice1 = new Dice
    var input: String = ""
    "it has 1 Player" should {
        val tui = new Tui()
        "start a game" in {
            tui.playernumber should equal (1)
        }
        "processInputLine" should {
            tui.processInputLine("q") should equal (0)
            tui.processInputLine("r") should be < 7
        }
        /*"checkinput" should {
            tui.checkinput("r", 1) should be ("It is Player A's turn\n")
            tui.checkinput("r", 2) should be ("It is Player B's turn\n")
            tui.checkinput("r", 3) should be ("It is Player C's turn\n")
            tui.checkinput("r", 4) should be ("It is Player D's turn\n")
        }
        "tui.turn(6)" should {
            tui.turn(6) shouldBe (mesh1.field1.cArr(0) = '')
        }
        "move" should {
            tui.move(1) should be (letterturn = 'A')
        }*/
    }
  }
}
