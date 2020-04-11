package panel;

import event.BusinessmanEvent;
import mota.MoTa;
import util.ImageUtil;
import util.TreasureUtil;

import javax.swing.*;
import java.awt.*;

/**  ����ͷѡ���������*/
public class ChoicePanel extends JLayeredPane {

    public JLabel bg;
    public JButton yesButton;
    public JButton noButton;
    public JTextArea msg;
    public static int[] isFirst = new int[51];  //�����ж��Ƿ���н���

    public ChoicePanel(){
        this.setBounds(64,100,288,200);
        bg = new JLabel();
        bg.setBounds(0,0,288,200);
        bg.setIcon(new ImageIcon(ImageUtil.otherMap.get(122).getScaledInstance(288,200,Image.SCALE_DEFAULT)));
        this.add(bg,1,0);
        msg = new JTextArea();
        msg.setBounds(20,20,248,100);
        msg.setFont(new Font("����",Font.PLAIN,17));
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);
        msg.setEditable(false);
        msg.setBackground(Color.BLACK);
        msg.setForeground(Color.WHITE);
        this.add(msg,2,0);
        yesButton = new JButton("��");
        yesButton.setBounds(56,150,60,20);
        this.add(yesButton,3,0);
        noButton = new JButton("��");
        noButton.setBounds(172,150,60,20);
        this.add(noButton,4,0);
        yesButton.addActionListener(actionEvent -> {
            switch (MainPanel.currentFloor){
                case 2:
                    int a = (int) (((double)MainPanel.hero.getAttack()) * 1.03);
                    int d = (int) (((double)MainPanel.hero.getDefense()) * 1.03);
                    MainPanel.hero.setAttack(a);
                    MainPanel.hero.setDefense(d);
                    new BusinessmanEvent().disappear(11,7,2);
                    break;
                case 6:
                    if (MainPanel.hero.getMoney() >= 50){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() - 50);
                        MainPanel.hero.setBKey(MainPanel.hero.getBKey() + 1);
                        isFirst[MainPanel.currentFloor] = 1;
                    }else {
                        msg.setText("    û��Ǯ�Ͳ�Ҫ������ƣ��Ͻ�ȥ׬Ǯ��");
                    }
                    break;
                case 7:
                    if (MainPanel.hero.getMoney() >= 50){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() - 50);
                        MainPanel.hero.setBKey(MainPanel.hero.getYKey() + 5);
                        isFirst[MainPanel.currentFloor] = 1;
                    }else {
                        msg.setText("    û��Ǯ�Ͳ�Ҫ������ƣ��Ͻ�ȥ׬Ǯ��");
                    }
                    break;
                case 12:
                    if (MainPanel.hero.getMoney() >= 800){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() - 800);
                        MainPanel.hero.setBKey(MainPanel.hero.getRKey() + 1);
                        isFirst[MainPanel.currentFloor] = 1;
                    }else {
                        msg.setText("    û��Ǯ�Ͳ�Ҫ������ƣ��Ͻ�ȥ׬Ǯ��");
                    }
                    break;
                case 15:
                    if (MainPanel.hero.getMoney() >= 200){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() - 200);
                        MainPanel.hero.setBKey(MainPanel.hero.getBKey() + 1);
                        isFirst[MainPanel.currentFloor] = 1;
                    }else {
                        msg.setText("    û��Ǯ�Ͳ�Ҫ������ƣ��Ͻ�ȥ׬Ǯ��");
                    }
                    break;
                case 28:
                    if (MainPanel.hero.getYKey() >= 1){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() + 100);
                        MainPanel.hero.setBKey(MainPanel.hero.getYKey() - 1);
                    }else {
                        msg.setText("    û������֮Կ��������������װ��ǲ��ɣ�");
                    }
                    break;
                case 31:
                    if (MainPanel.hero.getMoney() >= 1000){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() - 1000);
                        MainPanel.hero.setBKey(MainPanel.hero.getBKey() + 1);
                        MainPanel.hero.setBKey(MainPanel.hero.getYKey() + 4);
                        isFirst[MainPanel.currentFloor] = 1;
                    }else {
                        msg.setText("    û��Ǯ�Ͳ�Ҫ������ƣ��Ͻ�ȥ׬Ǯ��");
                    }
                    break;
                case 38:
                    if (MainPanel.hero.getMoney() >= 200){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() - 200);
                        MainPanel.hero.setBKey(MainPanel.hero.getYKey() + 3);
                        isFirst[MainPanel.currentFloor] = 1;
                    }else {
                        msg.setText("    û��Ǯ�Ͳ�Ҫ������ƣ��Ͻ�ȥ׬Ǯ��");
                    }
                    break;
                case 39:
                    if (MainPanel.hero.getMoney() >= 2000){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() - 2000);
                        MainPanel.hero.setBKey(MainPanel.hero.getBKey() + 3);
                        isFirst[MainPanel.currentFloor] = 1;
                    }else {
                        msg.setText("    û��Ǯ�Ͳ�Ҫ������ƣ��Ͻ�ȥ׬Ǯ��");
                    }
                    break;
                case 45:
                    if (MainPanel.hero.getMoney() >= 1000){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() - 1000);
                        MainPanel.hero.setHp(MainPanel.hero.getHp() + 2000);
                        isFirst[MainPanel.currentFloor] = 1;
                    }else {
                        msg.setText("    û��Ǯ�Ͳ�Ҫ������ƣ��Ͻ�ȥ׬Ǯ��");
                    }
                    break;
                case 47:
                    if (MainPanel.hero.getMoney() >= 4000){
                        MainPanel.hero.setMoney(MainPanel.hero.getMoney() - 4000);
                        TreasureUtil.isHaveSeismicScroll = true;
                        isFirst[MainPanel.currentFloor] = 1;
                    }else {
                        msg.setText("    û��Ǯ�Ͳ�Ҫ������ƣ��Ͻ�ȥ׬Ǯ��");
                    }
                    break;
            }
            StartPanel.mainPanel.remove(this);
            MainPanel.isMove = true;
            MoTa.mainFrame.requestFocus();
        });
        noButton.addActionListener(actionEvent -> {
            MainPanel.isMove = true;
            StartPanel.mainPanel.remove(this);
            MoTa.mainFrame.requestFocus();
        });
    }
}
