package GamePackage;

import java.awt.*;

/**
 * Created by женя on 01.05.2015.
 */
public abstract class Bonuses {
    //Fields
    protected double x;
    protected double y;
    protected int height;
    protected int width;

    protected double speed;
    protected double bonusEffect;
    protected double health;
    protected long timeEffect;
    protected String kindOfBonus;

    protected Color color;
    //Constructor
    public Bonuses(double x, double y){
        this.x =  x;
        this.y = y;
        height = 6;
        width = 6;

        speed = 2;
        health = 10;

        //bonusEffect = 4;

        //color = Color.WHITE;
    }

    //Functions
    //getters
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

    public long getTimeEffect(){
        return timeEffect;
    }

    public abstract String getKindOfBonus();

    public void hit(){
        health--;
    }

    public abstract double getBonusEffect();

    public boolean remove(){
        if (y > GamePanel.HEIGHT || health <= 0) return true;
        return false;
    }

    public void update(){
        y +=speed;
    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect((int) x, (int) y, height, width);
        g.setStroke(new BasicStroke(2));
        g.setColor(color.darker());
        g.drawRect((int)x,(int)y,height,width);
        g.setStroke(new BasicStroke(1));
    }

}
