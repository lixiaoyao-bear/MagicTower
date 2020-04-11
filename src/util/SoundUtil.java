package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SoundUtil {

    public AudioClip sound;
    public URL address;

    public SoundUtil(){
        File f = new File("sound/背景1.wav");
        try {
            address = f.toURI().toURL();
            sound = Applet.newAudioClip(address);
            sound.loop();
        } catch (MalformedURLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        //loadSound("背景1.wav",0);
    }

    /**  播放音乐，0为循坏播放，1为播放一次*/
    public void loadSound(String name,int choice){
        AudioClip audioClip;
        URL url;
        File f = new File("sound/" + name);
        try {
            url = f.toURI().toURL();
            audioClip = Applet.newAudioClip(url);
            if (choice == 0)
                audioClip.loop();
            else
                audioClip.play();
        } catch (MalformedURLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
