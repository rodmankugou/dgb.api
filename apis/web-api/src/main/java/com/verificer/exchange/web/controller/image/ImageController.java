package com.verificer.exchange.web.controller.image;

import com.verificer.utils.SDateUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/image")
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Value("#{configProperties['LOCAL_STORAGE_PATH']}")
    private String localStoragePath;


    /**
     * 获取图片验证码
     *
     * @param response
     */
    @ApiOperation(
            value = "忽略，该接口用于获取图片，不需要对接"

    )
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/get/{imageId}", method = RequestMethod.GET)
    public void imageCode(@PathVariable("imageId") String imageId, HttpServletResponse response) {
        FileInputStream fi = null;
        ServletOutputStream os = null;
        try {
            response.setHeader("Pragma", "private");
            response.setHeader("Cache-Control", "private");
            response.setDateHeader("Expires", System.currentTimeMillis()+1000L*60*60*24);
            response.setContentType("image/jpeg");



            File picFIle = new File((localStoragePath+imageId));
            if(!picFIle.exists()){
                picFIle = new File(localStoragePath+imageId+".jpg");
            }

            fi = new FileInputStream(picFIle);
            int i = fi.available(); // 得到文件大小
            byte data[] = new byte[i];
            fi.read(data); // 读数据


            os = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
            os.write(data); // 输出数据
            os.flush();


        } catch (Exception e) {
            logger.error("获取验证码异常:{}", e.getMessage(), e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fi != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        }
    }

    /**
     * 获取图片验证码
     *
     * @param response
     */
    @ApiOperation(
            value = "忽略，该接口用于获取图片，不需要对接"

    )
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/avatar/get/{imageId}", method = RequestMethod.GET)
    public void avatarImage(@PathVariable("imageId") String imageId, HttpServletResponse response) {
        FileInputStream fi = null;
        ServletOutputStream os = null;
        try {
            response.setHeader("Cache-Control", "private");
            response.setDateHeader("Expires", System.currentTimeMillis()+1000*60*10);
            response.setContentType("image/jpeg");

            fi = new FileInputStream(new File(localStoragePath+imageId+".jpg"));
            int i = fi.available(); // 得到文件大小
            byte data[] = new byte[i];
            fi.read(data); // 读数据


            os = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
            os.write(data); // 输出数据
            os.flush();


        } catch (Exception e) {
            logger.error("获取验证码异常:{}", e.getMessage(), e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fi != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        }
    }
}
