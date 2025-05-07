import impl.movement.TextInputMovementStrategy;
import core.WumpusWorld;
import impl.User;

public class Main {
    public static void main(String[] args) {
        WumpusWorld wumpusWorld = new WumpusWorld(6);
        User antonio = new User();
        antonio.setPoints(10); // Ele é vip já começa com pontos kkkkk

        wumpusWorld.setHunter(antonio);
        // TODO: Fazer um sistema de input manual e o que ele vai automatico (sem IA).
        // TODO: Integra IA de verdade para pensar a movimentação

        antonio.setBehavior(wumpusWorld.WORLD, new TextInputMovementStrategy());
    }
}