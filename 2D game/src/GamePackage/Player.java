package GamePackage;

import java.awt.*;

/**
 * Created by женя on 30.04.2015.
 */
public class Player {

    //Fields
    private double x;
    private double y;
    private int r;

    private int speed;
    private double health;

    private double dx; //коэф смещения
    private double dy;

    private Color color1;

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean isFiring;

    //Constructor
    public Player(){
        x = GamePanel.WIDTH/2;
        y = GamePanel.HEIGHT/2;
        r = 5;

        speed=5;
        dx = 0;
        dy = 0;
        health = 15;

        color1 = Color.white;

        up = false;
        down = false;
        left = false;
        right = false;
        isFiring = false;
    }

    //Functions
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getR() {
        return r;
    }

    public void bonusEffect(double bonusEffect, String kindOfBonus){
        if (kindOfBonus.equals("Бонус скорости")) {
          speed += bonusEffect;
        }

        if (kindOfBonus.equals("Здоровье")) {
            health += bonusEffect;
        }

        System.out.println("bonus on");
    }

    public void removeBonusEffect(double bonusEffect, String kindOfBonus){
        if (kindOfBonus.equals("Бонус скорости")) {
            speed -= bonusEffect;
        }

        if (kindOfBonus.equals("Здоровье")) {
            health -= bonusEffect;
        }

        System.out.println("bonus off");
    }

    public boolean gameOver(){
        if (health <= 0) return true;
        return false;
    }

    public void hit(){
        health--;
        System.out.println(health);
    }

    public void update(){
        if (up && y > r){
            dy = -speed;
        }

        if (down && y < GamePanel.HEIGHT-r){
            dy = speed;
        }

        if (left && x > r){
           dx = -speed;
        }

        if (right && x < GamePanel.WIDTH - r){
            dx = speed;
        }

        if(up && left || up && right || down && left || down && right){
            double angle = Math.toRadians(45);
            dy = dy * Math.sin(angle);
            dx = dx * Math.cos(angle);
        }

        y += dy;
        x += dx;

        dx=0;
        dy=0;


        if (isFiring ){
                GamePanel.bullets.add(new Bullet());
                Sound.playSound("sound/gun13.wav");
        }
    }


    public void draw(Graphics2D g){
        g.setColor(color1);
        g.fillOval((int) x-r,(int) y-r, 2*r,2*r );

        g.setStroke(new BasicStroke(3)); //толщина линии
        g.setColor(color1.darker());
        g.drawOval((int) x-r,(int) y-r, 2*r,2*r);
        g.setStroke(new BasicStroke(1));

    }
}
