package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class TwentyNineFloorEvent {

    public JLabel event;
    public int index;
    public Image image;
    public Timer timer;

    public TwentyNineFloorEvent(){
        event = new JLabel();
        event.setBounds(192,64,32,320);
        index = 1;
        MainPanel.isMove = false;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
           image = ImageUtil.eventMap.get(8)[index];
           event.setIcon(new ImageIcon(image));
           index++;
           if (index == 2)
               MainPanel.map[MainPanel.currentFloor][3][6] = 0;
           if (index == 18)
               MainPanel.map[MainPanel.currentFloor][2][6] = 0;
           if (index == 101){
               MainPanel.isMove = true;
               MainPanel.map[2][11][10] = 84;
               StartPanel.mainPanel.remove(event);
               timer.stop();
           }
        });
    }
}
