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
        this.setFont(new Font("����",Font.PLAIN,16));
    }

    /**  �鿴��ǰ¥��Ĺ���*/
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
            //�������ӵ�б���˫��������
            if (TreasureUtil.isHaveHolyShield) {
                if (m.getName().equals("����") || m.getName().equals("������ʿ") || m.getName().equals("��Ѫ��")) {
                    return "" + ((m.getAttack() - MainPanel.hero.getDefense()) * (m.getHp() / (MainPanel.hero.getAttack() * 2 - m.getDefence())));
                }
            }
            if (TreasureUtil.isHaveDragonKillSword) {
                if (m.getName().equals("ħ��"))
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
            g.drawString("����  "+monster.getName(),60,i * 47 + 28);
            g.drawString("����  " + monster.getAttack(),183,i * 47 + 28);
            g.drawString("�𡤾�  " + monster.getMoney() + "��" + monster.getExp(),274,i * 47 + 28);
            g.drawString("����    " + monster.getHp(),60,i * 47 + 50);
            g.drawString("����  " + monster.getDefence(),183,i * 47 + 50);
            g.drawString("��ʧ    " + loseHp(monster),282,i * 47 + 50);
            i++;
        }
    }
}
