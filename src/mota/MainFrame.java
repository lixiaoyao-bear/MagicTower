package mota;

import util.ImageUtil;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        this.setTitle("ħ��");
        this.setBounds(400,200,632,448);
        this.setIconImage(ImageUtil.tuBiao); //���ô������Ͻ�ͼ��
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
