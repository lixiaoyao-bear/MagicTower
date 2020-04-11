package event;

import panel.ChoicePanel;
import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;
import util.MsgUtil;
import util.TreasureUtil;

import javax.swing.*;
import java.awt.*;

public class BusinessmanEvent {

    public void blueMan(int x,int y){
        MainPanel.isMove = false;
        switch (MainPanel.currentFloor){
            case 2:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.twoBlueMan);
                new Thread(() -> {
                    while (true){
                        if (MainPanel.dialogPanel.isDialogEnd){
                            MainPanel.hero.setMoney(MainPanel.hero.getMoney() + 1000);
                            break;
                        }else {
                            StartPanel.mainPanel.threadSleep(100);
                        }
                    }
                }).start();
                break;
            case 3:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.threeBlueMan);
                TreasureUtil.isHaveLookEye = true;
                break;
            case 4:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.fourBlueMan);
                break;
            case 6:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.sixBlueMan);
                break;
            case 16:
                if (x == 1){
                    StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.sixteenBlueMan1);
                }else {
                    StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.sixteenBlueMan2);
                    TreasureUtil.isHaveHolyWater = true;
                    //MainPanel.msgPanel = new MsgPanel("获得圣水，可用L键使用");
                    //StartPanel.mainPanel.add(MainPanel.msgPanel);
                }
                break;
            case 18:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.eighteenBlueMan);
                break;
            case 21:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.twentyOneBlueMan);
                break;
            case 23:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.twentyThreeBlueMan);
                break;
            case 27:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.twentySevenBlueMan);
                break;
            case 31:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.thirtyOneBlueMan);
                break;
            case 33:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.thirtyThreeBlueMan);
                break;
            case 36:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.thirtySixBlueMan);
                break;
            case 37:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.thirtySevenBlueMan);
                break;
            case 39:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.thirtyNineBlueMan);
                break;
            case 42:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.fortyTwoBlueMan);
                break;
            case 45:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.fortyFiveBlueMan);
                break;
            case 46:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.fortySixBlueMan);
                break;
            case 48:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(81)[0], MsgUtil.fortyEightBlueMan);
                break;
        }
        manDis(x,y,1);
    }

    public void redMan(int x,int y){
        MainPanel.isMove = false;
        switch (MainPanel.currentFloor){
            case 2:
                redChoice(x,y,MsgUtil.twoRedMan,MsgUtil.twoRedMan);
                break;
            case 6:
                redChoice(x,y,MsgUtil.sixRedMan1,MsgUtil.sixRedMan2);
                break;
            case 7:
                redChoice(x,y,MsgUtil.sevenRedMan1,MsgUtil.sevenRedMan2);
                break;
            case 12:
                redChoice(x,y,MsgUtil.twelveRedMan1,MsgUtil.twelveRedMan2);
                break;
            case 15:
                redChoice(x,y,MsgUtil.fifteenRedMan1,MsgUtil.fifteenRedMan2);
                break;
            case 28:
                redChoice(x,y,MsgUtil.twentyEightRedMan,MsgUtil.twentyEightRedMan);
                break;
            case 31:
                redChoice(x,y,MsgUtil.thirtyOneRedMan1,MsgUtil.thirtyOneRedMan2);
                break;
            case 38:
                redChoice(x,y,MsgUtil.thirtyEightRedMan1,MsgUtil.thirtyEightRedMan2);
                break;
            case 39:
                redChoice(x,y,MsgUtil.thirtyNineRedMan1,MsgUtil.thirtyNineRedMan2);
                break;
            case 45:
                redChoice(x,y,MsgUtil.fortyFiveRedMan1,MsgUtil.fortyFiveRedMan2);
                break;
            case 47:
                redChoice(x,y,MsgUtil.fortySevenRedMan1,MsgUtil.fortySevenRedMan2);
                break;
        }
    }

    public void thief(){
        switch (MainPanel.currentFloor){
            case 2:
                if (!TreasureUtil.isTwentyNineStart) {
                    if (MainPanel.twoFloorEvent.count == 0) {
                        StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0], MsgUtil.twoThief2);
                        MainPanel.twoFloorEvent.oneTalkEnd();
                    } else {
                        StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0], MsgUtil.twoThief3);
                        MainPanel.twoFloorEvent.twoTalkEnd();
                    }
                }else {
                    StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0], MsgUtil.twoThief4);
                    new Thread(() -> {
                        while (true){
                            if (MainPanel.dialogPanel.isDialogEnd){
                                MainPanel.map[2][11][10] = 0;
                                MainPanel.map[35][9][4] = 0;
                                MainPanel.map[35][10][5] = 84;
                                break;
                            }else {
                                StartPanel.mainPanel.threadSleep(100);
                            }
                        }
                    }).start();
                }
                break;
            case 15:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0],MsgUtil.fifteenThief);
                MainPanel.dialogPanel.dialogEnd(new FifteenFloorEvent().timer);
                break;
            case 29:
                if (TreasureUtil.isTwentyNineStart){
                    StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0],MsgUtil.twentyNineThief2);
                    MainPanel.dialogPanel.dialogEnd(new TwentyNineFloorEvent().timer);
                }else {
                    StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0],MsgUtil.twentyNineThief1);
                }
                break;
            case 35:
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0],MsgUtil.thirtyFiveThief);
                MainPanel.dialogPanel.dialogEnd(new ThirtyFiveFloorEvent().timer);
                break;
            case 50:
                MainPanel.map[50][5][6] = 77;
                StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(84)[0],MsgUtil.fiftyKing);
                break;
        }
    }

    public void manDis(int x,int y,int id){
        new Thread(()->{
            while (true){
                if (MainPanel.dialogPanel.isDialogEnd){
                    disappear(x,y,id);
                    break;
                }
            }
        }).start();
    }

    public void redChoice(int x,int y,String[] text1,String[] text2){
        if (ChoicePanel.isFirst[MainPanel.currentFloor] == 0){
            ChoicePanel choicePanel = new ChoicePanel();
            choicePanel.msg.setText(text1[0]);
            StartPanel.mainPanel.add(choicePanel);
        }else {
            StartPanel.mainPanel.startDialog(ImageUtil.npcMap.get(82)[0],text2);
            manDis(x,y,2);
        }
    }

    public void disappear(int x,int y,int id){
        JLabel event = new JLabel();
        if (id == 3)
            event.setBounds(32 * x,32 * y,32,64);
        else
            event.setBounds(32 * x,32 * y,32,32);
        final int[] index = {0};
        final Image[] image = new Image[1];
        StartPanel.mainPanel.add(event);
        MainPanel.isMove = false;
        new Timer(100,actionEvent -> {
            image[0] = ImageUtil.npcMap.get(id)[index[0]];
            event.setIcon(new ImageIcon(image[0]));
            index[0]++;
            if (index[0] == 1){
                MainPanel.map[MainPanel.currentFloor][y][x] = 0;
            }
            if (index[0] == 10){
                MainPanel.isMove = true;
                StartPanel.mainPanel.remove(event);
                ((Timer) actionEvent.getSource()).stop();
            }
        }).start();
    }

    public void appear(int x,int y){
        JLabel event = new JLabel();
        event.setBounds(32 * x,32 * y,32,64);
        final int[] index = {9};
        final Image[] image = new Image[1];
        StartPanel.mainPanel.add(event);
        MainPanel.isMove = false;
        new Timer(100,actionEvent -> {
            image[0] = ImageUtil.npcMap.get(3)[index[0]];
            event.setIcon(new ImageIcon(image[0]));
            index[0]--;
            if (index[0] == -1){
                MainPanel.isMove = true;
                MainPanel.map[MainPanel.currentFloor][y][x] = 1;
                StartPanel.mainPanel.remove(event);
                ((Timer) actionEvent.getSource()).stop();
            }
        }).start();
    }
}
