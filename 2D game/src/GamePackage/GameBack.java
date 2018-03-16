package GamePackage;

import java.awt.*;

/**
 * Created by женя on 30.04.2015.
 */
public class GameBack {
    //Fields

    private Color color;

    //Constructor
    public  GameBack(){
        color = color.BLUE;
    }

    //функции
    public  void update(){

    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT);
    }
}
