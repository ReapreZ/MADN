package aview

import model.{Mesh, Field, House, Dice}
import org.scalatest._
import matchers.should.Matchers
import wordspec.AnyWordSpec

class TuiSpec extends AnyWordSpec with Matchers{
  "A TUI" when {
    var mesh1 = new Mesh(0,0,0)
    var playernumber = 0
    var playerturn = 0
    val dice1 = new Dice
    var input: String = ""
    "it has 1 Player" should {
        val tui = new Tui()
        ""
    }
  }
}
