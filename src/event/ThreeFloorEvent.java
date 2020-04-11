package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;
import util.MsgUtil;

import javax.swing.*;
import java.awt.*;

public class ThreeFloorEvent {

    public int index;
    public JLabel event;
    public Image image;
    public Timer timer;

    public ThreeFloorEvent(){
        index = 1;
        MainPanel.isMove = false;
        event = new JLabel();
        event.setBounds(128,224,96,128);
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
            image = ImageUtil.eventMap.get(3)[index];
            event.setIcon(new ImageIcon(image));
            index++;
            if (index == 19){
                StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(79)[0],MsgUtil.threeKing1);
                MainPanel.dialogPanel.dialogEnd(timer);
                timer.stop();

            }
            if (index == 34){
                StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(79)[0],MsgUtil.threeKing2);
                MainPanel.dialogPanel.dialogEnd(timer);
                timer.stop();
            }
            if (index == 51){
                StartPanel.mainPanel.remove(event);
                MainPanel.twoFloorEvent = new TwoFloorEvent();
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0],MsgUtil.twoThief1);
                new Thread(() -> {
                    while (true){
                        if (MainPanel.dialogPanel.isDialogEnd){
                            StartPanel.mainPanel.remove(MainPanel.twoFloorEvent.event);
                            MainPanel.hero.setHp(400);
                            MainPanel.hero.setAttack(10);
                            MainPanel.hero.setDefense(10);
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
                MainPanel.map[MainPanel.currentFloor][9][5] = 0;
                MainPanel.map[MainPanel.currentFloor][11][2] = 80;
                MainPanel.currentFloor = 2;
                MainPanel.map[MainPanel.currentFloor][10][1] = 0;
                MainPanel.map[MainPanel.currentFloor][8][3] = 80;
                MainPanel.heroDirection = 2;
                MainPanel.heroX = 3;
                MainPanel.heroY = 8;
                timer.stop();
            }
        });
        timer.start();
    }
}
