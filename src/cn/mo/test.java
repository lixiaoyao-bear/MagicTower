package cn.mo;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) throws Exception{
        /*int value ,value1;
        int k= 0,j = 0;
        for (int i = 11; i < 51; i++) {
            File file = new File("src\\maps\\" + i +".map");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.print("{");
            while ((value1 = bufferedReader.read()) != -1){
                if (value1 == 32){
                    System.out.print(", ");
                }else if (value1 > 40){
                    value = value1 -48;
                    k++;
                    System.out.print(value);
                }else {
                    if (j == 0){
                        System.out.print("},\n");
                        System.out.print("{");
                        j++;
                    }else{
                        j = 0;
                    }
                }
            }
            System.out.print("}");
            System.out.println();
            System.out.println();
        }*/
        /*Timer timer = new Timer(150,actionEvent -> {
            System.out.println("1");
        });
        timer.start();
        System.out.println(timer);*/
        Collection c = new ArrayList();
        Map map = new HashMap();
    }
}
