package model.FileIOComponent

import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.Directives.* 
import model.fileIOComponent.fileIOJsonImpl.FileIO
import model.fileIOComponent.FileIOInterface
import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.Directives.* 


class fileIOService {

    var fileIO: FileIO = new FileIO

    val fileIoRoute = get {
        path("fileio") {
            complete(HttpEntity(ContentTypes.`application/json`, fileIO.gameToJson(fileIO.load).toString))
        }
    }
}
