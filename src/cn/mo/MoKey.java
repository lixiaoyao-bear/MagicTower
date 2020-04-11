package cn.mo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


public class MoKey implements KeyListener{
	
	private MoJpanel keypanel = new MoJpanel();
	int[] isguai =new int[40];
	
	public MoKey(MoJpanel mj) {
		this.keypanel = mj;
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_F1 :
			if(keypanel.mzhu.special[0] == 1) {
				MoDialog keyd = new MoDialog();
				keyd.keyd.setBounds(400, 300, 340, 240);
				keyd.ta.setText("");
				for(int i = 0;i < 13;i++){
					for(int j = 0;j < 13;j++) {
						if(keypanel.map1[i][j] >= 40 && keypanel.map1[i][j] < 80 && this.isguai[keypanel.map1[i][j]-40] == 0) {
							keypanel.mgmanger.guai(keypanel.map1, i, j);
							this.isguai[keypanel.map1[i][j]-40] = 1;
						    keyd.ta.append(keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getgname()+"   攻击："
						    		+keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getggong()+"    防御："
						    		+keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getgfang()+"    生命："
						    		+keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getglife()+"    金币："
						    		+keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getggold()+"\r\n");
						}
					}
				}
				Arrays.fill(isguai, 0);
				keyd.keyd.setVisible(true);
			}
			/*keypanel.md.ta.setText("");
			if(keypanel.panelspecial[0] == 1) {
				for(int i = 0;i < 13;i++){
					for(int j = 0;j < 13;j++) {
						if(keypanel.map1[i][j] > 40 && keypanel.map1[i][j] < 80 && this.isguai[keypanel.map1[i][j]-40] == 0) {
							keypanel.mgmanger.guai(keypanel.map1, i, j);
							this.isguai[keypanel.map1[i][j]-40] = 1;
						    keypanel.md.keyd.setBounds(400, 300, 340, 240);
						    keypanel.md.ta.append(keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getgname()+"   攻击："
						    		+keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getggong()+"    防御："
						    		+keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getgfang()+"    生命："
						    		+keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getglife()+"    金币："
						    		+keypanel.mgmanger.mguai[keypanel.map1[i][j]-40].getggold()+"\r\n");
						}
					}
				}
				Arrays.fill(isguai, 0);
				keypanel.md.keyd.setVisible(true);
			}*/
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
}
