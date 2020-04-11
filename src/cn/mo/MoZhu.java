package cn.mo;

public class MoZhu {

	private int zlife,zgong,zfang;
	private int yellowkey = 0,bluekey = 0,redkey = 0;
	private int zgold;
	int[] special = new int[10];
	
	public MoZhu() {
		this.zgong = 10;
		this.zfang = 10;
		this.zlife = 400;
		this.zgold = 20;
	}
	
	public Boolean opendoor(int key) {
		if(key > 0)
			return true;
		else
			return false;
	}
	
	public void setzlife(int life) {
		this.zlife = life;
	}
	
	public void setzgong(int gong) {
		this.zgong = gong;
	}
	
	public void setzfang(int fang) {
		this.zfang = fang;
	}
	
	public void setyellowkey(int ykey) {
		this.yellowkey = ykey;
	}
	
	public void setbluekey(int bkey) {
		this.bluekey = bkey;
	}
	
	public void setredkey(int rkey) {
		this.redkey = rkey;
	}
	
	public void setzgold(int zg) {
		this.zgold = zg;
	}
	
	public void setspecial(int i) {
		this.special[i] =1;
	}
	
	public int getzlife() {
		return this.zlife;
	}
	
	public int getzgong() {
		return this.zgong;
	}
	
	public int getzfang() {
		return this.zfang;
	}
	
	public int getyellowkey() {
		return this.yellowkey;
	}
	
	public int getbluekey() {
		return this.bluekey;
	}
	
	public int getredkey() {
		return this.redkey;
	}
	
	public int getzgold() {
		return this.zgold;
	}
	
	public int[] getspecial() {
		return this.special;
	}
}
