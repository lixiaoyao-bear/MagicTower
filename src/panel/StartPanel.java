package panel;

import mota.MoTa;
import util.ImageUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Image.SCALE_DEFAULT;

public class StartPanel extends JPanel {

    public JLabel bgLabel;
    public JLabel beginLabel;
    public JLabel setLabel;
    public JLabel leaveLabel;
    public ImageIcon bgIcon;
    public ImageIcon beginIcon1;
    public ImageIcon setIcon1;
    public ImageIcon leaveIcon1;
    public ImageIcon beginIcon2;
    public ImageIcon setIcon2;
    public ImageIcon leaveIcon2;

    public static MainPanel mainPanel;

    public StartPanel(){
        bgIcon = new ImageIcon(ImageUtil.bg.getScaledInstance(632,448,SCALE_DEFAULT));
        beginIcon1 = new ImageIcon(ImageUtil.xyx1);
        setIcon1 = new ImageIcon(ImageUtil.sz1);
        leaveIcon1 = new ImageIcon(ImageUtil.tc1);
        beginIcon2 = new ImageIcon(ImageUtil.xyx2);
        setIcon2 = new ImageIcon(ImageUtil.sz2);
        leaveIcon2 = new ImageIcon(ImageUtil.tc2);
        bgLabel = new JLabel(bgIcon);
        beginLabel = new JLabel(beginIcon1);
        setLabel = new JLabel(setIcon1);
        leaveLabel = new JLabel(leaveIcon1);
        bgLabel.setBounds(0,0,632,448);
        beginLabel.setBounds(245,220,125,30);
        setLabel.setBounds(245,260,125,30);
        leaveLabel.setBounds(245,300,125,30);
        this.setLayout(null);
        this.add(beginLabel);
        this.add(setLabel);
        this.add(leaveLabel);
        this.add(bgLabel);
    }

    /* 开始界面图标更换事件**/
    public void labelEvent(){
        beginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainPanel = new MainPanel();
                mainPanel.setBounds(0,0,632,416);
                MoTa.mainFrame.add(mainPanel);
                MoTa.mainFrame.addKeyListener(mainPanel);
                MoTa.mainFrame.setContentPane(mainPanel);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                beginLabel.setIcon(beginIcon2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                beginLabel.setIcon(beginIcon1);
            }
        });
        setLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null,"暂时无法设置");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setLabel.setIcon(setIcon2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setLabel.setIcon(setIcon1);
            }
        });
        leaveLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                leaveLabel.setIcon(leaveIcon2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                leaveLabel.setIcon(leaveIcon1);
            }
        });
    }
}
