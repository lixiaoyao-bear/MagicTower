package cn.mo;

public class MoNpc {

	private int[][] npcmap = new int[13][13];
	private String message;
	
	public void say(int[][] nmap,int y,int x) {
		this.npcmap = nmap;
		switch(npcmap[y][x]) {
		case 81 :
			this.message = "      我可以给你怪物手册，你可以用快捷键F1去使用它。它可以帮你查阅怪物资料。";
			break;
		}
	}
	
	public String getmessage() {
		return this.message;
	}
}
