package event;

import panel.StartPanel;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class FailEvent {

    public JLabel event;
    public int index;
    public Image image;
    public Timer timer;

    public FailEvent(){
        event = new JLabel();
        event.setBounds(0,0,416,416);
        index = 1;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
            image = ImageUtil.eventMap.get(0)[index].getScaledInstance(416,416,Image.SCALE_DEFAULT);
            event.setIcon(new ImageIcon(image));
            index++;
            if (index == 81){
                StartPanel.mainPanel.remove(event);
                timer.stop();
            }
        });
        timer.start();
    }
}
