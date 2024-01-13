package game_logic.services.behaviour.jumping;

import game_logic.services.behaviour.jumping.JumpMoveService;

public class KnightMoveService extends JumpMoveService {
    @Override
    public int[][] getSteps() {
        return new int[][] {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };
    }



}
