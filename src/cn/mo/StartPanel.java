package cn.mo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements KeyListener{
	ImageIcon kaishi = new ImageIcon("src\\开始.jpg");
    Image kai = kaishi.getImage();
    String mingLing[] = {"开始游戏","继续游戏","退出"};
    int mingNum = 0;
    public boolean Decide;
    //MoFrame startmf;
	public StartPanel() {
		//this.startmf = mf;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(kai,0,0,465,325,this);
		g.setColor(Color.BLUE);
		if(mingNum == 0){
			g.drawRect(190, 158, 100, 30);
		}
		else if(mingNum == 1){
			g.drawRect(190, 205, 100, 30);
		}
		else{
			g.drawRect(190, 250, 100, 30);
		}
		//g.drawRect(200, 200, 80, 30);
	}
	
	public boolean getDecide() {
		return Decide;
	}
	
	public void keyPressed(KeyEvent ke) {
		switch(ke.getKeyCode()){
		case KeyEvent.VK_UP:
			if(mingNum == 0){
				break;
			}
			mingNum--;
			break;
		case KeyEvent.VK_DOWN:
			if(mingNum == 2){
				break;
			}
			mingNum++;
			break;
		
			
		case KeyEvent.VK_ENTER:
			Decide = true;
			//setDecide();
			break;
		}
		//System.out.println(Decide);
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
