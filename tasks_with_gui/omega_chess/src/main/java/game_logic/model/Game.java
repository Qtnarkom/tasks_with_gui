package game_logic.model;

import game_logic.model.board.Board;
import game_logic.model.figure.Color;
import game_logic.model.figure.Figure;
import game_logic.model.figure.TypeFigure;

import java.util.*;

public class Game {
    private LinkedList<Player> players;
    private Map<Player, Color> side;
    private Set<Figure> movingFigures;
    private Board board;

    public Game(LinkedList<Player> players, Map<Player, Color> side, Board board) {
        this.players = players;
        this.side = side;
        this.board = board;
        this.movingFigures = new HashSet<>();
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }

    public Map<Player, Color> getSide() {
        return side;
    }
    public Color getCurrentPlayerColor(){
        return side.get(players.peekFirst());
    }

    public void setSide(Map<Player, Color> side) {
        this.side = side;
    }

    public Board getBoard() {
        return board;
    }

    public Set<Figure> getMovingFigures() {
        return movingFigures;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setMovingFigures(Set<Figure> movingFigures) {
        this.movingFigures = movingFigures;
    }

    public Player getPlayer(Color color){
        for (Map.Entry<Player, Color> entry : side.entrySet()) {
            if (entry.getValue() == color) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Figure getKing(Color color){
        for (int i = 0; i < getBoard().row(); i++) {
            for (int j = 0; j < getBoard().col(); j++) {
                Figure f = getBoard().getFigure(i, j);
                if (f != null && f.getColor().equals(color) && f.getType().equals(TypeFigure.KING)){
                    return f;
                }
            }
        }
        return null;
    }
}
