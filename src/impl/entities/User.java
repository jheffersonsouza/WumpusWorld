package impl.entities;

import core.entity.BaseEntity;
import core.entity.DeathReason;
import core.entity.LivingEntity;
import core.world.World;

public class User extends LivingEntity {
    // TODO: Lista de ouros, se for maior igual ao requerido quando ele pisar no
    // (0,0) o obj é setado para true e finaliza o jogo. Por agora uma variável nova vai ser usada.
    // Condição de vitória (pegar um ouro).
    public boolean obj;
    public boolean winned;
    private int points;

    private final int tilesMoved;

    public User() {
        super();
        this.obj = false;
        this.winned = false;
        this.points = 0;

        // TODO: Criar de fato dado estatisticos opcionais
        this.tilesMoved = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public void setupLogic(World WORLD) {

    }

    @Override
    public String asString() {
        return "\uD83C\uDFF9";
    }

    /**
     * @param entry Entidade que está colidindo.
     */
    @Override
    public void colision(BaseEntity entry) {
        if (entry instanceof Monster) {
            this.kill(DeathReason.EATEN);
        }
    }

    /**
     * Unused on Hunter.
     */
    @Override
    public boolean isReserved() {
        return false;
    }

    @Override
    public void kill(DeathReason reason) {
        super.kill(reason);
        this.addPoints(-10000);
    }

    public void win() {
        shouldMove(false);
        winned = true;
    }
    public boolean hasWin(){
        return winned && obj;
    }
}
