package core.entity.move;

import core.world.World;

public interface MovementStrategy {
    void determineNextAction(World world);
}
