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
            //tui.playernumber should equal (1)
        }
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
}
