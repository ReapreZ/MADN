package aview

import model.{Mesh, Field, House, Dice}
import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class TuiSpec extends AnyWordSpec with Matchers{
    "A TUI" when {
        val tui = new Tui()
        "processInputLine" should {
            tui.processInputLine("q") should equal (0)
            tui.processInputLine("r") should be < 7
        }
        "startgame with 1,1,1" should {
            val houseamount = 1
            val playeramount = 1
            val cellamount = 1
            val mesh1 = Mesh(cellamount, playeramount, houseamount)
            tui.startgame() shouldBe (mesh1)
        }
    }
}
