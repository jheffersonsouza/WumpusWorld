package impl.movement;

import core.entity.BaseEntity;
import core.entity.move.MovementStrategy;
import core.world.World;

import java.util.Scanner;

public class DefaultMovementStrategy implements MovementStrategy {
    private Scanner input;

    public DefaultMovementStrategy() {
        this.input = new Scanner(System.in);
    }

    @Override
    public boolean determineNextAction(BaseEntity e, World world) {
        return false;
    }
}
