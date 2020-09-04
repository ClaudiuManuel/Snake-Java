package wormgame.domain;

/**
 * @author calian
 */
public class Piece {
    private int x, y;

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean runsInto(Piece piece) {
        if (x == piece.getX() && y == piece.getY())
            return true;
        return false;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
