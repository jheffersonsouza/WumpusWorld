package build;

import core.entity.BaseEntity;
import core.world.World;

public class User extends BaseEntity {
    /**
     * Unused on Hunter.
     */
    @Override
    public boolean isReserved() {
        return false;
    }

    private int points;

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

}
