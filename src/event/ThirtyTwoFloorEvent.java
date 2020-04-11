package event;

import panel.AttackPanel;
import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;
import util.MsgUtil;

import javax.swing.*;
import java.awt.*;

public class ThirtyTwoFloorEvent {

    public JLabel event;
    public int index;
    public boolean isFirst;
    public Image image;
    public Timer timer;

    public ThirtyTwoFloorEvent(){
        event = new JLabel();
        event.setBounds(192,32,192,320);
        index = 1;
        isFirst = true;
        MainPanel.isMove = false;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
            if (isFirst)
                image = ImageUtil.eventMap.get(9)[index];
            else
                image = ImageUtil.eventMap.get(10)[index];
           event.setIcon(new ImageIcon(image));
           index++;
           if (isFirst){
               if (index == 42){
                   timer.stop();
                   StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(62)[0], MsgUtil.thirtyTwoMonster1);
                   MainPanel.dialogPanel.dialogEnd(timer);
               }
               if (index == 107){
                   MainPanel.attackPanel = new AttackPanel(62,6,9);
                   StartPanel.mainPanel.add(MainPanel.attackPanel);
                   new Thread(() -> {
                       while (true){
                           if (AttackPanel.isWin){
                               MainPanel.map[MainPanel.currentFloor][9][6] = 62;
                               index = 1;
                               isFirst = false;
                               event.setBounds(192,32,192,288);
                               StartPanel.mainPanel.threadSleep(1000);
                               StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(62)[0], MsgUtil.thirtyTwoMonster2);
                               MainPanel.dialogPanel.dialogEnd(timer);
                               break;
                           }else {
                               StartPanel.mainPanel.threadSleep(100);
                           }
                       }
                   }).start();
                   timer.stop();
               }
           }else {
               if (index == 2)
                   MainPanel.map[MainPanel.currentFloor][9][6] = 0;
               if (index == 69){
                   MainPanel.isMove = true;
                   StartPanel.mainPanel.remove(event);
                   timer.stop();
               }
           }
        });
        timer.start();
    }
}
