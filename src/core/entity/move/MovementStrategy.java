package core.entity.move;

import core.entity.LivingEntity;
import core.world.World;

public interface MovementStrategy {

    boolean isInputBased();
    void move(LivingEntity e, World world);

}
