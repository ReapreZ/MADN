package MADN

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Guice, Inject}
import com.google.inject.TypeLiteral
import controller.ControllerInterface
import controller.Controller
import model.meshComponent.meshBase.Mesh
import model.gameComponent.GameInterface
import net.codingwell.scalaguice.ScalaModule
import model.gameComponent.gameBase.Game
import model.meshComponent.MeshInterface

class MADNModule extends AbstractModule {

    override def configure(): Unit = {
        val mesh = new Mesh(0)
        val piecesOutMap:Map[Int,Int] =Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)
        val game = new Game(0,mesh, piecesOutMap)
        bind(classOf[ControllerInterface]).to(classOf[Controller])
        bind(classOf[GameInterface]).annotatedWith(Names.named("DefaultGameType")).toInstance(game)
        bind(classOf[GameInterface]).toInstance(new Game(1,mesh,piecesOutMap))
        
    }
}
