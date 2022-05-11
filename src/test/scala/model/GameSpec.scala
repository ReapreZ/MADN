package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.MeshComponent.MeshBase.Mesh
import model.GameComponent.GameBase.Game

class GameSpec extends AnyWordSpec with Matchers {
  
    "A Game is played" when {
        val game = new Game(0,new Mesh(7,3,1),0,0,0,0)
        val mesh = new Mesh(7,3,1)
        "getOut" should {
            "when rolledDice < 6" in {
                game.getOut(1,mesh) shouldBe (game.copy(playerturn = game.playerturn+1))
            }
            "when rolledDice < 6 and Playerturn == Playeramount" in {
                val game1 = game.copy(playerturn = 3)
                game1.getOut(1,mesh) shouldBe (game1.copy(playerturn = 1))
            }
            "when rolledDice = 6 and its A's turn" in {
                val game1 = game.copy()
                val game2 = game.copy(playerturn = 1)
                game1.mesh10.field1.cArr(0) = 'A'
                game1.mesh10.house1.hArr(0) = 'H'
                
                game2.getOut(6,mesh) shouldBe (game1.copy(playerturn = 1,piecesOutA = game.piecesOutA + 1))
            }
            "when rolledDice = 6 and its A's turn and all Pieces out" in {
                val game1 = game.copy()
                val game2 = game.copy(playerturn = 1, piecesOutA = 3)
                game1.mesh10.field1.cArr(6) = 'A'
                game1.mesh10.field1.cArr(0) = '_'
                
                game2.getOut(6,mesh) shouldBe (game1.copy(playerturn = 1, piecesOutA = 3))
            }
            "when rolledDice = 6 and its B's turn" in {
                val game1 = game.copy()
                val game2 = game.copy(playerturn = 2)
                game1.mesh10.field1.cArr(22) = 'B'
                game1.mesh10.house1.hArr(3) = 'H'
                
                game2.getOut(6,mesh) shouldBe (game1.copy(playerturn = 2,piecesOutB = game.piecesOutB + 1))
            }
            "when rolledDice = 6 and its B's turn and all Pieces out" in {
                val game1 = game.copy()
                val game2 = game.copy(playerturn = 2, piecesOutB = 3)
                game1.mesh10.field1.cArr(28) = 'B'
                game1.mesh10.field1.cArr(22) = '_'
                
                game2.getOut(6,mesh) shouldBe (game1.copy(playerturn = 2, piecesOutB = 3))
            }
            "when rolledDice = 6 and its C's turn" in {
                val game1 = game.copy()
                val game2 = game.copy(playerturn = 3)
                game1.mesh10.field1.cArr(44) = 'C'
                game1.mesh10.house1.hArr(6) = 'H'
                
                game2.getOut(6,mesh) shouldBe (game1.copy(playerturn = 3,piecesOutC = game.piecesOutC + 1))
            }
            "when rolledDice = 6 and its C's turn and all Pieces out" in {
                val game1 = game.copy()
                val game2 = game.copy(playerturn = 3, piecesOutC = 3)
                game1.mesh10.field1.cArr(50) = 'C'
                game1.mesh10.field1.cArr(44) = '_'
                
                game2.getOut(6,mesh) shouldBe (game1.copy(playerturn = 3, piecesOutC = 3))
            }
        }
        "checkInput" should {
            "when input != r" in {
                game.checkinput(1, mesh) shouldBe (game.copy(playerturn = game.playerturn+1))
            }
            "when input = r" in {
                game.checkinput(1, mesh) shouldBe (game.copy(playerturn = game.playerturn+1))
            }
        }
        "move" should {
            "when a figure is out" in {
                val game1 = new Game(0,new Mesh(7,3,1),0,0,0,0)
                game1.mesh10.field1.cArr(0) = '_'
                game1.mesh10.field1.cArr(1) = 'A'
                game.move(1, mesh) shouldBe (game1)
            }
            "when no figure is out" in {
                game.move(1,mesh) shouldBe (game)
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