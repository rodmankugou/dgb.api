package com.verificer.tools.gen;

import com.verificer.utils.SImageUtils;

import java.awt.*;
import java.io.File;

public class AvatarGen {
    static final Color[] colors = {Color.LIGHT_GRAY,
            Color.DARK_GRAY,
            Color.RED,
            Color.PINK,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.MAGENTA,
            Color.CYAN,
            Color.BLUE,
    };

    public static void genAvatar(String dir,int height,int width){
        for(int i= 0 ; i  < colors.length; i++){
            File file = new File(dir + "/"+ (i+1)+".png");
            SImageUtils.genImage(height,width,colors[i],file);
        }
    }

    public static void main(String args[]){
        genAvatar("/Users/liujinhua/Desktop/avatars",100,100);
    }
}
