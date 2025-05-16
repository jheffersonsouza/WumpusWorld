package core.entity;

import core.entity.move.Position;
import core.world.World;

public abstract class BaseEntity {
    public Position POS;

    public BaseEntity setPos(int x, int y) {
        POS = new Position(x, y);
        return this;
    }
    public BaseEntity setPos(Position pos) {
        this.POS = pos;
        return this;
    }

    public abstract void setupLogic(World WORLD);

    public abstract String asString();

    public abstract boolean isReserved();

    /**
     * @param entry Entidade que está colidindo.
     */
    // Checar colisões de maneira hardcoded
    public abstract void colision(BaseEntity entry);

}
