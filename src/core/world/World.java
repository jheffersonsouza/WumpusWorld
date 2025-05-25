package core.world;

import core.entity.BaseEntity;
import core.entity.LivingEntity;
import core.entity.move.Position;
import core.entity.trait.EmissionEntity;
import impl.entities.Monster;
import impl.entities.User;
import impl.entities.Void;
import utils.GraphicMock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

        /*
         // DEBUG
         if (!entity.asString().isEmpty()) {
         System.out.println(entity.asString() + " placed at position " + entity.POS.x + ", " + entity.POS.y);
         }
         */
        if (tile.size() == 1 && tile.getFirst() instanceof Void) {
            tile.clear();
            tile.add(entity);
            return;
        }
        tile.stream()
                .filter(t -> !(t instanceof Void) && !(t instanceof EmissionEntity))
                .forEach(t -> {
                    t.colision(entity);
                });
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
            // verificação hardcoded pra movimentar as caracteristica que sao persistentes
            if (entity instanceof Monster){
                Monster m = (Monster) entity;
                m.getEmissionTrait().update(newPos);
            }
            if (entity instanceof User hunter) {
                hunter.addPoints(-1);
            }

        }
        // TODO: Later, listen to the else, to know when IA is moving wrong.
    }

    public int getSize() {
        return WORLD_SIZE;
    }

    public void gridUpdated() {
        GraphicMock.print(this);
        //System.out.println("Pontos: " + hunter.getPoints());

    }

    public void startGameTick(User hunter) {
        System.out.println("Iniciando IA...");
        for (int i = 0; i < WORLD_SIZE; i++) {
            for (int j = 0; j < WORLD_SIZE; j++) {
                WORLD[i][j].stream()
                        .filter(t -> t instanceof LivingEntity)
                        .forEach(e -> {
                            ((LivingEntity) e).shouldMove(true);
                        });
            }
        }
        /**
         * Tick strategy:
         * 1. O caçador se move.
         * 2. Os outros monstros/living entities se movem
         * 3. Checa condições de encerramento.
         * 4. Mostra a grid atualizada.
         */
        // TODO: Talvez cria uma lista de living entities depois, pra nao ficar iterando todo tempo
        while (true) {
            hunter.nextMove(this);
            // Verificação da condição de vitória. Pois, nesse caso não teria mais pq aguardar o monstro se mover.
            if (hunter.obj && hunter.POS.y == 0 && hunter.POS.x == 0) {
                // O cara cumpriu o objetivo e foi colocado no (0,0). Ganhou.
                hunter.win();
                // O metodo win vai ser abstrato no futuro.
                break;
            }
            List<LivingEntity> livingEntities = new ArrayList<>();
            for (int i = 0; i < WORLD_SIZE; i++) {
                for (int j = 0; j < WORLD_SIZE; j++) {
                    WORLD[i][j].stream()
                            .filter(t -> t instanceof LivingEntity)
                            .filter(t -> !(t instanceof User))
                            .forEach(le -> livingEntities.add((LivingEntity) le));
                }
            }

            livingEntities.forEach(e -> e.nextMove(this));

            gridUpdated();
            // Verificando condição de derrota, pois o monstro pode ter alcançado o jogador nesse movimento que ele fez.
            if (!hunter.isAlive()) {
                break;
            }

            // Como um manual input sempre tem um delay é de boa nao dar uma espera, mas em caso de IA
            // isso vai ser movimentado insta, então é interessante ter um delay de um segundos.
            if (!hunter.getMovementStrategy().isInputBased()) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        System.out.println("GameTick Ended...");
    }
}
