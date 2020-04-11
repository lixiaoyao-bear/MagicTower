package panel;

import bean.Hero;
import event.*;
import mota.MoTa;
import util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class MainPanel extends JPanel implements KeyListener {

    public static int[][][] map;  //地图信息三维数组
    public static int currentFloor;  //当前楼层
    public static int maxFloor;  //到过最大楼层
    public static Hero hero;  //角色对象
    public int monsterIndex;  //怪物图片下标索引
    public int heroIndex;  //角色图片下标索引
    public int doorIndex;  //开门图片下标索引
    public static int heroDirection;  //角色方向
    public static int heroX;  //角色x坐标
    public static int heroY;  //角色y坐标
    public static boolean isMove;  //角色是否能移动
    public static AttackPanel attackPanel;  //战斗信息面板对象
    public static MsgPanel msgPanel;  //提示信息面板对象
    public static ShopPanel shopPanel;  //商店面板
    public static ThreeFloorEvent threeFloorEvent;   //3层事件内容
    public static TwoFloorEvent twoFloorEvent;   //2层事件内容
    public static TenFloorEvent tenFloorEvent;  //10层事件内容
    public static DialogPanel dialogPanel;    //对话面板
    public static LookMonsterPanel lookMonsterPanel;  //查看怪物信息面板
    public SoundUtil soundUtil;  //音乐播放类
    //public static int count;
    public static int twentyThreeCount;  //23楼隐藏墙体条件数量

    public MainPanel(){
        map = MapUtil.map;
        currentFloor = 1;
        maxFloor = 50;
        soundUtil = new SoundUtil();
        hero = new Hero();
        monsterIndex = 0;
        heroIndex = 0;
        doorIndex = 0;
        //count = 0;
        twentyThreeCount = 0;
        heroDirection = 1;
        isMove = true;
        heroX = MapUtil.heroUpPosition[currentFloor][0];
        heroY = MapUtil.heroUpPosition[currentFloor][1];
        this.setLayout(null);
        new Timer(100,actionEvent -> {
            monsterIndex++;
            if (monsterIndex > 3)
                monsterIndex = 0;
            repaint();
        }).start();
        Thread event = new Thread(new AllFloorEvent());
        event.start();
    }

    /** 主面板组件绘制方法*/
    public void paintComponent(Graphics g){
        g.drawImage(ImageUtil.otherMap.get(107),0,0,416,416,this);
        g.drawImage(ImageUtil.otherMap.get(108),416,0,this);
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                int id = map[currentFloor][i][j];
                if (id == 30){
                    g.drawImage(ImageUtil.doorMap.get(id)[monsterIndex],j * 32, i * 32, this);
                }else if(id < 40){
                    g.drawImage(ImageUtil.otherMap.get(id),j * 32, i * 32, this);
                }else if (id == 55){
                    g.drawImage(ImageUtil.monsterMap.get(55)[monsterIndex],160, 128, this);
                }else if (id == 61){
                    g.drawImage(ImageUtil.monsterMap.get(61)[monsterIndex],160, 160, this);
                }else if(id < 80 || id >=200){
                    g.drawImage(ImageUtil.monsterMap.get(id)[monsterIndex],j * 32, i * 32, this);
                }else if (id == 80){
                    g.drawImage(ImageUtil.heroMap.get(heroDirection)[heroIndex],j * 32, i * 32, this);
                }else if (id < 100) {
                    g.drawImage(ImageUtil.npcMap.get(id)[monsterIndex],j * 32, i * 32, this);
                }else if (id >= 115){
                    g.drawImage(ImageUtil.otherMap.get(id),j * 32, i * 32, this);
                }
            }
        }
        heroMsg(g);
    }

    /** 角色信息面板绘制方法*/
    public void heroMsg(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("宋体",Font.PLAIN,22));
        g.drawString(""+hero.getLevel(),520,68);
        g.drawString(""+hero.getHp(),520,98);
        g.drawString(""+hero.getAttack(),520,121);
        g.drawString(""+hero.getDefense(),520,146);
        g.drawString(""+hero.getMoney(),520,170);
        g.drawString(""+hero.getExp(),520,196);
        g.drawString(""+hero.getYKey(),520,236);
        g.drawString(""+hero.getBKey(),520,271);
        g.drawString(""+hero.getRKey(),520,302);
        g.drawString(""+currentFloor,510,335);
    }

    /** 角色移动中坐标变换方法*/
    public void changeHeroPosition(int x,int y,int dir){
        map[currentFloor][y][x] = 80;
        map[currentFloor][heroY][heroX] = 0;
        heroDirection = dir;
        if (heroIndex == 3)
            heroIndex = 0;
        else
            heroIndex++;
        repaint();
        soundUtil.loadSound("走路.wav",1);
    }

    /** 开门动画方法，2为黄门，3为蓝门，4为红门，5为绿门，6为铁门*/
    public void openDoor(int x,int y,int id){
        JLabel doorLabel = new JLabel();
        final Image[] doorImage = new Image[1];
        final int[] index = {0};
        doorLabel.setBounds(32 * x,32 * y,32,32);
        this.add(doorLabel,2,0);
        isMove = false;
        new Timer(100,actionEvent -> {
            doorImage[0] = ImageUtil.doorMap.get(id)[index[0]];
            doorLabel.setIcon(new ImageIcon(doorImage[0]));
            index[0]++;
            if (index[0] == 1)
                map[currentFloor][y][x] = 0;
            if (index[0] == 4){
                isMove = true;
                this.remove(doorLabel);
                ((Timer)actionEvent.getSource()).stop();
            }
        }).start();
    }

    /**   线程休眠方法*/
    public void threadSleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** 特殊怪物战斗方法,指兽人和兽人武士*/
    public void fightWithSpecialMonster(int id,int x,int y){
        if (TreasureUtil.isHaveHolyShield){
            hero.setAttack(hero.getAttack() * 2);
            attackPanel = new AttackPanel(id, x, y);
            this.add(attackPanel);
            new Thread(() -> {
                while (true){
                    if (AttackPanel.isWin){
                        hero.setAttack(hero.getAttack() / 2);
                        break;
                    }else {
                        threadSleep(100);
                    }
                }
            }).start();
        }else {
            attackPanel = new AttackPanel(id, x, y);
            this.add(attackPanel);
        }
    }

    /** 角色移动方法，返回true表明成功移动，角色坐标变换，false表明移动失败，留在原地*/
    public boolean walk(int x, int y, int dir) {
        switch (map[currentFloor][y][x]) {
            case 0:  //空地
                changeHeroPosition(x, y, dir);
                return true;
            case 2:  //黄门
                if (hero.getYKey() > 0) {
                    soundUtil.loadSound("开门上下楼.wav",1);
                    openDoor(x, y, 2);
                    hero.setYKey(hero.getYKey() - 1);
                }
                break;
            case 3:  //蓝门
                if (hero.getBKey() > 0) {
                    soundUtil.loadSound("开门上下楼.wav",1);
                    openDoor(x, y, 3);
                    hero.setBKey(hero.getBKey() - 1);
                }
                break;
            case 4:  //红门
                if (hero.getRKey() > 0) {
                    soundUtil.loadSound("开门上下楼.wav",1);
                    openDoor(x, y, 4);
                    hero.setRKey(hero.getRKey() - 1);
                }
                break;
            case 7:  //明黄之钥
                changeHeroPosition(x, y, dir);
                hero.setYKey(hero.getYKey() + 1);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个明黄之钥");
                this.add(msgPanel);
                return true;
            case 8:  //深蓝之钥
                changeHeroPosition(x, y, dir);
                hero.setBKey(hero.getBKey() + 1);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个深蓝之钥");
                this.add(msgPanel);
                return true;
            case 9:  //腥红之钥
                changeHeroPosition(x, y, dir);
                hero.setRKey(hero.getRKey() + 1);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个腥红之钥");
                this.add(msgPanel);
                return true;
            case 10:  //红宝石
                changeHeroPosition(x, y, dir);
                int attack = (currentFloor / 10) + 1;
                hero.setAttack(hero.getAttack() + attack);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个红宝石，攻击+" + attack);
                this.add(msgPanel);
                return true;
            case 11:  //蓝宝石
                changeHeroPosition(x, y, dir);
                int defence = (currentFloor / 10) + 1;
                hero.setDefense(hero.getDefense() + defence);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个蓝宝石，防御+" + defence);
                this.add(msgPanel);
                return true;
            case 12:  //小血瓶
                changeHeroPosition(x, y, dir);
                int hp1 = 50 * ((currentFloor / 10) + 1);
                hero.setHp(hero.getHp() + hp1);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个小血瓶，生命+" + hp1);
                this.add(msgPanel);
                return true;
            case 13:  //大血瓶
                changeHeroPosition(x, y, dir);
                int hp2 = 200 * ((currentFloor / 10) + 1);
                hero.setHp(hero.getHp() + hp2);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个大血瓶，生命+" + hp2);
                this.add(msgPanel);
                return true;
            case 15:  //铁剑
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 10);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一把铁剑，攻击+10");
                this.add(msgPanel);
                return true;
            case 16:  //铁盾
                changeHeroPosition(x, y, dir);
                hero.setDefense(hero.getDefense() + 10);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个铁盾，防御+10");
                this.add(msgPanel);
                return true;
            case 17:  //银盾
                changeHeroPosition(x, y, dir);
                hero.setDefense(hero.getDefense() + 20);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个银盾，防御+20");
                this.add(msgPanel);
                return true;
            case 18:  //神圣剑
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 100);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一把神圣剑，攻击+100");
                this.add(msgPanel);
                return true;
            case 19:  //银剑
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 20);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一把银剑，攻击+20");
                this.add(msgPanel);
                return true;
            case 20:  //骑士剑
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 40);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一把骑士剑，攻击+40");
                this.add(msgPanel);
                return true;
            case 21:  //神圣盾
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveHolyShield = true;
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得神圣盾，可以免疫魔法攻击");
                this.add(msgPanel);
                return true;
            case 22:  //圣剑
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 50);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一把圣剑，攻击+50");
                this.add(msgPanel);
                return true;
            case 24:  //圣盾
                changeHeroPosition(x, y, dir);
                hero.setDefense(hero.getDefense() + 50);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得一个圣盾，防御+50");
                this.add(msgPanel);
                return true;
            case 25:  //上楼器
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveUpFloorFly = true;
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得上楼器，可用U键到当前楼层上一层");
                this.add(msgPanel);
                return true;
            case 26:  //飞行器
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveFloorFly = true;
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得飞行器，可用Pg UP/DOWN键进行楼层跳跃");
                this.add(msgPanel);
                return true;
            case 27:  //记事本
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveNotebook = true;
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得记事本，可用N键查看重要对话");
                this.add(msgPanel);
                return true;
            case 28:  //炸弹
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveBomb = true;
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得炸弹，可用B键炸毁周围敌人（头目无效）");
                this.add(msgPanel);
                return true;
            case 30:  //商店
                shopPanel = new ShopPanel();
                this.add(shopPanel);
                return false;
            case 32:  //镐
                changeHeroPosition(x, y, dir);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得镐，可用P键破坏周围墙体");
                this.add(msgPanel);
                return true;
            case 34:  //上楼
                map[currentFloor][heroY][heroX] = 0;
                currentFloor++;
                soundUtil.loadSound("开门上下楼.wav",1);
                if (currentFloor == 44)  //44楼无法直接上去，跳过
                    currentFloor++;
                if (currentFloor > maxFloor)  //记录到达的最大楼层
                    maxFloor = currentFloor;
                heroX = MapUtil.heroUpPosition[currentFloor][0];
                heroY = MapUtil.heroUpPosition[currentFloor][1];
                map[currentFloor][heroY][heroX] = 80;
                repaint();
                return false;
            case 35:  //下楼
                map[currentFloor][heroY][heroX] = 0;
                currentFloor--;
                if (currentFloor == 44)  //44楼无法直接上去，跳过
                    currentFloor--;
                heroX = MapUtil.heroDownPosition[currentFloor][0];
                heroY = MapUtil.heroDownPosition[currentFloor][1];
                map[currentFloor][heroY][heroX] = 80;
                repaint();
                return false;
            case 36:  //瞬移
                changeHeroPosition(x, y, dir);
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得瞬移，可3次使用J键到按中心对称的空地");
                this.add(msgPanel);
                TreasureUtil.isHaveTeleport = true;
                return true;
            case 37:  //下楼器
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveDownFloorFly = true;
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得下楼器，可用D键到达当前楼层下一层");
                this.add(msgPanel);
                return true;
            case 38:  //雪花
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveSnowflake = true;
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得雪花，可用I键将熔岩冻为平地");
                this.add(msgPanel);
                return true;
            case 39:  //魔法钥匙
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveMagicKey = true;
                soundUtil.loadSound("获得物品.wav",1);
                msgPanel = new MsgPanel("获得魔法钥匙，可用K键打开一层楼所有的黄色门");
                this.add(msgPanel);
                return true;
            case 49:  //兽人
                fightWithSpecialMonster(49,x,y);
                return false;
            case 53:  //兽人武士
                fightWithSpecialMonster(53,x,y);
                return false;
            case 55:  //大乌贼
                attackPanel = new AttackPanel(55, x, y);
                this.add(attackPanel);
                new Thread(() -> {
                    while (true){
                        if (AttackPanel.isWin){
                            //openDoor(6, 3, 5);
                            map[15][3][6] = 0;
                            map[15][4][5] = 0;
                            map[15][4][6] = 0;
                            map[15][4][7] = 0;
                            map[15][5][5] = 0;
                            map[15][5][6] = 32;
                            map[15][5][7] = 0;
                            map[15][6][5] = 0;
                            map[15][6][6] = 0;
                            map[15][6][7] = 0;
                            break;
                        }else {
                            threadSleep(100);
                        }
                    }
                }).start();
                return false;
            case 61:  //魔龙
                if (TreasureUtil.isHaveDragonKillSword){
                    hero.setAttack(hero.getAttack() * 2);
                }
                attackPanel = new AttackPanel(61, x, y);
                this.add(attackPanel);
                new Thread(() -> {
                    while (true){
                        if (AttackPanel.isWin){
                            if (TreasureUtil.isHaveDragonKillSword)
                                hero.setAttack(hero.getAttack() / 2);
                            threadSleep(1000);
                            map[35][5][5] = 13;
                            map[35][5][6] = 13;
                            map[35][5][7] = 13;
                            map[35][6][5] = 0;
                            map[35][6][6] = 38;
                            map[35][6][7] = 0;
                            map[35][7][5] = 0;
                            map[35][7][6] = 0;
                            map[35][7][7] = 0;
                            openDoor(6,3,5);
                            break;
                        }else {
                            threadSleep(100);
                        }
                    }
                }).start();
                return false;
            case 70:  //吸血鬼
                if (TreasureUtil.isHaveHolyShield){
                    hero.setAttack(hero.getAttack() * 2);
                }
                attackPanel = new AttackPanel(70, x, y);
                this.add(attackPanel);
                new Thread(() -> {
                    while (true){
                        if (isMove) {
                            if (TreasureUtil.isHaveHolyShield)
                                hero.setAttack(hero.getAttack() / 2);
                            startDialog(ImageUtil.monsterMap.get(70)[0],MsgUtil.twentyMonster2);
                            map[currentFloor][6][6] = 70;
                            new Thread(() -> {
                                while (true){
                                    if (dialogPanel.isDialogEnd){
                                        map[currentFloor][6][6] = 0;
                                        map[currentFloor][3][6] = 0;
                                        map[currentFloor][9][6] = 0;
                                        map[currentFloor][4][5] = 7;
                                        map[currentFloor][4][6] = 7;
                                        map[currentFloor][4][7] = 7;
                                        map[currentFloor][5][4] = 10;
                                        map[currentFloor][6][4] = 10;
                                        map[currentFloor][7][4] = 10;
                                        map[currentFloor][5][8] = 11;
                                        map[currentFloor][6][8] = 11;
                                        map[currentFloor][7][8] = 11;
                                        map[currentFloor][8][5] = 13;
                                        map[currentFloor][8][6] = 13;
                                        map[currentFloor][8][7] = 13;
                                        break;
                                    }else {
                                        threadSleep(100);
                                    }
                                }
                            }).start();
                            break;
                        }else{
                            threadSleep(100);
                        }
                    }
                }).start();
                return false;
            case 72:  //大法师
                attackPanel = new AttackPanel(72, x, y);
                this.add(attackPanel);
                new Thread(() -> {
                    while (true){
                        if (AttackPanel.isWin){
                            map[25][8][4] = 9;
                            map[25][8][5] = 9;
                            map[25][8][7] = 9;
                            map[25][8][8] = 9;
                            break;
                        }else {
                            threadSleep(100);
                        }
                    }
                }).start();
                return false;
            case 81:  //蓝老头
                new BusinessmanEvent().blueMan(x,y);
                return false;
            case 82:  //红老头
                new BusinessmanEvent().redMan(x,y);
                return false;
            case 83:  //公主
                startDialog(ImageUtil.npcMap.get(83)[0],MsgUtil.princess);
                return false;
            case 84:  //小偷
                new BusinessmanEvent().thief();
                return false;
            case 115:  //3楼事件触发
                threeFloorEvent = new ThreeFloorEvent();
                changeHeroPosition(x, y, dir);
                return true;
            case 117:  //墙体漏洞消失
                new BusinessmanEvent().disappear(x,y,3);
                if (currentFloor == 41){
                    new Thread(() -> {
                        threadSleep(1100);
                        map[41][2][10] = 63;
                        AllFloorEvent.end[41] = 1;
                    }).start();
                }
                return false;
            case 118:  //10楼事件触发
                tenFloorEvent = new TenFloorEvent();
                changeHeroPosition(x, y, dir);
                return true;
            case 119:  //10楼事件触发2
                startDialog(ImageUtil.monsterMap.get(48)[0],MsgUtil.tenMonster2);
                changeHeroPosition(x, y, dir);
                return true;
            case 120:  //10楼事件触发3
                tenFloorEvent.thiefTalk();
                return false;
            case 121:  //20楼事件触发
                new TwentyFloorEvent();
                changeHeroPosition(x, y, dir);
                return true;
            case 123:  //隐藏墙体漏洞出现
                new BusinessmanEvent().appear(x,y);
                if (currentFloor == 23)  //记录23楼墙体出现个数，29楼事件触发条件
                    twentyThreeCount++;
                return false;
            case 124:  //30楼事件触发
                new ThirtyTwoFloorEvent();
                changeHeroPosition(x, y, dir);
                return true;
            case 125:  //40楼事件触发
                startDialog(ImageUtil.monsterMap.get(62)[0], MsgUtil.fortyTwoMonster1);
                dialogPanel.dialogEnd(new FortyTwoFloorEvent().timer);
                changeHeroPosition(x, y, dir);
                return true;
            case 126:  //49楼事件触发
                startDialog(ImageUtil.monsterMap.get(79)[0], MsgUtil.fortyNineMonster1);
                dialogPanel.dialogEnd(new FortyNineFloorEvent().timer);
                changeHeroPosition(x, y, dir);
                return true;
            /*case 127:  //
                changeHeroPosition(x, y, dir);
                if (!TreasureUtil.isHaveHolyShield){
                    msgPanel = new MsgPanel("你没有神圣盾，受到魔法攻击生命-100");
                    this.add(msgPanel);
                    hero.setHp(hero.getHp() - 100);
                    changeToEarth(x,y,127);
                }
                return true;
            case 128:
                changeHeroPosition(x, y, dir);
                if (!TreasureUtil.isHaveHolyShield){
                    msgPanel = new MsgPanel("你没有神圣盾，受到魔法攻击生命-200");
                    this.add(msgPanel);
                    hero.setHp(hero.getHp() - 200);
                    if (map[currentFloor][heroY + 1][heroX] == 63 && map[currentFloor][heroY + 2][heroX] == 0){
                        map[currentFloor][heroY + 2][heroX] = 63;
                        map[currentFloor][heroY + 1][heroX] = 128;
                    }else if (map[currentFloor][heroY - 1][heroX] == 63 && map[currentFloor][heroY - 2][heroX] == 0){
                        map[currentFloor][heroY - 2][heroX] = 63;
                        map[currentFloor][heroY - 1][heroX] = 128;
                    }else {
                        changeToEarth(x,y,128);
                    }
                }
                return true;
            case 129:
                changeHeroPosition(x, y, dir);
                if (!TreasureUtil.isHaveHolyShield){
                    msgPanel = new MsgPanel("你没有神圣盾，受到魔法攻击生命-300");
                    this.add(msgPanel);
                    hero.setHp(hero.getHp() - 300);
                    changeToEarth(x,y,129);
                }
                return true;*/
            case 130:  //魔法警卫
                changeHeroPosition(x, y, dir);
                if (!TreasureUtil.isHaveHolyShield){
                    msgPanel = new MsgPanel("你没有神圣盾，受到魔法攻击生命减半");
                    this.add(msgPanel);
                    hero.setHp(hero.getHp() / 2);
                    changeToEarth(x,y,130);
                }
                return true;
            case 131:  //特殊怪物巫师和高级巫师
                changeHeroPosition(x, y, dir);
                if (!TreasureUtil.isHaveHolyShield){
                    int a = 0;
                    int b = 0;
                    int x1 = 0,y1 = 0;
                    if (map[currentFloor][y - 1][x] == 64) //主角四周巫师个数
                        a++;
                    else if (map[currentFloor][y + 1][x] == 64)
                        a++;
                    else if (map[currentFloor][y][x - 1] == 64)
                        a++;
                    else if (map[currentFloor][y][x + 1] == 64)
                        a++;
                    if (map[currentFloor][y - 1][x] == 63) {  //主角四周高级巫师个数
                        b++;
                        x1 = x;
                        y1 = y - 1;
                    }
                    else if (map[currentFloor][y + 1][x] == 63) {
                        b++;
                        x1 = x;
                        y1 = y + 1;
                    }
                    else if (map[currentFloor][y][x - 1] == 63) {
                        b++;
                        x1 = x - 1;
                        y1 = y;
                    }
                    else if (map[currentFloor][y][x + 1] == 63) {
                        b++;
                        x1 = x + 1;
                        y1 = y;
                    }
                    if (!(a == 0 && b == 0)){
                        int hp = a * 100 + b * 200;
                        msgPanel = new MsgPanel("你没有神圣盾，受到魔法攻击生命-" + hp);
                        this.add(msgPanel);
                        hero.setHp(hero.getHp() - hp);
                        changeToEarth(x,y,131);
                        if (b != 0 && heroX == x1) {  //若是高级巫师，且和主角在同一方向，高级巫师向空地跳一格
                            if (map[currentFloor][y1 - 1][x1] == 0) {
                                map[currentFloor][y1 - 1][x1] = 63;
                                map[currentFloor][y1][x1] = 131;
                            }
                            if (map[currentFloor][y1 + 1][x1] == 0) {
                                map[currentFloor][y1 + 1][x1] = 63;
                                map[currentFloor][y1][x1] = 131;
                            }
                        }
                    }
                }
                return true;
            case 132:  //40楼事件触发
                changeHeroPosition(x, y, dir);
                startDialog(ImageUtil.monsterMap.get(62)[0],MsgUtil.fortyMonster1);
                dialogPanel.dialogEnd(new FortyFloorEvent().timer);
                return true;
            case 135:  //幸运金币
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveLuckyGold = true;
                msgPanel = new MsgPanel("获得幸运金币，击杀怪物之后获得双倍金币");
                this.add(msgPanel);
                return true;
            /*case 200:
                fight(map[currentFloor][y][x], x, y);
                return false;
            case 201:
                fight(map[currentFloor][y][x], x, y);
                return false;*/
            case 48:  //10楼boss骷髅队长
                attackPanel = new AttackPanel(map[currentFloor][y][x], x, y);
                this.add(attackPanel);
                tenFloorEvent.fightBoss(x,y);
                return false;
            default:  //怪物战斗
                if (map[currentFloor][y][x] >= 40 && map[currentFloor][y][x] < 80) {
                    attackPanel = new AttackPanel(map[currentFloor][y][x], x, y);
                    this.add(attackPanel);
                }
        }
        return false;
    }

    /** 主角离开特殊怪物指定扣减伤害点后，将变为空地的地方还原成伤害点*/
    public void changeToEarth(int x,int y,int id){
        new Thread(() -> {
            threadSleep(1000);
            int a = heroY - 1;  //这里减1没有意义，原本想直接赋值，但后面if判断会警告，所以-1
            int b = heroX - 1;
            while (true){
                if (a != (heroY - 1)|| b != (heroX - 1)){  //判断是否移动
                    map[currentFloor][y][x] = id;
                    break;
                }else {
                    threadSleep(100);
                }
            }
        }).start();
    }

    /*public void fight(int id,int x,int y){
        attackPanel = new AttackPanel(id, x, y);
        this.add(attackPanel);
        new Thread(() -> {
            while (true){
                if (isMove) {
                    count++;
                    break;
                }else{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }*/

    /**   开始对话方法*/
    public void startDialog(Image img,String[] text){
        dialogPanel = new DialogPanel(new ImageIcon(img));
        dialogPanel.message = text;
        dialogPanel.msg.setText(dialogPanel.message[dialogPanel.index]);
        dialogPanel.setVisible(true);
        this.add(dialogPanel,5,0);
        MoTa.mainFrame.addKeyListener(dialogPanel);
    }

    /**  判断是否在楼梯口旁边，用于飞行器使用条件，是返回true，否返回false*/
    public boolean isAroundStairs(){
        return map[currentFloor][heroY - 1][heroX] == 34 || map[currentFloor][heroY - 1][heroX] == 35 ||
                map[currentFloor][heroY + 1][heroX] == 34 || map[currentFloor][heroY + 1][heroX] == 35 ||
                map[currentFloor][heroY][heroX - 1] == 34 || map[currentFloor][heroY][heroX - 1] == 35 ||
                map[currentFloor][heroY][heroX + 1] == 34 || map[currentFloor][heroY][heroX + 1] == 35;
    }

    /**  判断当前楼层是否有与value值相同的地点，有变为空地，用于地震卷轴和魔法钥匙，以及上下楼器*/
    public void changeHeroValue(int value){
        label:
        for (int i = 1; i < 12; i++)
            for (int j = 1; j < 12; j++) {
                if (map[currentFloor][i][j] == value) {
                    map[currentFloor][i][j] = 0;
                    if (value == 80)
                        break label;
                }

            }
    }

    /** 主面板键盘事件方法，用来控制角色的移动*/
    @Override
    public void keyPressed(KeyEvent e) {
        if (isMove) {
            switch (e.getKeyCode()) {
                case VK_UP:  //向上移动
                    if (walk(heroX, heroY - 1, 1))
                        heroY--;
                    break;
                case VK_DOWN:  //向下移动
                    if (walk(heroX, heroY + 1, 2))
                        heroY++;
                    break;
                case VK_LEFT:  //向左移动
                    if (walk(heroX - 1, heroY, 3))
                        heroX--;
                    break;
                case VK_RIGHT:  //向右移动
                    if (walk(heroX + 1, heroY, 4))
                        heroX++;
                    break;
                case VK_B: //炸弹
                    if (TreasureUtil.isHaveBomb){
                        int exp = 0;
                        int money = 0;
                        //判断四周是否有怪物
                        if (map[currentFloor][heroY + 1][heroX] >= 40 && map[currentFloor][heroY + 1][heroX] < 80){
                            exp += MonsterUtil.monster.get(map[currentFloor][heroY + 1][heroX]).getExp();
                            money += MonsterUtil.monster.get(map[currentFloor][heroY + 1][heroX]).getMoney();
                            map[currentFloor][heroY + 1][heroX] = 0;
                        }
                        if (map[currentFloor][heroY - 1][heroX] >= 40 && map[currentFloor][heroY - 1][heroX] < 80){
                            exp += MonsterUtil.monster.get(map[currentFloor][heroY - 1][heroX]).getExp();
                            money += MonsterUtil.monster.get(map[currentFloor][heroY - 1][heroX]).getMoney();
                            map[currentFloor][heroY - 1][heroX] = 0;
                        }
                        if (map[currentFloor][heroY][heroX + 1] >= 40 && map[currentFloor][heroY][heroX + 1] < 80){
                            exp += MonsterUtil.monster.get(map[currentFloor][heroY][heroX + 1]).getExp();
                            money += MonsterUtil.monster.get(map[currentFloor][heroY][heroX + 1]).getMoney();
                            map[currentFloor][heroY][heroX + 1] = 0;
                        }
                        if (map[currentFloor][heroY][heroX - 1] >= 40 && map[currentFloor][heroY][heroX - 1] < 80){
                            exp += MonsterUtil.monster.get(map[currentFloor][heroY][heroX - 1]).getExp();
                            money += MonsterUtil.monster.get(map[currentFloor][heroY][heroX - 1]).getMoney();
                            map[currentFloor][heroY][heroX - 1] = 0;
                        }
                        hero.setMoney(hero.getMoney() + money);
                        hero.setExp(hero.getExp() + exp);
                        msgPanel = new MsgPanel("获得经验 " + exp +" 金币 " + money);
                        this.add(msgPanel);
                        TreasureUtil.isHaveBomb = false;
                    }
                    break;
                case VK_H:  //查看怪物信息
                    if (TreasureUtil.isHaveLookEye){
                        if (TreasureUtil.isLookEyeStart){  //显示面板
                            lookMonsterPanel = new LookMonsterPanel();
                            lookMonsterPanel.look();
                            this.add(lookMonsterPanel);
                            lookMonsterPanel.setVisible(true);
                            TreasureUtil.isLookEyeStart = false;
                        }else{  //取消面板
                            lookMonsterPanel.setVisible(false);
                            this.remove(lookMonsterPanel);
                            TreasureUtil.isLookEyeStart = true;
                        }
                    }
                    break;
                case VK_J:  //瞬移
                    if (TreasureUtil.isHaveTeleport){
                        //判断当前位置的对称地点是否为空地
                        if (map[currentFloor][12 - heroY][12 -heroX] == 0){
                            map[currentFloor][heroY][heroX] = 0;
                            map[currentFloor][12 - heroY][12 -heroX] = 80;
                            heroY = 12 - heroY;
                            heroX = 12 -heroX;
                            TreasureUtil.teleportCount--;
                        }else {
                            msgPanel = new MsgPanel("对称地点不是空地，无法瞬移");
                            this.add(msgPanel);
                        }
                        if (TreasureUtil.teleportCount == 0)
                            TreasureUtil.isHaveTeleport = false;
                    }
                    break;
                case VK_I:  //雪花
                    if (TreasureUtil.isHaveSnowflake){
                        if (map[currentFloor][heroY - 1][heroX] == 85){
                            map[currentFloor][heroY - 1][heroX] = 0;
                        }
                        if (map[currentFloor][heroY][heroX + 1] == 85){
                            map[currentFloor][heroY][heroX + 1] = 0;
                        }
                        if (map[currentFloor][heroY][heroX - 1] == 85){
                            map[currentFloor][heroY][heroX - 1] = 0;
                        }
                    }
                    break;
                case VK_P:  //镐
                    if (TreasureUtil.isHavePick){
                        if (map[currentFloor][heroY + 1][heroX] == 1){
                            new BusinessmanEvent().disappear(heroX,heroY + 1,3);
                        }
                        if (map[currentFloor][heroY - 1][heroX] == 1){
                            new BusinessmanEvent().disappear(heroX,heroY - 1,3);
                        }
                        if (map[currentFloor][heroY][heroX + 1] == 1){
                            new BusinessmanEvent().disappear(heroX + 1,heroY,3);
                        }
                        if (map[currentFloor][heroY][heroX - 1] == 1){
                            new BusinessmanEvent().disappear(heroX - 1,heroY,3);
                        }
                        TreasureUtil.isHavePick = false;
                    }
                    break;
                case VK_K:  //魔法钥匙
                    if (TreasureUtil.isHaveMagicKey){
                        changeHeroValue(2);
                        TreasureUtil.isHaveMagicKey = false;
                    }
                    break;
                case VK_Q:  //地震卷轴
                    if (TreasureUtil.isHaveSeismicScroll){
                        changeHeroValue(1);
                        TreasureUtil.isHaveSeismicScroll = false;
                    }
                    break;
                case VK_U:  //上楼器
                    if (TreasureUtil.isHaveUpFloorFly){
                        if (map[currentFloor + 1][heroY][heroX] != 0){
                            msgPanel = new MsgPanel("对应地点不是空地，无法使用上楼器");
                            this.add(msgPanel);
                        }else {
                            map[currentFloor][heroY][heroX] = 0;
                            currentFloor++;
                            changeHeroValue(80);
                            map[currentFloor][heroY][heroX] = 80;
                            TreasureUtil.isHaveUpFloorFly = false;
                        }
                    }
                    break;
                case VK_D:  //下楼器
                    if (TreasureUtil.isHaveDownFloorFly){
                        if (map[currentFloor - 1][heroY][heroX] != 0){
                            msgPanel = new MsgPanel("对应地点不是空地，无法使用下楼器");
                            this.add(msgPanel);
                        }else {
                            map[currentFloor][heroY][heroX] = 0;
                            currentFloor--;
                            changeHeroValue(80);
                            map[currentFloor][heroY][heroX] = 80;
                            TreasureUtil.isHaveDownFloorFly = false;
                        }
                    }
                    break;
                case VK_PAGE_UP:
                    if (TreasureUtil.isHaveFloorFly){
                        if (isAroundStairs()){
                            if ((currentFloor + 1) > maxFloor){
                                msgPanel = new MsgPanel("无法使用飞行器到未曾到过的楼层");
                                this.add(msgPanel);
                            }else {
                                currentFloor++;
                                heroX = MapUtil.heroUpPosition[currentFloor][0];
                                heroY = MapUtil.heroUpPosition[currentFloor][1];
                                repaint();
                            }
                        }else {
                            msgPanel = new MsgPanel("请在楼梯口旁使用飞行器");
                            this.add(msgPanel);
                        }
                    }
                    break;
                case VK_PAGE_DOWN:
                    if (TreasureUtil.isHaveFloorFly){
                        if (isAroundStairs()){
                            if ((currentFloor - 1) < 1){
                                msgPanel = new MsgPanel("这是你能到达的最低楼层");
                                this.add(msgPanel);
                            }else {
                                currentFloor--;
                                heroX = MapUtil.heroDownPosition[currentFloor][0];
                                heroY = MapUtil.heroDownPosition[currentFloor][1];
                                repaint();
                            }
                        }else {
                            msgPanel = new MsgPanel("请在楼梯口旁使用飞行器");
                            this.add(msgPanel);
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
