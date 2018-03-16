package GamePackage;

/**
 * Created by женя on 02.05.2015.
 */
public class BonusEffect {
    //Field
    private double bonusEffect;
    private long timeEffect;
    private String kindOfBonus;
    private long bonusTimer;
    public long diff;

    //Constructor
    public BonusEffect(double bonusEffect, long timeEffect, String kindOfBonus){
        this.bonusEffect = bonusEffect;
        this.kindOfBonus = kindOfBonus;
        this.timeEffect = timeEffect;
        bonusTimer = System.nanoTime();
        diff = 0;
    }

    //Functions
    public boolean remove(){
        if (timeEffect == 0) return false;
        if (bonusTimer > 0){
            diff += (System.nanoTime() - bonusTimer)/1000000; //перевод в милисекунды
            bonusTimer = System.nanoTime();
        }

        if (diff > timeEffect){
            bonusTimer = 0;
            diff = 0;
            return true;
        }
        return false;
    }

    public double getBonusEffect(){
        return bonusEffect;
    }

    public String getKindOfBonus(){
        return kindOfBonus;
    }

    public void draw(){

    }
}
