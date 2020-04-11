package cn.mo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoEvent extends JPanel{

	boolean isThree = false;
	ImageIcon moWang = new ImageIcon("src\\怪物\\30.png");
	JLabel threeDialog = new JLabel(moWang);
	
	
	public void moWeiZhi() {
		
	}
	
	public void draw(Graphics g) {
		if(isThree) {
			isThree = false;
			threeDialog.setBounds(100, 100, 200, 200);
			threeDialog.setOpaque(true);
			threeDialog.setBackground(Color.BLUE);
			threeDialog.setText("魔王：又来一个送死的，今天本座心情好，饶你不死，若你有胆的话，本座在最后一层等你！");
		}
	}
}
