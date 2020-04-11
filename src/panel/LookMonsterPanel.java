package panel;

import bean.Monster;
import util.ImageUtil;
import util.MonsterUtil;
import util.TreasureUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class LookMonsterPanel extends JLayeredPane {

    public Set<Integer> monsterSet;
    public int count;
    public int index;

    public LookMonsterPanel(){
        this.setBounds(0,1,416,410);
        monsterSet = new HashSet<>();
        count = 0;
        this.setOpaque(true);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("黑体",Font.PLAIN,16));
    }

    /**  查看当前楼层的怪物*/
    public void look(){
        for (int i = 1;i < 12;i++) {
            for (int j = 1; j < 12; j++) {
                int id = MainPanel.map[MainPanel.currentFloor][i][j];
                if (id >= 40 && id < 80){
                    monsterSet.add(id);
                }
            }
        }
        count = monsterSet.size();
        new Timer(100,actionEvent -> {
            index++;
            if (index > 3)
                index = 0;
            this.repaint();
        }).start();
    }

    public String loseHp(Monster m){
        if (MainPanel.hero.getAttack() <= m.getDefence())
            return "???";
        else if (MainPanel.hero.getDefense() >= m.getAttack())
            return "" + 0;
        else {
            //特殊怪物拥有宝物双倍攻击力
            if (TreasureUtil.isHaveHolyShield) {
                if (m.getName().equals("兽人") || m.getName().equals("兽人武士") || m.getName().equals("吸血鬼")) {
                    return "" + ((m.getAttack() - MainPanel.hero.getDefense()) * (m.getHp() / (MainPanel.hero.getAttack() * 2 - m.getDefence())));
                }
            }
            if (TreasureUtil.isHaveDragonKillSword) {
                if (m.getName().equals("魔龙"))
                    return "" + ((m.getAttack() - MainPanel.hero.getDefense()) * (m.getHp() / (MainPanel.hero.getAttack() * 2 - m.getDefence())));
            }
            return "" + ((m.getAttack() - MainPanel.hero.getDefense()) * (m.getHp() / (MainPanel.hero.getAttack() - m.getDefence())));
        }
    }

    public void paintComponent(Graphics g){
        g.drawImage(ImageUtil.treasureMap.get(0)[count].getScaledInstance(416,410,Image.SCALE_DEFAULT),0,0,this);
        int i = 0;
        for (Integer id : monsterSet) {
            Monster monster = MonsterUtil.monster.get(id);
            g.drawImage(ImageUtil.monsterMap.get(id)[index], 15, i * 47 + 18, this);
            g.drawString("名称  "+monster.getName(),60,i * 47 + 28);
            g.drawString("攻击  " + monster.getAttack(),183,i * 47 + 28);
            g.drawString("金·经  " + monster.getMoney() + "·" + monster.getExp(),274,i * 47 + 28);
            g.drawString("生命    " + monster.getHp(),60,i * 47 + 50);
            g.drawString("防御  " + monster.getDefence(),183,i * 47 + 50);
            g.drawString("损失    " + loseHp(monster),282,i * 47 + 50);
            i++;
        }
    }
}
