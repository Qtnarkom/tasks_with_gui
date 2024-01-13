package game_logic.services.behaviour.jumping;

import game_logic.model.figure.Figure;
import game_logic.services.behaviour.MoveServiceImpl;
import game_logic.services.move.Move;
import game_logic.services.move.Position;
import game_logic.services.move.TypeMove;

import java.util.ArrayList;
import java.util.List;

public class KingMoveService extends JumpMoveService {

    @Override
    public int[][] getSteps() {
        return new int[][] {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1}, {1, 0},  {1, 1}
        };
    }



}
