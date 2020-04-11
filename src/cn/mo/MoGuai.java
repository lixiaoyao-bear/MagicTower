package cn.mo;

public class MoGuai {

	private int ggong,gfang,glife,ggold;
	private String gname;
	
	public MoGuai(String gn,int gg,int gf,int gl,int go) {		
		this.gname = gn;
		this.ggong = gg;
		this.gfang = gf;
		this.glife = gl;
		this.ggold = go;		
	}
	
	public void setglife(int gl) {
		this.glife = gl;
	}
	
	public String getgname() {
		return this.gname;
	}
	
	public int getggong() {
		return this.ggong;
	}
	
	public int getgfang() {
		return this.gfang;
	}
	
	public int getglife() {
		return this.glife;
	}
	
	public int getggold() {
		return this.ggold;
	}
}
