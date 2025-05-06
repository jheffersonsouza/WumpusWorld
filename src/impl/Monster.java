package impl;

import core.entity.trait.EmissionTrait;
import core.entity.LivingEntity;
import core.world.World;

public class Monster extends LivingEntity {
    public Monster() {
        super();
    }
    @Override
    public boolean isReserved() {
        return false;
    }

    @Override
    public void setupLogic(World world) {
        // c é Catinga
        new EmissionTrait(world, POS, "c");
    }

    @Override
    public String asString() {
        return "\uD83D\uDC79";
    }


}
