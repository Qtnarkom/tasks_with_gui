package game_logic.services.behaviour.sliding;


public class RookMoveService extends LongSlideMoveService {

    @Override
    public int[][] getSteps() {
        return new int[][] {
                {0, 1}, {1, 0},
                {0, -1}, {-1, 0}
        };
    }
}
