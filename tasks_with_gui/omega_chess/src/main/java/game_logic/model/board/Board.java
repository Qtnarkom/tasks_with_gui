package game_logic.model.board;

import game_logic.model.figure.Figure;
import game_logic.services.move.Position;

public class Board {
    private Figure[][] board;

    public Board(Figure[][] board) {
        this.board = board;
    }
    public Figure[][] getBoard() {
        return board;
    }
    public void setBoard(Figure[][] board) {
        this.board = board;
    }

    public Figure getFigure(Position position) {
        return getFigure(position.getRow(), position.getCol());
    }

    public Figure getFigure(int row, int col) {
        return board[row][col];
    }

    public int row(){
        return board.length;
    }


    public void setFigure(Position position, Figure figure){
        setFigure(position.getRow(), position.getCol(), figure);
    }

    public void setFigure(int row, int col, Figure figure){
        board[row][col] = figure;
    }
    public int col(){
        return board[0].length;
    }

    public Position findPos(Figure figure){
        for (int i = 0; i < row(); i++) {
            for (int j = 0; j < col(); j++) {
                Figure f = getFigure(i, j);

                if (figure.equals(f))
                    return new Position(i, j);
            }
        }
        return null;
    }
    public Board copy(){
        Figure[][] board = new Figure[row()][col()];
        for (int i = 0; i < row(); i++) {
            for (int j = 0; j < col(); j++) {
                Figure f = getFigure(i, j);
                if(f != null){
                    board[i][j] = f.copy();
                }
            }
        }
        return new Board(board);
    }


}

