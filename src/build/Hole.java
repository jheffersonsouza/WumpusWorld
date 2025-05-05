package build;

import core.entity.BaseEntity;
import core.entity.ExpositionTrait;
import core.world.World;

public class Hole extends BaseEntity {
    @Override
    public boolean isReserved() {
        return true;
    }

    @Override
    public void setupLogic(World world) {
        // b é Brisa
        new ExpositionTrait(world, this, "b");
    }

    @Override
    public String asString() {
        return "\uD83D\uDD73"; // Buraco
    }


}
