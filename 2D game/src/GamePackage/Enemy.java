package GamePackage;

import java.awt.*;

/**
 * Created by женя on 30.04.2015.
 */
public class Enemy {
    //Fields
    private double x;
    private double y;
    private int r;

    private double speed;
    private double dx;
    private double dy;

    private double chanceOfBonus;

    private double health;

    private int type;
    public int rank;

    private Color color;

    //Constructor
    public Enemy(int type, int rank){
        this.type = type;
        this.rank = rank;
        switch (type){
            case(1):
                color = Color.GREEN;
                switch (rank){
                case(1):
                    x=Math.random()*GamePanel.WIDTH;
                    y = 0;
                    speed = 2;

                    health = 2;
                    r = 7;

                    chanceOfBonus = 0.05;

                    double angle = Math.toRadians(Math.random()*360);
                    dx = Math.sin(angle)*speed;
                    dy = Math.cos(angle)*speed;
                break;

                case(2):
                    color = color.darker();
                    x = Math.random()*GamePanel.WIDTH;
                    y = 0;
                    speed = 3;

                    health = 4;
                    r = 10;

                    chanceOfBonus = 0.1;

                    double angle1 = Math.toRadians(Math.random()*360);
                    dx = Math.sin(angle1)*speed;
                    dy = Math.cos(angle1)*speed;
                break;

                case(3):
                    color = color.darker();
                    x = Math.random()*GamePanel.ыTH;
                    y = 0;
                    speed = 5;

                    health = 7;
                    r = 14;

                    chanceOfBonus = 0.15;

                    double angle2 = Math.toRadians(Math.random()*360);
                    dx = Math.sin(angle2)*speed;
                    dy = Math.cos(angle2)*speed;
                break;
                }
            break;

            case(2):
                switch (rank){
                    case(1):
                        color = new Color(255,0,0,220);
                        x=Math.random()*GamePanel.WIDTH;
                        y = 0;
                        speed = 4;

                        health = 8;
                        r = 15;

                        chanceOfBonus = 0.2;

                        double angle = Math.toRadians(Math.random()*360);
                        dx = Math.sin(angle)*speed;
                        dy = Math.cos(angle)*speed;
                        break;

                    case(2):
                        color = new Color(255,0,0,180);
                        x = Math.random()*GamePanel.WIDTH;
                        y = 0;
                        speed = 3;

                        health = 10;
                        r = 16;

                        chanceOfBonus = 0.25;

                        double angle1 = Math.toRadians(Math.random()*360);
                        dx = Math.sin(angle1)*speed;
                        dy = Math.cos(angle1)*speed;
                        break;

                    case(3):
                        color = new Color(255,0,0,170);
                        x = Math.random()*GamePanel.WIDTH;
                        y = 0;
                        speed = 2;

                        health = 15;
                        r = 20;

                        chanceOfBonus = 0.3;

                        double angle2 = Math.toRadians(Math.random()*360);
                        dx = Math.sin(angle2)*speed;
                        dy = Math.cos(angle2)*speed;
                        break;
                }
                break;
        }
    }

    //Functions
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getR(){
        return r;
    }

    public boolean remove(){
        if (health <= 0 ) return true;
        return false;
    }

    public void hit(){
        health--;
    }

    public void update(){
        if(x < 0 && dx <0) dx = -dx;
        if (x > GamePanel.WIDTH && dx > 0) dx = -dx;

        if(y < 0 && dy < 0) dy = -dy;
        if (y > GamePanel.HEIGHT && dy > 0) dy = -dy;

        x += dx;
        y += dy;
    }

    public double getChanceOfBonus(){
        return chanceOfBonus;
    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval((int) (x-r), (int) (y-r),2*r,2*r);
        g.setStroke(new BasicStroke(3));

        g.setColor(color.darker());

        g.drawOval((int) (x-r), (int) (y-r),2*r,2*r);
        g.setStroke(new BasicStroke(1));
    }

    public void drawSecondRank(Graphics2D g){
        g.setColor(color);
        g.fillOval((int) (x-r), (int) (y-r),2*r,2*r);
    }
}
