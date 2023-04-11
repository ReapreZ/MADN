package modelComponent.fileIOComponent

trait FileIOInterface {
  
    def load: GameInterface
    def save(game:GameInterface): Unit

}
