package game_logic.services.behaviour.sliding;

import game_logic.services.behaviour.MoveServiceImpl;
import game_logic.services.move.Move;

import java.util.ArrayList;
import java.util.List;

public class QueenMoveService extends MoveServiceImpl {
    @Override
    public List<Move> getNext() {
        List<Move> possibleMoves = new ArrayList<>();

        possibleMoves.addAll(
                new RookMoveService().setGame(getGame()).setPos(getPos()).getNext()
        );
        possibleMoves.addAll(
                new BishopMoveService().setGame(getGame()).setPos(getPos()).getNext()
        );

        return possibleMoves;
    }
}
