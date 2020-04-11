package cn.mo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MoSound {

	AudioClip sound;
	String musicName = "周品 - 不负.wav"; 
	URL address;
	
	public void loadSound() {
		File f = new File("src\\sound\\" + musicName);
		try {
			address = f.toURI().toURL();
			sound = Applet.newAudioClip(address);
			//sound.loop();
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void setMusic(String mu) {
		this.musicName = mu;
	}
	
	public void bofang(String name) {
		this.setMusic(name);
		this.loadSound();
		this.sound.play();
	}
}
