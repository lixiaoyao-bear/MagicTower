package mota;

import panel.StartPanel;

public class MoTa {

    public static MainFrame mainFrame;

    public static void main(String[] args) {
        mainFrame = new MainFrame();
        StartPanel startPanel = new StartPanel();
        startPanel.labelEvent();
        mainFrame.add(startPanel);
        mainFrame.setVisible(true);
    }
}
