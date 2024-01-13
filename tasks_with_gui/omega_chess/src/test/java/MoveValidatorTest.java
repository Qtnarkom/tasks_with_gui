import game_logic.model.Game;
import game_logic.model.Player;
import game_logic.model.figure.Color;
import game_logic.model.figure.Figure;
import game_logic.model.figure.TypeFigure;
import game_logic.services.behaviour.PawnMoveService;
import game_logic.services.filler.GameFactory;
import game_logic.services.move.Move;
import game_logic.services.move.Position;
import game_logic.services.move.TypeMove;
import game_logic.services.validator.ChoiceValidator;
import game_logic.services.validator.MoveValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveValidatorTest {
    @Test
    void testValidMove() {
        Game game = new GameFactory().createGame();
        Player player = game.getPlayers().peek();
        Figure figure = new Figure(Color.WHITE, TypeFigure.PAWN, new PawnMoveService());

        game.getBoard().setFigure(new Position(2, 0), figure);

        Move move = new Move(new Position(2, 0), new Position(3, 0), TypeMove.COMMON);

        MoveValidator moveValidator = new MoveValidator()
                .setGame(game)
                .setPlayer(player)
                .setMove(move);

        assertTrue(moveValidator.isValid());
    }

    @Test
    void testInvalidMove() {
        Game game = new GameFactory().createGame();
        Player player = game.getPlayers().peek();
        Figure figure = new Figure(Color.WHITE, TypeFigure.PAWN, new PawnMoveService());

        game.getBoard().setFigure(new Position(2, 0), figure);

        Move move = new Move(new Position(2, 0), new Position(5, 0), TypeMove.COMMON);

        MoveValidator moveValidator = new MoveValidator()
                .setGame(game)
                .setPlayer(player)
                .setMove(move);



        assertFalse(moveValidator.isValid());
    }

    @Test
    void testValidChoice() {
        Game game = new GameFactory().createGame();
        Player player = game.getPlayers().peek();
        Figure figure = new Figure(Color.WHITE, TypeFigure.PAWN, new PawnMoveService());

        game.getBoard().setFigure(new Position(2, 0), figure);

        Position choice = new Position(2, 0);

        ChoiceValidator choiceValidator = new ChoiceValidator()
                .setGame(game)
                .setPlayer(player);



        assertTrue(choiceValidator.isValid(choice));
    }
    @Test
    void testInvalidChoice() {
        Game game = new GameFactory().createGame();
        Player player = game.getPlayers().peek();
        Figure figure = new Figure(Color.BLACK, TypeFigure.PAWN, new PawnMoveService());

        game.getBoard().setFigure(new Position(2, 0), figure);


        Position choice = new Position(2, 0);

        ChoiceValidator choiceValidator = new ChoiceValidator()
                .setGame(game)
                .setPlayer(player);



        assertFalse(choiceValidator.isValid(choice));
    }

}
