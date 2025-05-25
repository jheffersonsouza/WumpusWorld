package core.world;

import core.entity.BaseEntity;
import impl.entities.Gold;
import impl.entities.Hole;
import impl.entities.Monster;
import impl.movement.DefaultMovementStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenerator {
    public long SEED;
    public int size;
    private final List<BaseEntity> defaultEntities;
    private final Random seededRandom;

    public WorldGenerator(long seed, int size) {
        this.defaultEntities = new ArrayList<>();
        this.SEED = seed;
        this.seededRandom = new Random(SEED);
        this.size = size;
        // Sanitizando a size. futuramente vai ser um user input que determina size.
        if (size < 3 || size > 10) {
            System.out.println("Error: size must be between 3 and 10. Setting to default size (3)");
            this.size = 3;
        }
        System.out.println("Seed: " + SEED);
    }


    public int[] getRandomPosition(ArrayList<BaseEntity>[][] WORLD, boolean reserved) {
        int row = seededRandom.nextInt(0, size);
        int column = seededRandom.nextInt(0, size);
        // O começo já é reservado para o caçador.
        if (row == 0 && column == 0) {
            return getRandomPosition(WORLD, reserved);
        }
        // Verifica se no tile já tem uma entidade que nao permite ficar outro la,
        // tipo o buraco já ta la, nao pode colocar o ouro.
        boolean alreadyHasUniqueEntity = WORLD[row][column]
                .stream()
                .anyMatch(BaseEntity::isReserved);
        if (alreadyHasUniqueEntity && reserved) {
            return getRandomPosition(WORLD, true);
        }

        return new int[]{row, column};
    }

    public WorldGenerator addEntity(BaseEntity entity) {
        defaultEntities.add(entity);
        return this;
    }

    public List<BaseEntity> getDefaultEntities() {
        if (defaultEntities.isEmpty()) {
            defaultEntities.add(new Gold());
            defaultEntities.add(new Monster().setBehavior(new DefaultMovementStrategy()));
            defaultEntities.add(new Hole());
        }
        return defaultEntities;
    }

}