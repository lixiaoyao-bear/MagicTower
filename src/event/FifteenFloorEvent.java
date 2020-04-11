package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class FifteenFloorEvent {

    public JLabel event;
    public int index;
    public Image image;
    public Timer timer;

    public FifteenFloorEvent(){
        event = new JLabel();
        event.setBounds(192,32,128,64);
        index = 1;
        MainPanel.isMove = false;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
           image = ImageUtil.eventMap.get(6)[index];
            event.setIcon(new ImageIcon(image));
            index++;
            if (index == 6)
                MainPanel.map[MainPanel.currentFloor][1][8] = 0;
            if (index == 15)
                MainPanel.map[MainPanel.currentFloor][1][9] = 0;
            if (index == 50)
                timer.stop();
        });
    }
}
