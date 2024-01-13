package game_logic.services.behaviour;

import game_logic.model.figure.Color;
import game_logic.model.figure.Figure;
import game_logic.services.move.Move;
import game_logic.services.move.Position;
import game_logic.services.move.TypeMove;
import game_logic.services.support.PositionMath;

import java.util.ArrayList;
import java.util.List;

public class PawnMoveService extends MoveServiceImpl{


    @Override
    public List<Move> getNext() {
        List<Move> possibleMoves = new ArrayList<>();

        Position currentPosition = getPos();

        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getCol();

        Color pawnColor = getGame().getBoard().getFigure(currentPosition).getColor();

        int direction = (pawnColor == Color.WHITE) ? 1 : -1;

        Position oneStepForward = new Position(currentRow + direction, currentCol);
        if (isValidMove(oneStepForward)) {
            possibleMoves.add(new Move(getPos(), oneStepForward, TypeMove.COMMON));

            if (isFirstMove() && isValidMove(new Position(currentRow+direction*2, currentCol))) {
                possibleMoves.add(
                        new Move(getPos(), new Position(currentRow+direction*2, currentCol), TypeMove.COMMON));
            }
        }

        Position attackLeft = new Position(currentRow+direction, currentCol-1);

        Position attackRight = new Position(currentRow+direction, currentCol+1);

        if (isValidAttack(attackLeft)) {
            possibleMoves.add(
                    new Move(getPos(), attackLeft, TypeMove.KILL));
        }

        if (isValidAttack(attackRight)) {
            possibleMoves.add(
                    new Move(getPos(), attackRight, TypeMove.KILL));
        }

        return possibleMoves;
    }

    private boolean isValidMove(Position position) {
        return isValidPosition(position) && getGame().getBoard().getFigure(position) == null;
    }

    private boolean isValidAttack(Position position) {
        return isValidPosition(position) &&
                getGame().getBoard().getFigure(position) != null &&
                getGame().getBoard().getFigure(position).getColor() != getGame().getBoard().getFigure(getPos()).getColor();
    }


    private boolean isFirstMove() {
        Figure figure = getGame().getBoard().getFigure(getPos());
        return !getGame().getMovingFigures().contains(figure);
    }
}
