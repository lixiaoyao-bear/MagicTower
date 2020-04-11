package cn.mo;

import javax.swing.JOptionPane;

public class MoShop {

	private MoZhu shopZhu = new MoZhu();
	
	public Boolean choise(MoJpanel mj) {
		//this.shopZhu = mj.mzhu;
		String[] a = {"增加800生命","增加5点攻击","增加5点防御","离开"};
		//JOptionPane jomessage = new JOptionPane();
		String str = (String) JOptionPane.showInputDialog(null,"25金币为你开启上帝之手","商店",JOptionPane.INFORMATION_MESSAGE,null,a,a[0]);
		this.shopZhu = mj.mzhu;
		if(mj.mzhu.getzgold() >= 25) {
			switch(str) {
			case "增加800生命" :
				mj.mzhu.setzlife(mj.mzhu.getzlife() + 800);
				mj.mzhu.setzgold(mj.mzhu.getzgold() - 25);
				return true;
			case "增加5点攻击" :
				mj.mzhu.setzgong(mj.mzhu.getzgong() + 5);
				mj.mzhu.setzgold(mj.mzhu.getzgold() - 25);
				return true;
			case "增加5点防御" :
				mj.mzhu.setzfang(mj.mzhu.getzfang() + 5);
				mj.mzhu.setzgold(mj.mzhu.getzgold() - 25);
				return true;
			case "离开" :
				return false;
			default :
				return false;
			}
		}
		return false;
	}
	
	public MoZhu getZhu() {
		return this.shopZhu;
	}
}