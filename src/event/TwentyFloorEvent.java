package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;
import util.MsgUtil;

import javax.swing.*;
import java.awt.*;

public class TwentyFloorEvent {

    public JLabel event;
    public JLabel doorEvent;
    public int index;
    public int doorIndex;
    public Image image;
    public Image doorImage;
    public Timer timer;

    public TwentyFloorEvent(){
        event = new JLabel();
        doorEvent = new JLabel();
        event.setBounds(160,160,96,96);
        doorEvent.setBounds(192,288,32,32);
        index = 2;
        doorIndex = 3;
        MainPanel.isMove = false;
        StartPanel.mainPanel.add(event,2,0);
        StartPanel.mainPanel.add(doorEvent,3,0);
        new Timer(100,actionEvent -> {
            doorImage = ImageUtil.doorMap.get(104)[doorIndex];
            doorEvent.setIcon(new ImageIcon(doorImage));
            doorIndex--;
            if (doorIndex == -1){
                MainPanel.map[MainPanel.currentFloor][9][6] = 5;
                StartPanel.mainPanel.remove(doorEvent);
                ((Timer) actionEvent.getSource()).stop();
            }
        }).start();
        timer = new Timer(100,actionEvent -> {
            image = ImageUtil.eventMap.get(7)[index];
            event.setIcon(new ImageIcon(image));
            index++;
            if (index == 3){
                MainPanel.map[MainPanel.currentFloor][5][5] = 0;
                MainPanel.map[MainPanel.currentFloor][5][6] = 0;
                MainPanel.map[MainPanel.currentFloor][5][7] = 0;
                MainPanel.map[MainPanel.currentFloor][6][5] = 0;
                MainPanel.map[MainPanel.currentFloor][6][7] = 0;
                MainPanel.map[MainPanel.currentFloor][7][5] = 0;
                MainPanel.map[MainPanel.currentFloor][7][6] = 0;
                MainPanel.map[MainPanel.currentFloor][7][7] = 0;
            }
            if (index == 62){
                MainPanel.map[MainPanel.currentFloor][6][6] = 70;
            }
            if (index == 67){
                MainPanel.isMove = true;
                StartPanel.mainPanel.remove(event);
                StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(70)[0], MsgUtil.twentyMonster1);
                timer.stop();
            }
        });
        timer.start();
    }
}
