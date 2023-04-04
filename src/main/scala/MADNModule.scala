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
        val mesh = Mesh(0)
        val piecesOutList:List[Int] = List(0,0,0,0)
        val game = Game(0,mesh)
        bind(classOf[ControllerInterface]).to(classOf[Controller])
        bind(classOf[GameInterface]).annotatedWith(Names.named("DefaultGameType")).toInstance(game)
        bind(classOf[GameInterface]).toInstance(Game(1,mesh))
        
    }
}
