package game_logic.services.behaviour.sliding;

import game_logic.services.behaviour.sliding.LongSlideMoveService;

public class BishopMoveService extends LongSlideMoveService {

    @Override
    public int[][] getSteps() {
        return new int[][] {
                {-1, -1}, {-1, 1},
                {1, -1}, {1, 1}
        };
    }

}
