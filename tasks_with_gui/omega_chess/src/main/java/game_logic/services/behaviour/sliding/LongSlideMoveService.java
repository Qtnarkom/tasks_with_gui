package game_logic.services.behaviour.sliding;

import game_logic.model.figure.Figure;
import game_logic.services.behaviour.MoveService;
import game_logic.services.behaviour.MoveServiceImpl;
import game_logic.services.move.Move;
import game_logic.services.move.Position;
import game_logic.services.move.TypeMove;

import java.util.ArrayList;
import java.util.List;

public abstract class LongSlideMoveService extends MoveServiceImpl {

    private int[][] steps;

    public MoveService setSteps(int[][] steps){
        this.steps = steps;
        return this;
    }

    public int[][] getSteps() {
        return steps;
    }

    @Override
    public List<Move> getNext() {
        List<Move> possibleMoves = new ArrayList<>();

        Position currentPosition = getPos();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getCol();


        for (int[] move : getSteps()) {

            int newRow = currentRow + move[0];
            int newCol = currentCol + move[1];

            while (isValidPosition(newRow, newCol)) {
                Figure figureOnPos = getGame().getBoard().getFigure(newRow, newCol);
                if (figureOnPos != null && figureOnPos.getColor().equals(getGame().getBoard().getFigure(currentRow, currentCol).getColor())) {
                    break;
                }

                Position newPosition = new Position(newRow, newCol);
                Move bishopMove = new Move(currentPosition, newPosition, TypeMove.COMMON);
                possibleMoves.add(bishopMove);

                if(figureOnPos != null)
                    break;

                newRow += move[0];
                newCol += move[1];


            }
        }

        return possibleMoves;
    }
}
