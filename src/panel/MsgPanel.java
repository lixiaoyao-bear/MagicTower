package panel;

import mota.MoTa;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** 消息提示面板类*/
public class MsgPanel extends JLayeredPane {

    public JLabel bg;
    public JLabel msg;

    public MsgPanel(String text){
        //this.setBounds(20,100,558,67);    //62  9
        MainPanel.isMove = false;
        bg = new JLabel();
        bg.setIcon(new ImageIcon(ImageUtil.msgMap.get(8)));
        msg = new JLabel();
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        msg.setForeground(Color.WHITE);
        msg.setFont(new Font("宋体",Font.PLAIN,28));
        msg.setBounds(0,0,558,67);
        bg.setBounds(0,0,558,67);
        this.add(bg,1,0);
        this.add(msg,2,0);
        this.setOpaque(true);
        this.setVisible(true);
        //提示信息面板动画
        new Timer(50, new ActionListener() {
            int count = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count <= 9) {
                    MainPanel.msgPanel.setBounds(299-count*31,100,count*62,67);
                    bg.setBounds(0,0,count * 62,67);
                    msg.setBounds(0,0,count * 62,67);
                }else {
                    MainPanel.msgPanel.setBounds(20,100,558,67);
                    bg.setBounds(0,0,558,67);
                    msg.setBounds(0,0,558,67);
                }

                msg.setText(text);
                MoTa.mainFrame.repaint();
                count++;
                if (count > 18) {
                    StartPanel.mainPanel.remove(MainPanel.msgPanel);
                    MainPanel.attackPanel = null;
                    MainPanel.isMove = true;
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();
    }
}
