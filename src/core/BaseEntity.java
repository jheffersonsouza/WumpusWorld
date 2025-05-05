package core;

import java.util.ArrayList;

public abstract class BaseEntity {
    public int x;
    public int y;


    public BaseEntity setPos(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public abstract void setupLogic(ArrayList<BaseEntity>[][] WORLD);
    public abstract String asString();
    public abstract boolean isReserved();

}
