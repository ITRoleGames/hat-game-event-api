package rubber.dutch.hat.game.api

import java.util.*

interface GameEvent {

    val type: GameEventType

    val gameId: UUID
}
