package GamePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    //Fields
    public static int WIDTH=400;
    public static int HEIGHT=400;

    //поток
    private Thread thread;

    private BufferedImage image;  //создание холста
    private Graphics2D g;         //кисть

    private  int FPS;
    private double millisToFPS;
    private long timerFPS;
    private int sleepTime;

    public static GameBack background;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static ArrayList<Bonuses> bonuses;
    public static ArrayList<BonusEffect> effects;
    public static Wave wave;

    //Constructor
    public GamePanel(){
        super(); //конструтор JPanel
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(new Listeners());
    }


    //Functions
    public void start(){
        thread = new Thread(this);
        thread.start(); //запуск потока (перехо к методу run();
    }

    public void run() {   //игровой цикл

        FPS = 30;
        millisToFPS = 1000/FPS;
        sleepTime = 0;

        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D)image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //сглаживание

        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        bonuses = new ArrayList<Bonuses>();
        effects = new ArrayList<BonusEffect>();
        wave = new Wave();

        while (!player.gameOver()){     //можно дописать состояния
            timerFPS = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();

            timerFPS = (System.nanoTime()-timerFPS)/1000000;

            //FPS
            if (millisToFPS > timerFPS){
                sleepTime = (int) (millisToFPS - timerFPS);
            }  else sleepTime = 1;
            try {
                thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timerFPS = 0;
            sleepTime = 1;
        }
    }

    public  void  gameUpdate(){ //обновления объектов
        //Background update
        background.update();

        //Player update
        player.update();

        //Bullets update
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            boolean remove =bullets.get(i).remove();
            if(remove){
                bullets.remove(i);
                i--;
            }
        }

        //Enemies update
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }

        //Bullets-enemies collide
        bulletsEnemies_collide();

        //Player-enemy collides
        playerEnemy_collides();

        //Wave update
        wave.update();

        //Bonuses update
        for (int i = 0; i < bonuses.size(); i++) {
            bonuses.get(i).update();
            if(bonuses.get(i).remove()){
                bonuses.remove(i);
                i--;
            }
        }
        //bullets-bonuses collide
        bulletsBonuses_collide();

        //bonuses-player collide
        bonusesPlayer_collide();

        //remove effects
        for (int i = 0; i < effects.size(); i++) {
            if (effects.get(i).remove()){
                player.removeBonusEffect(effects.get(i).getBonusEffect(), effects.get(i).getKindOfBonus());
                effects.remove(i);
                System.out.println("remove");
            }
        }


    }

    public void playerEnemy_collides(){
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();

            double dx=ex-px;
            double dy=ey-py;
            double dist = Math.sqrt(dx*dx+dy*dy);

            if ((int)dist <= e.getR()+player.getR()){
                e.hit();
                player.hit();

                boolean remove = e.remove();
                if (remove) {
                    enemies.remove(i);
                    i--;
                }
            }
        }
    }

    public void bonusesPlayer_collide(){
        for (int i = 0; i < bonuses.size(); i++) {
            Bonuses e = bonuses.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();

            double dx=ex-px;
            double dy=ey-py;
            double dist = Math.sqrt(dx*dx+dy*dy);

            double bonusR = Math.sqrt(e.getHeight() * e.getHeight() + e.getWidth() * e.getWidth());

            if ((int)dist <= bonusR+player.getR()){
                effects.add(new BonusEffect(bonuses.get(i).getBonusEffect(),bonuses.get(i).getTimeEffect(),bonuses.get(i).getKindOfBonus()));
                Sound.playSound("Sound/iron3.wav");
                player.bonusEffect(e.getBonusEffect(),e.getKindOfBonus());
                bonuses.remove(i);
                i--;
            }
        }

    }

    public void bulletsBonuses_collide(){
        for (int i = 0; i < bonuses.size(); i++) {
            Bonuses bonus = bonuses.get(i);
            double bonusX = bonus.getX();
            double bonusY = bonus.getY();
            double bonusR = Math.sqrt(bonus.getHeight() * bonus.getHeight() + bonus.getWidth() * bonus.getWidth());
            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                double bx = b.getX();
                double by = b.getY();

                double dx = bx-bonusX;
                double dy = by - bonusY;
                double dist = Math.sqrt(dx*dx+dy*dy);

                if (dist <= bonusR+b.getR()){
                    bonuses.get(i).hit();
                    bullets.remove(j);
                    if (bonuses.get(i).remove()){
                        bonuses.get(i).remove();
                        i--;
                        break;
                    }
                }
            }
        }
    }

    public void bulletsEnemies_collide(){
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();
            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                double bx = b.getX();
                double by = b.getY();

                double dx=ex-bx;
                double dy=ey-by;

                double dist = Math.sqrt(dx*dx+dy*dy);

                if ((int)dist <= e.getR() + b.getR()){
                    e.hit();
                    bullets.remove(j);
                    j--;
                    boolean remove = e.remove();
                    if (remove){
                        if (Math.random() <= enemies.get(i).getChanceOfBonus()) {
                            if (Math.random() <= 0.5){
                                bonuses.add(new BonusOfSpeed(e.getX(), e.getY()));
                            } else bonuses.add(new BonusOfHealth(e.getX(),e.getY()));

                        }//Добавить шанс выпадения
                        enemies.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
    }

    public void  gameRender(){  //обновления графики
        background.draw(g);

        player.draw(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i).rank != 2) enemies.get(i).draw(g);
            else enemies.get(i).drawSecondRank(g);
        }

        if (wave.showWave()){
            wave.draw(g);
        }

        for (int i = 0; i < bonuses.size(); i++) {
            bonuses.get(i).draw(g);
        }

    }

    private void gameDraw(){  //отрисовка на экран
        Graphics g2 = this.getGraphics();  //присваем всё, что нарисовалось
        g2.drawImage(image,0,0,null);
        g2.dispose(); //удаляем переменную
    }

}
