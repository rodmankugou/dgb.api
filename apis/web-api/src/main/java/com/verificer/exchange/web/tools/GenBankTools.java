package com.verificer.exchange.web.tools;

import com.verificer.utils.SStringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GenBankTools {
    public static void main(String args[]) throws IOException {
        File file = new File("/Users/liujinhua/dev/ideaWorkSpace/bitver.api/work_note/temp2");
        List<String> lines = FileUtils.readLines(file);

        Long cur = System.currentTimeMillis();
        for(String str : lines){
            if(SStringUtils.isEmpty(str))
                continue;

            String bankName = SStringUtils.split(str,"\\\t").get(1);

            String sqlTmp = "insert into `bitver_biz`.`bank` (  `bank_name`, `branch_name`, `create_time`) values (  '#bankName', 'None', '#cur');";
            String sql = sqlTmp.replaceAll("#bankName",bankName);
            sql = sql.replaceAll("#cur",cur.toString());
            System.out.println(sql);
        }
    }
}
