package GamePackage;

import java.awt.*;

public class Wave {
    //Fields
    private int waveNumber;

    private long waveTimer;
    private long waveDelay;
    private long waveTimerDiff;

    private int waveMultiplier;

    private String waveText;

    //Constructor
    public Wave(){
        waveNumber = 1;

        waveTimer = 0;
        waveDelay = 5000;//5 секунд
        waveTimerDiff = 0;
        waveMultiplier = 5;

        waveText = " В О Л Н А -";
    }

    //Functions
    public void createEnemies(){
        int enemyCount = waveNumber * waveMultiplier;
        switch (waveNumber){
            case (1):
                    while (enemyCount > 0){
                        int rank = 1;
                        int type = 1;//(int) Math.random()*waveNumber;
                        GamePanel.enemies.add(new Enemy(type,rank));
                        enemyCount -= type*rank;
                    }
            break;

            case (2):
                while (enemyCount > 0){
                    int rank = Math.round((float) Math.random() + 1);
                    int type = Math.round((float) Math.random() + 1);
                    GamePanel.enemies.add(new Enemy(type,rank));
                    enemyCount -= type*rank;
                }
            break;

            case (3):
                while (enemyCount > 0){
                    int rank = Math.round((float) Math.random() + 2);
                    int type = Math.round((float) Math.random() + 1);
                    GamePanel.enemies.add(new Enemy(type,rank));
                    enemyCount -= type*rank;
                }
            break;

            default:
                while (enemyCount > 0){
                    int rank = Math.round((float) Math.random() + 2);
                    int type = Math.round((float) Math.random() + 1);
                    GamePanel.enemies.add(new Enemy(type,rank));
                    enemyCount -= type*rank;
                }
        }

        waveNumber++;
    }

    public void update(){
        if (GamePanel.enemies.size() == 0 && waveTimer == 0){
            waveTimer = System.nanoTime();
        }

        if (waveTimer > 0){
            waveTimerDiff += (System.nanoTime() - waveTimer)/1000000; //перевод в милисекунды
            waveTimer = System.nanoTime();
        }

        if (waveTimerDiff > waveDelay){
            createEnemies();
            waveTimer = 0;
            waveTimerDiff = 0;
        }

    }

    public boolean showWave(){
        if (waveTimer != 0 ){
            return  true;
        } else return false;
    }

    public void draw(Graphics2D g){
        double divider = waveDelay/180;
        double alpha = waveTimerDiff/divider;
        alpha = 255 * Math.sin(Math.toRadians(alpha));
        if (alpha < 0) alpha=0;
        if (alpha > 255) alpha = 255;

        g.setFont(new Font("consolas", Font.PLAIN,20)); //замена размера
        g.setColor(new Color(255, 255, 255, (int) alpha));
        String s = " -  "+waveNumber+"ая" + waveText;
        long length = (int) g.getFontMetrics().getStringBounds(s,g).getWidth(); //длинна текта
        g.drawString(s, GamePanel.WIDTH/2 - (int) length/2, GamePanel.HEIGHT/2);

    }
}
