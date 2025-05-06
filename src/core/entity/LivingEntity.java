package core.entity;

import core.world.World;
import impl.movement.DefaultMovementStrategy;
import core.entity.move.MovementStrategy;

public abstract class LivingEntity extends BaseEntity {
    private MovementStrategy movementStrategy;

    public LivingEntity() {
        this.movementStrategy = new DefaultMovementStrategy();
    }

    public void setBehavior(World world, MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
        boolean response = true;
        while (response){
            response = movementStrategy.determineNextAction(this, world);
        }

    }
}
