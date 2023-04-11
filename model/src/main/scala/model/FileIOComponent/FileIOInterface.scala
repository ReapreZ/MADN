package model.fileIOComponent

import model.gameComponent.GameInterface

trait FileIOInterface {
  
    def load: GameInterface
    def save(game:GameInterface): Unit

}
