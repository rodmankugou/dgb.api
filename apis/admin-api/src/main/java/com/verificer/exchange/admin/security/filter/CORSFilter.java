package com.verificer.exchange.admin.security.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域fiter
 */
public class CORSFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Accept", "text/html,application/json,application/x-www-form-urlencoded,multipart/form-data");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "token,Referer,User-Agent,Authorization,Origin,X-Requested-With,Content-Type,Accept,language,veri-ex-client");
        chain.doFilter(req, res);
    }



    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
