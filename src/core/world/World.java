package core.world;

import core.entity.BaseEntity;
import core.entity.move.Position;
import impl.Void;
import utils.GraphicMock;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final int WORLD_SIZE;
    public ArrayList<BaseEntity>[][] WORLD;

    public World(WorldGenerator worldgen) {
        this.WORLD = new ArrayList[worldgen.size][worldgen.size];
        this.WORLD_SIZE = worldgen.size;

        // Por enquanto define todos os espaços como Void.
        for (int i = 0; i < WORLD_SIZE; i++) {
            for (int j = 0; j < WORLD_SIZE; j++) {
                place(new Void().setPos(i, j));
            }
        }

        // Gerar um local aleatorio entre 0 a 2, (0,1,2)
        // verificar se nao é o [0][0] (loal do caçador já)
        // verificar se ja tem algo nesse local
        // Colocar a entidade nele
        worldgen.getDefaultEntities().forEach(e -> {
            int[] pos = worldgen.getRandomPosition(WORLD, e.isReserved());
            e.setPos(pos[0], pos[1]);
            place(e);
            // Possivelmente um problema de dependencia circular.
            e.setupLogic(this);

        });

    }

    public void place(BaseEntity entity) {
        // y = i (Linha)  e x = j (Coluna)
        List<BaseEntity> tile = WORLD[entity.POS.y][entity.POS.x];
        if (tile == null) {
            WORLD[entity.POS.y][entity.POS.x] = new ArrayList<>();
            // Essa recursão é cancelada por ela mesmo já que isso só roda uma vez.
            place(entity);
            return;
        }

        if (!entity.asString().isEmpty()) {
            System.out.println(entity.asString() + " placed at position " + entity.POS.x + ", " + entity.POS.y);
        }
        if (tile.size() == 1 && tile.getFirst() instanceof Void) {
            tile.clear();
            tile.add(entity);
            return;
        }
        // aqui significa que tem ou 0 ou mais de 1 coisa no quadrado já.
        // nao vai verificar se pode ta no tile aqui nao.
        tile.add(entity);
    }

    public void remove(BaseEntity e) {
        for (ArrayList<BaseEntity>[] row : WORLD) {
            for (ArrayList<BaseEntity> tile : row) {
                tile.remove(e);
            }
        }
    }

    public void move(BaseEntity entity, Position newPos) {
        newPos = Position.getSafePos(newPos, WORLD_SIZE);
        if (newPos != null) {
            remove(entity);
            place(entity.setPos(newPos));
            gridUpdated();
        }
        // TODO: Later, listen to the else, to know when IA is moving wrong.
    }

    public int getSize() {
        return WORLD_SIZE;
    }

    public void gridUpdated() {
        GraphicMock.print(this);
    }
}
