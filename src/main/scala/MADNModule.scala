package MADN

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Guice, Inject}
import com.google.inject.TypeLiteral
import net.codingwell.scalaguice.ScalaModule

class MADNModule extends AbstractModule {

    override def configure(): Unit = {
        val mesh = Mesh(0)
        val piecesOutMap:Map[Int,Int] =Map(0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0)
        val game = Game(0,mesh, piecesOutMap)
        bind(classOf[ControllerInterface]).to(classOf[Controller])
        bind(classOf[GameInterface]).annotatedWith(Names.named("DefaultGameType")).toInstance(game)
        bind(classOf[GameInterface]).toInstance(Game(1,mesh,piecesOutMap))
        
    }
}
