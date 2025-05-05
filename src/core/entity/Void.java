package core.entity;

import core.BaseEntity;

public class Void implements BaseEntity {
    @Override
    public void setupLogic() {

    }

    @Override
    public String asString() {
        return ".";
    }
}
