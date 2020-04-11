package event;

import panel.MainPanel;
import panel.StartPanel;
import util.ImageUtil;
import util.MsgUtil;
import util.TreasureUtil;

public class AllFloorEvent implements Runnable {

    public int count;
    public static int[] end = new int[51];
    public int[][] guard;

    @Override
    public void run() {
        count = 0;
        guard = new int[][]{
                {0,0,0,0},
                {0,0},
                {0,0}
        };
        while (true){
            switch (MainPanel.currentFloor){
                case 2:
                    if (end[2] == 0){
                        if (MainPanel.map[2][2][6] == 0 && MainPanel.map[2][2][8] == 0){
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(5,5,6);
                            StartPanel.mainPanel.openDoor(9,5,6);
                            StartPanel.mainPanel.openDoor(5,8,6);
                            StartPanel.mainPanel.openDoor(9,8,6);
                            StartPanel.mainPanel.openDoor(5,11,6);
                            StartPanel.mainPanel.openDoor(9,11,6);
                            end[2] = 1;
                        }
                    }
                    break;
                case 8:
                    if (end[8] == 0){
                        if (MainPanel.map[8][5][11] == 0 && MainPanel.map[8][5][9] == 0){
                            //if (MainPanel.map[8][4][10] != 0)
                            end[8] = 1;
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(10,4,5);
                            //MainPanel.map[8][4][10] = 104;
                        }
                    }
                    break;
                case 10:
                    if (end[10] == 1){
                        if (MainPanel.map[10][4][5] != 44 && MainPanel.map[10][5][5] != 44
                          && MainPanel.map[10][6][5] != 44 && MainPanel.map[10][4][7] != 44
                          && MainPanel.map[10][5][7] != 44 && MainPanel.map[10][6][7] != 44
                          && MainPanel.map[10][4][6] != 45 && MainPanel.map[10][6][6] != 45){
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(6,3,5);
                            end[10] = 0;
                        }
                    }
                    break;
                case 11:
                    if (end[11] == 0){
                        if (MainPanel.map[11][5][1] == 0 && MainPanel.map[11][5][3] == 0){
                            end[11] = 1;
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(2,4,5);
                        }
                    }
                    break;
                case 14:
                    if (end[14] == 0){
                        if (MainPanel.map[14][1][1] == 0 && MainPanel.map[14][1][3] == 0 &&
                                MainPanel.map[14][2][2] == 0){
                            end[14] = 1;
                            StartPanel.mainPanel.threadSleep(1000);
                            new BusinessmanEvent().disappear(1,3,3);
                            StartPanel.mainPanel.threadSleep(1100);
                            MainPanel.map[14][3][1] = 9;
                        }
                    }
                    break;
                case 17:
                    if (end[17] == 0){
                        if (MainPanel.map[17][8][1] == 0 && MainPanel.map[17][8][3] == 0 && guard[0][0] == 0){
                            guard[0][0] = 1;
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(2,7,5);
                        }
                        if (MainPanel.map[17][5][1] == 0 && MainPanel.map[17][5][3] == 0 && guard[0][1] == 0){
                            guard[0][1] = 1;
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(2,4,5);
                        }
                        if (MainPanel.map[17][8][11] == 0 && MainPanel.map[17][8][9] == 0 && guard[0][2] == 0){
                            guard[0][2] = 1;
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(10,7,5);
                        }
                        if (MainPanel.map[17][5][11] ==0 && MainPanel.map[17][5][9] == 0 && guard[0][3] == 0){
                            guard[0][3] = 1;
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(10,4,5);
                        }
                        if (guard[0][1] != 0 && guard[0][3] != 0)
                            end[17] = 1;
                    }
                    break;
                case 19:
                    if (end[19] == 0){
                        if (MainPanel.map[19][3][6] == 0){
                            StartPanel.mainPanel.threadSleep(1100);
                            MainPanel.map[19][3][6] = 33;
                            end[19] = 1;
                        }
                    }
                    break;
                case 23:
                    if (end[23] == 0){
                        if (MainPanel.twentyThreeCount == 43){
                            TreasureUtil.isTwentyNineStart = true;
                            end[23] = 1;
                        }
                    }
                    break;
                case 30:
                    if (end[30] == 0){
                        if (MainPanel.map[30][5][3] == 0 && MainPanel.map[30][5][4] == 0 && MainPanel.map[30][5][5] == 0
                        && MainPanel.map[30][5][7] == 0 && MainPanel.map[30][5][8] == 0 && MainPanel.map[30][5][9] == 0){
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(6,4,5);
                            end[30] = 1;
                        }
                    }
                case 32:
                    if (end[32] == 0){
                        if (MainPanel.map[32][10][1] == 0 && MainPanel.map[32][10][3] == 0){
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(2,9,5);
                            end[32] = 1;
                        }
                    }
                    break;
                case 33:
                    if (end[33] == 0 && MainPanel.heroX == 10 && MainPanel.heroY == 5){
                        MainPanel.map[33][4][10] = 5;
                        MainPanel.map[33][8][10] = 5;
                        end[33] = 1;
                    }
                    if (end[33] == 1){
                        if (MainPanel.map[33][5][9] == 0 && MainPanel.map[33][5][11] == 0 &&
                                MainPanel.map[33][7][9] == 0 && MainPanel.map[33][7][11] == 0){
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(10,4,5);
                            StartPanel.mainPanel.openDoor(10,8,5);
                            end[33] = 2;
                        }
                    }
                    break;
                case 34:
                    if (end[34] == 0){
                        if (MainPanel.map[34][4][5] == 0 && MainPanel.map[34][4][7] == 0
                        && MainPanel.map[34][4][9] == 0 && MainPanel.map[34][4][11] == 0
                        && MainPanel.map[34][8][5] == 0 && MainPanel.map[34][8][7] == 0
                        && MainPanel.map[34][8][9] == 0 && MainPanel.map[34][8][11] == 0){
                            StartPanel.mainPanel.threadSleep(1000);
                            MainPanel.map[34][5][1] = 7;
                            MainPanel.map[34][5][3] = 7;
                            MainPanel.map[34][7][1] = 7;
                            MainPanel.map[34][7][3] = 7;
                            MainPanel.map[34][6][2] = 9;
                            end[34] = 1;
                        }
                    }
                    break;
                case 38:
                    if (end[38] == 0){
                        if (MainPanel.map[38][10][1] == 0 && MainPanel.map[38][10][3] == 0){
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(2,9,5);
                            end[38] = 1;
                        }
                    }
                    break;
                case 39:
                    if (end[39] == 0){
                        if (MainPanel.map[39][2][4] == 0 && MainPanel.map[39][4][6] == 0
                        && MainPanel.map[39][2][2] != 0 && MainPanel.map[39][2][6] != 0
                        && MainPanel.map[39][4][2] != 0 && MainPanel.map[39][6][2] != 0
                        && MainPanel.map[39][6][4] != 0 && MainPanel.map[39][6][6] != 0){
                            StartPanel.mainPanel.threadSleep(500);
                            StartPanel.mainPanel.openDoor(4,4,2);
                            StartPanel.mainPanel.threadSleep(500);
                            MainPanel.map[39][4][4] = 36;
                            end[39] = 1;
                        }
                    }
                    break;
                case 41:
                    if (end[41] == 1){
                        if (MainPanel.map[41][2][10] == 0){
                            StartPanel.mainPanel.threadSleep(1000);
                            MainPanel.map[41][5][6] = 37;
                            MainPanel.map[41][6][5] = 1;
                            MainPanel.map[41][6][6] = 1;
                            MainPanel.map[41][6][7] = 1;
                            MainPanel.map[41][7][5] = 0;
                            MainPanel.map[41][7][6] = 0;
                            MainPanel.map[41][7][7] = 0;
                            end[41] = 0;
                        }
                    }
                    break;
                case 44:
                    if (end[44] == 0){
                        if (MainPanel.map[44][9][5] == 0 && MainPanel.map[44][9][7] == 0){
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(6,8,5);
                            end[44] = 1;
                        }
                    }
                    break;
                case 49:
                    if (end[49] == 0){
                        if (MainPanel.map[49][10][5] == 0 && MainPanel.map[49][10][7] == 0 && guard[2][0] == 0){
                            guard[2][0] = 1;
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(6,9,5);
                        }
                        if (MainPanel.map[49][8][5] == 0 && MainPanel.map[49][8][7] == 0 && guard[2][1] == 0){
                            guard[2][1] = 1;
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.openDoor(6,7,5);
                        }
                        if (guard[2][0] == 1 && guard[2][1] == 1)
                            end[49] = 1;
                    }
                    if (end[49] == 1){
                        if (MainPanel.map[49][2][6] == 0 && MainPanel.map[49][3][5] == 0
                        && MainPanel.map[49][3][7] == 0 && MainPanel.map[49][4][6] == 0
                        && MainPanel.map[49][2][5] != 0 && MainPanel.map[49][2][7] != 0
                        && MainPanel.map[49][4][5] != 0 && MainPanel.map[49][4][7] != 0){
                            StartPanel.mainPanel.threadSleep(1000);
                            StartPanel.mainPanel.startDialog(ImageUtil.monsterMap.get(78)[0], MsgUtil.fortyNineMonster2);
                            MainPanel.map[49][3][6] = 78;
                            end[49] = 2;
                        }
                    }
                    break;
            }
            if (end[49] == 2 && MainPanel.map[49][3][6] == 0)
                break;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
