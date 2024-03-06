package com.verificer.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.poi.hpsf.Thumbnail;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 支持图片压缩以及格式转换
 */
public class SImageUtils {
    public static void scale(InputStream in, OutputStream out, double factor) throws IOException {
        try {
            BufferedImage originalImage = ImageIO.read(in);


            Thumbnails.of(originalImage)
                    .scale(factor)
                    .outputFormat("jpg")
                    .toOutputStream(out);
        } catch (IOException e) {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ioException) {
                //
            }
            if (out != null)
                out.close();
        }

    }

    public static void scale(File origin, File dest, Double factor) throws IOException {
        scale(new FileInputStream(origin), new FileOutputStream(dest), factor);
    }

    /**
     * 将图片等比压缩到maxSize以下
     *
     * @param in
     * @param out
     * @param maxSize 图片大小，单位为k
     * @throws IOException
     */
    public static void scaleForceSize(InputStream in, OutputStream out, int maxSize) throws IOException {
        try {
            BufferedImage originalImage = ImageIO.read(in);
            double imageArea = originalImage.getHeight() * originalImage.getWidth();
            double standard = 12000000 / 1024;
            double cur = imageArea / maxSize;
            double factor = 0;
            if (cur < standard) {
                factor = 1;
            } else {
                factor = standard / cur;
            }


            Thumbnails.of(originalImage)
                    .scale(factor)
                    .outputFormat("jpg")
                    .toOutputStream(out);
        } catch (IOException e) {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ioException) {
                //
            }
            if (out != null)
                out.close();
        }

    }


    /**
     * 将图片等比压缩到maxSize以下
     *
     * @param origin
     * @param dest
     * @param maxSize 图片大小，单位为k
     * @throws IOException
     */
    public static void scaleForceSize(File origin, File dest, int maxSize) throws IOException {
        scaleForceSize(new FileInputStream(origin), new FileOutputStream(dest), maxSize);
    }


    /**
     * 将图片按照固定比例缩小到宽度多少以下
     *
     * @param in
     * @param out
     * @param maxWidth 图片大小，单位为k
     * @throws IOException
     */
    public static void scaleForceWidth(InputStream in, OutputStream out, int maxWidth) throws IOException {
        try {
            BufferedImage originalImage = ImageIO.read(in);
            double factor = 0;
            if (originalImage.getWidth() < maxWidth) {
                factor = 1;
            } else {
                factor = new Double(maxWidth) / originalImage.getWidth();
            }


            Thumbnails.of(originalImage)
                    .scale(factor)
                    .outputFormat("jpg")
                    .toOutputStream(out);
        } catch (IOException e) {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ioException) {
                //
            }
            if (out != null)
                out.close();
        }

    }

    /**
     * 将图片按照固定比例缩小到宽度多少以下
     *
     * @param origin
     * @param dest
     * @param maxWidth 最大宽度
     * @throws IOException
     */
    public static void scaleForceWidth(File origin, File dest, int maxWidth) throws IOException {
        scaleForceWidth(new FileInputStream(origin), new FileOutputStream(dest), maxWidth);
    }

    public static void genImage(int height, int width,Color color, File file) {
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(file);
            genImage(height, width,color, fo);
        } catch (IOException e) {
            if (fo != null){
                try {
                    fo.close();
                } catch (IOException ioException) {
                    //
                }
            }

            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void genImage(int height, int width,Color color, OutputStream outputStream) throws IOException {
        //width 生成图宽度
        // height 生成图高度
        //创建一个width xheight ，RGB高彩图，类型可自定
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //取得图形
        Graphics g = img.getGraphics();
        //设置颜色
        g.setColor(color);
        //填充
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        //在d盘创建个文件
        //以png方式写入，可改成jpg其他图片
        ImageIO.write(img, "jpg", outputStream);
    }


    public static void main(String args[]) throws Exception {
//        File file = new File("/Users/liujinhua/Desktop/weixin7.jpeg");
//        File out = new File("/Users/liujinhua/Desktop/weixin7_mini.jpg");
//        scaleForceWidth(file, out, 300);

//        File file2 = new File("/Users/liujinhua/Desktop/weixin5.jpeg");
//        File out2 = new File("/Users/liujinhua/Desktop/weixin5_mini.jpg");
//        scaleForceSize(file2,out2,2048);

        genImage(20000,20000,Color.red,new File("/Users/liujinhua/Desktop/image/scale/red-6M.png"));
        genImage(20000,20000,Color.BLUE,new File("/Users/liujinhua/Desktop/image/scale/blue-6M.png"));
        genImage(20000,20000,Color.GREEN,new File("/Users/liujinhua/Desktop/image/scale/green-6M.png"));
    }


}
