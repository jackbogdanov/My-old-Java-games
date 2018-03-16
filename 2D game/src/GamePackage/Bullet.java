package GamePackage;

import java.awt.*;

/**
 * Created by женя on 30.04.2015.
 */
public class Bullet {
    //Fields
    private double x;
    private double y;
    private int r;

    private Color color;
    private int speed;

    //Constructor
    public Bullet(){
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
        r = 2;
        speed=10;

        color = Color.WHITE;
    }

    //Functions
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getR() {return r;}

    public boolean remove(){
        if (y<0){
            return true;
        }
        return false;
    }

    public void update(){
        y -= speed;
    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval((int) x,(int) y,r,2*r);

    }
}
