package core.entity.trait;

import core.entity.BaseEntity;
import core.entity.move.Position;
import core.world.World;

import java.util.ArrayList;


public class EmissionTrait {
    private final World world;
    private final Position position;
    private final String displayName;

    private final ArrayList<BaseEntity> entities;

    public EmissionTrait(World world, Position pos, String displayName) {
        this.world = world;
        this.position = pos;
        this.displayName = displayName;
        this.entities = new ArrayList<>();
        spawn();
    }

    private void spawn() {
        entities.forEach(world::remove);
        entities.clear();
        addIfValid(new Position(position.up()));
        addIfValid(new Position(position.down()));
        addIfValid(new Position(position.left()));
        addIfValid(new Position(position.right()));
        entities.forEach(world::place);
    }

    private void addIfValid(Position pos) {
        pos = Position.getSafePos(pos, world.getSize());
        if (pos != null){
            entities.add(new EmissionEntity(displayName).setPos(pos));
        }
    }
}
