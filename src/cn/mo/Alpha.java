package cn.mo;

import sun.awt.image.BufferedImageGraphicsConfig;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Alpha {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 55; i++) {
            System.out.println(transferAlpha2File("image/event/49floor/" + i + ".png",
                    "image/event/49floor/2/" + i + ".png"));
        }
        //transImage("image/image/10floor/" + "50" + ".png","image/image/10floor/2/" + "50" + ".png",0);
        //trans("image/image/10floor/" + "50" + ".png","image/image/10floor/2/" + "50" + ".png");
    }

    public static void trans(String src,String des) throws IOException {
        BufferedImage avatarImage = ImageIO.read(new File(src));
        int width = avatarImage.getWidth();
        int height = avatarImage.getHeight();
        BufferedImage formatAvatarImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImageGraphicsConfig config = BufferedImageGraphicsConfig.getConfig(formatAvatarImage);
        formatAvatarImage =config.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        Graphics2D graphics = formatAvatarImage.createGraphics();
        graphics.drawImage(avatarImage,0,0,width,height,null);
        graphics.dispose();
        System.out.println(ImageIO.write(formatAvatarImage, "png", new File(des)));
    }

    public static boolean transferAlpha2File(String imgSrc, String imgTarget) {
        File file = new File(imgSrc);
        boolean result;
        try (InputStream is = new FileInputStream(file)) {
            // 如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream();
            BufferedImage bi = ImageIO.read(is);
            ImageIcon imageIcon = new ImageIcon(bi);
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    if (((255 - R) == 0) && ((255 - G) == 0) && ((255 - B) == 0)) {
                        rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                    }
                    bufferedImage.setRGB(j2, j1, rgb);
                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            result = ImageIO.write(bufferedImage, "png", new File(imgTarget));// 直接输出文件
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        // TODO Auto-generated catch block
        return result;
    }

    public static void transImage(String srcFile,String desFile,int alpha) throws IOException{
        BufferedImage temp =  ImageIO.read(new File(srcFile));//取得图片
        transparentImage(temp, desFile, alpha);
    }

    public static void transparentImage(BufferedImage srcImage,
                                        String desFile, int alpha) throws IOException {
        int imgHeight = srcImage.getHeight();//取得图片的长和宽
        int imgWidth = srcImage.getWidth();
        int c = srcImage.getRGB(3, 3);
        //防止越位
        if (alpha < 0) {
            alpha = 0;
        } else if (alpha > 10) {
            alpha = 10;
        }
        BufferedImage bi = new BufferedImage(imgWidth, imgHeight,
                BufferedImage.TYPE_4BYTE_ABGR);//新建一个类型支持透明的BufferedImage
        for(int i = 0; i < imgWidth; ++i)//把原图片的内容复制到新的图片，同时把背景设为透明
        {
            for(int j = 0; j < imgHeight; ++j)
            {
                //把背景设为透明
                if(srcImage.getRGB(i, j) == c){
                    bi.setRGB(i, j, c & 0x00ffffff);
                }
                //设置透明度
                else{
                    int rgb = bi.getRGB(i, j);
                    rgb = ((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
                    bi.setRGB(i, j, rgb);
                }
            }
        }
        System.out.println(ImageIO.write(bi, "png", new File(desFile)));
    }
}
