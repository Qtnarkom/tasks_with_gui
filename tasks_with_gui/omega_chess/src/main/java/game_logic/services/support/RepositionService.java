package game_logic.services.support;

import game_logic.model.Game;
import game_logic.model.figure.Figure;
import game_logic.services.move.Move;

import java.util.HashMap;
import java.util.LinkedList;

public class RepositionService {
    private Game game;

    public RepositionService setGame(Game game){
        this.game = game;
        return this;
    }
    public void reposition(Move move){
        Figure figure = game.getBoard().getFigure(move.getPos1());
        game.getBoard().setFigure(move.getPos1(), null);
        game.getBoard().setFigure(move.getPos2(), figure);
    }

    public Game simulateMove(Move move) {
        Game simulatedGame = new Game(new LinkedList<>(game.getPlayers()), new HashMap<>(game.getSide()), game.getBoard().copy());

        Figure movedFigure = simulatedGame.getBoard().getFigure(move.getPos1());
        simulatedGame.getBoard().setFigure(move.getPos1(), null);
        simulatedGame.getBoard().setFigure(move.getPos2(), movedFigure);

        return simulatedGame;
    }
}
