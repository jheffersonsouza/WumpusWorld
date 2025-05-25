package impl.entities;

import core.entity.BaseEntity;
import core.entity.DeathReason;
import core.entity.LivingEntity;
import core.entity.trait.EmissionEntity;
import core.entity.trait.EmissionTrait;
import core.world.World;

public class Monster extends LivingEntity {
    private EmissionTrait emissionTrait;
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
        this.emissionTrait = new EmissionTrait(world, POS, "c");
    }

    @Override
    public String asString() {
        return "\uD83D\uDC79";
    }

    /**
     * @param entry Entidade que está colidindo.
     */
    @Override
    public void colision(BaseEntity entry) {
        if (entry instanceof User hunter) {
            hunter.kill(DeathReason.EATEN);
        }
    }

    public EmissionTrait getEmissionTrait() {
        return emissionTrait;
    }

}
