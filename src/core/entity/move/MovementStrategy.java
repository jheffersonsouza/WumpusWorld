package core.entity.move;

import core.entity.LivingEntity;
import core.world.World;

public interface MovementStrategy {

    boolean determineNextAction(LivingEntity e, World world);

}
