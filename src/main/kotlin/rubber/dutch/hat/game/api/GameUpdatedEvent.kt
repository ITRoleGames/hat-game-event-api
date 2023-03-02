package rubber.dutch.hat.game.api

import java.util.*

data class GameUpdatedEvent(override val gameId: UUID) : GameEvent {

    override val type = GameEventType.GAME_UPDATED
}
