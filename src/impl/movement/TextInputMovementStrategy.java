package impl.movement;

import core.entity.BaseEntity;
import core.entity.move.MovementStrategy;
import core.entity.move.Position;
import core.world.World;

import java.util.Scanner;

public class TextInputMovementStrategy implements MovementStrategy {
    private Scanner input;

    public TextInputMovementStrategy() {
        this.input = new Scanner(System.in);
    }

    @Override
    public boolean determineNextAction(BaseEntity entity, World world) {
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
                return false;
            default:
                System.out.println("Opção inválida.");

        }
        return true;
    }
}
