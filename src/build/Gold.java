package build;

import core.entity.BaseEntity;
import core.entity.ExpositionTrait;
import core.world.World;

public class Gold extends BaseEntity {
    @Override
    public boolean isReserved() {
        return true;
    }
    @Override
    public void setupLogic(World world) {
        // r é Brilho kkkkkkkkkkk ta fogo usar um caractere só.
        new ExpositionTrait(world, this, "r");
    }
    @Override
    public String asString() {
        return "\uD83D\uDCB0";
    }


}
