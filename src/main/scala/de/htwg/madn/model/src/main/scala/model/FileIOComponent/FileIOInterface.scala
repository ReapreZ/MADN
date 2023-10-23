package de.htwg.madn.model.fileIOComponent

import de.htwg.madn.model.gameComponent.GameInterface
import play.api.libs.json._

trait FileIOInterface {
  
    def load: GameInterface
    def save(game:GameInterface): Unit
    val fileIoRoute: akka.http.scaladsl.server.Route

}
