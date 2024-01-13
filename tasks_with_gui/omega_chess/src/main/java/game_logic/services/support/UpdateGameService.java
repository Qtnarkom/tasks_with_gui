package game_logic.services.support;

import game_logic.model.Game;
import game_logic.model.Player;
import game_logic.model.figure.Figure;
import game_logic.model.figure.TypeFigure;
import game_logic.services.behaviour.sliding.QueenMoveService;
import game_logic.services.move.Move;
import game_logic.services.validator.MoveValidator;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class UpdateGameService {

    private Game game;

    public UpdateGameService() {
    }

    public UpdateGameService setGame(Game game){
        this.game = game;
        return this;
    }
    public void update(Move move){
        if (new MoveValidator().setGame(game).setMove(move).setPlayer(game.getPlayers().peek()).isValid()){
            Figure figure = game.getBoard().getFigure(move.getPos1());
            Set<Figure> figures = game.getMovingFigures();
            figures.add(figure);
            game.setMovingFigures(figures);
            new RepositionService().setGame(game).reposition(move);
            LinkedList<Player> players = game.getPlayers();
            players.add(players.pollFirst());
            game.setPlayers(players);
            if((figure.getType().equals(TypeFigure.PAWN))&&(move.getPos2().getRow() == game.getBoard().row()-1 || move.getPos2().getRow() == 0)){
                Figure f = new Figure(figure.getColor(), TypeFigure.QUEEN, new QueenMoveService());
                game.getBoard().setFigure(move.getPos2(), f);
            }
        }
    }

}
