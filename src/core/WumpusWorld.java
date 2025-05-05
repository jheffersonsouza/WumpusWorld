package core;

import core.entity.Gold;
import core.entity.Hole;
import core.entity.Monster;
import core.entity.User;
import core.entity.Void;
import utils.GraphicMock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * na real  o world é uma lista de baseEntity pq num local so Aij pode ter mais de uma entity
 * tipo ter o brilho do ouro, o buraco e o fedor do monstro
 * etc..., ai casas vazias sao listas .empty()
 * na hora de mostrar é so iterar a lista e fazer o texto tipo Ouro/Fedor/Brisa
 * <p>
 * melhroar a nomenclatura
 * escolher um "design pattern"
 * <p>
 * trocar todos i,j por x,y pra visualizar melhor a situação. é mais facil de entender como plano cartesiano
 */
public class WumpusWorld {
    private WorldGenerator generator;
    public ArrayList<BaseEntity>[][] WORLD;
    public int WORLD_SIZE;
    private User hunter;

    /**
     * Later on add a seed to randomize according to seed, and make it possible to recreate
     * a known world.
     */
    public WumpusWorld(final int size) {
        // Sanitizando a size. futuramente vai ser um user input que determina size.
        // esse classe vai ser iniciada por um handler ou parser ou sei la qual design pattern
        //mas é feito agnostica de console, vai ser mais pra poder ser implementado tanto em
        // um console com ascii ou em um aplicação ou site, vai virar mais uma api.
        if (size < 3 || size > 10) {
            System.out.println("Error: size must be between 3 and 10. Setting to default size (3)");
            return;
        }
        this.generator = new WorldGenerator();
        this.WORLD = new ArrayList[size][size];
        this.WORLD_SIZE = size;

        generator.getDefaultEntities().forEach(e -> {
            int[] pos = getRandomPosition(e, size);
            e.setPos(pos[0], pos[1]);
            addToWorld(e);
            // TODO: Talvez seja interessante armazenar posição logo no abstract
            // to fazendo isso já
            e.setupLogic(WORLD);
            System.out.println(e.asString() + " placed at position " + pos[0] + ", " + pos[1]);

        });

        // Os espaços que nao tem entidade viram espaços Vazios.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                addToWorld(new Void().setPos(i,j));
            }
        }
        // Gerar um local aleatorio entre 0 a 2, (0,1,2)
        // verificar se nao é o [0][0] (loal do caçador já)
        // verificar se ja tem algo nesse local
        // Colocar a entidade nele
    }


    public void setHunter(User hunter) {
        hunter.setPos(0, 0);
        addToWorld(hunter);
        this.hunter = hunter;
        gridUpdated();
    }

    private void addToWorld(BaseEntity entity) {
        List<BaseEntity> tile = WORLD[entity.x][entity.y];
        if (tile == null) {
            WORLD[entity.x][entity.y] = new ArrayList<>();
            addToWorld(entity); // Essa recursão é cancelada por ela mesmo já que isso só roda uma vez.
            return;
        }
        if (tile.size() == 1) {
            if (tile.getFirst() instanceof Void) {
                tile.clear();
                tile.add(entity);
            }
            return;
        }
        // aqui significa que tem ou 0 ou mais de 1 coisa no quadrado já.
        // nao vai verificar se pode ta no tile aqui nao.
        tile.add(entity);
    }

    private void gridUpdated() {
        GraphicMock.print(WORLD, WORLD_SIZE);
    }

    private int[] getRandomPosition(BaseEntity e, int size) {
        int row = new Random().nextInt(0, size);
        int column = new Random().nextInt(0, size);
        // O começo já é reservado para o caçador.
        if (row == 0 && column == 0) {
            return getRandomPosition(e, size);
        }
        // Verifica se no tile já tem uma entidade que nao permite ficar outro la,
        // tipo o buraco já ta la, nao pode colocar o ouro.
        // TODO: Talvez converter a Grid em objeto, e fazer tipo um getTile(x, y)
        boolean alreadyHasUniqueEntity = WORLD[row][column]
                .stream()
                .anyMatch(BaseEntity::isReserved);
        if (alreadyHasUniqueEntity && e.isReserved()) {
            return getRandomPosition(e, size);
        }
        return new int[]{row, column};
    }


}

class WorldGenerator {
    private final List<BaseEntity> defaultEntities = new ArrayList<>();

    public WorldGenerator() {
        defaultEntities.add(new Gold());
        defaultEntities.add(new Monster());
        defaultEntities.add(new Hole());
    }

    public List<BaseEntity> getDefaultEntities() {
        return defaultEntities;
    }

}
