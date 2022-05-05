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
            tui.processInputLine("t") should equal (-1)
        }
        "startgame with 7,1,1" should {
            val cellamount = 7
            val playeramount = 3
            val houseamount = 1
            val mesh1 = Mesh(cellamount, playeramount, houseamount)
            tui.startgame() shouldBe (mesh1)
        }
    }
}