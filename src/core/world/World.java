package core.world;

import core.entity.BaseEntity;
import core.entity.trait.EmissionEntity;
import impl.Void;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            int[] pos = getRandomPosition(e.isReserved());
            e.setPos(pos[0], pos[1]);
            place(e);
            // Possivelmente um problema de dependencia circular.
            e.setupLogic(this);

        });

    }

    public void place(BaseEntity entity) {
        // y = i (Linha)  e x = j (Coluna)
        List<BaseEntity> tile = WORLD[entity.y][entity.x];
        if (tile == null) {
            WORLD[entity.y][entity.x] = new ArrayList<>();
            // Essa recursão é cancelada por ela mesmo já que isso só roda uma vez.
            place(entity);
            return;
        }
        if (!entity.asString().isEmpty()) {
            System.out.println(entity.asString() + " placed at position " + entity.x + ", " + entity.y);
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

    private int[] getRandomPosition(boolean reserved) {
        int row = new Random().nextInt(0, WORLD_SIZE);
        int column = new Random().nextInt(0, WORLD_SIZE);
        // O começo já é reservado para o caçador.
        if (row == 0 && column == 0) {
            return getRandomPosition(reserved);
        }
        // Verifica se no tile já tem uma entidade que nao permite ficar outro la,
        // tipo o buraco já ta la, nao pode colocar o ouro.
        boolean alreadyHasUniqueEntity = WORLD[row][column]
                .stream()
                .anyMatch(BaseEntity::isReserved);
        if (alreadyHasUniqueEntity && reserved) {
            return getRandomPosition(true);
        }

        return new int[]{row, column};
    }

    public int getSize() {
        return WORLD_SIZE;
    }

    public void remove(BaseEntity e) {
        for (ArrayList<BaseEntity>[] row : WORLD) {
            for (ArrayList<BaseEntity> tile : row) {
                tile.remove(e);
            }
        }
    }
}
