package com.verificer.utils;

import com.verificer.web.common.response.CommonResponseCode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by liujinhua on 2020/12/24.
 */
public class ImageCodeUtils {
    public static String getVerifyCodeKey(String imageId) {
        return String.format("%s:%s", "verify_code", imageId);
    }

    /**
     * 验证图片验证码
     * @param redisUtil
     * @param imageId
     * @param code
     */
    public static boolean checkCode(RedisUtil redisUtil,String imageId,String code){
        String verifyKey = getVerifyCodeKey(imageId);
        if (redisUtil.exists(verifyKey)) {
            String validateCode = (String) redisUtil.get(verifyKey);
            if (code.equalsIgnoreCase(validateCode)) {
                return true;
            }
        }
        return false;
    }

    public static void removeCodeFromCache(RedisUtil redisUtil,String imageId){
        redisUtil.remove(getVerifyCodeKey(imageId));
    }

    /**
     * 生成图片验证码
     * @param redisUtil
     * @param imageId
     * @return
     */
    public static BufferedImage generateImage(RedisUtil redisUtil,String imageId){
        BufferedImage image = null;
        //生成随机字串
        String verifyCode = generateVerifyCode();
        //存入 redis 中
        String key = getVerifyCodeKey(imageId);
        redisUtil.set(key, verifyCode, 600L);

        int width = 60, height = 33;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = null;

        g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        g.setColor(new Color(255, 255, 255));
        g.drawRect(0, 0, width - 1, height - 1);

        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        char[] chars = verifyCode.toCharArray();
        int lenth = verifyCode.length();
        for (int i = 0; i < lenth; i++) {
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawChars(chars, i, 1, (50 / lenth) * i + 10, 22);
        }
        return image;
    }

    /**
     * 使用指定源生成验证码
     *
     * @return
     */
    public static String generateVerifyCode() {
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        return sRand;
    }

    static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
