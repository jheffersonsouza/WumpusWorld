package impl.entities;

import core.entity.BaseEntity;
import core.entity.DeathReason;
import core.entity.trait.EmissionTrait;
import core.world.World;

public class Hole extends BaseEntity {
    @Override
    public boolean isReserved() {
        return true;
    }


    @Override
    public void setupLogic(World world) {
        // b é Brisa
        new EmissionTrait(world, POS, "b");
    }

    @Override
    public String asString() {
        return "\uD83D\uDD73"; // Buraco
    }

    /**
     * @param entry Entidade que está colidindo.
     */
    @Override
    public void colision(BaseEntity entry) {
        // Caçador -> Buraco
        if (entry instanceof User hunter) {
            hunter.kill(DeathReason.FALL);
        }
    }


}
