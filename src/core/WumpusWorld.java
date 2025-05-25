package core;

import core.world.World;
import core.world.WorldGenerator;
import impl.entities.User;

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
    public World WORLD;
    private User hunter;


    public WumpusWorld(int size) {
        this.WORLD = new World(new WorldGenerator(new Random().nextLong(), size));

    }

    public WumpusWorld(long seed, int size) {
        this.WORLD = new World(new WorldGenerator(seed, size));
    }


    public void setHunter(User hunter) {
        hunter.setPos(0, 0);
        WORLD.place(hunter);
        this.hunter = hunter;
        // Posição inicial.
        WORLD.gridUpdated();
        WORLD.startGameTick(hunter);
    }


}


