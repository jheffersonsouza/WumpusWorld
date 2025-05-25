package impl.movement;

import core.entity.LivingEntity;
import core.entity.move.MovementStrategy;
import core.entity.move.Position;
import core.world.World;
import impl.User;

import java.util.concurrent.atomic.AtomicReference;


public class DefaultMovementStrategy implements MovementStrategy {
    public DefaultMovementStrategy() {

    }

    @Override
    public boolean determineNextAction(LivingEntity e, World world) {
        // Testar se isso funciona, mas basicamente é uma gambiarra para pegar a Pos do Hunter sempre.
        // Que pra ser menos hardcoded eu tenho que determinar target.
        // TODO: Determinar melhor o target e pos.
        AtomicReference<Position> pos = new AtomicReference<>();
        for (int i = 0; i < world.getSize(); i++) {
            for (int j = 0; j < world.getSize(); j++) {
                world.WORLD[i][j]
                        .stream().filter(t -> t instanceof User)
                        .forEach(h -> {
                            pos.set(new Position(h.POS));
                        });
                if (pos.get() != null) break;
            }
            if (pos.get() != null) break;
        }
        System.out.print("Debugging hunter finder ia, Pos: " + pos.get().x + ", " + pos.get().y);
        // Se pa pode ser optimizado, mas deve funcionar desse jeito.
        // É uma "IA" meio burrinha.
        int diffY = pos.get().y - e.POS.y;
        if (diffY > 0) {
            // O target ta mais em baixo do que você, voce tem que descer linha
            // ou seja, aumentar y.
            world.move(e, e.POS.down());
        } else if (diffY < 0) {
            // O target ta em uma linha de cima, voce tem que subir linha, diminuir y.
            world.move(e, e.POS.up());
        } else {
            // Checa por x, pq voces estão na mesma linha.
            int diffX = pos.get().x - e.POS.x;
            if (diffX > 0) {
                // Tenho que ir pra direita.
                world.move(e, e.POS.right());
            } else if (diffX < 0) {
                world.move(e, e.POS.left());
            }
        }
        return true;
    }
}
