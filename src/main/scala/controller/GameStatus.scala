package controller

object GameStatus extends Enumeration {
    type GameStatus = Value
    val IDLE,NEW,SET,UNDO,REDO,SAVED,COULD_NOT_SAVE,LOADED,COULD_NOT_LOAD,FINISHED = Value

    val map = Map[GameStatus, String](
        IDLE -> "",
        NEW -> "A new game was created",
        SET -> "A Cell was set",
        UNDO -> "Undone one step",
        REDO -> "Redone one step",
        LOADED -> "A new Game was loaded",
        COULD_NOT_LOAD -> "The file could not be loaded",
        SAVED -> "The Game was saved",
        COULD_NOT_SAVE -> "The game could not be saved",
        FINISHED -> "The Game is over"
    )
    def message(gameStatus: GameStatus) = {
        map(gameStatus)
  }
}
