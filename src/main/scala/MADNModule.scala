package MADN

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Guice, Inject}
import com.google.inject.TypeLiteral
import controller.ControllerInterface
import controller.Controller
import model.meshcomponent.meshbase.Mesh
import model.gamecomponent.GameInterface
import net.codingwell.scalaguice.ScalaModule
import model.gamecomponent.gamebase.Game
import model.meshcomponent.MeshInterface

class MADNModule extends AbstractModule {

    override def configure(): Unit = {
        val mesh = Mesh(0)
        val game = Game(0,mesh)
        bind(classOf[ControllerInterface]).to(classOf[Controller])
        bind(classOf[GameInterface]).annotatedWith(Names.named("DefaultGameType")).toInstance(game)
        bind(classOf[GameInterface]).toInstance(Game(1,mesh))
        
    }
}
