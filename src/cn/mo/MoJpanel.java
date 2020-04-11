package cn.mo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MoJpanel extends JPanel implements KeyListener {

	private Image [] guaiwu;   //�洢����ͼƬ
	private Image [] wuping;   //�洢��ƷͼƬ
	private Image [] renwu;    //�洢����ͼƬ
	private Image backGround;
	Image attackBackground = Toolkit.getDefaultToolkit().getImage("src\\zbg.png");    //�洢ս������ͼ
	private MoMap mmap;    //��ʼ��ͼ�࣬���ڵ�ͼ֮�����
	private MoSave msave = new MoSave();  //���������е�ͼ�洢�����
	MoZhu mzhu = new MoZhu();   //�����࣬����������ز���
	//private MoAttack mattack = new MoAttack();
	MoGuaiManger mgmanger = new MoGuaiManger();   //��������࣬���ڹ�����ز���
	MoWuManger mwmanger = new MoWuManger();     //��Ʒ�����࣬������Ʒ��ز���
	int[][] map1 = new int[13][13];        //��ͼ����
	private int zhux,zhuy;          //��������x,y����
	private int direction = 0;      //�������߷���
	private int count = 0;         //�������߲���
	private int panelfloor = 1;    //������е�¥�����
	private int[] bigguai = new int[3];   //�洢ռ�ݵ�ͼ������ӵĴ����
	private int[] ispanelfloor = new int[50];
	private int[] iswu = new int[40];
	MoDialog md = new MoDialog();    //�Ի�����
	private MoNpc mnpc = new MoNpc();  //NPC��
	private MoSound msound = new MoSound();  //�����࣬����������ز���
	private MoShop mshop = new MoShop();    //�̵��࣬�����̵����
	private Boolean isAttack = false;    //�ж��Ƿ���ս��״̬
	//private MoThread mthread = new MoThread(this);
	Boolean isEnd = false;    //�ж��Ƿ��ܹ��߶�
	Boolean isAttactEnd = false;   //�ж��Ƿ�ս������
	boolean isStart = false;
	//ImageIcon kaishi = new ImageIcon("D:\\IDEA��Ŀ����\\MoTa\\src\\��ʼ.jpg");
	//this.getClass().getClassLoader().getResource("��ʼ.jpg")
	ImageIcon kaishi = new ImageIcon("src\\��ʼ.jpg");
    Image kai = kaishi.getImage();
    String mingLing[] = {"��ʼ��Ϸ","��Ϸ˵��","�˳�"};
    
    boolean isPanelThree = true;
    boolean isPaintThree = false;
    MoEvent panelEvent = new MoEvent();
    int mingNum = 0;
	
	int glife = 0;   //������������
	int a;     //������
	
	public MoJpanel() {             //���캯����ʼ���������ͼƬ
		//MoSound msound = new MoSound();
		//msound.loadSound();
		//msound.sound.loop();
		wuping = new Image[40];
		renwu = new Image[40];
		guaiwu = new Image[40];
		//backGround = Toolkit.getDefaultToolkit().getImage("D:\IDEA��Ŀ����\MoTa\src\\b.png");
		//backGround = Toolkit.getDefaultToolkit().getImage("D:\IDEA��Ŀ����\MoTa\src\\bf.jpg");
		for(int i = 0;i <= 36;i++) {
			//if(Toolkit.getDefaultToolkit().getImage("src\\��Ʒ\\" + i +".png") != null)
			wuping[i] = Toolkit.getDefaultToolkit().getImage("src\\��Ʒ\\" + i +".png");
			//if(Toolkit.getDefaultToolkit().getImage("src\\����NPC\\" + i +".png") != null)
			renwu[i] = Toolkit.getDefaultToolkit().getImage("src\\����NPC\\" + i +".png");
			guaiwu[i] = Toolkit.getDefaultToolkit().getImage("src\\����\\" + i +".png");
		}
		
		//threeDialog.setText("gerwhg");
	}
	
	public void musicBF(String m) {       //���ּ���
		msound.setMusic(m);
		msound.loadSound();
		msound.sound.play();
	}
	
	public void jump(int f,int c,int isf) {    //¥��֮����Ծ
		mmap = new MoMap(f,c,isf);
		this.panelfloor = mmap.floor;
		this.ispanelfloor[f] = mmap.ismapfloor;
		this.map1 = mmap.map;	
		this.zhux = mmap.zx;
		this.zhuy = mmap.zy;
		//msound.sound.stop();	
		//System.out.println("hey jump");
		repaint();
		
	}
	
	public void paint(Graphics g) {      //����ͼƬ
		super.paint(g);
		if(!this.isStart) {
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
		}
		else{
			g.drawImage(backGround,325,0,465,350,0,30,124,400,this);
			for(int p = 0;p < 13;p++) {
			    for(int q = 0;q < 13;q++) {
			    	if(map1[p][q] < 40) {
			    		if(map1[p][q] == 36 && bigguai[1] == 1) {
			    			continue; 	    			
				    	}
			    		if(map1[p][q] == 36 && bigguai[1] == 0) {
				    		g.drawImage(wuping[map1[p][q]], q*25,p*25, q*25+75,p*25+25,0,0,96,32,this);
			    			bigguai[1] = 1;
			    			continue;
				    	}
			    		g.drawImage(wuping[map1[p][q]], q*25,p*25, 25, 25,this);
			    	}
			    	if(map1[p][q] >= 40 && map1[p][q] < 80) {	
			    		if((map1[p][q] == 55 || map1[p][q] == 61) && bigguai[0] == 1) {
			    			continue; 	    			
				    	}
			    		if((map1[p][q] == 55 || map1[p][q] == 61) && bigguai[0] == 0) {
				    		g.drawImage(guaiwu[map1[p][q]-40], q*25,p*25, q*25+75,p*25+75,0,0,96,96,this);
			    			bigguai[0] = 1;
			    			continue;
				    	}
			    		g.drawImage(guaiwu[map1[p][q]-40], q*25,p*25, 25, 25,this);
			    	}
			    	if(map1[p][q] == 80) {
			    		g.drawImage(renwu[0], q*25,p*25,q*25+25,p*25+25,count*32,direction*33,count*32+32,direction*32+32,new Color(85, 85, 85),this);
			    	}
			    	if(map1[p][q] > 80) {
			    		g.drawImage(renwu[map1[p][q]-80], q*25,p*25, 25, 25,this);
			    	}
			    }
		    }
			/*if(this.isAttack) {   //ս��ͼ����
				//mattack.draw(g);
				g.drawImage(attackBackground,0,0,325,250,0,0,400,250,null);
				g.drawImage(guaiwu[a], 17,65, 61, 69,null);
				g.drawImage(renwu[0], 252,65,313,134,0,0,32,32,null);
				g.setColor(Color.RED);
				g.drawString(""+mzhu.getzlife(), 188, 80);
				g.drawString(""+mzhu.getzgong(), 188, 104);
				g.drawString(""+mzhu.getzfang(), 188, 126);
				//System.out.println("life");
				g.drawString(""+glife, 125, 80);
				g.drawString(""+mgmanger.mguai[a].getggong(), 125, 104);
				g.drawString(""+mgmanger.mguai[a].getgfang(), 125, 126);
			}*/
			
			Arrays.fill(bigguai, 0);
			g.setColor(Color.BLACK);
			this.draw(g);
			this.threeDraw(g);
			g.drawString("��    "+this.panelfloor+"   ��", 350, 250);
		}
		//System.out.println("hei");
	}
	
	public void draw(Graphics g) {    //�����Ϣ����
		g.drawString("��    "+this.panelfloor+"   ��", 350, 250);
		g.drawString("������"+mzhu.getzgong(), 350, 80);
		g.drawString("������"+mzhu.getzfang(), 350, 100);
		g.drawString("������"+mzhu.getzlife(), 350, 120);
		g.drawString("��ң�"+mzhu.getzgold(), 350, 140);
		g.drawString("����Կ�ף�"+mzhu.getyellowkey(), 350, 160);
		g.drawString("����Կ�ף�"+mzhu.getbluekey(), 350, 180);
		g.drawString("�Ⱥ�Կ�ף�"+mzhu.getredkey(), 350, 200);
	}
	
	public void threeDraw(Graphics g) {
		if(this.isPaintThree) {
			Image moWang =Toolkit.getDefaultToolkit().getImage("D:\\IDEA��Ŀ����\\MoTa\\src\\����\\30.png");
			JLabel threeDialog = new JLabel();
			this.add(threeDialog);
			g.drawImage(moWang, 25*5, 25*7,25,25, this);
			this.setLayout(null);
			threeDialog.setBounds(100, 200, 150, 100);
			threeDialog.setOpaque(true);
			threeDialog.setBackground(Color.BLUE);
			threeDialog.setText("<html><body>ħ��������һ��������,���챾������ã����㲻���������е��Ļ������������һ����㣡</body></html>");
			this.isPaintThree = false;
			
			/*try {
				repaint();
				TimeUnit.MILLISECONDS.sleep(5000);
				jump(1,0,1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}
	}
	
	public void keyPressed(KeyEvent ke) {    //�����¼���������������
		if(!this.isEnd) {
		switch(ke.getKeyCode()) {
		case KeyEvent.VK_UP :
			if(walkall(zhuy-1,zhux,3))
			    zhuy--;
			break;
		case KeyEvent.VK_DOWN :
			if(walkall(zhuy+1,zhux,0))
			    zhuy++;
			break;
		case KeyEvent.VK_LEFT :
			if(walkall(zhuy,zhux-1,1))
			    zhux--;
			break;
		case KeyEvent.VK_RIGHT :
			if(walkall(zhuy,zhux+1,2))
			    zhux++;
			break;
		case KeyEvent.VK_S:
			if(mingNum == 2){
				break;
			}
			mingNum++;
			break;
		case KeyEvent.VK_W:
			if(mingNum == 0){
				break;
			}
			mingNum--;
			break;
		case KeyEvent.VK_ENTER:
			if(mingLing[mingNum] == "��ʼ��Ϸ") {
				isStart = true;
			}
			else if(mingLing[mingNum] == "��Ϸ˵��") {
				JOptionPane.showMessageDialog(this,"����������������ң���ȡ��ͬԿ�׿�����ͬ��ɫ���ţ�");
			}
			else {
				System.exit(0);
			}
			//setDecide();
			break;
		}
		//System.out.println("hey keypressed");
		repaint();
		msave.save(map1,mmap.floor);
		}
	}
	
	public void walk(int y,int x,int d) {   //�������߶�ͼ����
		map1[y][x] = 80;
		map1[zhuy][zhux] = 0;
		this.direction = d;
		if(count == 3) {
			count = 0;
		}else {
			count++;
		}
	}
	
	public void floor(int y,int x,int c,int isf) {   //¥����ز���
		if(c == 0) {
			this.panelfloor++;
		}else {
			this.panelfloor--;
		}
		map1[y][x] = 80;
		map1[zhuy][zhux] = 0;
		jump(this.panelfloor,c,isf);
	}
	
	public void getwu(int a) {          //��ȡ����Ʒ������¥�������ͬ�����ӵĹ���Ҳ��ͬ
		int b = 0;
		if(a >= 10 && a <= 13) 
			b = (int) this.panelfloor / 10 + 1;
		else
			b = 1;
		mzhu.setzgong(mzhu.getzgong() + mwmanger.mwu[a].getwgong() *b);
		mzhu.setzfang(mzhu.getzfang() + mwmanger.mwu[a].getwfang() *b);
		mzhu.setzlife(mzhu.getzlife() + mwmanger.mwu[a].getwlife() *b);
		mzhu.setzgold(mzhu.getzgold() + mwmanger.mwu[a].getwgold() *b);
		mzhu.setyellowkey(mzhu.getyellowkey() + mwmanger.mwu[a].getykey() *b);
		mzhu.setbluekey(mzhu.getbluekey() + mwmanger.mwu[a].getbkey() *b);
		mzhu.setredkey(mzhu.getredkey() + mwmanger.mwu[a].getrkey() *b);
	}
	
	public Boolean walkall(int y,int x,int d) {      //���������ж�
		switch(map1[y][x]) {
		case 0 :            //�����ߵ�����
			walk(y,x,d);
			if(this.isPanelThree) {
				if(this.panelfloor == 3 && y == 8 && x == 5) {
					System.out.println(y);
					this.isPaintThree = true;
					this.isPanelThree = false;
				}
			}
			return true;
		case 2 :          //��ɫ��
			if(mzhu.opendoor(mzhu.getyellowkey())) {
				walk(y,x,d);
				musicBF("kaidoor.wav");
				mzhu.setyellowkey(mzhu.getyellowkey()-1);
				return true;
			}			
			break;
		case 3 :     //��ɫ��
			if(mzhu.opendoor(mzhu.getbluekey())) {
				walk(y,x,d);
				musicBF("kaidoor.wav");
				mzhu.setbluekey(mzhu.getbluekey()-1);
				return true;
			}			
			break;
		case 4 :     //��ɫ��
			if(mzhu.opendoor(mzhu.getredkey())) {
				walk(y,x,d);
				musicBF("kaidoor.wav");
				mzhu.setredkey(mzhu.getredkey()-1);
				return true;
			}			
			break;
		case 30 :     //�̵�
			if(mshop.choise(this))
				this.mzhu = mshop.getZhu();		
			return false;
		/*case 7 :
			walk(y,x,d);
			mzhu.setyellowkey(mzhu.getyellowkey()+1);
			return true;
		case 8 :
			walk(y,x,d);
			mzhu.setbluekey(mzhu.getbluekey()+1);
			return true;
		case 9 :
			walk(y,x,d);
			mzhu.setredkey(mzhu.getredkey()+1);
			return true;*/
		/*case 13 :
			walk(y,x,d);
			wu(0,0,200);
			return true;*/
		case 34 :    //����һ��
			floor(y,x,0,this.ispanelfloor[this.panelfloor+1]);
			musicBF("sxlouti.wav");
			return false;
		case 35 :   //����һ��
			floor(y,x,1,this.ispanelfloor[this.panelfloor-1]);
			musicBF("sxlouti.wav");
			return false;
		default :
			if(map1[y][x] < 40 && map1[y][x] != 1) {        //�����Ʒ
				mwmanger.wuman(map1, y, x);
				musicBF("hdwupin.wav");
				if(this.iswu[map1[y][x]] == 0) {			
					JOptionPane.showMessageDialog(this,"��ϲ����"+mwmanger.mwu[map1[y][x]].getwname());
					this.iswu[map1[y][x]] = 1;
				}
				if(!mwmanger.mwu[map1[y][x]].isspecial()) {
					getwu(map1[y][x]);
					walk(y,x,d);
					return true;
				}			
			}
			if(map1[y][x] >= 40 && map1[y][x] < 80) {   //��������
				a = map1[y][x] - 40;
				mgmanger.guai(map1, y, x);
				//glife = mgmanger.mguai[a].getglife();
                MoAttack moAttack = new MoAttack(guaiwu[a],renwu[0],mzhu,mgmanger.mguai[a]);
                //moAttack.setSize(325,250);
                moAttack.setPreferredSize(new Dimension(325, 250));
                //this.setLayout(null);
                this.add(moAttack);
                Thread attack = new Thread(moAttack);
                attack.start();
                moAttack.revalidate();
                //repaint();
				/*MoThread mt = new MoThread();
				mt.setPriority(10);
				mt.start();
				//Thread.yield();
				if(mzhu.getzlife() <= 0) {
					return false;
				}
				if(glife <= 0) {
					walk(y,x,d);
					//musicBF("actorgj.wav");
					mzhu.setzgold(mzhu.getzgold() + mgmanger.mguai[a].getggold());
					return true;
				}*/
			}
			if(map1[y][x] >= 80) {        //����NPC
				mnpc.say(map1, y, x);
				md.ta.setText(mnpc.getmessage());
				md.keyd.setVisible(true);
				map1[y][x] = 0;
				mzhu.special[0] = 1;
				return false;
			}
			//break;
		}
		return false;
	}	
	
	public void keyReleased(KeyEvent arg0) {		
	}

	public void keyTyped(KeyEvent arg0) {
	}
	
	public void attactLife(int ga,int who) {   //�����ս������������
		if(who == 0) {
			musicBF("actorgj.wav");
			mgmanger.mguai[ga].setglife(mgmanger.mguai[ga].getglife() - (mzhu.getzgong() - mgmanger.mguai[ga].getgfang()));
			glife = mgmanger.mguai[ga].getglife();
			if(glife <= 0) {
				glife = 0;
				isAttactEnd = true;
			}
		}else {
			musicBF("guaiwugj.wav");
			mzhu.setzlife(mzhu.getzlife() - (mgmanger.mguai[ga].getggong() - mzhu.getzfang()));
			if(mzhu.getzlife() <= 0) {
				mzhu.setzlife(0);
				isAttactEnd = true;
			}
		}
	}
	
	public class MoThread extends Thread{     //�ڲ������ս������Ч��
		@Override
		public void run() {
			int w = 0;
			isAttack = true;
			isAttactEnd = false;
			isEnd = true;
			while(!isAttactEnd) {   //!(mzhu.getzlife() <= 0) && !(glife <= 0)
				attactLife(a, w);
				repaint();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//attactLife(a, w);
				if(w == 0)
					w = 1;
				else
					w = 0;
				if(glife <= 0) {
					isAttack = false;
					isEnd = false;
					repaint();
				}
				if(mzhu.getzlife() <= 0) {
					isAttack = false;
					repaint();
					
					JOptionPane.showMessageDialog(null,"ɧ�꣬�㻹��̫���ˣ�");
				}
			}
			isAttactEnd = false;
			
		}
	}
}
