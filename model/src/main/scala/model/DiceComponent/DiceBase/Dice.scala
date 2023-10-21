package model.diceComponent.diceBase
import scala.util.{Try,Success,Failure}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
final case class Dice() extends DiceStrategy{
    override def diceRandom(num: Int=5): Int = (1 + scala.util.Random.nextInt(num))
    override def magicDice(num: Int=6): Int = num

    val diceRoute = get {
        concat(
            path("dice") {
                complete(diceRandom().toString())
            },
            path("magicdice") {
                complete(magicDice().toString())
            }
            )
        }
}
