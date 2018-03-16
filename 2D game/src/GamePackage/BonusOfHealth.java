package GamePackage;

import java.awt.*;

/**
 * Created by женя on 02.05.2015.
 */
public class BonusOfHealth extends Bonuses{

    public BonusOfHealth(double x, double y){
        super(x,y);
        bonusEffect = 2;
        color = Color.RED;
        timeEffect = 0;
        kindOfBonus = "Здоровье";
    }

    @Override
    public String getKindOfBonus() {
        return kindOfBonus;
    }

    public double getBonusEffect() {
        return bonusEffect;
    }

}
