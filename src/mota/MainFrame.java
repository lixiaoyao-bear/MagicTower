package mota;

import util.ImageUtil;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        this.setTitle("魔塔");
        this.setBounds(400,200,632,448);
        this.setIconImage(ImageUtil.tuBiao); //设置窗体左上角图标
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
