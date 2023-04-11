package controllerComponent

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
        NEW -> "A new game was created\n",
        SET -> "A new Mesh was created\n",
        UNDO -> "Undone one step\n",
        REDO -> "Redone one step\n",
        LOADED -> "A new Game was loaded\n",
        COULD_NOT_LOAD -> "The file could not be loaded\n",
        SAVED -> "The Game was saved\n",
        COULD_NOT_SAVE -> "The game could not be saved\n",
        FINISHED -> "The Game is over\n"
    )
}