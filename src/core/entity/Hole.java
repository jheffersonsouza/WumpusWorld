package core.entity;

import core.BaseEntity;

import java.util.ArrayList;

public class Hole extends BaseEntity {
    @Override
    public boolean isReserved() {
        return true;
    }

    @Override
    public void setupLogic(ArrayList<BaseEntity>[][] WORLD) {
        // Checa se o tile de cima é válido
        // Ele ta verificando se existe uma linha superior a atual
        // talvez eu tenho que ver se nao da um indexoutofbounds
        if (WORLD[y + 1] != null) {
            // precisava de um add to world auqi, mais motivo pra esse world se encapsualr em classe.
            // vou fazer isso agora
        }


    }

    @Override
    public String asString() {
        return "B"; // Buraco
    }


}
