package game_logic.services.behaviour.jumping;

import game_logic.services.behaviour.MoveService;
import game_logic.services.behaviour.MoveServiceImpl;
import game_logic.services.move.Move;
import game_logic.services.move.Position;
import game_logic.services.move.TypeMove;

import java.util.ArrayList;
import java.util.List;

public abstract class JumpMoveService extends MoveServiceImpl {
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

            if (isValidPosition(newRow, newCol)) {
                Position newPosition = new Position(newRow, newCol);

                if (isSquareEmptyOrOpponent(newPosition)) {
                    Move champMove = new Move(currentPosition, newPosition, TypeMove.COMMON);
                    possibleMoves.add(champMove);
                }
            }
        }

        return possibleMoves;
    }


}
