package game_logic.services.filler;

import game_logic.model.Game;
import game_logic.model.Player;
import game_logic.model.board.Board;
import game_logic.model.figure.Color;
import game_logic.model.figure.Figure;
import game_logic.model.figure.TypeFigure;
import game_logic.services.behaviour.PawnMoveService;
import game_logic.services.behaviour.jumping.ChampMoveService;
import game_logic.services.behaviour.jumping.KingMoveService;
import game_logic.services.behaviour.jumping.KnightMoveService;
import game_logic.services.behaviour.jumping.WizardMoveService;
import game_logic.services.behaviour.sliding.BishopMoveService;
import game_logic.services.behaviour.sliding.QueenMoveService;
import game_logic.services.behaviour.sliding.RookMoveService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GameFactory {

    public Game createGame(){
        LinkedList<Player> players = new LinkedList<>();
        Player p1 = new Player(
                "Player 1_WHITE"
        );
        players.add(p1);

        Player p2 = new Player("Player 2_BLACK");

        players.add(p2);

        Map<Player, Color> playerColorMap = new HashMap<>();
        playerColorMap.put(p1, Color.WHITE);
        playerColorMap.put(p2, Color.BLACK);

        return new Game(players, playerColorMap, createBoard());
    }

    public Board createBoard(){
        Figure[][] figures = new Figure[10][12];

        fillPawnLine(figures, 1, Color.WHITE);
        fillPawnLine(figures, 8, Color.BLACK);

        fillSecondLine(figures, 0, Color.WHITE);
        fillSecondLine(figures, 9, Color.BLACK);

        return new Board(figures);
    }

    private void fillPawnLine(Figure[][] figures, int row, Color color){
        for (int i = 0; i < figures[0].length; i++) {
            figures[row][i] = new Figure(color, TypeFigure.PAWN, new PawnMoveService());
        }
    }

    private void fillSecondLine(Figure[][] figures, int row, Color color){
        figures[row][0] = new Figure(color, TypeFigure.WIZARD, new WizardMoveService());
        figures[row][11] = new Figure(color, TypeFigure.WIZARD, new WizardMoveService());

        figures[row][1] = new Figure(color, TypeFigure.CHAMP, new ChampMoveService());
        figures[row][10] = new Figure(color, TypeFigure.CHAMP, new ChampMoveService());

        figures[row][2] = new Figure(color, TypeFigure.ROOK, new RookMoveService());
        figures[row][9] = new Figure(color, TypeFigure.ROOK, new RookMoveService());

        figures[row][3] = new Figure(color, TypeFigure.KNIGHT, new KnightMoveService());
        figures[row][8] = new Figure(color, TypeFigure.KNIGHT, new KnightMoveService());

        figures[row][4] = new Figure(color, TypeFigure.BISHOP, new BishopMoveService());
        figures[row][7] = new Figure(color, TypeFigure.BISHOP, new BishopMoveService());

        figures[row][5] = new Figure(color, TypeFigure.QUEEN, new QueenMoveService());
        figures[row][6] = new Figure(color, TypeFigure.KING, new KingMoveService());
    }
}
