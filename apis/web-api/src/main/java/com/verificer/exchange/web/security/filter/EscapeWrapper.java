package com.verificer.exchange.web.security.filter;


import com.verificer.utils.check.XSSCheckUtil;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

/**
 * Created by choufengleilei on 2018\7\22
 */
public class EscapeWrapper extends HttpServletRequestWrapper {

    private static final String sqlReg = "( ? : ')|(?:--)|(/\\*(?:|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|where|join)\\b)";

    private static final String jsReg = "[()|{}';,<>?￥……（）——【】‘；：”“。，、？]";

    private Map<String, String[]> parameterMap;  //所有参数的Map集合
    public EscapeWrapper(HttpServletRequest request) {
        super(request);
        parameterMap = request.getParameterMap();
    }

    //重写几个HttpServletRequestWrapper中的方法
    /**
     * 获取所有参数名
     * @return 返回所有参数名
     * */
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<String>(parameterMap.keySet());
        return vector.elements();
    }

    /**
     * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值
     * 接收一般变量 ，如text类型
     *
     * @param name 指定参数名
     * @return 指定参数名的值
     * */
    @Override
    public String getParameter(String name) {
        String[] results = parameterMap.get(name);
        if(results == null || results.length <= 0)
            return null;
        else{
            return escapeXSS(results[0]);
        }
    }

    /**
     * 获取指定参数名的所有值的数组，如：checkbox的所有数据
     * 接收数组变量 ，如checkobx类型
     * */
    @Override
    public String[] getParameterValues(String name) {
        String[] results = parameterMap.get(name);
        if(results == null || results.length <= 0)
            return null;
        else{
            int length = results.length;
            for(int i=0;i<length;i++){
                results[i] = escapeXSS(results[i]);
            }
            return results;
        }
    }

    /**
     * 过滤字符串中的js脚本
     * 解码：StringEscapeUtils.unescapeXml(escapedStr)
     * */
    private String escapeXSS(String str){
        str = StringEscapeUtils.escapeXml(str);

        boolean validateJS = XSSCheckUtil.validate(str, jsReg);
        if(!validateJS){
            str = XSSCheckUtil.replace(str,jsReg);
        }
        boolean validateSQL = XSSCheckUtil.validate(str, sqlReg);
        if(!validateSQL){
            str = XSSCheckUtil.replace(str,sqlReg);
        }
        return str;
    }
}
