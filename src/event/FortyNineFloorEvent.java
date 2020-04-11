package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class FortyNineFloorEvent {

    public JLabel event;
    public int index;
    public Image image;
    public Timer timer;

    public FortyNineFloorEvent(){
        event = new JLabel();
        event.setBounds(160,64,128,128);
        index = 1;
        MainPanel.isMove = false;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
            image = ImageUtil.eventMap.get(13)[index];
            event.setIcon(new ImageIcon(image));
            index++;
            if (index == 56){
                MainPanel.isMove = true;
                MainPanel.map[49][2][5] = 67;
                MainPanel.map[49][2][6] = 67;
                MainPanel.map[49][2][7] = 67;
                MainPanel.map[49][3][5] = 67;
                MainPanel.map[49][3][6] = 79;
                MainPanel.map[49][3][7] = 67;
                MainPanel.map[49][4][5] = 67;
                MainPanel.map[49][4][6] = 67;
                MainPanel.map[49][4][7] = 67;
                StartPanel.mainPanel.remove(event);
                timer.stop();
            }
        });
    }
}
