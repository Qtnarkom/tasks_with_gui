package game_logic.services.behaviour;

import game_logic.model.Game;
import game_logic.model.figure.Figure;
import game_logic.services.move.Move;
import game_logic.services.move.Position;

import java.util.List;

public abstract class MoveServiceImpl implements MoveService{

    private Game game;
    private Position position;

    @Override
    public MoveService setGame(Game game) {
        this.game = game;
        return this;
    }

    @Override
    public MoveService setPos(Position position) {
        this.position = position;
        return this;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public Position getPos() {
        return position;
    }


    @Override
    public List<Move> getNext() {
        return null;
    }

    public boolean isValidPosition(Position position) {
        return isValidPosition(position.getRow(), position.getCol());
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < getGame().getBoard().row() && col >= 0 && col < getGame().getBoard().col();
    }

    public boolean isSquareEmptyOrOpponent(Position position) {
        return isSquareEmptyOrOpponent(position.getRow(), position.getCol());
    }

    public boolean isSquareEmptyOrOpponent(int row, int col) {
        Figure figure = getGame().getBoard().getFigure(row, col);

        return figure == null || figure.getColor() != getGame().getBoard().getFigure(getPos()).getColor();
    }
}
