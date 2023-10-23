package de.htwg.madn.MADN

import de.htwg.madn.controller.ControllerInterface
import de.htwg.madn.controller.controllerBase.Controller
import de.htwg.madn.model.meshComponent.meshBase.Mesh
import de.htwg.madn.model.gameComponent.GameInterface
import de.htwg.madn.model.gameComponent.gameBase.Game
import de.htwg.madn.model.meshComponent.MeshInterface
import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Guice, Inject}
import com.google.inject.TypeLiteral
import net.codingwell.scalaguice.ScalaModule

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
