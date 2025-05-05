import core.WumpusWorld;
import build.User;

public class Main {
    public static void main(String[] args) {
        WumpusWorld wumpusWorld = new WumpusWorld(5);
        User antonio = new User();
        antonio.setPoints(10); // Ele é vip já começa com pontos kkkkk
        // TODO: Fazer um sistema de input manual e o que ele vai automatico (sem IA).
        // TODO: Integra IA de verdade para pensar a movimentação
        //antonio.setGoal(new ManInput());

        wumpusWorld.setHunter(antonio);


    }
}