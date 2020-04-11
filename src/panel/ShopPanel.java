package panel;

import mota.MoTa;
import util.ImageUtil;
import util.MapUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

/**  商店类*/
public class ShopPanel extends JLayeredPane {

    public JLabel bg;
    public JLabel monster;
    public JTextArea msg;
    public JLabel space;
    public JLabel name;
    public ImageIcon spaceBg;
    public Image image;
    public int index;
    public int floor;
    public static int[][] money = MapUtil.shopMoney;
    public static int[] count = new int[]{0,0,0,0};
    public static int[][] hp = MapUtil.shopHP;
    public static int[][] ad = MapUtil.shopAttackAndDenfence;

    public ShopPanel(){
        this.setBounds(68,64,277,297);
        MainPanel.isMove = false;
        bg = new JLabel();
        bg.setIcon(new ImageIcon(ImageUtil.otherMap.get(112)));
        bg.setBounds(0,0,277,297);
        name = new JLabel("什么都能卖大佬");
        name.setBounds(60,10,200,30);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("宋体",Font.PLAIN,18));
        msg = new JTextArea();
        msg.setBounds(5,50,270,240);
        msg.setFont(new Font("宋体",Font.PLAIN,17));
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);
        msg.setEditable(false);
        msg.setBackground(Color.BLACK);
        msg.setForeground(Color.WHITE);
        msg.setText("  年轻的小伙子哟，你想拥有无上的力量吗？你想成为无敌的强者吗？" +
                "你想成为站在金字塔最顶端的人吗？\n"  + "  那就先去查查你的存款有多少，" +
                "不然的话就别在这做梦了。\n" + "  算了，看你可怜，还是给你一点帮助吧。记住" +
                "，按空格键进入选择，按小键盘的8,2键进行选择，按5键确定。\n" + "  奉劝你谨慎选择，还有" +
                "，下次记得多带点钱过来。");
        monster = new JLabel();
        monster.setBounds(10,10,32,32);
        space = new JLabel();
        space.setBounds(190,275,75,13);
        spaceBg = new ImageIcon(ImageUtil.otherMap.get(113));
        index = 0;
        floor = -1;
        //this.setLayout(null);
        this.add(bg,1,0);
        this.add(msg,2,0);
        this.add(monster,3,0);
        this.add(space,4,0);
        this.add(name,5,0);
        this.setOpaque(true);
        this.setVisible(true);
        Timer timer = new Timer(250, actionEvent -> {
            image = ImageUtil.doorMap.get(30)[index].getScaledInstance(32,32,Image.SCALE_DEFAULT);
            monster.setIcon(new ImageIcon(image));
            index++;
            if (index > 3)
                index = 0;
            if ((index + 1) % 2 == 0)
                space.setIcon(spaceBg);
            else
                space.setIcon(null);
        });
        timer.start();
        MoTa.mainFrame.addKeyListener(new KeyListener() {
            int choice;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case VK_SPACE:
                        choice = 0;
                        space.setBounds(40,100,195,40);  //60,50,48
                        spaceBg = new ImageIcon(ImageUtil.otherMap.get(114).getScaledInstance(195,40,Image.SCALE_DEFAULT));
                        space.setIcon(spaceBg);
                        msg.setFont(new Font("宋体",Font.PLAIN,18));
                        shopChange();
                        break;
                    case VK_NUMPAD8:
                        if (choice != 0) {
                            choice--;
                            space.setBounds(40,100+choice*43,195,40);
                        }
                        break;
                    case VK_NUMPAD2:
                        if (choice != 3){
                            choice++;
                            space.setBounds(40,100+choice*43,195,40);
                        }
                        break;
                    case VK_NUMPAD5:
                        if (choice == 3){
                            timer.stop();
                            StartPanel.mainPanel.remove(MainPanel.shopPanel);
                            MoTa.mainFrame.removeKeyListener(this);
                            MainPanel.isMove = true;
                            MainPanel.shopPanel = null;
                        }else if (MainPanel.hero.getMoney() >= money[floor][count[floor]]){
                            MainPanel.hero.setMoney(MainPanel.hero.getMoney() - money[floor][count[floor]]);
                            switch (choice){
                                case 0:
                                    MainPanel.hero.setHp(MainPanel.hero.getHp() + hp[floor][count[floor]]);
                                    break;
                                case 1:
                                    MainPanel.hero.setAttack(MainPanel.hero.getAttack() + ad[floor][0]);
                                    break;
                                case 2:
                                    MainPanel.hero.setDefense(MainPanel.hero.getDefense() + ad[floor][1]);
                                    break;
                            }
                            count[floor]++;
                            shopChange();
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void shopChange(){
        switch (MainPanel.currentFloor){
            case 4:
                floor = 0;
                msg.setText("   花费" + money[floor][count[floor]] + "金币我就给你提升一个能力\n\n"+
                        "         增加"+hp[0][count[floor]]+"生命\n\n" + "         增加" + ad[0][0] +
                        "点攻击\n\n" + "         增加" + ad[0][1] + "点防御\n\n" + "         回归现实\n\n");
                break;
            case 12:
                floor = 1;
                msg.setText("   花费" + money[floor][count[floor]] + "金币我就给你提升一个能力\n\n"+
                        "         增加"+hp[1][count[floor]]+"生命\n\n" + "         增加" + ad[1][0] +
                        "点攻击\n\n" + "         增加" + ad[1][1] + "点防御\n\n" + "         回归现实\n\n");
                break;
            case 32:
                floor = 2;
                msg.setText("   花费" + money[floor][count[floor]] + "金币我就给你提升一个能力\n\n"+
                        "         增加"+hp[2][count[floor]]+"生命\n\n" + "         增加" + ad[2][0] +
                        "点攻击\n\n" + "         增加" + ad[2][1] + "点防御\n\n" + "         回归现实\n\n");
                break;
            case 46:
                floor = 3;
                msg.setText("   花费" + money[floor][count[floor]] + "金币我就给你提升一个能力\n\n"+
                        "         增加"+hp[3][count[floor]]+"生命\n\n" + "         增加" + ad[3][0] +
                        "点攻击\n\n" + "         增加" + ad[3][1] + "点防御\n\n" + "         回归现实\n\n");
                break;
        }
    }
}
