package game_logic.services.support;

import game_logic.model.Game;
import game_logic.model.Player;
import game_logic.model.figure.Color;
import game_logic.model.figure.Figure;
import game_logic.model.figure.TypeFigure;
import game_logic.services.behaviour.MoveService;
import game_logic.services.move.Move;
import game_logic.services.move.Position;


import java.util.ArrayList;
import java.util.List;

public class MateCheckerService {
    private Game game;
    private Player player;
    private Move move;


    public MateCheckerService() {
    }

    public MateCheckerService setGame(Game game){
        this.game = game;
        return this;
    }
    public MateCheckerService setPlayer(Player player){
        this.player = player;
        return this;
    }

    public MateCheckerService setMove(Move move) {
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




    public boolean isBecomeMate(Color playerColor){
        Color opponentColor = getOpponentColor(playerColor);

        Game simulated = new RepositionService().setGame(game).simulateMove(move);
        return isCheckmate(simulated, playerColor);
    }


    public boolean isCheckmate(Game game, Color playerColor) {

        if (isSituationSafe(game, playerColor)) {
            return false;
        }

        List<Move> legalMoves = getLegalMoves(game, playerColor);
        for (Move legalMove : legalMoves) {
            Game simulated = new RepositionService().setGame(game).simulateMove(legalMove);
            if (isSituationSafe(simulated, playerColor)) {
                return false;
            }
        }

        return true;
    }

    /*private boolean hasEscape(Color playerColor, Color opponentColor){
        List<Move> legalMoves = getLegalMoves(game, playerColor);
        for (Move legalMove : legalMoves) {
            Game simulated = new RepositionService().setGame(game).simulateMove(legalMove);
            if (isSituationSafe(simulated, playerColor, opponentColor)) {
                return false;
            }
        }
        return true;
    }*/
    private List<Move> getLegalMoves(Game game, Color currentPlayerColor) {
        List<Move> legalMoves = new ArrayList<>();

        for (int i = 0; i < game.getBoard().row(); i++) {
            for (int j = 0; j < game.getBoard().col(); j++) {
                Figure currentFigure = game.getBoard().getFigure(i, j);

                if (currentFigure != null && currentFigure.getColor().equals(currentPlayerColor)) {
                    MoveService moveService = currentFigure.getMoveService();
                    moveService.setGame(game).setPos(game.getBoard().findPos(currentFigure));

                    legalMoves.addAll(moveService.getNext());
                }
            }
        }

        return legalMoves;
    }
    /*public boolean isCheckmate(Game game, Color playerColor) {
        Color opponentColor = getOpponentColor(playerColor);


        if(!isSituationSafe(game, playerColor, opponentColor)){
            Game simulated = new RepositionService().setGame(game).simulateMove(move);
            return isSituationSafe(simulated, playerColor, opponentColor);
        }

        // Получаем короля игрока, которого мы проверяем на мат
        Figure king = getKing(game, playerColor);
        Position kingPos = game.getBoard().findPos(king);

        // Если король не находится под шахом, то нет матовой ситуации
        if (!isSquareUnderAttack(game, kingPos, opponentColor)) {
            return false;
        }

        if (isMoveSafe(playerColor, opponentColor)){

        }
        // Проверяем, может ли король сделать безопасный ход
        if (hasSafeMoves(game, king, playerColor)) {
            return false;
        }

        // Проверяем, можно ли блокировать атаку фигур противника
        List<Figure> opponentFigures = getFiguresByColor(game, getOpponentColor(playerColor));
        for (Figure opponentFigure : opponentFigures) {
            MoveService moveService = opponentFigure.getMoveService();
            moveService.setGame(game).setPos(game.getBoard().findPos(opponentFigure));

            List<Move> possibleMoves = moveService.getNext();
            for (Move possibleMove : possibleMoves) {
                // Симулируем ход фигуры противника
                Game simulatedGame = new RepositionService().setGame(game).simulateMove(possibleMove);

                // Получаем новую позицию короля после симулированного хода
                Figure simulatedKing = getKing(simulatedGame, playerColor);

                // Если король может сделать безопасный ход, то это не мат
                if (hasSafeMoves(simulatedGame, simulatedKing, playerColor)) {
                    return false;
                }
            }
        }

        // Если король не может сделать безопасных ходов и атаку не удалось блокировать, то это мат
        return true;
    }*/

    public boolean isSituationSafe(Game game, Color playerColor){
        //Game simulated = new RepositionService().setGame(game).simulateMove(move);
        Color opponentColor = getOpponentColor(playerColor);
        Figure king = getKing(game, playerColor);
        Position kingPos = game.getBoard().findPos(king);
        System.out.println("King pos "+ kingPos);

        return !isSquareUnderAttack(game, kingPos, opponentColor);
    }


    private Color getOpponentColor(Color playerColor) {
        // Получаем цвет противоположного игрока
        return (playerColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private boolean isSquareUnderAttack(Game game, Position position, Color attackerColor) {

        List<Figure> opponentFigures = getFiguresByColor(game, attackerColor);
        for (Figure opponentFigure : opponentFigures) {
            MoveService moveService = opponentFigure.getMoveService();
            moveService.setGame(game).setPos(game.getBoard().findPos(opponentFigure));

            List<Move> possibleMoves = moveService.getNext();

            if (possibleMoves.stream().anyMatch(move -> move.getPos2().getRow() == position.getRow() && move.getPos2().getCol() == position.getCol())) {
                return true;
            }

        }

        return false;
    }

    private List<Figure> getFiguresByColor(Game game, Color color){
        List<Figure> figures = new ArrayList<>();
        for (int i = 0; i < game.getBoard().row(); i++) {
            for (int j = 0; j < game.getBoard().col(); j++) {
                Figure f = game.getBoard().getFigure(i, j);
                if (f != null && f.getColor().equals(color)){
                    figures.add(f);
                }
            }
        }
        return figures;
    }


    private Figure getKing(Game game, Color color){

        for (int i = 0; i < game.getBoard().row(); i++) {
            for (int j = 0; j < game.getBoard().col(); j++) {
                Figure f = game.getBoard().getFigure(i, j);
                if (f != null && f.getColor().equals(color) && f.getType().equals(TypeFigure.KING)){
                    return f;
                }
            }
        }

        return null;
    }
    private boolean hasSafeMoves(Game game, Figure king, Color playerColor) {
        MoveService moveService = king.getMoveService().setGame(game).setPos(game.getBoard().findPos(king));
        List<Move> possibleMoves = moveService.getNext();

        for (Move possibleMove : possibleMoves) {
            Game simulatedGame = new RepositionService().setGame(game).simulateMove(possibleMove);

            Figure simulatedKing = getKing(simulatedGame, playerColor);

            if (!isSquareUnderAttack(simulatedGame, game.getBoard().findPos(simulatedKing), getOpponentColor(playerColor))) {
                return true;
            }

            if (canCoverKing(simulatedGame, king, getOpponentColor(playerColor))) {
                return true;
            }
        }

        return false;
    }

    private boolean canCoverKing(Game game, Figure king, Color opponentColor) {
        List<Figure> playerFigures = getFiguresByColor(game, king.getColor());

        for (Figure playerFigure : playerFigures) {
            MoveService moveService = playerFigure.getMoveService();
            moveService.setGame(game).setPos(game.getBoard().findPos(playerFigure));

            List<Move> possibleMoves = moveService.getNext();
            for (Move possibleMove : possibleMoves) {
                Game simulatedGame = new RepositionService().setGame(game).simulateMove(possibleMove);

                if (!isSquareUnderAttack(simulatedGame, game.getBoard().findPos(king), opponentColor)) {
                    return true;
                }
            }
        }

        return false;
    }
}
