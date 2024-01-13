package game_logic.services.support;

import game_logic.services.move.Position;

public class PositionMath {

    private Position position;

    public PositionMath(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position plus(Position step){
        return new Position(
            step.getRow() + position.getRow(), step.getCol() + position.getCol()
        );
    }
}
