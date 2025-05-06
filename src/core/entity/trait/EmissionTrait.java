package core.entity.trait;

import core.entity.BaseEntity;
import core.entity.move.Position;
import core.world.World;

import java.util.ArrayList;


public class EmissionTrait {
    private World world;
    private Position position;
    private String displayName;

    private ArrayList<BaseEntity> entities;

    public EmissionTrait(World world, Position pos, String displayName) {
        this.world = world;
        this.position = pos;
        this.displayName = displayName;
        this.entities = new ArrayList<>();
        spawn();
    }

    private void spawn() {
        entities.forEach(e -> {
            world.remove(e);
        });
        entities.clear();
        addIfValid(position.up());
        addIfValid(position.down());
        addIfValid(position.left());
        addIfValid(position.right());
        entities.forEach(world::place);
    }

    private void addIfValid(Position pos) {
        pos = Position.getSafePos(pos, world.getSize());
        if (pos != null) entities.add(new EmissionEntity(displayName).setPos(pos));
    }
}
