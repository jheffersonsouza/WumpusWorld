package impl.movement;

import core.entity.move.MovementStrategy;
import core.world.World;

import java.util.Scanner;

public class DefaultMovementStrategy implements MovementStrategy {
    private Scanner input;

    public DefaultMovementStrategy() {
        this.input = new Scanner(System.in);
    }

    @Override
    public void determineNextAction(World world) {
        System.out.print("Mova-se(W/A/S/D): ");
        String choosed = this.input.nextLine();
        switch (choosed.toUpperCase()) {
            case "W":

                break;
            case "S":

                break;
            case "A":

                break;

            case "D":
                break;
        }
    }
}
