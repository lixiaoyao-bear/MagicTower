package cn.mo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MoMap {

	FileReader fr;
	BufferedReader bfr;
	int[][] map = new int[13][13];
	int floor;
	int zx,zy;
	int ismapfloor;//是否是第一次经过楼层,0为第一次
	
	public MoMap(int fl,int choise,int isf) {
		floor = fl;
		int i = 0,j = 0,k = 0;
		int ms,bs;
		if(choise == 0 && isf == 0) {
			File fi = new File("src\\maps\\" + floor +".map");
			this.ismapfloor = 1;
			try {
				fr = new FileReader(fi);
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else {
			File fi = new File("src\\save\\" + floor +".map");
			this.ismapfloor = 1;
			try {
				fr = new FileReader(fi);
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		bfr = new BufferedReader(fr);		
		try {
			while((ms = bfr.read()) != -1) {
				if(k == 13) {
					i++;
					j = 0;
					k = 0;
				}
				if((bs = bfr.read()) != 32 && bs > 40) {
					if(ms > 40) {
						map[i][j] = ms -48;
					}
					map[i][j] = 10 * map[i][j] + (bs - 48);
					if(map[i][j] == 80) {
						this.zx = j;
						this.zy = i;
					}
					j++;
					k++;
					bfr.read();
					continue;
				}
				//System.out.println(ms);
				if(ms > 40) {
					map[i][j] = ms -48;
					j++;
					k++;
				}												
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		/*for(i = 0;i < 13;i++) {
			for(j = 0;j < 13;j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");
			}
			System.out.println("\n");
		}*/
	}
	
	/*public int[] isnewfloor() {
		return this.ismapfloor;
	}*/
}
 