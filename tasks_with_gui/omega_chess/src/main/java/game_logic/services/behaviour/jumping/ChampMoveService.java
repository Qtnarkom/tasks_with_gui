package game_logic.services.behaviour.jumping;

import game_logic.services.behaviour.jumping.JumpMoveService;

public class ChampMoveService extends JumpMoveService {

    @Override
    public int[][] getSteps() {
        return new int[][] {
                {-2, 0}, {2, 0},
                {0, -2}, {0, 2},
                {-2, -2}, {-2, 2},
                {2, 2}, {2, -2},
                {-1, 0}, {0, 1}, {0, -1}, {1, 0}
        };
    }




}
