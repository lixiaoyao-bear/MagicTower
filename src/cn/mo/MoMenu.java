package cn.mo;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MoMenu extends JMenu{

	public JMenuBar jmb = new JMenuBar();
	private JMenu jm1 = new JMenu("��ʼ");
	private JMenu jm2 = new JMenu("����");
	private JMenu jm3 = new JMenu("���");
	private JMenuItem jmi11 = new JMenuItem("��ʼ��Ϸ");
	private JMenuItem jmi12 = new JMenuItem("������Ϸ");
	private JMenuItem jmi13 = new JMenuItem("�˳���Ϸ");
	private JMenuItem jmi21 = new JMenuItem("��˹�");
	private JMenuItem jmi22 = new JMenuItem("�㻹Ҫ������");
	private JMenuItem jmi23 = new JMenuItem("��Ա");
	private JMenuItem jmi31 = new JMenuItem("����");
	private JMenuItem jmi32 = new JMenuItem("����");
	private JMenuItem jmi33 = new JMenuItem("����");
	
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
