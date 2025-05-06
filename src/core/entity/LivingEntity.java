package core.entity;

import impl.movement.DefaultMovementStrategy;
import core.entity.move.MovementStrategy;

public abstract class LivingEntity extends BaseEntity {
    private MovementStrategy movementStrategy;

    public LivingEntity() {
        this.movementStrategy = new DefaultMovementStrategy();
    }

    public void setBehavior(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
}
