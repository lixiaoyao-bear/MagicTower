package panel;

import mota.MoTa;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.VK_SPACE;

/**  对话面板类*/
public class DialogPanel extends JLayeredPane implements KeyListener {

    public JTextArea msg;
    public JLabel bg;
    public JLabel icon;
    public ImageIcon hero;
    public ImageIcon img;
    public String[] message;
    public int index;
    public boolean isSpace;
    public volatile boolean isDialogEnd;

    public DialogPanel(ImageIcon imageIcon){
        this.setBounds(32,200,354,170);
        bg = new JLabel();
        bg.setIcon(new ImageIcon(ImageUtil.otherMap.get(107)));
        bg.setBounds(0,0,354,170);
        bg.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 4));
        icon = new JLabel();
        img = imageIcon;
        hero = new ImageIcon(ImageUtil.heroMap.get(2)[0]);
        icon.setIcon(img);
        icon.setBounds(10,10,32,32);
        msg = new JTextArea();
        msg.setBounds(10,50,334,120);
        msg.setFont(new Font("宋体",Font.PLAIN,17));
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);
        msg.setEditable(false);
        msg.setOpaque(false);
        msg.setFocusable(false);
        msg.setForeground(Color.WHITE);
        index = 0;
        isSpace = true;
        isDialogEnd = false;
        this.add(bg,1,0);
        this.add(icon,2,0);
        this.add(msg,3,0);
        this.setOpaque(true);
        this.setVisible(true);
    }

    /**  判断对话是否结束，结束之后启动动画*/
    public void dialogEnd(Timer timer){
        new Thread(() -> {
            while (true){
                if (isDialogEnd){
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (isSpace) {
            if (e.getKeyCode() == VK_SPACE) {
                index++;
                //判断对话是否结束
                if (index >= message.length) {
                    index = 0;
                    isDialogEnd = true;
                    this.setVisible(false);
                    MainPanel.isMove = true;
                    MoTa.mainFrame.removeKeyListener(this);
                    StartPanel.mainPanel.remove(this);
                } else {
                    //更换对话图标
                    if (index % 2 == 0)
                        icon.setIcon(img);
                    else
                        icon.setIcon(hero);
                    msg.setText(message[index]);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
