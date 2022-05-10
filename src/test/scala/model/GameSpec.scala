package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model._

class GameSpec extends AnyWordSpec with Matchers {
  
    "A Game is played" when {
        val game = new Game(0,new Mesh(0,0,0))
        val mesh = new Mesh(7,3,1)
        "getOut" should {
            "when rolledDice < 6" in {
                game.getOut(1,mesh) shouldBe (mesh)
            }
            "when rolledDice = 6" in {
                val mesh1 = mesh
                mesh1.field1.cArr(0) = 'A'
                mesh1.field1.cArr(22) = 'B'
                mesh1.field1.cArr(44) = 'C'
                game.getOut(6,mesh) shouldBe (mesh1)
            }
        }
        "checkInput" should {
            "when input != r" in {
                game.checkinput(1, mesh) shouldBe (mesh)
            }
            "when input = r" in {
                game.checkinput(1, mesh) shouldBe (mesh)
            }
        }
        "move" should {
            "when a figure is out" in {
                val mesh1 = mesh
                mesh1.field1.cArr(0) = '_'
                mesh1.field1.cArr(1) = 'A'
                game.move(1, mesh) shouldBe (mesh1)
            }
            "when no figure is out" in {
                game.move(1,mesh) shouldBe (mesh)
            }
        }
        "getTurnC" should {
            "when playerturn = 1" in {
                game.getTurnC(1) shouldBe 'A'
            }
            "when playerturn = 2" in {
                game.getTurnC(2) shouldBe 'B'
            }
            "when playerturn = 3" in {
                game.getTurnC(3) shouldBe 'C'
            }
            "when playerturn = 4" in {
                game.getTurnC(4) shouldBe 'D'
            }
            "when playerturn = 5" in {
                game.getTurnC(5) shouldBe ' '
            }
        }
    }
}