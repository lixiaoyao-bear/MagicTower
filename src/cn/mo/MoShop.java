package cn.mo;

import javax.swing.JOptionPane;

public class MoShop {

	private MoZhu shopZhu = new MoZhu();
	
	public Boolean choise(MoJpanel mj) {
		//this.shopZhu = mj.mzhu;
		String[] a = {"����800����","����5�㹥��","����5�����","�뿪"};
		//JOptionPane jomessage = new JOptionPane();
		String str = (String) JOptionPane.showInputDialog(null,"25���Ϊ�㿪���ϵ�֮��","�̵�",JOptionPane.INFORMATION_MESSAGE,null,a,a[0]);
		this.shopZhu = mj.mzhu;
		if(mj.mzhu.getzgold() >= 25) {
			switch(str) {
			case "����800����" :
				mj.mzhu.setzlife(mj.mzhu.getzlife() + 800);
				mj.mzhu.setzgold(mj.mzhu.getzgold() - 25);
				return true;
			case "����5�㹥��" :
				mj.mzhu.setzgong(mj.mzhu.getzgong() + 5);
				mj.mzhu.setzgold(mj.mzhu.getzgold() - 25);
				return true;
			case "����5�����" :
				mj.mzhu.setzfang(mj.mzhu.getzfang() + 5);
				mj.mzhu.setzgold(mj.mzhu.getzgold() - 25);
				return true;
			case "�뿪" :
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