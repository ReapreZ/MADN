package de.htwg.madn.model.fileIOComponent

import de.htwg.madn.model.gameComponent.GameInterface

trait FileIOInterface {
  
    def load: GameInterface
    def save(game:GameInterface): Unit

}
