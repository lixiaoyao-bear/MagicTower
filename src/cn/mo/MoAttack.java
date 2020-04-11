package cn.mo;

import java.awt.*;

import javax.swing.*;

public class MoAttack extends JPanel implements Runnable{
	private MoZhu mzhu;
	//private MoGuaiManger gwManger;
	private MoGuai moGuai;
	private MoSound moSound = new MoSound();
	private MoJpanel moJpanel;
	private Image attackBackground = Toolkit.getDefaultToolkit().getImage("src\\zbg.png");
	//private MoGuai attackguai = new MoGuai();	
	Boolean isAttackEnd = true;
	int whoAttack = 0;
	private Image guai;
	private Image renwu;
	private int glife;

	public MoAttack(Image guai,Image renwu,MoZhu mzhu,MoGuai moGuai){
		this.guai = guai;
		this.renwu = renwu;
		this.mzhu = mzhu;
		this.moGuai = moGuai;
		//this.setSize(new Dimension(325, 250));
		//this.glife = this.moGuai.getglife();
	}

	public MoAttack(MoJpanel moJpanel){
		this.moJpanel = moJpanel;
	}

	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(attackBackground,0,0,325,250,0,0,400,250,null);
		g.drawImage(guai, 17,65, 61, 69,null);
		g.drawImage(renwu, 252,65,313,134,0,0,32,32,null);
		g.setColor(Color.RED);
		g.drawString(""+mzhu.getzlife(), 188, 80);
		g.drawString(""+mzhu.getzgong(), 188, 104);
		g.drawString(""+mzhu.getzfang(), 188, 126);
		//System.out.println("life");
		g.drawString(""+moGuai.getglife(), 125, 80);
		g.drawString(""+moGuai.getggong(), 125, 104);
		g.drawString(""+moGuai.getgfang(), 125, 126);
	}

	public void draw(Graphics g) {
		/*g.drawString("¹¥»÷£º"+mzhu.getzgong(), 350, 80);
		g.drawString("·ÀÓù£º"+mzhu.getzfang(), 350, 100);
		g.drawString("ÉúÃü£º"+mzhu.getzlife(), 350, 120);
		g.drawString("½ð±Ò£º"+mzhu.getzgold(), 350, 140);
		g.drawString("»ÆÔ¿³×£º"+mzhu.getyellowkey(), 350, 160);
		g.drawString("À¶Ô¿³×£º"+mzhu.getbluekey(), 350, 180);
		g.drawString("ºìÔ¿³×£º"+mzhu.getredkey(), 350, 200);*/
		
		g.drawImage(attackBackground,0,0,330,250,0,0,480,300,null);
		g.setColor(Color.RED);
		g.drawString(""+mzhu.getzlife(), 110, 70);
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			moSound.bofang("actorgj.wav");
			if(mzhu.getzgong() > moGuai.getgfang()){
				moGuai.setglife(moGuai.getglife() - (mzhu.getzgong() - moGuai.getgfang()));
				if(moGuai.getglife() <= 0){
					moGuai.setglife(0);
					repaint();
					break;
				}
			}
			repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			moSound.bofang("guaiwugj.wav");
			if(moGuai.getggong() > mzhu.getzfang()){
				mzhu.setzlife(mzhu.getzlife() - (moGuai.getggong() - mzhu.getzfang()));
				if(mzhu.getzlife() <= 0){
					mzhu.setzlife(0);
					repaint();
					JOptionPane.showMessageDialog(null,"É§Äê£¬Äã»¹ÊÇÌ«ÈõÁË£¡");
					break;
				}
			}
			repaint();
		}
	}

	public MoZhu getMzhu() {
		return mzhu;
	}
}
