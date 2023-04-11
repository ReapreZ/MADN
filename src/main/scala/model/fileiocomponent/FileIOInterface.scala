package model.fileIOComponent

import model.gamecomponent.GameInterface

trait FileIOInterface {
  
    def load: GameInterface
    def save(game:GameInterface): Unit

}
