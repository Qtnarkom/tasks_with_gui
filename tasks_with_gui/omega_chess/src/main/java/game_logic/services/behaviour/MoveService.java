package game_logic.services.behaviour;

import game_logic.model.Game;
import game_logic.services.move.Move;
import game_logic.services.move.Position;

import java.util.Collection;
import java.util.List;

public interface MoveService {
    MoveService setGame(Game game);
    MoveService setPos(Position position);

    Game getGame();
    Position getPos();
    List<Move> getNext();


}
