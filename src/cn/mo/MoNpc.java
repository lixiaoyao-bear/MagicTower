package cn.mo;

public class MoNpc {

	private int[][] npcmap = new int[13][13];
	private String message;
	
	public void say(int[][] nmap,int y,int x) {
		this.npcmap = nmap;
		switch(npcmap[y][x]) {
		case 81 :
			this.message = "      �ҿ��Ը�������ֲᣬ������ÿ�ݼ�F1ȥʹ�����������԰�����Ĺ������ϡ�";
			break;
		}
	}
	
	public String getmessage() {
		return this.message;
	}
}
