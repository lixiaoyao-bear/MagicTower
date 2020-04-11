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
        File f = new File("sound/����1.wav");
        try {
            address = f.toURI().toURL();
            sound = Applet.newAudioClip(address);
            sound.loop();
        } catch (MalformedURLException e) {
            // TODO �Զ����ɵ� catch ��
            e.printStackTrace();
        }
        //loadSound("����1.wav",0);
    }

    /**  �������֣�0Ϊѭ�����ţ�1Ϊ����һ��*/
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
            // TODO �Զ����ɵ� catch ��
            e.printStackTrace();
        }
    }
}
