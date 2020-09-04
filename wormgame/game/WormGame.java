package wormgame.game;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;
 
public class WormGame extends Timer implements ActionListener {
 
    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Apple apple;
    private Worm worm;
 
    public WormGame(int width, int height) {
        super(1000, null);
        this.width = width;
        this.height = height;
        this.continues = true;

        // the worm(snake) will start in the middle of the Jframe
        worm=new Worm(width/2,height/2,Direction.DOWN);
        int apple_w=new Random().nextInt(width);
        int apple_h=new Random().nextInt(height);
        apple=new Apple(apple_w,apple_h);
        // making sure the apple doens't spawn where the snake is currently placed
        while(apple_w==worm.getX()&&apple_h==worm.getY()){
            apple_w=new Random().nextInt(width);
            apple_h=new Random().nextInt(height);
            apple=new Apple(apple_w,apple_h);
        }
        addActionListener(this);
        setInitialDelay(500);
 
    }
 
    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }
 
    public int getHeight() {
        return height;
    }
 
    public int getWidth() {
        return width;
    }
 
    @Override
    public void actionPerformed(ActionEvent ae) {
        int app_w;
        int app_h;
        if (!continues) {
            return;
        }else {
            worm.move();
            if(worm.runsInto(apple)){
                worm.grow();
                //making sure the apple doens't spawn where the snake is currently placed
                do{
                 app_w=new Random().nextInt(width);
                 app_h=new Random().nextInt(height);
                 apple=new Apple(app_w,app_h);
                }while(app_w==worm.getX()&&app_h==worm.getY());
            }
            if(worm.runsIntoItself()){
                continues=false;
            }
            // checking if the head runs into a wall
            Piece head=worm.getPieces().get(worm.getLength()-1);
            if(head.getX()==0||head.getY()==0||head.getX()==width||head.getY()==height)
                continues=false;
            updatable.update();
            setDelay(1000 / worm.getLength());
           
        }
 
    }
    
    public Worm getWorm(){
        return worm;
    }
    
    public Apple getApple(){
        return apple;
    }
 
}
