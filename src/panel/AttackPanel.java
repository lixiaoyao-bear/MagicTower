package panel;

import bean.Monster;
import event.FailEvent;
import mota.MoTa;
import util.ImageUtil;
import util.MonsterUtil;
import util.TreasureUtil;

import javax.swing.*;

import java.awt.*;

import static java.awt.Image.SCALE_DEFAULT;

public class AttackPanel extends JLayeredPane {

    public ImageIcon bg;
    public ImageIcon monsterIcon;
    public JLabel attackBg;
    public JLabel monsterBg;
    public JLabel heroHp;
    public JLabel heroAttack;
    public JLabel heroDefence;
    public JLabel monsterHp;
    public JLabel monsterAttack;
    public JLabel monsterDefence;
    public Monster monster;
    public int hp;
    public int attack;
    public int defence;
    public int index;
    public static boolean isWin;
    public Timer timer;

    public AttackPanel(int id,int x,int y){
        index = 0;
        isWin = false;
        MainPanel.isMove = false;
        bg = new ImageIcon(ImageUtil.otherMap.get(109).getScaledInstance(580,300,SCALE_DEFAULT));
        attackBg = new JLabel(bg);
        monsterIcon = new ImageIcon(ImageUtil.monsterMap.get(id)[index].getScaledInstance(64,64,SCALE_DEFAULT));
        monsterBg = new JLabel(monsterIcon);
        heroHp = new JLabel();
        heroAttack = new JLabel();
        heroDefence = new JLabel();
        monsterHp = new JLabel();
        monsterAttack = new JLabel();
        monsterDefence = new JLabel();

        monster = MonsterUtil.monster.get(id);
        hp =  monster.getHp();
        attack = monster.getAttack();
        defence = monster.getDefence();
        this.setLayout(null);
        this.setBounds(20,20,580,300);
        attackBg.setBounds(0,0,580,300);
        this.add(attackBg,1,0);
        this.setOpaque(true);

        heroHp.setBounds(370,60,50,50);
        heroHp.setFont(new Font("宋体",Font.PLAIN,22));
        heroHp.setForeground(Color.WHITE);
        heroAttack.setBounds(370,130,50,50);
        heroAttack.setFont(new Font("宋体",Font.PLAIN,22));
        heroAttack.setForeground(Color.WHITE);
        heroDefence.setBounds(370,200,50,50);
        heroDefence.setFont(new Font("宋体",Font.PLAIN,22));
        heroDefence.setForeground(Color.WHITE);
        monsterHp.setBounds(180,60,50,50);
        monsterHp.setFont(new Font("宋体",Font.PLAIN,22));
        monsterHp.setForeground(Color.WHITE);
        monsterAttack.setBounds(180,130,50,50);
        monsterAttack.setFont(new Font("宋体",Font.PLAIN,22));
        monsterAttack.setForeground(Color.WHITE);
        monsterDefence.setBounds(180,200,50,50);
        monsterDefence.setFont(new Font("宋体",Font.PLAIN,22));
        monsterDefence.setForeground(Color.WHITE);
        monsterBg.setBounds(30,60,64,64);

        this.add(heroHp,2,0);
        this.add(heroAttack,3,0);
        this.add(heroDefence,4,0);
        this.add(monsterHp,5,0);
        this.add(monsterAttack,6,0);
        this.add(monsterDefence,7,0);
        this.add(monsterBg,8,0);

        heroHp.setText("" + MainPanel.hero.getHp());
        heroAttack.setText("" + MainPanel.hero.getAttack());
        heroDefence.setText("" + MainPanel.hero.getDefense());
        monsterHp.setText("" + hp);
        monsterAttack.setText("" + attack);
        monsterDefence.setText("" + defence);
        this.setVisible(true);

        //战斗信息面板动画
        timer = new Timer(500,actionEvent -> {
            if (isWin){
                MainPanel.map[MainPanel.currentFloor][y][x] = 0;
                MainPanel.hero.setExp(MainPanel.hero.getExp() + monster.getExp());
                StartPanel.mainPanel.soundUtil.loadSound("战斗胜利.wav",1);
                if (TreasureUtil.isHaveLuckyGold){
                    MainPanel.hero.setMoney(MainPanel.hero.getMoney() + monster.getMoney());
                    MainPanel.msgPanel = new MsgPanel("获得经验 "+ monster.getExp()+" 金币 "+ monster.getMoney() * 2);
                }else {
                    MainPanel.hero.setMoney(MainPanel.hero.getMoney() + monster.getMoney());
                    MainPanel.msgPanel = new MsgPanel("获得经验 "+ monster.getExp()+" 金币 "+ monster.getMoney() * 2);
                }
                StartPanel.mainPanel.remove(MainPanel.attackPanel);
                MainPanel.attackPanel = null;
                StartPanel.mainPanel.add(MainPanel.msgPanel,4,0);
                MainPanel.isMove = true;
                //((Timer) actionEvent.getSource()).stop();
                timer.stop();
            }else{
                StartPanel.mainPanel.soundUtil.loadSound("战斗.wav",1);
                attack();
                heroHp.setText("" + MainPanel.hero.getHp());
                monsterHp.setText("" + hp);
                index++;
                if (index > 3)
                    index = 0;
                monsterBg.setIcon(new ImageIcon(ImageUtil.monsterMap.get(id)[index].getScaledInstance(64,64,SCALE_DEFAULT)));
                MoTa.mainFrame.repaint();
                //显示战斗胜利图案
                if (hp <= 0){
                    monsterHp.setText("" + hp);
                    JLabel sl = new JLabel(new ImageIcon(ImageUtil.otherMap.get(110)));
                    JLabel zz = new JLabel(new ImageIcon(ImageUtil.otherMap.get(111).getScaledInstance(580,300,SCALE_DEFAULT)));
                    sl.setBounds(150,100,310,97);
                    zz.setBounds(0,0,580,300);
                    this.add(zz,9,0);
                    this.add(sl,10,0);
                    MoTa.mainFrame.repaint();
                    isWin = true;
                }
            }
        });
        timer.start();
    }

    /**  战斗，互相扣减生命值*/
    public void attack(){
        if (MainPanel.hero.getAttack() > defence)
            hp = hp -(MainPanel.hero.getAttack() - defence);
        if (hp <= 0){
            hp = 0;
            return;
        }
        if (attack > MainPanel.hero.getDefense())
            MainPanel.hero.setHp(MainPanel.hero.getHp() - (attack - MainPanel.hero.getDefense()));
        if (MainPanel.hero.getHp() <= 0){
            MainPanel.hero.setHp(0);
            timer.stop();
            StartPanel.mainPanel.remove(MainPanel.attackPanel);
            MainPanel.attackPanel = null;
            new FailEvent();
        }
    }
}
