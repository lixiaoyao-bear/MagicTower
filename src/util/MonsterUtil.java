package util;

import bean.Monster;

import java.util.HashMap;

public class MonsterUtil {

    public static HashMap<Integer, Monster> monster = new HashMap<>();

    static {
        monster.put(40,new Monster("��֮��",35,18,1,1,1));
        monster.put(41,new Monster("��֮��",45,20,2,2,2));
        monster.put(42,new Monster("С����",35,38,3,3,3));
        monster.put(43,new Monster("������ʦ",60,32,8,5,5));
        monster.put(44,new Monster("������",50,42,6,6,6));
        monster.put(45,new Monster("����ʿ��",55,52,12,8,8));
        monster.put(46,new Monster("�м�����",100,180,110,50,50));
        monster.put(47,new Monster("��������",50,48,22,12,12));
        monster.put(48,new Monster("���öӳ�",100,65,15,30,30));
        monster.put(49,new Monster("����",260,85,5,18,18));
        monster.put(50,new Monster("������",60,100,8,12,12));
        monster.put(51,new Monster("�߼���ʦ",100,95,30,22,22));
        monster.put(52,new Monster("��֮��",130,60,3,8,8));
        monster.put(53,new Monster("������ʿ",320,120,15,30,30));
        monster.put(54,new Monster("ʯͷ��",20,100,68,28,28));
        monster.put(55,new Monster("������",1200,180,20,100,100));
        monster.put(56,new Monster("����",320,199,66,144,144));
        monster.put(57,new Monster("˫�ֽ�ʿ",100,680,50,55,55));
        monster.put(58,new Monster("��սʿ",220,180,30,35,35));
        monster.put(59,new Monster("սʿ",210,200,65,45,45));
        monster.put(60,new Monster("��ʿ",160,230,105,65,65));
        monster.put(61,new Monster("ħ��",1500,600,250,800,800));
        monster.put(62,new Monster("��ʿ�ӳ�",120,150,50,100,100));
        monster.put(63,new Monster("�߼���ʦ",200,380,130,90,90));
        monster.put(64,new Monster("������ʦ",220,370,110,80,80));
        monster.put(65,new Monster("��Ѫ����",200,390,90,50,50));
        monster.put(66,new Monster("����",360,310,20,40,40));
        monster.put(67,new Monster("ħ������",230,450,100,100,100));
        monster.put(68,new Monster("�ڰ���ʿ",180,430,210,120,120));
        monster.put(69,new Monster("���öӳ�",100,65,15,30,30));
        monster.put(70,new Monster("��Ѫ��",444,199,66,144,144));
        monster.put(71,new Monster("�߼�����",180,460,360,200,200));
        monster.put(72,new Monster("��ʦ",4500,560,310,1000,1000));
        monster.put(77,new Monster("ħ��",5000,1580,190,500,500));
        monster.put(78,new Monster("ħ��",800,500,100,500,500));
        monster.put(79,new Monster("ħ��",8000,5000,1000,500,500));
        /*monster.put(200,new Monster("������",50,42,6,6,6));
        monster.put(201,new Monster("����ʿ��",55,52,12,8,8));
        monster.put(202,new Monster("���öӳ�",100,65,15,30,30));*/
    }
}
