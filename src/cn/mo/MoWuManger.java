package cn.mo;

public class MoWuManger {

	private int[][] wumap = new int[13][13];
	MoWu[] mwu = new MoWu[40];
	
	public void setwu(int i,String wn,int wg,int wf,int wl,int wo,int wy,int wb,int wr,int wt,Boolean iss) {
		MoWu mw = new MoWu(wn,wg,wf,wl,wo,wy,wb,wr,wt,iss);
		mwu[i] = mw;
	}//��������Ϊ��š����֡�������������������������Ǯ����Կ�ס���Կ�ס���Կ�ס�ʹ�ô������Ƿ�Ϊ������Ʒ
	
	public void wuman(int[][] wmap,int y,int x) {
		this.wumap = wmap;
		switch(wumap[y][x]) {
		case 7 :
			setwu(7,"����Կ��",0,0,0,0,1,0,0,1,false);
			break;
		case 8 :
			setwu(8,"����Կ��",0,0,0,0,0,1,0,1,false);
			break;
		case 9 :
			setwu(9,"�Ⱥ�Կ��",0,0,0,0,0,0,1,1,false);
			break;
		case 10 :
			setwu(10,"�챦ʯ",1,0,0,0,0,0,0,1,false);
			break;
		case 11 :
			setwu(11,"����ʯ",0,1,0,0,0,0,0,1,false);
			break;
		case 12 :
			setwu(12,"СѪƿ",0,0,50,0,0,0,0,1,false);
			break;
		case 13 :
			setwu(13,"��Ѫƿ",0,0,200,0,0,0,0,1,false);
			break;
		case 14 :
			setwu(14,"����Ѫƿ",0,0,400,0,0,0,0,1,false);
			break;
		case 15 :
			setwu(15,"����",10,0,0,0,0,0,0,1,false);
			break;
		case 16 :
			setwu(16,"����",0,10,0,0,0,0,0,1,false);
			break;
		case 17 :
			setwu(17,"����",0,20,0,0,0,0,0,1,false);
			break;
		case 18 :
			setwu(18,"��ʥ��",100,0,0,0,0,0,0,1,true);
			break;
		case 19 :
			setwu(19,"����",20,0,0,0,0,0,0,1,false);
			break;
		case 20 :
			setwu(20,"��ʿ��",40,0,0,0,0,0,0,1,false);
			break;
		case 21 :
			setwu(21,"��ʥ��",0,100,0,0,0,0,0,1,false);
			break;
		case 22 :
			setwu(22,"ʥ��",50,0,0,0,0,0,0,1,false);
			break;
		case 24 :
			setwu(24,"ʥ��",0,50,0,0,0,0,0,1,false);
			break;
		case 25 :
			setwu(25,"�����",0,0,0,0,0,0,0,1,true);
			break;
		case 26 :
			setwu(26,"������",0,0,0,0,0,0,0,1,true);
			break;
		case 27 :
			setwu(27,"�������",0,0,0,0,0,0,0,1,true);
			break;
		case 28 :
			setwu(28,"ըҩ",0,0,0,0,0,0,0,1,true);
			break;
		}
	}
}
