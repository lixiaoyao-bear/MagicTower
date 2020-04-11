package cn.mo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoFrame extends JFrame {

	private MoMenu mmenu = new MoMenu();//this
	private static MoJpanel mpanel = new MoJpanel();
	private static MoKey mkey = new MoKey(mpanel);
	//private MoThread frameThread = new MoThread();
	private int mousex;
	private int mousey;
	//boolean frameDecide = false;
	MoSound msound = new MoSound();
	static MoEvent frameEvent = new MoEvent();
	public boolean frameDecide;
	
	public MoFrame() {
		
		this.setTitle("魔塔");
		//this.setBounds(400, 200, 525, 390);
		this.setBounds(400, 200, 480, 390);
		Toolkit tk = this.getToolkit();       
		Image tubiao = tk.getImage("src\\mo.jpg");    //获取图标图像
		Image tu = tubiao.getScaledInstance(900,900, Image.SCALE_DEFAULT);  //缩放图标
		this.setIconImage(tu);
		/*ImageIcon ks = new ImageIcon("src\\开始.jpg");
		//Image ks = tk.getImage("src\\开始.jpg");
		JLabel jl = new JLabel(ks);
		this.add(jl);
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mousex = e.getX();
			}
		});*/
		this.setJMenuBar(mmenu.jmb);
		//this.add(mpanel);
		
		//this.addKeyListener(mpanel);
		//this.addKeyListener(mkey);
		//mpanel.jump(1,0,0);
		//msound.loadSound();
		//msound.sound.play();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		//StartPanel sp = new StartPanel();
		MoFrame mf = new MoFrame();
		mf.add(mpanel);
		mf.addKeyListener(mpanel);
		//mf.addKeyListener(mkey);
		mpanel.jump(1,0,0);
	}
}
