package com.verificer.account.itf;

import com.verificer.utils.SStringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegralTools {
    private static final String VERSION_SPLIT = "@";

    private static Map<String,IntegralRemarkParser> parserMap = new HashMap<>();
    private static IntegralRemarkParser parser ;

    static {

        V1Parser v1Parser = new V1Parser();
        parser = v1Parser;
        parserMap.put(v1Parser.getVersion(),v1Parser);
    }

    public static class IntegralRemark{
        public String title;
        public String subTitle;
    }

    static interface IntegralRemarkParser{
        IntegralRemark parse(String remark);
        String format(String title,String subTitle);
        String getVersion();
    }

    static class V1Parser implements IntegralRemarkParser{
        private static final String INTEGRAL_REMARK_VERSION = "v1";
        private static final String INTEGRAL_REMARK_SPLIT = "@";
        @Override
        public IntegralRemark parse(String remark) {
            List<String> fields = SStringUtils.split(remark,INTEGRAL_REMARK_SPLIT);
            IntegralRemark r = new IntegralRemark();
            r.title = fields.get(1);
            r.subTitle = fields.get(2);
            return r;
        }

        @Override
        public String format(String title, String subTitle) {
            title = title == null ? " " : title;
            subTitle = title == null ? " " : subTitle;
            if(title.contains(INTEGRAL_REMARK_SPLIT))
                throw new RuntimeException("IntegralRemark 的 title 不能包含字符@");
            if(subTitle.contains(INTEGRAL_REMARK_SPLIT))
                throw new RuntimeException("IntegralRemark 的 subTitle 不能包含字符@");


            return INTEGRAL_REMARK_VERSION+VERSION_SPLIT+title+INTEGRAL_REMARK_SPLIT+subTitle;
        }

        @Override
        public String getVersion() {
            return INTEGRAL_REMARK_VERSION;
        }
    }

    public static IntegralRemark parse(String remark){
        if(SStringUtils.isEmpty(remark))
            throw new RuntimeException("Remark can not be empty");
        String version = remark.split(VERSION_SPLIT,2)[0];
        IntegralRemarkParser parser = parserMap.get(version);
        return parser.parse(remark);

    }

    public static String format(String title ,String subTitle){
        return parser.format(title,subTitle);
    }


}
