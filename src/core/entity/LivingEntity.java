package core.entity;

import core.entity.move.MovementStrategy;
import core.world.World;
import impl.movement.DefaultMovementStrategy;

public abstract class LivingEntity extends BaseEntity {
    //TODO: Esse sistema de parar o jogo por morte ou por ganhar ta feio viu.
    private boolean shouldMove; // Nem sempre uma entidade viva deve se mover.
    private boolean alive;
    private DeathReason deathReason;
    private MovementStrategy movementStrategy;

    public LivingEntity() {
        this.alive = true;
        this.shouldMove = false;
        this.deathReason = null;
        this.movementStrategy = new DefaultMovementStrategy();
    }

    public LivingEntity setBehavior(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
        return this;
    }

    public void nextMove(World world) {
        if (shouldMove()) movementStrategy.move(this, world);
    }

    public void kill(DeathReason reason) {
        this.alive = false;
        this.deathReason = reason;
    }

    public boolean shouldMove() {
        if (!alive) {
            return false;
        }
        return shouldMove;
    }

    public void shouldMove(boolean shouldMove) {
        this.shouldMove = shouldMove;
    }

    public boolean isAlive() {
        return alive;
    }

    public DeathReason getDeathReason() {
        return deathReason;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

}
