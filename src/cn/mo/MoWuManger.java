package cn.mo;

public class MoWuManger {

	private int[][] wumap = new int[13][13];
	MoWu[] mwu = new MoWu[40];
	
	public void setwu(int i,String wn,int wg,int wf,int wl,int wo,int wy,int wb,int wr,int wt,Boolean iss) {
		MoWu mw = new MoWu(wn,wg,wf,wl,wo,wy,wb,wr,wt,iss);
		mwu[i] = mw;
	}//参数依次为编号、名字、攻击力、防御力、生命、金钱、黄钥匙、蓝钥匙、红钥匙、使用次数、是否为特殊物品
	
	public void wuman(int[][] wmap,int y,int x) {
		this.wumap = wmap;
		switch(wumap[y][x]) {
		case 7 :
			setwu(7,"明黄钥匙",0,0,0,0,1,0,0,1,false);
			break;
		case 8 :
			setwu(8,"深蓝钥匙",0,0,0,0,0,1,0,1,false);
			break;
		case 9 :
			setwu(9,"腥红钥匙",0,0,0,0,0,0,1,1,false);
			break;
		case 10 :
			setwu(10,"红宝石",1,0,0,0,0,0,0,1,false);
			break;
		case 11 :
			setwu(11,"蓝宝石",0,1,0,0,0,0,0,1,false);
			break;
		case 12 :
			setwu(12,"小血瓶",0,0,50,0,0,0,0,1,false);
			break;
		case 13 :
			setwu(13,"大血瓶",0,0,200,0,0,0,0,1,false);
			break;
		case 14 :
			setwu(14,"超级血瓶",0,0,400,0,0,0,0,1,false);
			break;
		case 15 :
			setwu(15,"铁剑",10,0,0,0,0,0,0,1,false);
			break;
		case 16 :
			setwu(16,"铁盾",0,10,0,0,0,0,0,1,false);
			break;
		case 17 :
			setwu(17,"银盾",0,20,0,0,0,0,0,1,false);
			break;
		case 18 :
			setwu(18,"神圣剑",100,0,0,0,0,0,0,1,true);
			break;
		case 19 :
			setwu(19,"银剑",20,0,0,0,0,0,0,1,false);
			break;
		case 20 :
			setwu(20,"骑士剑",40,0,0,0,0,0,0,1,false);
			break;
		case 21 :
			setwu(21,"神圣盾",0,100,0,0,0,0,0,1,false);
			break;
		case 22 :
			setwu(22,"圣剑",50,0,0,0,0,0,0,1,false);
			break;
		case 24 :
			setwu(24,"圣盾",0,50,0,0,0,0,0,1,false);
			break;
		case 25 :
			setwu(25,"上天翅",0,0,0,0,0,0,0,1,true);
			break;
		case 26 :
			setwu(26,"飞天杖",0,0,0,0,0,0,0,1,true);
			break;
		case 27 :
			setwu(27,"修真玉简",0,0,0,0,0,0,0,1,true);
			break;
		case 28 :
			setwu(28,"炸药",0,0,0,0,0,0,0,1,true);
			break;
		}
	}
}
