package core.entity.move;

import core.entity.BaseEntity;
import core.world.World;

public interface MovementStrategy {
    boolean determineNextAction(BaseEntity e, World world);
}
