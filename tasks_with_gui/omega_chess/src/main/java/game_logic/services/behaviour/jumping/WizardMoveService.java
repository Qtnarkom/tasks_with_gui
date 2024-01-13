package game_logic.services.behaviour.jumping;

public class WizardMoveService extends JumpMoveService {

    @Override
    public int[][] getSteps() {
        return new int[][] {
                {3, -1}, {3, 1},
                {-3, -1}, {-3, 1},
                {-1, 3}, {-1, -3},
                {1, 3}, {1, -3},
                {1, 1}, {-1, -1},
                {1, -1}, {-1, 1}
        };
    }




}
