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

    public static int[][][] map;  //��ͼ��Ϣ��ά����
    public static int currentFloor;  //��ǰ¥��
    public static int maxFloor;  //�������¥��
    public static Hero hero;  //��ɫ����
    public int monsterIndex;  //����ͼƬ�±�����
    public int heroIndex;  //��ɫͼƬ�±�����
    public int doorIndex;  //����ͼƬ�±�����
    public static int heroDirection;  //��ɫ����
    public static int heroX;  //��ɫx����
    public static int heroY;  //��ɫy����
    public static boolean isMove;  //��ɫ�Ƿ����ƶ�
    public static AttackPanel attackPanel;  //ս����Ϣ������
    public static MsgPanel msgPanel;  //��ʾ��Ϣ������
    public static ShopPanel shopPanel;  //�̵����
    public static ThreeFloorEvent threeFloorEvent;   //3���¼�����
    public static TwoFloorEvent twoFloorEvent;   //2���¼�����
    public static TenFloorEvent tenFloorEvent;  //10���¼�����
    public static DialogPanel dialogPanel;    //�Ի����
    public static LookMonsterPanel lookMonsterPanel;  //�鿴������Ϣ���
    public SoundUtil soundUtil;  //���ֲ�����
    //public static int count;
    public static int twentyThreeCount;  //23¥����ǽ����������

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

    /** �����������Ʒ���*/
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

    /** ��ɫ��Ϣ�����Ʒ���*/
    public void heroMsg(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("����",Font.PLAIN,22));
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

    /** ��ɫ�ƶ�������任����*/
    public void changeHeroPosition(int x,int y,int dir){
        map[currentFloor][y][x] = 80;
        map[currentFloor][heroY][heroX] = 0;
        heroDirection = dir;
        if (heroIndex == 3)
            heroIndex = 0;
        else
            heroIndex++;
        repaint();
        soundUtil.loadSound("��·.wav",1);
    }

    /** ���Ŷ���������2Ϊ���ţ�3Ϊ���ţ�4Ϊ���ţ�5Ϊ���ţ�6Ϊ����*/
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

    /**   �߳����߷���*/
    public void threadSleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** �������ս������,ָ���˺�������ʿ*/
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

    /** ��ɫ�ƶ�����������true�����ɹ��ƶ�����ɫ����任��false�����ƶ�ʧ�ܣ�����ԭ��*/
    public boolean walk(int x, int y, int dir) {
        switch (map[currentFloor][y][x]) {
            case 0:  //�յ�
                changeHeroPosition(x, y, dir);
                return true;
            case 2:  //����
                if (hero.getYKey() > 0) {
                    soundUtil.loadSound("��������¥.wav",1);
                    openDoor(x, y, 2);
                    hero.setYKey(hero.getYKey() - 1);
                }
                break;
            case 3:  //����
                if (hero.getBKey() > 0) {
                    soundUtil.loadSound("��������¥.wav",1);
                    openDoor(x, y, 3);
                    hero.setBKey(hero.getBKey() - 1);
                }
                break;
            case 4:  //����
                if (hero.getRKey() > 0) {
                    soundUtil.loadSound("��������¥.wav",1);
                    openDoor(x, y, 4);
                    hero.setRKey(hero.getRKey() - 1);
                }
                break;
            case 7:  //����֮Կ
                changeHeroPosition(x, y, dir);
                hero.setYKey(hero.getYKey() + 1);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ������֮Կ");
                this.add(msgPanel);
                return true;
            case 8:  //����֮Կ
                changeHeroPosition(x, y, dir);
                hero.setBKey(hero.getBKey() + 1);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ������֮Կ");
                this.add(msgPanel);
                return true;
            case 9:  //�Ⱥ�֮Կ
                changeHeroPosition(x, y, dir);
                hero.setRKey(hero.getRKey() + 1);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ���Ⱥ�֮Կ");
                this.add(msgPanel);
                return true;
            case 10:  //�챦ʯ
                changeHeroPosition(x, y, dir);
                int attack = (currentFloor / 10) + 1;
                hero.setAttack(hero.getAttack() + attack);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ���챦ʯ������+" + attack);
                this.add(msgPanel);
                return true;
            case 11:  //����ʯ
                changeHeroPosition(x, y, dir);
                int defence = (currentFloor / 10) + 1;
                hero.setDefense(hero.getDefense() + defence);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ������ʯ������+" + defence);
                this.add(msgPanel);
                return true;
            case 12:  //СѪƿ
                changeHeroPosition(x, y, dir);
                int hp1 = 50 * ((currentFloor / 10) + 1);
                hero.setHp(hero.getHp() + hp1);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ��СѪƿ������+" + hp1);
                this.add(msgPanel);
                return true;
            case 13:  //��Ѫƿ
                changeHeroPosition(x, y, dir);
                int hp2 = 200 * ((currentFloor / 10) + 1);
                hero.setHp(hero.getHp() + hp2);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ����Ѫƿ������+" + hp2);
                this.add(msgPanel);
                return true;
            case 15:  //����
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 10);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ������������+10");
                this.add(msgPanel);
                return true;
            case 16:  //����
                changeHeroPosition(x, y, dir);
                hero.setDefense(hero.getDefense() + 10);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ�����ܣ�����+10");
                this.add(msgPanel);
                return true;
            case 17:  //����
                changeHeroPosition(x, y, dir);
                hero.setDefense(hero.getDefense() + 20);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ�����ܣ�����+20");
                this.add(msgPanel);
                return true;
            case 18:  //��ʥ��
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 100);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ����ʥ��������+100");
                this.add(msgPanel);
                return true;
            case 19:  //����
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 20);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ������������+20");
                this.add(msgPanel);
                return true;
            case 20:  //��ʿ��
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 40);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ����ʿ��������+40");
                this.add(msgPanel);
                return true;
            case 21:  //��ʥ��
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveHolyShield = true;
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("�����ʥ�ܣ���������ħ������");
                this.add(msgPanel);
                return true;
            case 22:  //ʥ��
                changeHeroPosition(x, y, dir);
                hero.setAttack(hero.getAttack() + 50);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ��ʥ��������+50");
                this.add(msgPanel);
                return true;
            case 24:  //ʥ��
                changeHeroPosition(x, y, dir);
                hero.setDefense(hero.getDefense() + 50);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���һ��ʥ�ܣ�����+50");
                this.add(msgPanel);
                return true;
            case 25:  //��¥��
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveUpFloorFly = true;
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("�����¥��������U������ǰ¥����һ��");
                this.add(msgPanel);
                return true;
            case 26:  //������
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveFloorFly = true;
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("��÷�����������Pg UP/DOWN������¥����Ծ");
                this.add(msgPanel);
                return true;
            case 27:  //���±�
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveNotebook = true;
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("��ü��±�������N���鿴��Ҫ�Ի�");
                this.add(msgPanel);
                return true;
            case 28:  //ը��
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveBomb = true;
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���ը��������B��ը����Χ���ˣ�ͷĿ��Ч��");
                this.add(msgPanel);
                return true;
            case 30:  //�̵�
                shopPanel = new ShopPanel();
                this.add(shopPanel);
                return false;
            case 32:  //��
                changeHeroPosition(x, y, dir);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("��ø䣬����P���ƻ���Χǽ��");
                this.add(msgPanel);
                return true;
            case 34:  //��¥
                map[currentFloor][heroY][heroX] = 0;
                currentFloor++;
                soundUtil.loadSound("��������¥.wav",1);
                if (currentFloor == 44)  //44¥�޷�ֱ����ȥ������
                    currentFloor++;
                if (currentFloor > maxFloor)  //��¼��������¥��
                    maxFloor = currentFloor;
                heroX = MapUtil.heroUpPosition[currentFloor][0];
                heroY = MapUtil.heroUpPosition[currentFloor][1];
                map[currentFloor][heroY][heroX] = 80;
                repaint();
                return false;
            case 35:  //��¥
                map[currentFloor][heroY][heroX] = 0;
                currentFloor--;
                if (currentFloor == 44)  //44¥�޷�ֱ����ȥ������
                    currentFloor--;
                heroX = MapUtil.heroDownPosition[currentFloor][0];
                heroY = MapUtil.heroDownPosition[currentFloor][1];
                map[currentFloor][heroY][heroX] = 80;
                repaint();
                return false;
            case 36:  //˲��
                changeHeroPosition(x, y, dir);
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���˲�ƣ���3��ʹ��J���������ĶԳƵĿյ�");
                this.add(msgPanel);
                TreasureUtil.isHaveTeleport = true;
                return true;
            case 37:  //��¥��
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveDownFloorFly = true;
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("�����¥��������D�����ﵱǰ¥����һ��");
                this.add(msgPanel);
                return true;
            case 38:  //ѩ��
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveSnowflake = true;
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���ѩ��������I�������Ҷ�Ϊƽ��");
                this.add(msgPanel);
                return true;
            case 39:  //ħ��Կ��
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveMagicKey = true;
                soundUtil.loadSound("�����Ʒ.wav",1);
                msgPanel = new MsgPanel("���ħ��Կ�ף�����K����һ��¥���еĻ�ɫ��");
                this.add(msgPanel);
                return true;
            case 49:  //����
                fightWithSpecialMonster(49,x,y);
                return false;
            case 53:  //������ʿ
                fightWithSpecialMonster(53,x,y);
                return false;
            case 55:  //������
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
            case 61:  //ħ��
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
            case 70:  //��Ѫ��
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
            case 72:  //��ʦ
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
            case 81:  //����ͷ
                new BusinessmanEvent().blueMan(x,y);
                return false;
            case 82:  //����ͷ
                new BusinessmanEvent().redMan(x,y);
                return false;
            case 83:  //����
                startDialog(ImageUtil.npcMap.get(83)[0],MsgUtil.princess);
                return false;
            case 84:  //С͵
                new BusinessmanEvent().thief();
                return false;
            case 115:  //3¥�¼�����
                threeFloorEvent = new ThreeFloorEvent();
                changeHeroPosition(x, y, dir);
                return true;
            case 117:  //ǽ��©����ʧ
                new BusinessmanEvent().disappear(x,y,3);
                if (currentFloor == 41){
                    new Thread(() -> {
                        threadSleep(1100);
                        map[41][2][10] = 63;
                        AllFloorEvent.end[41] = 1;
                    }).start();
                }
                return false;
            case 118:  //10¥�¼�����
                tenFloorEvent = new TenFloorEvent();
                changeHeroPosition(x, y, dir);
                return true;
            case 119:  //10¥�¼�����2
                startDialog(ImageUtil.monsterMap.get(48)[0],MsgUtil.tenMonster2);
                changeHeroPosition(x, y, dir);
                return true;
            case 120:  //10¥�¼�����3
                tenFloorEvent.thiefTalk();
                return false;
            case 121:  //20¥�¼�����
                new TwentyFloorEvent();
                changeHeroPosition(x, y, dir);
                return true;
            case 123:  //����ǽ��©������
                new BusinessmanEvent().appear(x,y);
                if (currentFloor == 23)  //��¼23¥ǽ����ָ�����29¥�¼���������
                    twentyThreeCount++;
                return false;
            case 124:  //30¥�¼�����
                new ThirtyTwoFloorEvent();
                changeHeroPosition(x, y, dir);
                return true;
            case 125:  //40¥�¼�����
                startDialog(ImageUtil.monsterMap.get(62)[0], MsgUtil.fortyTwoMonster1);
                dialogPanel.dialogEnd(new FortyTwoFloorEvent().timer);
                changeHeroPosition(x, y, dir);
                return true;
            case 126:  //49¥�¼�����
                startDialog(ImageUtil.monsterMap.get(79)[0], MsgUtil.fortyNineMonster1);
                dialogPanel.dialogEnd(new FortyNineFloorEvent().timer);
                changeHeroPosition(x, y, dir);
                return true;
            /*case 127:  //
                changeHeroPosition(x, y, dir);
                if (!TreasureUtil.isHaveHolyShield){
                    msgPanel = new MsgPanel("��û����ʥ�ܣ��ܵ�ħ����������-100");
                    this.add(msgPanel);
                    hero.setHp(hero.getHp() - 100);
                    changeToEarth(x,y,127);
                }
                return true;
            case 128:
                changeHeroPosition(x, y, dir);
                if (!TreasureUtil.isHaveHolyShield){
                    msgPanel = new MsgPanel("��û����ʥ�ܣ��ܵ�ħ����������-200");
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
                    msgPanel = new MsgPanel("��û����ʥ�ܣ��ܵ�ħ����������-300");
                    this.add(msgPanel);
                    hero.setHp(hero.getHp() - 300);
                    changeToEarth(x,y,129);
                }
                return true;*/
            case 130:  //ħ������
                changeHeroPosition(x, y, dir);
                if (!TreasureUtil.isHaveHolyShield){
                    msgPanel = new MsgPanel("��û����ʥ�ܣ��ܵ�ħ��������������");
                    this.add(msgPanel);
                    hero.setHp(hero.getHp() / 2);
                    changeToEarth(x,y,130);
                }
                return true;
            case 131:  //���������ʦ�͸߼���ʦ
                changeHeroPosition(x, y, dir);
                if (!TreasureUtil.isHaveHolyShield){
                    int a = 0;
                    int b = 0;
                    int x1 = 0,y1 = 0;
                    if (map[currentFloor][y - 1][x] == 64) //����������ʦ����
                        a++;
                    else if (map[currentFloor][y + 1][x] == 64)
                        a++;
                    else if (map[currentFloor][y][x - 1] == 64)
                        a++;
                    else if (map[currentFloor][y][x + 1] == 64)
                        a++;
                    if (map[currentFloor][y - 1][x] == 63) {  //�������ܸ߼���ʦ����
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
                        msgPanel = new MsgPanel("��û����ʥ�ܣ��ܵ�ħ����������-" + hp);
                        this.add(msgPanel);
                        hero.setHp(hero.getHp() - hp);
                        changeToEarth(x,y,131);
                        if (b != 0 && heroX == x1) {  //���Ǹ߼���ʦ���Һ�������ͬһ���򣬸߼���ʦ��յ���һ��
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
            case 132:  //40¥�¼�����
                changeHeroPosition(x, y, dir);
                startDialog(ImageUtil.monsterMap.get(62)[0],MsgUtil.fortyMonster1);
                dialogPanel.dialogEnd(new FortyFloorEvent().timer);
                return true;
            case 135:  //���˽��
                changeHeroPosition(x, y, dir);
                TreasureUtil.isHaveLuckyGold = true;
                msgPanel = new MsgPanel("������˽�ң���ɱ����֮����˫�����");
                this.add(msgPanel);
                return true;
            /*case 200:
                fight(map[currentFloor][y][x], x, y);
                return false;
            case 201:
                fight(map[currentFloor][y][x], x, y);
                return false;*/
            case 48:  //10¥boss���öӳ�
                attackPanel = new AttackPanel(map[currentFloor][y][x], x, y);
                this.add(attackPanel);
                tenFloorEvent.fightBoss(x,y);
                return false;
            default:  //����ս��
                if (map[currentFloor][y][x] >= 40 && map[currentFloor][y][x] < 80) {
                    attackPanel = new AttackPanel(map[currentFloor][y][x], x, y);
                    this.add(attackPanel);
                }
        }
        return false;
    }

    /** �����뿪�������ָ���ۼ��˺���󣬽���Ϊ�յصĵط���ԭ���˺���*/
    public void changeToEarth(int x,int y,int id){
        new Thread(() -> {
            threadSleep(1000);
            int a = heroY - 1;  //�����1û�����壬ԭ����ֱ�Ӹ�ֵ��������if�жϻᾯ�棬����-1
            int b = heroX - 1;
            while (true){
                if (a != (heroY - 1)|| b != (heroX - 1)){  //�ж��Ƿ��ƶ�
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

    /**   ��ʼ�Ի�����*/
    public void startDialog(Image img,String[] text){
        dialogPanel = new DialogPanel(new ImageIcon(img));
        dialogPanel.message = text;
        dialogPanel.msg.setText(dialogPanel.message[dialogPanel.index]);
        dialogPanel.setVisible(true);
        this.add(dialogPanel,5,0);
        MoTa.mainFrame.addKeyListener(dialogPanel);
    }

    /**  �ж��Ƿ���¥�ݿ��Աߣ����ڷ�����ʹ���������Ƿ���true���񷵻�false*/
    public boolean isAroundStairs(){
        return map[currentFloor][heroY - 1][heroX] == 34 || map[currentFloor][heroY - 1][heroX] == 35 ||
                map[currentFloor][heroY + 1][heroX] == 34 || map[currentFloor][heroY + 1][heroX] == 35 ||
                map[currentFloor][heroY][heroX - 1] == 34 || map[currentFloor][heroY][heroX - 1] == 35 ||
                map[currentFloor][heroY][heroX + 1] == 34 || map[currentFloor][heroY][heroX + 1] == 35;
    }

    /**  �жϵ�ǰ¥���Ƿ�����valueֵ��ͬ�ĵص㣬�б�Ϊ�յأ����ڵ�������ħ��Կ�ף��Լ�����¥��*/
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

    /** ���������¼��������������ƽ�ɫ���ƶ�*/
    @Override
    public void keyPressed(KeyEvent e) {
        if (isMove) {
            switch (e.getKeyCode()) {
                case VK_UP:  //�����ƶ�
                    if (walk(heroX, heroY - 1, 1))
                        heroY--;
                    break;
                case VK_DOWN:  //�����ƶ�
                    if (walk(heroX, heroY + 1, 2))
                        heroY++;
                    break;
                case VK_LEFT:  //�����ƶ�
                    if (walk(heroX - 1, heroY, 3))
                        heroX--;
                    break;
                case VK_RIGHT:  //�����ƶ�
                    if (walk(heroX + 1, heroY, 4))
                        heroX++;
                    break;
                case VK_B: //ը��
                    if (TreasureUtil.isHaveBomb){
                        int exp = 0;
                        int money = 0;
                        //�ж������Ƿ��й���
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
                        msgPanel = new MsgPanel("��þ��� " + exp +" ��� " + money);
                        this.add(msgPanel);
                        TreasureUtil.isHaveBomb = false;
                    }
                    break;
                case VK_H:  //�鿴������Ϣ
                    if (TreasureUtil.isHaveLookEye){
                        if (TreasureUtil.isLookEyeStart){  //��ʾ���
                            lookMonsterPanel = new LookMonsterPanel();
                            lookMonsterPanel.look();
                            this.add(lookMonsterPanel);
                            lookMonsterPanel.setVisible(true);
                            TreasureUtil.isLookEyeStart = false;
                        }else{  //ȡ�����
                            lookMonsterPanel.setVisible(false);
                            this.remove(lookMonsterPanel);
                            TreasureUtil.isLookEyeStart = true;
                        }
                    }
                    break;
                case VK_J:  //˲��
                    if (TreasureUtil.isHaveTeleport){
                        //�жϵ�ǰλ�õĶԳƵص��Ƿ�Ϊ�յ�
                        if (map[currentFloor][12 - heroY][12 -heroX] == 0){
                            map[currentFloor][heroY][heroX] = 0;
                            map[currentFloor][12 - heroY][12 -heroX] = 80;
                            heroY = 12 - heroY;
                            heroX = 12 -heroX;
                            TreasureUtil.teleportCount--;
                        }else {
                            msgPanel = new MsgPanel("�ԳƵص㲻�ǿյأ��޷�˲��");
                            this.add(msgPanel);
                        }
                        if (TreasureUtil.teleportCount == 0)
                            TreasureUtil.isHaveTeleport = false;
                    }
                    break;
                case VK_I:  //ѩ��
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
                case VK_P:  //��
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
                case VK_K:  //ħ��Կ��
                    if (TreasureUtil.isHaveMagicKey){
                        changeHeroValue(2);
                        TreasureUtil.isHaveMagicKey = false;
                    }
                    break;
                case VK_Q:  //�������
                    if (TreasureUtil.isHaveSeismicScroll){
                        changeHeroValue(1);
                        TreasureUtil.isHaveSeismicScroll = false;
                    }
                    break;
                case VK_U:  //��¥��
                    if (TreasureUtil.isHaveUpFloorFly){
                        if (map[currentFloor + 1][heroY][heroX] != 0){
                            msgPanel = new MsgPanel("��Ӧ�ص㲻�ǿյأ��޷�ʹ����¥��");
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
                case VK_D:  //��¥��
                    if (TreasureUtil.isHaveDownFloorFly){
                        if (map[currentFloor - 1][heroY][heroX] != 0){
                            msgPanel = new MsgPanel("��Ӧ�ص㲻�ǿյأ��޷�ʹ����¥��");
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
                                msgPanel = new MsgPanel("�޷�ʹ�÷�������δ��������¥��");
                                this.add(msgPanel);
                            }else {
                                currentFloor++;
                                heroX = MapUtil.heroUpPosition[currentFloor][0];
                                heroY = MapUtil.heroUpPosition[currentFloor][1];
                                repaint();
                            }
                        }else {
                            msgPanel = new MsgPanel("����¥�ݿ���ʹ�÷�����");
                            this.add(msgPanel);
                        }
                    }
                    break;
                case VK_PAGE_DOWN:
                    if (TreasureUtil.isHaveFloorFly){
                        if (isAroundStairs()){
                            if ((currentFloor - 1) < 1){
                                msgPanel = new MsgPanel("�������ܵ�������¥��");
                                this.add(msgPanel);
                            }else {
                                currentFloor--;
                                heroX = MapUtil.heroDownPosition[currentFloor][0];
                                heroY = MapUtil.heroDownPosition[currentFloor][1];
                                repaint();
                            }
                        }else {
                            msgPanel = new MsgPanel("����¥�ݿ���ʹ�÷�����");
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
