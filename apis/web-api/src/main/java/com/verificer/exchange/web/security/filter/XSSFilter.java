package com.verificer.exchange.web.security.filter;

import com.alibaba.dubbo.rpc.RpcContext;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.ThreadLocalUtil;
import com.verificer.web.common.security.xss.XssFilterWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by choufengleilei on 2018\7\22
 */
public class XSSFilter extends OncePerRequestFilter {
    private volatile String exclude = null;  //不需要过滤的路径集合
    private volatile Pattern pattern = null;  //匹配不需要过滤路径的正则表达式

    public void setExclude(String exclude) {
        this.exclude = exclude;
        if(SStringUtils.isEmpty(exclude)){
            pattern = null;
        }else {
            pattern = Pattern.compile(getRegStr(exclude));
        }
    }

    /**
     * XSS过滤
     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //把language 存RpcContext
        String requestLanguage = request.getHeader("language");//请求头获取language
        ThreadLocalUtil.remove("LOCAL_LANGUAGE");
        if(null!=requestLanguage && !requestLanguage.equals("")){
             RpcContext.getContext().setAttachment("LOCAL_LANGUAGE",requestLanguage);
            //System.out.println("requestLanguage dofilter@@@@@@@@@==="+requestLanguage);
            ThreadLocalUtil.set("LOCAL_LANGUAGE",requestLanguage);
        }

        String requestURI = request.getRequestURI();
        if(!SStringUtils.isEmpty(requestURI))
            requestURI = requestURI.replace(request.getContextPath(),"");

        if(pattern != null && pattern.matcher(requestURI).matches())
            filterChain.doFilter(request, response);
        else{
            XssFilterWrapper xssFilterWrapper = new XssFilterWrapper(request);
            filterChain.doFilter(xssFilterWrapper, response);
        }
    }

    /**
     * 将传递进来的不需要过滤得路径集合的字符串格式化成一系列的正则规则
     * @param str 不需要过滤的路径集合
     * @return 正则表达式规则
     * */
    private String getRegStr(String str){
        if(!SStringUtils.isEmpty(str)){
            String[] excludes = str.split(";");  //以分号进行分割
            int length = excludes.length;
            for(int i=0;i<length;i++){
                String tmpExclude = excludes[i];
                //对点、反斜杠和星号进行转义
                tmpExclude = tmpExclude.replace("\\", "\\\\").replace(".", "\\.").replace("*", ".*");
                tmpExclude = "^" + tmpExclude + "$";
                excludes[i] = tmpExclude;
            }
            return SStringUtils.join(excludes, "|");
        }
        return str;
    }
}
