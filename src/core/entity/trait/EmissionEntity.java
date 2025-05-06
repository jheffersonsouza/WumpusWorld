package core.entity.trait;

import core.entity.BaseEntity;
import core.world.World;

public class EmissionEntity extends BaseEntity {
    private final String name;

    public EmissionEntity(String name) {
        this.name = name;
    }

    @Override
    public void setupLogic(World WORLD) {

    }

    @Override
    public String asString() {
        return name;
    }

    @Override
    public boolean isReserved() {
        return false;
    }

}
