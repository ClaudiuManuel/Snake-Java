
package wormgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import wormgame.domain.Apple;
import wormgame.domain.Worm;
import wormgame.game.WormGame;

/**
 *
 * @author calian
 */
public class DrawingBoard extends JPanel implements Updatable{
    private WormGame w_game;
    private int dimension;
    
    public DrawingBoard(WormGame game,int pieceLength){
        w_game=game;
        dimension=pieceLength;
       
    }
    
     @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        Worm worm=w_game.getWorm();
        for(int i=0;i<worm.getLength();i++){
            graphics.fill3DRect(worm.getPieces().get(i).getX()*dimension, worm.getPieces().get(i).getY()*dimension, dimension, dimension, true);
        }
        graphics.setColor(Color.RED);
        Apple helper=w_game.getApple();
        graphics.fillOval(helper.getX()*dimension, helper.getY()*dimension, dimension,dimension );
       
    }
    
   
    public void update(){
        super.repaint();
    }
}
