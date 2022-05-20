package controller

enum GameStatus {
    case IDLE
    case NEW
    case SET
    case UNDO
    case REDO
    case SAVED
    case COULD_NOT_SAVE
    case LOADED
    case COULD_NOT_LOAD
    case FINISHED

    def map = Map[GameStatus, String](
        IDLE -> "",
        NEW -> "A new game was created",
        SET -> "A new Mesh was created",
        UNDO -> "Undone one step",
        REDO -> "Redone one step",
        LOADED -> "A new Game was loaded",
        COULD_NOT_LOAD -> "The file could not be loaded",
        SAVED -> "The Game was saved",
        COULD_NOT_SAVE -> "The game could not be saved",
        FINISHED -> "The Game is over"
    )
}