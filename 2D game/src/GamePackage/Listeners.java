package GamePackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by женя on 30.04.2015.
 */
public class Listeners implements KeyListener {


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            Player.up = true;
        }

        if(key == KeyEvent.VK_S){
            Player.down = true;
        }

        if(key == KeyEvent.VK_A){
            Player.left = true;
        }

        if(key == KeyEvent.VK_D){
            Player.right = true;
        }

        if(key == KeyEvent.VK_SPACE){
            Player.isFiring = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            Player.up = false;
        }

        if(key == KeyEvent.VK_S){
            Player.down = false;
        }

        if(key == KeyEvent.VK_A){
            Player.left = false;
        }

        if(key == KeyEvent.VK_D){
            Player.right = false;
        }

        if(key == KeyEvent.VK_SPACE){
            Player.isFiring = false;
        }
    }



    public void keyTyped(KeyEvent e) {}
}
