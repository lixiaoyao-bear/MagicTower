package event;

import panel.AttackPanel;
import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;
import util.MsgUtil;

import javax.swing.*;
import java.awt.*;

public class FortyFloorEvent {

    public JLabel event;
    public int index;
    public Image image;
    public Timer timer;

    public FortyFloorEvent(){
        event = new JLabel();
        event.setBounds(64,32,288,224);
        index = 1;
        MainPanel.isMove = false;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100, actionEvent -> {
           image = ImageUtil.eventMap.get(14)[index];
           event.setIcon(new ImageIcon(image));
           index++;
           if (index == 2){
               MainPanel.map[40][1][6] = 0;
               MainPanel.map[40][2][2] = 0;
               MainPanel.map[40][2][3] = 0;
               MainPanel.map[40][2][4] = 0;
               MainPanel.map[40][2][8] = 0;
               MainPanel.map[40][2][9] = 0;
               MainPanel.map[40][2][10] = 0;
               MainPanel.map[40][4][3] = 0;
               MainPanel.map[40][4][4] = 0;
               MainPanel.map[40][4][5] = 0;
               MainPanel.map[40][4][7] = 0;
               MainPanel.map[40][4][8] = 0;
               MainPanel.map[40][4][9] = 0;
           }
           if (index == 20){
               fight(58,5,4);
           }
           if (index == 35)
               fight(58,4,4);
           if (index == 49)
               talk(58,3,4, MsgUtil.fortyMonster2);
           if (index == 58)
               fight(59,7,4);
           if (index == 68)
               fight(59,8,4);
           if (index == 79)
               talk(59,9,4, MsgUtil.fortyMonster3);
           if (index == 92)
               fight(57,4,2);
           if (index == 101)
               fight(57,3,2);
           if (index == 111)
               talk(57,2,2, MsgUtil.fortyMonster4);
           if (index == 119)
               fight(60,8,2);
           if (index == 127)
               fight(60,9,2);
           if (index == 136)
               talk(60, 10, 2, MsgUtil.fortyMonster5);
           if (index == 142) {
               MainPanel.map[40][1][6] = 34;
               talk(62, 6, 1, MsgUtil.fortyMonster6);
           }
           if (index == 150){
               timer.stop();
               StartPanel.mainPanel.remove(event);
               MainPanel.map[40][1][6] = 34;
               MainPanel.map[40][2][2] = 7;
               MainPanel.map[40][2][3] = 7;
               MainPanel.map[40][2][4] = 7;
               MainPanel.map[40][2][8] = 10;
               MainPanel.map[40][2][9] = 10;
               MainPanel.map[40][2][10] = 10;
               MainPanel.map[40][4][3] = 13;
               MainPanel.map[40][4][4] = 13;
               MainPanel.map[40][4][5] = 13;
               MainPanel.map[40][4][7] = 11;
               MainPanel.map[40][4][8] = 11;
               MainPanel.map[40][4][9] = 11;
           }
        });
    }

    public void fight(int id,int x,int y){
        timer.stop();
        MainPanel.attackPanel = new AttackPanel(id,x,y);
        StartPanel.mainPanel.add(MainPanel.attackPanel,3,0);
        new Thread(() -> {
            while (true){
                if (AttackPanel.isWin){
                    StartPanel.mainPanel.threadSleep(1000);
                    timer.start();
                    break;
                }else {
                    StartPanel.mainPanel.threadSleep(100);
                }
            }
        }).start();
    }

    public void talk(int id,int x,int y,String[] text){
        timer.stop();
        MainPanel.attackPanel = new AttackPanel(id,x,y);
        StartPanel.mainPanel.add(MainPanel.attackPanel,3,0);
        new Thread(() -> {
            while (true){
                if (AttackPanel.isWin){
                    StartPanel.mainPanel.threadSleep(1000);
                    StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(62)[0],text);
                    MainPanel.dialogPanel.dialogEnd(timer);
                    break;
                }else {
                    StartPanel.mainPanel.threadSleep(100);
                }
            }
        }).start();
    }
}
