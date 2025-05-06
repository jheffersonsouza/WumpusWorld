package impl;

import core.entity.BaseEntity;
import core.entity.trait.EmissionTrait;
import core.world.World;

public class Gold extends BaseEntity {
    @Override
    public boolean isReserved() {
        return true;
    }
    @Override
    public void setupLogic(World world) {
        // r é Brilho kkkkkkkkkkk ta fogo usar um caractere só.
        new EmissionTrait(world, this, "r");
    }
    @Override
    public String asString() {
        return "\uD83D\uDCB0";
    }


}
