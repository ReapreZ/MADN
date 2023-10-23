package de.htwg.madn.model.FileIOComponent

import de.htwg.madn.model.fileIOComponent.fileIOJsonImpl.FileIO
import de.htwg.madn.model.fileIOComponent.FileIOInterface
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._ 
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._


class fileIOService {

    var fileIO: FileIO = new FileIO

    val fileIoRoute = get {
        path("fileio") {
            complete(HttpEntity(ContentTypes.`application/json`, fileIO.gameToJson(fileIO.load).toString))
        }
    }
}
