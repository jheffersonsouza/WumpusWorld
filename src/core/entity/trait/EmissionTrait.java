package core.entity.trait;

import core.entity.BaseEntity;
import core.entity.move.Position;
import core.world.World;

import java.util.ArrayList;


public class EmissionTrait {
    private final World world;
    private Position position;
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
        // acho que chamar so uma vez world.remove() ja resolveria, ja que ele itera sobre todos os tile lá e remove a entidade por tipo, nao por objeto.
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
        if (pos != null) {
            entities.add(new EmissionEntity(displayName).setPos(pos));
        }
    }

    public void update(Position newPos) {
        this.position = newPos;
        spawn();
    }
}
