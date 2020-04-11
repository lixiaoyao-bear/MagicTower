package cn.mo;

public class MoGuaiManger {

	private int[][] guaimap = new int[13][13];
	MoGuai[] mguai = new MoGuai[40];
	
	public void neguai(int i,String name,int go,int gf,int gl,int gd) {
		MoGuai mg = new MoGuai(name,go,gf,gl,gd);
		mguai[i] = mg;
	}
	
	public void guai(int[][] gmap,int y,int x) {
		this.guaimap = gmap;
		switch(guaimap[y][x]) {
		case 40 :
			neguai(0,"绿色史莱姆", 18, 1, 35, 1);
			break;
		case 41 :
			neguai(1,"红色史莱姆", 20, 2, 45, 2);
			break;
		case 42 :
			neguai(2,"小蝙蝠", 38, 3, 35, 3);
			break;
		case 43 :
			neguai(3,"初级法师", 32, 8, 60, 5);
			break;
		case 44 :
			neguai(4,"骷髅人", 42, 6, 50, 6);
			break;
		case 45 :
			neguai(5,"骷髅士兵", 52, 12, 55, 8);
			break;
		case 46 :
			neguai(6,"中级卫兵", 180, 110, 100, 50);
			break;
		case 47 :
			neguai(7,"初级卫兵", 48, 22, 50, 12);
			break;
		case 48 :
			neguai(8,"骷髅队长", 65, 15, 100, 30);
			break;
		case 49 :
			neguai(9,"兽人", 85, 5, 260, 18);
			break;
		case 50 :
			neguai(10,"大蝙蝠", 100, 8, 60, 12);
			break;
		case 51 :
			neguai(11,"高级法师", 95, 30, 100, 22);
			break;
		case 52 :
			neguai(12,"大史莱姆", 60, 3, 130, 8);
			break;
		case 53 :
			neguai(13,"兽人武士", 120, 15, 320, 30);
			break;
		case 54 :
			neguai(14,"石头人", 100, 68, 20, 28);
			break;
		case 55 :
			neguai(15,"大乌贼", 180, 20, 1200, 100);
			break;
		case 56 :
			neguai(16,"幽灵", 199, 66, 320, 144);
			break;
		case 57 :
			neguai(17,"双手剑士", 680, 50, 100, 55);
			break;
		case 58 :
			neguai(18,"鬼战士", 180, 30, 220, 35);
			break;
		case 59 :
			neguai(19,"战士", 200, 65, 210, 45);
			break;
		case 60 :
			neguai(20,"骑士", 230, 105, 160, 65);
			break;
		case 61 :
			neguai(21,"魔龙", 600, 250, 1500, 800);
			break;
		case 62 :
			neguai(22,"骑士队长", 150, 50, 120, 100);
			break;
		case 63 :
			neguai(23,"高级巫师", 380, 130, 200, 90);
			break;
		case 64 :
			neguai(24,"初级巫师", 370, 110, 220, 80);
			break;
		case 65 :
			neguai(25,"吸血蝙蝠", 390, 90, 200, 50);
			break;
		case 66 :
			neguai(26,"史莱姆王", 310, 20, 360, 40);
			break;
		case 67 :
			neguai(27,"魔法警卫", 450, 100, 230, 100);
			break;
		case 68 :
			neguai(28,"黑暗骑士", 430, 210, 180, 120);
			break;
		case 69 :
			neguai(29,"骷髅队长", 65, 15, 100, 30);
		}
	}
}
