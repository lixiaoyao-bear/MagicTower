package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class TwoFloorEvent {

    public int index;
    public JLabel event;
    public Image image;
    public Timer timer;
    public int count;

    public TwoFloorEvent(){
        index = 1;
        count = 0;
        event = new JLabel();
        event.setBounds(0,0,416,416);
        event.setIcon(new ImageIcon(ImageUtil.otherMap.get(116).getScaledInstance(416,416,Image.SCALE_DEFAULT)));
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(50,actionEvent ->{
            if (count == 0){
                image = ImageUtil.eventMap.get(1)[index];
            }else{
                image = ImageUtil.eventMap.get(2)[index];
            }
            event.setIcon(new ImageIcon(image));
            index++;
            if (count == 0){
                if (index == 15)
                    MainPanel.map[MainPanel.currentFloor][7][3] = 0;
                if (index == 49){
                    count++;
                    index = 1;
                    MainPanel.isMove = true;
                    MainPanel.map[MainPanel.currentFloor][9][1] = 84;
                    StartPanel.mainPanel.remove(event);
                    timer.stop();
                }
            }else {
                if (index == 6)
                    MainPanel.map[MainPanel.currentFloor][9][1] = 0;
                if (index == 33){
                    count++;
                    MainPanel.isMove = true;
                    StartPanel.mainPanel.remove(event);
                    timer.stop();
                }
            }
        });
    }

    public void oneTalkEnd(){
        StartPanel.mainPanel.remove(event);
        new Thread(() -> {
            while (true){
                if (MainPanel.dialogPanel.isDialogEnd){
                    MainPanel.map[MainPanel.currentFloor][7][2] = 0;
                    event.setBounds(32,224,96,96);
                    StartPanel.mainPanel.add(event,2,0);
                    timer.start();
                    break;
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void twoTalkEnd(){
        new Thread(() -> {
            while (true){
                if (MainPanel.dialogPanel.isDialogEnd){
                    event.setBounds(32,288,64,96);
                    StartPanel.mainPanel.add(event,2,0);
                    timer.start();
                    break;
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
