package core.entity;

import core.BaseEntity;

public class User implements BaseEntity {
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
    public void setupLogic() {

    }

    @Override
    public String asString() {
        return "H";
    }
}
