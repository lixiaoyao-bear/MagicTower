package cn.mo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoEvent extends JPanel{

	boolean isThree = false;
	ImageIcon moWang = new ImageIcon("src\\����\\30.png");
	JLabel threeDialog = new JLabel(moWang);
	
	
	public void moWeiZhi() {
		
	}
	
	public void draw(Graphics g) {
		if(isThree) {
			isThree = false;
			threeDialog.setBounds(100, 100, 200, 200);
			threeDialog.setOpaque(true);
			threeDialog.setBackground(Color.BLUE);
			threeDialog.setText("ħ��������һ�������ģ����챾������ã����㲻���������е��Ļ������������һ����㣡");
		}
	}
}
