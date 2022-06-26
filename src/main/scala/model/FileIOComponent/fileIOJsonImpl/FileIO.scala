package model.fileIOComponent.fileIOJsonImpl

import model.fileIOComponent.FileIOInterface
import model.gameComponent.GameInterface
import play.api.libs.json._


class FileIO extends FileIOInterface {

    override def load: GameInterface = {

    }

    override def save(game:GameInterface): Unit = {
        import java.io._
        val pw = new PrintWriter(new File("game.json"))
        pw.write(Json.prettyPrint(gameToJson(game)))
        pw.close
    }
  
}
