package impl.entities;

import core.entity.BaseEntity;
import core.entity.trait.EmissionTrait;
import core.world.World;

// TODO: Fazer o ouro com traços persistentes, e fazer ele ser removido quando ter uma colisão
public class Gold extends BaseEntity {
    @Override
    public boolean isReserved() {
        return true;
    }


    @Override
    public void setupLogic(World world) {
        // r é Brilho kkkkkkkkkkk ta fogo usar um caractere só.
        new EmissionTrait(world, POS, "r");
    }

    @Override
    public String asString() {
        return "\uD83D\uDCB0";
    }

    /**
     * @param entry Entidade que está colidindo.
     */
    @Override
    public void colision(BaseEntity entry) {
        if (entry instanceof User hunter) {
            // TODO: No futuro criar uma HunterObjective pra diferenciar quantia de ouros ou outros objetivos
            // como matar o monstro e etc... Criar uma classe para condição de vitoria.
            hunter.obj = true;
            hunter.addPoints(100);
        }
    }

}
