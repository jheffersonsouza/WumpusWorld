package core.entity;

import core.entity.move.Position;
import core.world.World;

public abstract class BaseEntity {
    private Position POS;

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

}
