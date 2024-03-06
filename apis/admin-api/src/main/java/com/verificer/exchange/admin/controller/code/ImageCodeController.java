package com.verificer.exchange.admin.controller.code;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.Maps;
import com.verificer.ErrCode;
import com.verificer.beans.ImageCodeRespVo;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.utils.ImageCodeUtils;
import com.verificer.utils.RedisUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 图片验证码
 */
@Api(value = "/", description = "图形验证码", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping(value = "/image/code")
public class
ImageCodeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ImageCodeController.class);
    @Autowired
    private RedisUtil redisUtil;

    @Value("#{configProperties['USE_SSL']}")
    private Integer useSSL;

	/*@RequestMapping("fixImageCode")
	public ModelAndView getImageCode(String fixCode) {
		ModelAndView modelAndView = new ModelAndView("fixImageCode");
		modelAndView.addObject("fixCode", fixCode);
		return modelAndView;
	}*/

    @ApiOperation(
            value = "生成图片验证码",
            response = ImageCodeRespVo.class,
            notes = "返回图验证码id和图片url用于后续的验证",
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public Object getImage(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length())
                .append(request.getContextPath()).toString();
        Map<String, Object> map = Maps.newHashMap();
        String imageId = UUID.randomUUID().toString();

        if (useSSL > 0) {
            tempContextUrl = tempContextUrl.replace("http", "https");
        }
        map.put("imageId", imageId);
        map.put("url", tempContextUrl + "/image/code/get/" + imageId);
        return Response.dataSuccess(map);
    }

    /**
     * 验证图片验证码
     *
     * @param imageCode
     * @return Object
     */
    @ApiOperation(
            value = "验证图片验证码",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "imageId", value = "图形验证码Id", paramType = "form",dataType = "String",required = true),
            @ApiImplicitParam(name = "imageCode", value = "图形验证码", paramType = "form",dataType = "String",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Object checkImageCode(@RequestParam String imageId,
                                 @RequestParam String imageCode) {
        if (StringUtils.isBlank(imageCode) || StringUtils.isBlank(imageId)) {
            return Response.failed(ErrCode.PARAMS_ERR);
        }
        String key = getVerifyCodeKey(imageId);

        if(!ImageCodeUtils.checkCode(redisUtil,imageId,imageCode)){
            return Response.failed(ErrCode.IMAGE_CODE_CHECK_FAILED);
        }else {
            return Response.simpleSuccess();
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
    @RequestMapping(value = "/get/{imageId}", method = RequestMethod.GET)
    public void imageCode(@PathVariable("imageId") String imageId, HttpServletResponse response) {
        BufferedImage image = null;
        ServletOutputStream os = null;
        Graphics g = null;
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            //生成随机字串
            String verifyCode = generateVerifyCode();
            //存入 redis 中
            String key = getVerifyCodeKey(imageId);
            redisUtil.set(key, verifyCode, 600L);

            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            os = response.getOutputStream();
            int width = 60, height = 33;

            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

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
            //System.out.println(verifyCode);
            ImageIO.write(image, "JPEG", os);

        } catch (Exception e) {
            logger.error("获取验证码异常:{}", e.getMessage(), e);
        } finally {
            g.dispose();
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getVerifyCodeKey(String imageId) {
        return String.format("%s:%s", "verify_code", imageId);
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

    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


}
