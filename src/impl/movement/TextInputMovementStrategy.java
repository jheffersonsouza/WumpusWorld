package impl.movement;

import core.entity.LivingEntity;
import core.entity.move.MovementStrategy;
import core.world.World;

import java.util.Scanner;

public class TextInputMovementStrategy implements MovementStrategy {
    private final Scanner input;

    public TextInputMovementStrategy() {
        this.input = new Scanner(System.in);
    }

    @Override
    public boolean isInputBased() {
        return true;
    }

    @Override
    public void move(LivingEntity entity, World world) {
        if (!entity.isAlive()) return;
        System.out.print("Mova-se (W/A/S/D/sair): ");
        String choosed = this.input.nextLine();
        switch (choosed.toUpperCase()) {
            case "W":
                world.move(entity, entity.POS.up());
                break;
            case "S":
                world.move(entity, entity.POS.down());
                break;
            case "A":
                world.move(entity, entity.POS.left());
                break;
            case "D":
                world.move(entity, entity.POS.right());
                break;
            case "SAIR":
                return;
            default:
                System.out.println("Opção inválida.");

        }
    }

}
