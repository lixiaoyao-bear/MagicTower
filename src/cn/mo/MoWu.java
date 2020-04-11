package cn.mo;

public class MoWu {

	private String wname;
	private int ykey,bkey,rkey,wgong,wfang,wlife,wgold,wcount;
	private Boolean isspecicalwu;
	MoJpanel wupanel = new MoJpanel();
	
	public MoWu(String wn,int wg,int wf,int wl,int wd,int yk,int bk,int rk,int wc,Boolean wis) {
		this.wname = wn;
		this.wgong = wg;
		this.wfang = wf;
		this.wlife = wl;
		this.wgold = wd;
		this.ykey = yk;
		this.bkey = bk;
		this.rkey = rk;
		this.wcount = wc;
		this.isspecicalwu = wis;
	}
	
	public String getwname() {
		return this.wname;
	}
	
	public int getwgong() {
		return this.wgong;
	}
	
	public int getwfang() {
		return this.wfang;
	}
	
	public int getwlife() {
		return this.wlife;
	}
	
	public int getykey() {
		return this.ykey;
	}
	
	public int getbkey() {
		return this.bkey;
	}
	
	public int getrkey() {
		return this.rkey;
	}
	
	public int getwgold() {
		return this.wgold;
	}
	
	public int getwcount() {
		return this.wcount;
	}
	
	public Boolean isspecial() {
		return this.isspecicalwu;
	}
}
