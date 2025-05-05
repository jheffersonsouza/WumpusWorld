package core.world;

import core.entity.BaseEntity;
import build.Gold;
import build.Hole;
import build.Monster;

import java.util.ArrayList;
import java.util.List;

public class WorldGenerator {
    public int size;
    private final List<BaseEntity> defaultEntities = new ArrayList<>();

    public WorldGenerator(int size) {
        //TODO: os comentarios daqui alguams partes sao invalidas ja que isso veio do WumpusWorld.java

        // Sanitizando a size. futuramente vai ser um user input que determina size.
        // esse classe vai ser iniciada por um handler ou parser ou sei la qual design pattern
        //mas é feito agnostica de console, vai ser mais pra poder ser implementado tanto em
        // um console com ascii ou em um aplicação ou site, vai virar mais uma api.
        this.size = size;
        if (size < 3 || size > 10) {
            System.out.println("Error: size must be between 3 and 10. Setting to default size (3)");
            this.size = 3;
        }
        // TODO: Remover esse hardcoded e fazer com que a lista de defualt entities, seja definida
        // pelo usuário.
        defaultEntities.add(new Gold());
        defaultEntities.add(new Monster());
        defaultEntities.add(new Hole());
    }

    public List<BaseEntity> getDefaultEntities() {
        return defaultEntities;
    }

}