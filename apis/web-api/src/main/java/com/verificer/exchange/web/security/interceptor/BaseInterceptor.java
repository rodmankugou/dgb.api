package com.verificer.exchange.web.security.interceptor;

import com.alibaba.fastjson.JSON;
import com.verificer.web.common.response.Response;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 35336 on 2020/12/29.
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {
    protected void respAsJson(HttpServletResponse response, String errCode) throws IOException {

        response.reset();
        //设置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "false");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,HEAD,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);

        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(Response.failed(errCode)));
        pw.flush();
        pw.close();
    }
}
