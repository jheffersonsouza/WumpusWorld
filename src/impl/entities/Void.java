package impl.entities;

import core.entity.BaseEntity;
import core.world.World;

public class Void extends BaseEntity {

    @Override
    public void setupLogic(World WORLD) {

    }

    @Override
    public String asString() {
        return "";
    }

    @Override
    public boolean isReserved() {
        return false;
    }

   //Unused
    @Override
    public void colision(BaseEntity entry) {

    }
}
