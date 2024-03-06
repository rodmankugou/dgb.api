package com.verificer.exchange.admin.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.XSSCheckUtil;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by Administrator on 2018\7\19 0019.
 */
public class XSSFilterIO implements Filter {

    private List<String> excludes = new ArrayList<>();

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }
    public List<String> getExcludes() {
        return excludes;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludes = filterConfig.getInitParameter("excludes");
        if (!SStringUtils.isEmpty(excludes)) {
            String[] array = excludes.split(",");
            for (String url : array) {
                this.excludes.add(url);
            }
        }
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String requestPath = req.getRequestURI();
        requestPath = requestPath.substring(req.getContextPath().length() + 1);
        while (requestPath.endsWith("/")){ //预防uri末尾有 ‘/’
            requestPath = requestPath.substring(0, requestPath.length()-1);
        }
        for (String str : excludes) {
            if (str.endsWith("*")) {
                if (requestPath.startsWith(str.substring(0, str.length() - 1))){
                    chain.doFilter(req, resp);
                    return;
                }
            }
            if(str.equals(requestPath)) {
                chain.doFilter(req, resp);
                return;
            }
        }
        Map<String, Object> paramMap = new HashMap<>();
        String type = req.getContentType();
        ServletRequest requestWrapper = null;
        if(req instanceof HttpServletRequest) {
            requestWrapper = new ReaderReuseHttpServletRequestWrapper(req);
        }
        Reader reader = requestWrapper.getReader();
        // 读取Request Payload数据
        String Payload = IOUtils.toString(reader);
        if (type != null && type.startsWith("application/json")){
            JSONObject jsonObject = JSONObject.parseObject(Payload);
            if (jsonObject != null) {
                for(Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    paramMap.put(entry.getKey(), entry.getValue());
                }
            }
        } else if(type != null && type.startsWith("application/x-www-form-urlencoded")) {
            String[] kvs = Payload.split("&");
            for (String kv : kvs) {
                String[] lf = kv.split("=");
                paramMap.put(lf[0], lf[1]);
            }
        }
        // 获取请求参数
        Enumeration en = req.getParameterNames();
        while(en.hasMoreElements()) {
            String name = (String) en.nextElement();
            String value = req.getParameter(name);
            paramMap.put(name, value);
        }
        for(Map.Entry<String, Object> node : paramMap.entrySet()) {
            boolean valid = true;
            if (node.getValue() instanceof String)
                valid = XSSCheckUtil.validate((String)node.getValue(),"");
            if (!valid) {
                resp.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.write("{\"success\":false,\"msg\":\""+"参数不安全"+"\",\"code\":"+"0"+"}");
                writer.flush();
                return;
            }
            //效验js注入
            if (node.getValue() instanceof String) {
                XSSCheckUtil.validate((String) node.getValue(),"");
            }
        }
        chain.doFilter(requestWrapper, resp);
    }


    @Override
    public void destroy() {

    }

    /**
     * 两个方法都注明方法只能被调用一次，由于RequestBody是流的形式读取，
     * 那么流读了一次就没有了，所以只能被调用一次。
     * 既然是因为流只能读一次的原因，那么只要将流的内容保存下来，就可以实现反复读取了
     * @author LIU
     *
     */
    public static class ReaderReuseHttpServletRequestWrapper extends HttpServletRequestWrapper {


        private final byte[] body;

        public ReaderReuseHttpServletRequestWrapper(HttpServletRequest request)
                throws IOException {
            super(request);
            body = IOUtils.toString(request.getReader()).getBytes(Charset.forName("UTF-8"));
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            final ByteArrayInputStream bais = new ByteArrayInputStream(body);
            return new ServletInputStream() {

                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }

                @Override
                public int read() throws IOException {
                    return  bais.read();
                }

            };
        }
    }



}
