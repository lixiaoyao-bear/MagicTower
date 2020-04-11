package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;
import util.MsgUtil;

import javax.swing.*;
import java.awt.*;

public class TenFloorEvent {

    public JLabel event;
    public int index;
    public Image image;
    public Timer timer;

    public TenFloorEvent(){
        event = new JLabel();
        event.setBounds(32,32,352,224);
        index = 1;
        MainPanel.isMove = false;
        MainPanel.map[MainPanel.currentFloor][4][4] = 0;
        MainPanel.map[MainPanel.currentFloor][4][8] = 0;
        MainPanel.map[MainPanel.currentFloor][6][5] = 0;
        MainPanel.map[MainPanel.currentFloor][6][7] = 0;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
            image = ImageUtil.eventMap.get(4)[index];
            event.setIcon(new ImageIcon(image));
            index++;
            if (index == 13){
                StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(48)[0],MsgUtil.tenMonster1);
                new Thread(() -> {
                    while (true){
                        if (MainPanel.dialogPanel.isDialogEnd){
                            MainPanel.map[MainPanel.currentFloor][3][1] = 0;
                            MainPanel.map[MainPanel.currentFloor][3][2] = 0;
                            MainPanel.map[MainPanel.currentFloor][3][3] = 0;
                            MainPanel.map[MainPanel.currentFloor][4][2] = 0;
                            MainPanel.map[MainPanel.currentFloor][3][9] = 0;
                            MainPanel.map[MainPanel.currentFloor][3][10] = 0;
                            MainPanel.map[MainPanel.currentFloor][3][11] = 0;
                            MainPanel.map[MainPanel.currentFloor][4][10] = 0;
                            MainPanel.map[MainPanel.currentFloor][4][6] = 0;
                            timer.start();
                            break;
                        }else {
                            StartPanel.mainPanel.threadSleep(100);
                        }
                    }
                }).start();
                timer.stop();
            }
            if (index == 94){
                timer.stop();
                StartPanel.mainPanel.remove(event);
                AllFloorEvent.end[10] = 1;
                MainPanel.map[MainPanel.currentFloor][4][5] = 44;
                MainPanel.map[MainPanel.currentFloor][5][5] = 44;
                MainPanel.map[MainPanel.currentFloor][6][5] = 44;
                MainPanel.map[MainPanel.currentFloor][4][7] = 44;
                MainPanel.map[MainPanel.currentFloor][5][7] = 44;
                MainPanel.map[MainPanel.currentFloor][6][7] = 44;
                MainPanel.map[MainPanel.currentFloor][4][6] = 45;
                MainPanel.map[MainPanel.currentFloor][6][6] = 45;
                MainPanel.map[MainPanel.currentFloor][1][6] = 48;
                MainPanel.map[MainPanel.currentFloor][3][6] = 5;
                MainPanel.map[MainPanel.currentFloor][4][4] = 5;
                MainPanel.map[MainPanel.currentFloor][4][8] = 5;
                MainPanel.map[MainPanel.currentFloor][7][6] = 5;
            }
        });
        timer.start();
    }

    public void fightBoss(int x,int y){
        new Thread(() -> {
            while (true){
                if (MainPanel.isMove){
                    MainPanel.map[MainPanel.currentFloor][y][x] = 48;
                    StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(48)[0],MsgUtil.tenMonster3);
                    new Thread(() -> {
                        while (true){
                            if (MainPanel.dialogPanel.isDialogEnd){
                                MainPanel.map[MainPanel.currentFloor][y][x] = 0;
                                MainPanel.map[MainPanel.currentFloor][4][4] = 0;
                                MainPanel.map[MainPanel.currentFloor][4][8] = 0;
                                MainPanel.map[MainPanel.currentFloor][7][6] = 0;
                                MainPanel.map[MainPanel.currentFloor][11][6] = 34;
                                MainPanel.map[MainPanel.currentFloor][3][1] = 10;
                                MainPanel.map[MainPanel.currentFloor][3][2] = 10;
                                MainPanel.map[MainPanel.currentFloor][3][3] = 10;
                                MainPanel.map[MainPanel.currentFloor][4][1] = 13;
                                MainPanel.map[MainPanel.currentFloor][4][2] = 13;
                                MainPanel.map[MainPanel.currentFloor][4][3] = 13;
                                MainPanel.map[MainPanel.currentFloor][3][9] = 11;
                                MainPanel.map[MainPanel.currentFloor][3][10] = 11;
                                MainPanel.map[MainPanel.currentFloor][3][11] = 11;
                                MainPanel.map[MainPanel.currentFloor][4][9] = 7;
                                MainPanel.map[MainPanel.currentFloor][4][10] = 7;
                                MainPanel.map[MainPanel.currentFloor][4][11] = 7;
                                MainPanel.map[MainPanel.currentFloor][10][6] = 120;
                                break;
                            }else{
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                    break;
                }else{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void thiefTalk(){
        event.setBounds(32,256,192,128);
        index = 1;
        StartPanel.mainPanel.add(event,2,0);
        timer = new Timer(100,actionEvent -> {
            image = ImageUtil.eventMap.get(5)[index];
            event.setIcon(new ImageIcon(image));
            index++;
            if (index == 119){
                MainPanel.map[MainPanel.currentFloor][10][6] = 0;
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0],MsgUtil.tenThief);
                MainPanel.dialogPanel.dialogEnd(timer);
                /*StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0],MsgUtil.tenThief);
                new Thread(() -> {
                    while (true){
                        if (MainPanel.dialogPanel.isDialogEnd){
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
                }).start();*/
                timer.stop();
            }
            if (index == 131){
                StartPanel.mainPanel.remove(event);
                timer.stop();
            }
        });
        timer.start();
    }
}
