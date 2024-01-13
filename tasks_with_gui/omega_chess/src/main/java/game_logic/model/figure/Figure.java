package game_logic.model.figure;

import game_logic.services.behaviour.MoveService;

import java.util.Objects;

public class Figure {

    private Color color;
    private TypeFigure type;

    private MoveService moveService;

    public Figure(Color color, TypeFigure type, MoveService moveService) {
        this.color = color;
        this.type = type;
        this.moveService = moveService;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public TypeFigure getType() {
        return type;
    }

    public void setType(TypeFigure type) {
        this.type = type;
    }

    public MoveService getMoveService() {
        return moveService;
    }

    public void setMoveService(MoveService moveService) {
        this.moveService = moveService;
    }

    public Figure copy(){
        return new Figure(color, type, moveService);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return color == figure.color && type == figure.type && Objects.equals(moveService, figure.moveService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, moveService);
    }

    @Override
    public String toString() {
        return "Figure{" +
                "color=" + color +
                ", type=" + type +
                ", moveService=" + moveService +
                '}';
    }
}
