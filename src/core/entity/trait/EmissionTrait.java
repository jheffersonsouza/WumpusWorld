package core.entity.trait;

import core.entity.BaseEntity;
import core.entity.move.Position;
import core.world.World;

import java.util.ArrayList;


public class EmissionTrait {
    private World world;
    private Position position;
    private String displayName;
    private ArrayList<BaseEntity> entities;

    // TODO: Toda esse nomenclatura me da nojo, tenho que pensar em nomes melhores.
    public EmissionTrait(World world, Position pos, String displayName) {
        this.world = world;
        this.position = pos;
        this.displayName = displayName;
        this.entities = new ArrayList<>();

        spawn(pos);
    }

    public void move() {
        entities.forEach(e -> {
            world.remove(e);
            spawn(position);
        });

    }

    private void spawn(Position pos) {
        // Ele ta verificando se existe uma linha superior a atual
        // Aqui os y e x sao invertido pq o x que é horizontal é representado como coluna
        // que é o j. Então primeiro é verificado o y que é a linha.
        // nao to gostadn é muito if e else deve ter maneira mais simples.

        //TODO: Esse codigo da nojo, horrivel, deve ter maneira melhor de fazer isso.
        // Caso for index out of bounds só ignora pq o exposer está em um dos cantos.

        // possivel solução
        // checa se é menor que 0, pq ai ta mt na esquerda e muito em cima
        // e depois checar se o index é maior que o size() pra ver se passou da direita
        // e embaixo
        entities.clear();
        addIfValid(pos.up());
        addIfValid(pos.down());
        addIfValid(pos.left());
        addIfValid(pos.right());
        entities.forEach(world::place);
    }

    private void addIfValid(Position pos) {
        if (Position.isValid(pos, world.getSize())) {
            entities.add(new EmissionEntity(displayName).setPos(pos));
        }
    }
}
