package core.entity.move;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position setPos(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Position up() {
        return new Position(x, y - 1);
    }

    public Position down() {
        // Ir para baixo é passar para a proxima linha, ou seja, somar 1.
        return new Position(x, y + 1);
    }

    public Position left() {
        return new Position(x - 1, y);
    }

    public Position right() {
        return new Position(x + 1, y);
    }

    /**
     *
     * @param pos Posição a ser verificada.
     * @param WORLD_SIZE Essa size é o tamanho do mundo e não a quantia de elementos em cada linha/coluna
     * @return Retorna true se a posição é válida, do contrário false.
     */
    public static boolean isValid(Position pos, int WORLD_SIZE) {
        if (pos.x < 0 || pos.y < 0){
            return false;
        }
        return pos.x <= WORLD_SIZE - 1 && pos.y <= WORLD_SIZE - 1;
    }

    /**
     * Verficia se a posição dada é valida, se ela está dentro do mundo/plano.
     * @param pos Posição inicial.
     * @param WORLD_SIZE O tamanho do mundo.
     * @return A posição se for válida, do contrário null.
     */
    public static Position getSafePos(Position pos, int WORLD_SIZE) {
        if (Position.isValid(pos, WORLD_SIZE)) {
            return pos;
        }
        return null;
    }

}
