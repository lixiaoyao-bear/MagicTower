package bean;

import java.io.Serializable;

public class Monster implements Serializable {
    private String name;
    private int hp;
    private int attack;
    private int defence;
    private int exp;
    private int money;

    public Monster(String name,int hp,int attack,int defence,int exp,int money){
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.exp = exp;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getExp() {
        return exp;
    }

    public int getMoney() {
        return money;
    }
}
