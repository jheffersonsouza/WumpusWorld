package build;

import core.entity.BaseEntity;
import core.entity.ExpositionTrait;
import core.world.World;

public class Monster extends BaseEntity {
    @Override
    public boolean isReserved() {
        return false;
    }

    @Override
    public void setupLogic(World world) {
        // c é Catinga
        new ExpositionTrait(world, this, "c");
    }

    @Override
    public String asString() {
        return "\uD83D\uDC79";
    }


}
