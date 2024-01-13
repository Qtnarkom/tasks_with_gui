package game_logic.services.validator;

import game_logic.model.Game;
import game_logic.model.Player;
import game_logic.model.figure.Figure;
import game_logic.services.move.Position;

public class ChoiceValidator {
    private Game game;
    private Player player;

    public ChoiceValidator() {
    }

    public ChoiceValidator setGame(Game game){
        this.game = game;
        return this;
    }
    public ChoiceValidator setPlayer(Player player){
        this.player = player;
        return this;
    }

    public Game getGame(){
        return game;
    }
    public Player getPlayer(){
        return player;
    }

    public boolean isValid(Position position){

        Figure figure = getGame().getBoard().getFigure(position);

        return figure != null && figure.getColor().equals(
                getGame().getSide().get(getPlayer())
        );

    }



}
