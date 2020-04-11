package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class ThirtyFiveFloorEvent {

    public JLabel event;
    public int index;
    public Image image;
    public Timer timer;

    public ThirtyFiveFloorEvent(){
        event = new JLabel();
        event.setBounds(160,320,64,64);
        index = 1;
        MainPanel.isMove = false;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
           image = ImageUtil.eventMap.get(11)[index];
           event.setIcon(new ImageIcon(image));
           index++;
           if (index == 10)
               MainPanel.map[35][10][5] = 0;
           if (index == 37){
               MainPanel.isMove = true;
               StartPanel.mainPanel.remove(event);
               timer.stop();
           }
        });
    }
}
