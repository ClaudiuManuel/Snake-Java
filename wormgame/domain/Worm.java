package wormgame.domain;

import java.util.List;
import java.util.ArrayList;

import wormgame.Direction;

/**
 * @author calian
 */
public class Worm {
    private ArrayList<Piece> snake;
    private Direction directie;
    private int x, y;
    private boolean grow = false;

    public Worm(int originalX, int originalY, Direction originalDirection) {
        snake = new ArrayList<Piece>();
        directie = originalDirection;
        snake.add(new Piece(originalX, originalY)); //starting position
        x = originalX;
        y = originalY;

    }

    public Direction getDirection() {
        return directie;
    }

    public void setDirection(Direction dir) {
        directie = dir;
    }

    public int getLength() {
        return snake.size();
    }

    public List<Piece> getPieces() {
        return this.snake;
    }

    public void move() {
        //the snake will grow even if he doesn't eat an apple until he has 3 pieces
        if (this.snake.size() < 3) {
            grow();
        }

        if (this.directie == Direction.UP) {
            this.y--;
        }
        if (this.directie == Direction.DOWN) {
            this.y++;
        }
        if (this.directie == Direction.LEFT) {
            this.x--;
        }
        if (this.directie == Direction.RIGHT) {
            this.x++;
        }

        if (grow) {
            grow = false;
        } else {
            // always removing the last piece from the arraylist before adding the new one which contains the snake's new position(its head)
            this.snake.remove(0);
        }
        this.snake.add(new Piece(x, y));


    }

    public void grow() {
        grow = true;
    }


    public boolean runsInto(Piece piece) {
        try {
            for (Piece current : this.getPieces()) {
                if (current.runsInto(piece))
                    return true;
            }
            return false;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean runsIntoItself() {
        try {
            Piece head = snake.get(this.getLength() - 1);
            // checking if the snake's head happens to run into one of the snake's pieces
            for (int i = 0; i < snake.size() - 1; i++) {
                if (head.runsInto(this.getPieces().get(i)))
                    return true;
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
