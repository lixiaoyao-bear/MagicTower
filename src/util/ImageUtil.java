package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil {

    public static Map<Integer,Image[]> monsterMap = new HashMap<Integer,Image[]>();
    public static HashMap<Integer,Image[]> heroMap = new HashMap<>();
    public static HashMap<Integer,Image[]> doorMap = new HashMap<>();
    public static HashMap<Integer,Image[]> eventMap = new HashMap<>();
    public static HashMap<Integer,Image> otherMap = new HashMap<>();
    public static HashMap<Integer,Image> msgMap = new HashMap<>();
    public static HashMap<Integer,Image[]> npcMap = new HashMap<>();
    public static HashMap<Integer,Image[]> treasureMap = new HashMap<>();

    public static Image[] heroDown = new Image[4];
    public static Image[] heroUp = new Image[4];
    public static Image[] heroLeft = new Image[4];
    public static Image[] heroRight = new Image[4];
    public static Image[] lsslm = new Image[4];
    public static Image[] hsslm = new Image[4];
    public static Image[] klr = new Image[4];
    public static Image[] klsb = new Image[4];
    public static Image[] kldz = new Image[4];
    public static Image[] zjwb = new Image[4];
    public static Image[] sr = new Image[4];
    public static Image[] srws = new Image[4];
    public static Image[] cjwb = new Image[4];
    public static Image[] cjws = new Image[4];
    public static Image[] cjfs = new Image[4];
    public static Image[] slmw = new Image[4];
    public static Image[] xxbf = new Image[4];
    public static Image[] dslm = new Image[4];
    public static Image[] dbf = new Image[4];
    public static Image[] xbf = new Image[4];
    public static Image[] yl = new Image[4];
    public static Image[] zs = new Image[4];
    public static Image[] str = new Image[4];
    public static Image[] qs = new Image[4];
    public static Image[] qsdz = new Image[4];
    public static Image[] gjfs = new Image[4];
    public static Image[] gjws = new Image[4];
    public static Image[] gzs = new Image[4];
    public static Image[] mfjw = new Image[4];
    public static Image[] haqs = new Image[4];
    public static Image[] dwz = new Image[4];
    public static Image[] ml = new Image[4];
    public static Image[] ssjs = new Image[4];
    public static Image[] xxg = new Image[4];
    public static Image[] gjwb = new Image[4];
    public static Image[] dfs = new Image[4];
    public static Image[] mw = new Image[4];

    public static Image[] blueDoor = new Image[4];
    public static Image[] yellowDoor = new Image[4];
    public static Image[] redDoor = new Image[4];
    public static Image[] greenDoor = new Image[4];
    public static Image[] ironDoor = new Image[4];
    public static Image[] wallDis = new Image[4];
    public static Image[] shop = new Image[4];
    public static Image[] twoFloor1 = new Image[49];
    public static Image[] twoFloor2 = new Image[33];
    public static Image[] threeFloor = new Image[51];
    public static Image[] tenFloor1 = new Image[94];
    public static Image[] tenFloor2 = new Image[131];
    public static Image[] fifteenFloor = new Image[50];
    public static Image[] twentyFloor = new Image[67];
    public static Image[] twentyNineFloor = new Image[101];
    public static Image[] thirtyTwoFloor1 = new Image[107];
    public static Image[] thirtyTwoFloor2 = new Image[69];
    public static Image[] thirtyFiveFloor = new Image[37];
    public static Image[] fortyFloor = new Image[150];
    public static Image[] fortyTwoFloor = new Image[103];
    public static Image[] fortyNineFloor = new Image[56];
    public static Image[] fail = new Image[81];

    public static Image[] redMan = new Image[4];
    public static Image[] blueMan = new Image[4];
    public static Image[] thief = new Image[4];
    public static Image[] gz = new Image[4];
    public static Image[] yj = new Image[4];
    public static Image[] xx = new Image[4];
    public static Image[] redManDisappear = new Image[10];
    public static Image[] blueManDisappear = new Image[10];
    public static Image[] wallDisappear = new Image[10];

    public static Image[] dczy = new Image[9];

    public static Image tuBiao;
    public static Image bg;
    public static Image xyx1;
    public static Image xyx2;
    public static Image sz1;
    public static Image sz2;
    public static Image tc1;
    public static Image tc2;
    public static Image blackBg;
    public static Image mb;

    public static Image wall;
    public static Image earth;
    public static Image upFloor;

    //public static List<String> imagePathList = new ArrayList<String>();

    static {
        try{
            //imagePathList.add("");

            for (int i = 0;i < 4;i++){
                heroDown[i] = ImageIO.read(new File("image/Hero/HeroDown/" + i +".png"));
                heroUp[i] = ImageIO.read(new File("image/Hero/HeroUp/" + i +".png"));
                heroRight[i] = ImageIO.read(new File("image/Hero/HeroRight/" + i +".png"));
                heroLeft[i] = ImageIO.read(new File("image/Hero/HeroLeft/" + i +".png"));

                lsslm[i] = ImageIO.read(new File("image/Monster/绿色史莱姆/" + i +".png"));
                hsslm[i] = ImageIO.read(new File("image/Monster/红色史莱姆/" + i +".png"));
                zjwb[i] = ImageIO.read(new File("image/Monster/中级卫兵/" + i +".png"));
                sr[i] = ImageIO.read(new File("image/Monster/兽人/" + i +".png"));
                srws[i] = ImageIO.read(new File("image/Monster/兽人武士/" + i +".png"));
                cjwb[i] = ImageIO.read(new File("image/Monster/初级卫兵/" + i +".png"));
                cjws[i] = ImageIO.read(new File("image/Monster/初级巫师/" + i +".png"));
                cjfs[i] = ImageIO.read(new File("image/Monster/初级法师/" + i +".png"));
                slmw[i] = ImageIO.read(new File("image/Monster/史莱姆王/" + i +".png"));
                xxbf[i] = ImageIO.read(new File("image/Monster/吸血蝙蝠/" + i +".png"));
                dslm[i] = ImageIO.read(new File("image/Monster/大史莱姆/" + i +".png"));
                dbf[i] = ImageIO.read(new File("image/Monster/大蝙蝠/" + i +".png"));
                xbf[i] = ImageIO.read(new File("image/Monster/小蝙蝠/" + i +".png"));
                yl[i] = ImageIO.read(new File("image/Monster/幽灵/" + i +".png"));
                zs[i] = ImageIO.read(new File("image/Monster/战士/" + i +".png"));
                str[i] = ImageIO.read(new File("image/Monster/石头人/" + i +".png"));
                dwz[i] = ImageIO.read(new File("image/Monster/大乌贼/" + i +".png"));
                qs[i] = ImageIO.read(new File("image/Monster/骑士/" + i +".png"));
                ml[i] = ImageIO.read(new File("image/Monster/魔龙/" + i +".png"));
                ssjs[i] = ImageIO.read(new File("image/Monster/双手剑士/" + i +".png"));
                qsdz[i] = ImageIO.read(new File("image/Monster/骑士队长/" + i +".png"));
                klr[i] = ImageIO.read(new File("image/Monster/骷髅人/" + i +".png"));
                klsb[i] = ImageIO.read(new File("image/Monster/骷髅士兵/" + i +".png"));
                kldz[i] = ImageIO.read(new File("image/Monster/骷髅队长/" + i +".png"));
                gjws[i] = ImageIO.read(new File("image/Monster/高级巫师/" + i +".png"));
                gjfs[i] = ImageIO.read(new File("image/Monster/高级法师/" + i +".png"));
                gzs[i] = ImageIO.read(new File("image/Monster/鬼战士/" + i +".png"));
                mfjw[i] = ImageIO.read(new File("image/Monster/魔法警卫/" + i +".png"));
                haqs[i] = ImageIO.read(new File("image/Monster/黑暗骑士/" + i +".png"));
                gjwb[i] = ImageIO.read(new File("image/Monster/高级卫兵/" + i +".png"));
                xxg[i] = ImageIO.read(new File("image/Monster/吸血鬼/" + i +".png"));
                dfs[i] = ImageIO.read(new File("image/Monster/大法师/" + i +".png"));
                mw[i] = ImageIO.read(new File("image/Monster/魔王/" + i +".png"));

                blueDoor[i] = ImageIO.read(new File("image/Land/blue/" + i +".png"));
                yellowDoor[i] = ImageIO.read(new File("image/Land/yellow/" + i +".png"));
                redDoor[i] = ImageIO.read(new File("image/Land/red/" + i +".png"));
                greenDoor[i] = ImageIO.read(new File("image/Land/green/" + i +".png"));
                ironDoor[i] = ImageIO.read(new File("image/Land/iron/" + i +".png"));
                wallDis[i] = ImageIO.read(new File("image/Land/wall/" + i +".png"));
                shop[i] = ImageIO.read(new File("image/Land/shop/" + i +".png"));

                redMan[i] = ImageIO.read(new File("image/npc/红老头/" + i +".png"));
                blueMan[i] = ImageIO.read(new File("image/npc/蓝老头/" + i +".png"));
                thief[i] = ImageIO.read(new File("image/npc/小偷/" + i +".png"));
                gz[i] = ImageIO.read(new File("image/npc/公主/" + i +".png"));
                yj[i] = ImageIO.read(new File("image/npc/岩浆/" + i +".png"));
                xx[i] = ImageIO.read(new File("image/npc/星星/" + i +".png"));
            }

            for(int i = 0;i < 9;i++){
                dczy[i] = ImageIO.read(new File("image/treasure/" + i + ".png"));

            }

            for (int i = 0; i < 10; i++) {
                redManDisappear[i] = ImageIO.read(new File("image/npc/红老头消失/" + i +".png"));
                blueManDisappear[i] = ImageIO.read(new File("image/npc/蓝老头消失/" + i +".png"));
                wallDisappear[i] = ImageIO.read(new File("image/Land/墙消失/" + i +".png"));
            }

            for (int i = 1; i <= 48; i++) {
                twoFloor1[i] = ImageIO.read(new File("image/event/2floor/1/" + i + ".png"));
            }

            for (int i = 1; i <= 32; i++) {
                twoFloor2[i] = ImageIO.read(new File("image/event/2floor/2/" + i + ".png"));
            }

            for (int i = 1; i <= 50; i++) {
                threeFloor[i] = ImageIO.read(new File("image/event/3floor/" + i + ".png"));
            }

            for (int i = 1; i <= 93; i++) {
                tenFloor1[i] = ImageIO.read(new File("image/event/10floor/1/" + i + ".png"));
            }

            for (int i = 1; i <= 130; i++) {
                tenFloor2[i] = ImageIO.read(new File("image/event/10floor/2/" + i + ".png"));
            }

            for (int i = 1; i <= 49; i++) {
                fifteenFloor[i] = ImageIO.read(new File("image/event/15floor/" + i + ".png"));
            }

            for (int i = 1; i <= 66; i++) {
                twentyFloor[i] = ImageIO.read(new File("image/event/20floor/" + i + ".png"));
            }

            for (int i = 1; i <= 80; i++) {
                fail[i] = ImageIO.read(new File("image/event/fail/" + i + ".png"));
            }

            for (int i = 1;i <= 100;i++){
                twentyNineFloor[i] = ImageIO.read(new File("image/event/29floor/" + i + ".png"));
            }

            for (int i = 1;i <= 106;i++){
                thirtyTwoFloor1[i] = ImageIO.read(new File("image/event/32floor/1/" + i + ".png"));
            }

            for (int i = 1;i <= 68;i++){
                thirtyTwoFloor2[i] = ImageIO.read(new File("image/event/32floor/2/" + i + ".png"));
            }

            for (int i = 1;i <= 36;i++){
                thirtyFiveFloor[i] = ImageIO.read(new File("image/event/35floor/" + i + ".png"));
            }

            for (int i = 1;i <= 149;i++){
                fortyFloor[i] = ImageIO.read(new File("image/event/40floor/" + i + ".png"));
            }

            for (int i = 1;i <= 102;i++){
                fortyTwoFloor[i] = ImageIO.read(new File("image/event/42floor/" + i + ".png"));
            }

            for (int i = 1;i <= 55;i++){
                fortyNineFloor[i] = ImageIO.read(new File("image/event/49floor/" + i + ".png"));
            }

            treasureMap.put(0,dczy);

            tuBiao = ImageIO.read(new File("image/other/mo.jpg"));
            bg = ImageIO.read(new File("image/other/开始1.jpg"));
            blackBg = ImageIO.read(new File("image/other/bbg.png"));
            mb = ImageIO.read(new File("image/other/mb.jpg"));
            xyx1 = ImageIO.read(new File("image/other/1.png"));
            sz1 = ImageIO.read(new File("image/other/2.png"));
            tc1 = ImageIO.read(new File("image/other/3.png"));
            xyx2 = ImageIO.read(new File("image/other/11.jpg"));
            sz2 = ImageIO.read(new File("image/other/22.jpg"));
            tc2 = ImageIO.read(new File("image/other/33.jpg"));

            wall = ImageIO.read(new File("image/Land/1.png"));
            earth = ImageIO.read(new File("image/Land/0.png"));
            upFloor = ImageIO.read(new File("image/Land/34.png"));

            heroMap.put(1,heroUp);
            heroMap.put(2,heroDown);
            heroMap.put(3,heroLeft);
            heroMap.put(4,heroRight);

            doorMap.put(2,yellowDoor);
            doorMap.put(3,blueDoor);
            doorMap.put(4,redDoor);
            doorMap.put(5,greenDoor);
            doorMap.put(6,ironDoor);
            doorMap.put(101,yellowDoor);
            doorMap.put(102,blueDoor);
            doorMap.put(103,redDoor);
            doorMap.put(104,greenDoor);
            doorMap.put(105,wallDis);
            doorMap.put(30,shop);
            doorMap.put(117,wallDisappear);

            eventMap.put(0,fail);
            eventMap.put(1, twoFloor1);
            eventMap.put(2, twoFloor2);
            eventMap.put(3,threeFloor);
            eventMap.put(4,tenFloor1);
            eventMap.put(5,tenFloor2);
            eventMap.put(6,fifteenFloor);
            eventMap.put(7,twentyFloor);
            eventMap.put(8,twentyNineFloor);
            eventMap.put(9,thirtyTwoFloor1);
            eventMap.put(10,thirtyTwoFloor2);
            eventMap.put(11, thirtyFiveFloor);
            eventMap.put(12,fortyTwoFloor);
            eventMap.put(13,fortyNineFloor);
            eventMap.put(14,fortyFloor);

            otherMap.put(101,xyx1);
            otherMap.put(102,sz1);
            otherMap.put(103,tc1);
            otherMap.put(104,xyx2);
            otherMap.put(105,sz2);
            otherMap.put(106,tc2);
            otherMap.put(107,blackBg);
            otherMap.put(108,mb);
            otherMap.put(109,ImageIO.read(new File("image/other/BattleBg.png")));
            otherMap.put(110,ImageIO.read(new File("image/other/369.png")));
            otherMap.put(111,ImageIO.read(new File("image/other/371.png")));
            otherMap.put(112,ImageIO.read(new File("image/other/602.png")));
            otherMap.put(113,ImageIO.read(new File("image/other/423.png")));
            otherMap.put(114,ImageIO.read(new File("image/other/539.png")));
            otherMap.put(115,earth);
            otherMap.put(116,ImageIO.read(new File("image/other/515.png")));
            otherMap.put(117,wall);
            otherMap.put(118,earth);
            otherMap.put(119,earth);
            otherMap.put(120,earth);
            otherMap.put(121,earth);
            otherMap.put(122,ImageIO.read(new File("image/other/522.png")));
            otherMap.put(123,earth);
            otherMap.put(124,earth);
            otherMap.put(125,earth);
            otherMap.put(126,earth);
            otherMap.put(127,earth);
            otherMap.put(128,earth);
            otherMap.put(129,earth);
            otherMap.put(130,earth);
            otherMap.put(131,earth);
            otherMap.put(132,earth);
            otherMap.put(0,earth);
            otherMap.put(1,wall);
            otherMap.put(2,ImageIO.read(new File("image/equip/2.png")));
            otherMap.put(3,ImageIO.read(new File("image/equip/3.png")));
            otherMap.put(4,ImageIO.read(new File("image/equip/4.png")));
            otherMap.put(5,ImageIO.read(new File("image/equip/5.png")));
            otherMap.put(6,ImageIO.read(new File("image/equip/6.png")));
            otherMap.put(7,ImageIO.read(new File("image/equip/7.png")));
            otherMap.put(8,ImageIO.read(new File("image/equip/8.png")));
            otherMap.put(9,ImageIO.read(new File("image/equip/9.png")));
            otherMap.put(10,ImageIO.read(new File("image/equip/10.png")));
            otherMap.put(11,ImageIO.read(new File("image/equip/11.png")));
            otherMap.put(12,ImageIO.read(new File("image/equip/12.png")));
            otherMap.put(13,ImageIO.read(new File("image/equip/13.png")));
            otherMap.put(15,ImageIO.read(new File("image/equip/15.png")));
            otherMap.put(16,ImageIO.read(new File("image/equip/16.png")));
            otherMap.put(17,ImageIO.read(new File("image/equip/17.png")));
            otherMap.put(18,ImageIO.read(new File("image/equip/18.png")));
            otherMap.put(19,ImageIO.read(new File("image/equip/19.png")));
            otherMap.put(20,ImageIO.read(new File("image/equip/20.png")));
            otherMap.put(21,ImageIO.read(new File("image/equip/21.png")));
            otherMap.put(22,ImageIO.read(new File("image/equip/22.png")));
            otherMap.put(23,ImageIO.read(new File("image/equip/23.png")));
            otherMap.put(24,ImageIO.read(new File("image/equip/24.png")));
            otherMap.put(25,ImageIO.read(new File("image/equip/25.png")));
            otherMap.put(26,ImageIO.read(new File("image/equip/26.png")));
            otherMap.put(29,ImageIO.read(new File("image/equip/29.png")));
            otherMap.put(31,ImageIO.read(new File("image/equip/31.png")));
            otherMap.put(32,ImageIO.read(new File("image/equip/32.png")));
            otherMap.put(33,ImageIO.read(new File("image/equip/33.png")));
            otherMap.put(34,upFloor);
            otherMap.put(35,ImageIO.read(new File("image/Land/35.png")));
            otherMap.put(36,ImageIO.read(new File("image/equip/36.png")));
            otherMap.put(37,ImageIO.read(new File("image/equip/37.png")));
            otherMap.put(38,ImageIO.read(new File("image/equip/38.png")));
            otherMap.put(39,ImageIO.read(new File("image/equip/39.png")));
            otherMap.put(135,ImageIO.read(new File("image/equip/135.png")));
            otherMap.put(136,ImageIO.read(new File("image/equip/136.png")));

            monsterMap.put(40,lsslm);
            monsterMap.put(41,hsslm);
            monsterMap.put(42,xbf);
            monsterMap.put(43,cjfs);
            monsterMap.put(44,klr);
            monsterMap.put(45,klsb);
            monsterMap.put(46,zjwb);
            monsterMap.put(47,cjwb);
            monsterMap.put(48,kldz);
            monsterMap.put(49,sr);
            monsterMap.put(50,dbf);
            monsterMap.put(51,gjfs);
            monsterMap.put(52,dslm);
            monsterMap.put(53,srws);
            monsterMap.put(54,str);
            monsterMap.put(55,dwz);
            monsterMap.put(56,yl);
            monsterMap.put(57,ssjs);
            monsterMap.put(58,gzs);
            monsterMap.put(59,zs);
            monsterMap.put(60,qs);
            monsterMap.put(61,ml);
            monsterMap.put(62,qsdz);
            monsterMap.put(63,gjws);
            monsterMap.put(64,cjws);
            monsterMap.put(65,xxbf);
            monsterMap.put(66,slmw);
            monsterMap.put(67,mfjw);
            monsterMap.put(68,haqs);
            monsterMap.put(69,kldz);
            monsterMap.put(70,xxg);
            monsterMap.put(71,gjwb);
            monsterMap.put(72,dfs);
            monsterMap.put(77,mw);
            monsterMap.put(78,mw);
            monsterMap.put(79,mw);
            monsterMap.put(200,klr);
            monsterMap.put(201,klsb);
            monsterMap.put(202,kldz);

            npcMap.put(81,blueMan);
            npcMap.put(1,blueManDisappear);
            npcMap.put(82,redMan);
            npcMap.put(83,gz);
            npcMap.put(2,redManDisappear);
            npcMap.put(84,thief);
            npcMap.put(3,wallDisappear);
            npcMap.put(85,yj);
            npcMap.put(86,xx);

            for(int i = 1;i <= 8;i++){
                msgMap.put(i,ImageIO.read(new File("image/message/" + i + ".png")));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
