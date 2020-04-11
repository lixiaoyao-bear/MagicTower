package cn.mo;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MoMenu extends JMenu{

	public JMenuBar jmb = new JMenuBar();
	private JMenu jm1 = new JMenu("开始");
	private JMenu jm2 = new JMenu("音乐");
	private JMenu jm3 = new JMenu("相关");
	private JMenuItem jmi11 = new JMenuItem("开始游戏");
	private JMenuItem jmi12 = new JMenuItem("保存游戏");
	private JMenuItem jmi13 = new JMenuItem("退出游戏");
	private JMenuItem jmi21 = new JMenuItem("丑八怪");
	private JMenuItem jmi22 = new JMenuItem("你还要我怎样");
	private JMenuItem jmi23 = new JMenuItem("演员");
	private JMenuItem jmi31 = new JMenuItem("规则");
	private JMenuItem jmi32 = new JMenuItem("介绍");
	private JMenuItem jmi33 = new JMenuItem("背景");
	
	public MoMenu() {  //MoFrame mf
		
		this.add(jmb);
		//mf.setJMenuBar(jmb);
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jm1.add(jmi11);
		jm1.add(jmi12);
		jm1.add(jmi13);
		jm2.add(jmi21);
		jm2.add(jmi22);
		jm2.add(jmi23);
		jm3.add(jmi31);
		jm3.add(jmi32);
		jm3.add(jmi33);
	}
}
