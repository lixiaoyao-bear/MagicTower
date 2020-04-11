package bean;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Hero {
    private int level;
    private int hp;
    private int money;
    private int attack;
    private int defense;
    private int exp;
    private int yKey;
    private int bKey;
    private int rKey;

    public Hero(){
        level = 1;
        hp = 1000;
        money = 0;
        attack = 100;
        defense = 100;
        exp = 0;
        yKey = 0;
        bKey = 0;
        rKey = 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getYKey() {
        return yKey;
    }

    public void setYKey(int yKey) {
        this.yKey = yKey;
    }

    public int getBKey() {
        return bKey;
    }

    public void setBKey(int bKey) {
        this.bKey = bKey;
    }

    public int getRKey() {
        return rKey;
    }

    public void setRKey(int rKey) {
        this.rKey = rKey;
    }

    /*private Map<String, Integer[2]> bundsMap = new HashMap<String, Integer[2]>(30);

    static {
        bundsMap.put("level", {1, 50});
    }*/


    public boolean isOutOfBunds() {
        Field[] fields = Hero.class.getFields();
        for (Field field : fields) {

        }
        return false;
    }
}
