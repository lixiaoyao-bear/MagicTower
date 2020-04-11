package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;
import util.MsgUtil;

import javax.swing.*;
import java.awt.*;

public class FortyTwoFloorEvent {

    public JLabel event;
    public int index;
    public Image image;
    public Timer timer;

    public FortyTwoFloorEvent(){
        event = new JLabel();
        event.setBounds(160,192,96,160);
        index = 1;
        MainPanel.isMove = false;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
           image = ImageUtil.eventMap.get(12)[index];
           event.setIcon(new ImageIcon(image));
           index++;
           if (index == 6){
               MainPanel.map[42][10][6] = 0;
           }
           if (index == 36){
               StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(79)[0], MsgUtil.fortyTwoMonster2);
               MainPanel.dialogPanel.dialogEnd(timer);
               timer.stop();
           }
           if (index == 50){
               StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(62)[0], MsgUtil.fortyTwoMonster3);
               MainPanel.dialogPanel.dialogEnd(timer);
               timer.stop();
           }
           if (index == 83){
               StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(79)[0], MsgUtil.fortyTwoMonster4);
               MainPanel.dialogPanel.dialogEnd(timer);
               timer.stop();
           }
           if (index == 103){
               MainPanel.isMove = true;
               StartPanel.mainPanel.remove(event);
               timer.stop();
           }
        });
    }
}
