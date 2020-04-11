package cn.mo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MoSave {

	int[][] smap = new int[13][13];
	FileWriter fw;
	BufferedWriter bfw;
	int floor;
	
	public void save(int[][] map,int sf) {
		int i = 0,j = 0,k = 0,ss = 0;
		this.floor = sf;
		File f = new File("src\\save\\" + floor +".map");
		try {
			fw = new FileWriter(f);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		bfw = new BufferedWriter(fw);
		try {
			for(i = 0;i < 13;i++) {
				for(j = 0;j < 13;j++) {
					if(map[i][j] > 9) {
						ss = map[i][j] % 10;
						k = (map[i][j] - ss) / 10;
						bfw.write(k + 48);
						bfw.write(ss + 48);
						bfw.write(" ");
						continue;
					}
					bfw.write(map[i][j] + 48);
					bfw.write(" ");
				}
				bfw.write("\r\n");
			}
			bfw.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
