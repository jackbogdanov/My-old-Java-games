package GamePackage;

import javax.swing.*;

/**
 * Created by женя on 30.04.2015.
 */
public class GameStart {
    public static void main(String[] args) {
        GamePanel panel  = new GamePanel();
        JFrame startFrame =new JFrame("Shooter");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.setLocationRelativeTo(null); //рамка по центру
        startFrame.pack();
        startFrame.setVisible(true);
        panel.start();
    }
}
