package GamePackage;

import java.awt.*;

/**
 * Created by женя on 01.05.2015.
 */
public class BonusOfSpeed extends Bonuses {

    public BonusOfSpeed(double x, double y) {
        super(x, y);
        bonusEffect = 4;
        color = Color.WHITE;
        timeEffect = 10000;
        kindOfBonus = "Бонус скорости";
    }

    @Override
    public String getKindOfBonus() {
        return kindOfBonus;
    }

    public double getBonusEffect(){
        return bonusEffect;
    }

    
    
}
