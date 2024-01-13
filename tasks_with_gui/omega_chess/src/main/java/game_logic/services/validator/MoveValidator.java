package game_logic.services.validator;

import game_logic.model.Game;
import game_logic.model.Player;
import game_logic.model.figure.Color;
import game_logic.model.figure.Figure;
import game_logic.services.behaviour.MoveService;
import game_logic.services.move.Move;
import game_logic.services.move.Position;
import game_logic.services.support.MateCheckerService;
import game_logic.services.support.RepositionService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MoveValidator {
    private Game game;
    private Player player;
    private Move move;


    public MoveValidator() {
    }

    public MoveValidator setGame(Game game){
        this.game = game;
        return this;
    }
    public MoveValidator setPlayer(Player player){
        this.player = player;
        return this;
    }

    public MoveValidator setMove(Move move) {
        this.move = move;
        return this;
    }

    public Game getGame(){
        return game;
    }
    public Player getPlayer(){
        return player;
    }

    public Move getMove() {
        return move;
    }

    public boolean isValid() {
        if (game == null || player == null || move == null) {
            // Вернуть false, если не установлены необходимые параметры
            return false;
        }

        // Проверить, может ли фигура выполнить этот ход
        Figure sourceFigure = game.getBoard().getFigure(move.getPos1());
        if (sourceFigure == null || sourceFigure.getColor() != game.getSide().get(player)) {
            // Фигура не принадлежит игроку
            return false;
        }

        MoveService moveService = sourceFigure.getMoveService();
        moveService.setGame(game).setPos(move.getPos1());
        List<Move> possibleMoves = moveService.getNext();

        // Проверить, содержится ли предложенный ход в списке возможных ходов

        if (!isPosInList(possibleMoves, move.getPos2())) {
            return false;
        }

        // Проверить, не приведет ли этот ход к мату короля
        //System.out.println(!isCheckmate(game, game.getSide().get(player)));
        return !isCheckmate(game, game.getSide().get(player));

    }

    private boolean isCheckmate(Game game, Color playerColor) {
        Game simulated = new RepositionService().setGame(game).simulateMove(move);
        MateCheckerService checkerService = new MateCheckerService().setGame(simulated).setMove(move).setPlayer(simulated.getPlayer(playerColor));

        System.out.println(checkerService.isSituationSafe(simulated, playerColor));

        return !checkerService.isSituationSafe(simulated, playerColor);
    }

    private boolean isPosInList(List<Move> moves, Position pos){
        for (Move m: moves){
            Position p2 = m.getPos2();

            if (p2.getRow() == pos.getRow() && p2.getCol() == pos.getCol())
                return true;
        }
        return false;
    }

}
