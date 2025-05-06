package core;

import impl.User;
import core.world.World;
import core.world.WorldGenerator;
import utils.GraphicMock;

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

    /**
     * Later on add a seed to randomize according to seed, and make it possible to recreate
     * a known world.
     */
    public WumpusWorld(final int size) {
        this.WORLD = new World(new WorldGenerator(size));

    }

    public void setHunter(User hunter) {
        hunter.setPos(0, 0);
        WORLD.place(hunter);
        this.hunter = hunter;
        gridUpdated();
    }


    private void gridUpdated() {
        GraphicMock.print(WORLD);
    }


}


