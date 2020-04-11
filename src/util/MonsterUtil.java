package util;

import bean.Monster;

import java.util.HashMap;

public class MonsterUtil {

    public static HashMap<Integer, Monster> monster = new HashMap<>();

    static {
        monster.put(40,new Monster("绿之球",35,18,1,1,1));
        monster.put(41,new Monster("红之球",45,20,2,2,2));
        monster.put(42,new Monster("小蝙蝠",35,38,3,3,3));
        monster.put(43,new Monster("初级法师",60,32,8,5,5));
        monster.put(44,new Monster("骷髅人",50,42,6,6,6));
        monster.put(45,new Monster("骷髅士兵",55,52,12,8,8));
        monster.put(46,new Monster("中级卫兵",100,180,110,50,50));
        monster.put(47,new Monster("初级卫兵",50,48,22,12,12));
        monster.put(48,new Monster("骷髅队长",100,65,15,30,30));
        monster.put(49,new Monster("兽人",260,85,5,18,18));
        monster.put(50,new Monster("大蝙蝠",60,100,8,12,12));
        monster.put(51,new Monster("高级法师",100,95,30,22,22));
        monster.put(52,new Monster("灰之球",130,60,3,8,8));
        monster.put(53,new Monster("兽人武士",320,120,15,30,30));
        monster.put(54,new Monster("石头人",20,100,68,28,28));
        monster.put(55,new Monster("大乌贼",1200,180,20,100,100));
        monster.put(56,new Monster("幽灵",320,199,66,144,144));
        monster.put(57,new Monster("双手剑士",100,680,50,55,55));
        monster.put(58,new Monster("鬼战士",220,180,30,35,35));
        monster.put(59,new Monster("战士",210,200,65,45,45));
        monster.put(60,new Monster("骑士",160,230,105,65,65));
        monster.put(61,new Monster("魔龙",1500,600,250,800,800));
        monster.put(62,new Monster("骑士队长",120,150,50,100,100));
        monster.put(63,new Monster("高级巫师",200,380,130,90,90));
        monster.put(64,new Monster("初级巫师",220,370,110,80,80));
        monster.put(65,new Monster("吸血蝙蝠",200,390,90,50,50));
        monster.put(66,new Monster("球王",360,310,20,40,40));
        monster.put(67,new Monster("魔法警卫",230,450,100,100,100));
        monster.put(68,new Monster("黑暗骑士",180,430,210,120,120));
        monster.put(69,new Monster("骷髅队长",100,65,15,30,30));
        monster.put(70,new Monster("吸血鬼",444,199,66,144,144));
        monster.put(71,new Monster("高级卫兵",180,460,360,200,200));
        monster.put(72,new Monster("大法师",4500,560,310,1000,1000));
        monster.put(77,new Monster("魔王",5000,1580,190,500,500));
        monster.put(78,new Monster("魔王",800,500,100,500,500));
        monster.put(79,new Monster("魔王",8000,5000,1000,500,500));
        /*monster.put(200,new Monster("骷髅人",50,42,6,6,6));
        monster.put(201,new Monster("骷髅士兵",55,52,12,8,8));
        monster.put(202,new Monster("骷髅队长",100,65,15,30,30));*/
    }
}
