package core.entity;

import core.world.World;


public class ExpositionTrait {
    class ExpositionEntity extends BaseEntity {
        private final String name;

        public ExpositionEntity(String name) {
            this.name = name;
        }

        @Override
        public void setupLogic(World WORLD) {

        }

        @Override
        public String asString() {
            return name;
        }

        @Override
        public boolean isReserved() {
            return false;
        }
    }

    // TODO: Toda esse nomenclatura me da nojo, tenho que pensar em nomes melhores.
    public ExpositionTrait(World world, BaseEntity exposer, String name) {
        // Ele ta verificando se existe uma linha superior a atual
        // Aqui os y e x sao invertido pq o x que é horizontal é representado como coluna
        // que é o j. Então primeiro é verificado o y que é a linha.
        // nao to gostadn é muito if e else deve ter maneira mais simples.


        //TODO: Esse codigo da nojo, horrivel, deve ter maneira melhor de fazer isso.
        // Caso for index out of bounds só ignora pq o exposer está em um dos cantos.
        try {
            // Checa se o tile de cima é válido
            if (world.WORLD[exposer.y + 1][exposer.x] != null) {
                world.place(new ExpositionEntity(name).setPos(exposer.x, exposer.y + 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Ignore
        }
        try {
            // Checa se o tile de baixo é válido
            if (world.WORLD[exposer.y - 1][exposer.x] != null) {
                world.place(new ExpositionEntity(name).setPos(exposer.x, exposer.y - 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Ignore
        }
        try {
            // Checa se o tile da direita é válido.
            if (world.WORLD[exposer.y][exposer.x + 1] != null) {
                world.place(new ExpositionEntity(name).setPos(exposer.x + 1, exposer.y));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Ignore
        }
        try {
            // Checa se o tile da esquerda é válido.
            if (world.WORLD[exposer.y][exposer.x - 1] != null) {
                world.place(new ExpositionEntity(name).setPos(exposer.x - 1, exposer.y));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Ignore
        }


    }
}
