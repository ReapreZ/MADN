package MADN

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import controller.Controller
import controller.ControllerInterface
import model.gameComponent.GameInterface
import model.gameComponent.gameBase.Game


class MADNModule extends AbstractModule {

    override def configure() = {
        bind(classOf[ControllerInterface]).to(classOf[Controller])
        bind(classOf[GameInterface]).to(classOf[Game])
        bind(classOf[Int]).annotatedWith(Names.named("DefaultGameType")).toInstance(0)
    }

  
}
