package cn.mo;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class MoDialog  {

	public JTextArea ta = new JTextArea();
	String message;
	JDialog keyd = new JDialog();
	
	public MoDialog() {
		//super();
		keyd.setBounds(650, 280, 200, 200);
		keyd.add(ta);
		//ta.setSelectedTextColor(Color.RED);
		ta.setLineWrap(true);
		//ta.setBackground(Color.BLUE);
		ta.setEditable(false);
		
		ta.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					keyd.dispose();
				}
			}
		});
	}
	
	/*public MoDialog(MoJpanel moJpanel,Boolean model) {
		super();
		this.setBounds(650, 280, 200, 200);
		this.add(ta);
		//ta.setSelectedTextColor(Color.RED);
		ta.setLineWrap(true);
		
		ta.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//message = "�ҿ��Ը�������ֲᣬ������ÿ�ݼ�Hȥʹ�����������԰�����Ĺ�������";
					ta.setText(message);
					//this.setVisible(false);
					dispose();				
				}
			}
		});
	}*/
	
	public void setmessage(String m) {
		this.message = m;
	}
}
