package com.verificer.exchange.admin.controller;

import com.verificer.beans.UserIdentity;
import com.verificer.biz.beans.enums.ImageFormatTypeEnum;
import com.verificer.biz.beans.vo.ImageVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.security.login.ILoginMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

//    @Autowired
//    BizService bizService;
//
//
//    @Autowired
//    ILoginMonitor<UserIdentity> loginMonitor;
//
//
//    @RequestMapping("/{id}.{suffix}")
//    public Object  getImage(@PathVariable("id") String id,
//                            @PathVariable("suffix") String suffix,
//                            @RequestParam("ts") String tokenString,
//                            @RequestParam("cs") String checkString){
//        UserIdentity userIdentity = loginMonitor.getUserInfoBySubToken(tokenString,new UserIdentity());
//        if(userIdentity == null)
//            return "403 Forbidden";
//
//        ImageVo imageVo = null;
//        try {
//            imageVo = bizService.imageGetHdfsImageByIdForAdm(userIdentity.getId(),id,tokenString,checkString);
//        } catch (Exception e) {
//            logger.error("load image error, error message :"+ e.getMessage(),e);
//            return "403 Forbidden";
//        }
//
//        if(imageVo == null)
//            return "404 Not found!";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(ImageFormatTypeEnum.getContentType(imageVo.getFormatType()));
//
//        return new ResponseEntity<byte[]>(imageVo.getBinaryContent(), headers, HttpStatus.OK);
//
//    }
}
