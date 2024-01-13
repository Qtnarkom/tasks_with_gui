import game_logic.model.Game;
import game_logic.model.Player;

import game_logic.model.figure.Figure;

import game_logic.services.filler.GameFactory;
import game_logic.services.move.Move;
import game_logic.services.move.Position;
import game_logic.services.move.TypeMove;

import game_logic.services.support.MateCheckerService;
import game_logic.services.support.UpdateGameService;
import game_logic.services.validator.ChoiceValidator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ChessGUI extends JFrame {

    private Game chessGame;
    private JButton[][] boardButtons;
    private boolean playing = true;
    private Position selected = null;
    public ChessGUI(Game chessGame) {
        this.chessGame = chessGame;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(chessGame.getBoard().row(), chessGame.getBoard().col()));

        boardButtons = new JButton[chessGame.getBoard().row()][chessGame.getBoard().col()];

        for (int i = 0; i < chessGame.getBoard().row(); i++) {
            for (int j = 0; j < chessGame.getBoard().col(); j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(80, 80));
                button.addActionListener(new ChessButtonListener(i, j));
                boardButtons[i][j] = button;
                add(button);
            }
        }

        updateBoard();
        pack();
        setLocationRelativeTo(null);
    }

    private void updateBoard() {

        for (int i = 0; i < chessGame.getBoard().row(); i++) {
            for (int j = 0; j < chessGame.getBoard().col(); j++) {
                Figure figure = chessGame.getBoard().getFigure(i, j);
                if (figure != null) {
                    /*String pieceType = figure.getType().toString().substring(0, 1);
                    String pieceColor = figure.getColor().toString().substring(0, 1);*/
                    boardButtons[i][j].setText(figure.getType().toString());
                    if(figure.getColor().equals(game_logic.model.figure.Color.BLACK))
                        boardButtons[i][j].setBackground(Color.gray);
                    else
                        boardButtons[i][j].setBackground(Color.WHITE);

                } else {
                    boardButtons[i][j].setText("");
                    boardButtons[i][j].setBackground(Color.orange);
                }
            }

        }
        if (selected!=null){
            boardButtons[selected.getRow()][selected.getCol()].setBackground(Color.green);
        }
        Player player = chessGame.getPlayers().peekFirst();


        this.setTitle("Ходит: %s              Цвет: %s".formatted(player.getNickname(), chessGame.getSide().get(player)));

        if (new MateCheckerService().isCheckmate(chessGame, chessGame.getCurrentPlayerColor())){
            this.setTitle("ИГРОК %s ПРОИГРАЛ              ЦВЕТ: %s".formatted(player.getNickname(), chessGame.getSide().get(player)));
        }
        for (Player p: chessGame.getPlayers()){
            if (chessGame.getKing(chessGame.getSide().get(p)) == null){
                this.setTitle("ИГРОК %s ПРОИГРАЛ              ЦВЕТ: %s".formatted(player.getNickname(), chessGame.getSide().get(player)));
                playing = false;
            } else {

            }
        }
    }

    private class ChessButtonListener implements ActionListener {
        private int row;
        private int col;


        public ChessButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!playing)
                return;
            if (selected == null){
                if (new ChoiceValidator().setGame(chessGame).setPlayer(chessGame.getPlayers().peek()).isValid(new Position(row, col)))
                    selected = new Position(row, col);
            }
            else {
                Position next = new Position(row, col);
                Move move = new Move(selected, next, TypeMove.COMMON);
                new UpdateGameService().setGame(chessGame).update(move);

                selected = null;

            }
            updateBoard();
        }
    }

    public static void main(String[] args) {
        // Assuming you have initialized players, side, and board for the game
        Game game = new GameFactory().createGame();

        SwingUtilities.invokeLater(() -> new ChessGUI(game).setVisible(true));
    }
}