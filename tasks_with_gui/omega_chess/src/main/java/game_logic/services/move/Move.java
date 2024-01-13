package game_logic.services.move;

import java.util.Objects;

public class Move {
    private Position pos1;
    private Position pos2;
    private TypeMove type;

    public Move(Position pos1, Position pos2, TypeMove type) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.type = type;
    }

    public Position getPos1() {
        return pos1;
    }

    public void setPos1(Position pos1) {
        this.pos1 = pos1;
    }

    public Position getPos2() {
        return pos2;
    }

    public void setPos2(Position pos2) {
        this.pos2 = pos2;
    }

    public TypeMove getType() {
        return type;
    }

    public void setType(TypeMove type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Move{" +
                "pos1=" + pos1 +
                ", pos2=" + pos2 +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(pos1, move.pos1) && Objects.equals(pos2, move.pos2) && type == move.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos1, pos2, type);
    }
}
