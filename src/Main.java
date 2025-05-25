import core.WumpusWorld;
import impl.entities.User;
import impl.movement.TextInputMovementStrategy;
import utils.GraphicMock;

public class Main {
    public static void main(String[] args) {
        long seed = Long.parseLong("8215846772878890866"); // apagar dps
        WumpusWorld wumpusWorld = new WumpusWorld( 7);
        User antonio = new User();
        //antonio.setPoints(10);  Se ele for vip já começa com pontos kkkkk

        // TODO: Fazer um sistema de input manual e o que ele vai automatico (sem IA).
        // TODO: Integra IA de verdade para pensar a movimentação
        antonio.setBehavior(new TextInputMovementStrategy());

        wumpusWorld.setHunter(antonio);

        //Isso so vai ser executado quando tiver um break no gametick.
        GraphicMock.end(antonio);

    }
}