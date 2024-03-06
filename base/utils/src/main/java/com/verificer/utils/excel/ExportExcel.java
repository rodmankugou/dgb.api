package com.verificer.utils.excel;

import com.verificer.utils.SDateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *================================================
 * 文件信息描述
 * Copyright © 2016花无缺. All rights reserved.
 *
 * @文件名: ExportExcel.java
 * @工程名: hwq_works
 * @包名: com.hwq.util
 * @描述: TODO
 * @作者: zfh
 * @时间: 2016年1月26日 上午9:34:23
 * @version: V1.0
 *=================================================
 */

public class ExportExcel<T> {

    public void exportExcel(String title,String[] headers, Collection<T> dataset,
                            OutputStream out) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException
    {
        exportExcel(title, headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
    }
    public void exportExcel(String title, String[] headers,
                            Collection<T> dataset, OutputStream out, String pattern) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置列宽自适应
        for(short i=0;i<headers.length;i++){
            sheet.setColumnWidth(i, 30 * 256);
        }
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
		/*		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");*/

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++)
        {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
        Field[] fields = null;
        Integer listRegionBegin = null;
        Integer listRegionEnd = null;
        Integer listSize = null;
        int listTypeFiledCount = 0; //数据类中，已发现类型为List的属性数目
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext())
        {
            index++;
            listTypeFiledCount = 0;
            row = sheet.createRow(index);
            T t = (T) it.next();
            if(fields == null){
                fields = t.getClass().getDeclaredFields();
            }
            int cellIndex = 0;
            for (int i = 0; i < fields.length; i++){

                Field field = fields[i];
                String fieldName = field.getName();
                StringBuilder getMethodName = new StringBuilder("get").append(fieldName.substring(0, 1).toUpperCase())
                        .append(fieldName.substring(1));
                Class<?> tCls = t.getClass();
                Method getMethod = tCls.getMethod(getMethodName.toString(),
                        new Class[]
                                {});
                Object value = getMethod.invoke(t, new Object[]
                        {});
                // 判断值的类型后进行强制类型转换
                String textValue = null;
                if (value instanceof byte[]){
                    // 有图片时，设置行高为60px;
                    row.setHeightInPoints(60);
                    // 设置图片所在列宽度为80px,注意这里单位的一个换算
                    //sheet.setColumnWidth(i, (short) (35.7 * 80));
                    byte[] bsValue = (byte[]) value;
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                            1023, 255, (short) 6, index, (short) 6, index);
                    anchor.setAnchorType(2);
                    patriarch.createPicture(anchor, workbook.addPicture(
                            bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                }else if(value instanceof List){
                    //如果是数组
                    if(listTypeFiledCount > 0){
                        throw new IllegalStateException("目前不支持将包含有多个list属性的数据转换成excel行");
                    }
                    listTypeFiledCount ++;


                    //如果数组为空，则设为空
                    if(value == null || ((List) value).size() == 0){
                        HSSFCell cell = row.createCell(cellIndex);
                        cell.setCellStyle(style2);
                        cell.setCellValue("");
                        cellIndex ++;
                        continue;
                    }else{
                        Field[] _fields = ((List)value).get(0).getClass().getDeclaredFields();
                        HSSFRow _row = null;
                        HSSFCell _cell;
                        listSize = ((List) value).size();
                        listRegionBegin = cellIndex;
                        listRegionEnd = cellIndex + _fields.length;
                        for(int _i =0 ; _i < ((List) value).size();_i++){
                            if(_i == 0){
                                _row = row;
                            }else {
                                _row = sheet.createRow(++index);
                                for(int l = 0 ;l < listRegionBegin; l++){
                                    _cell = _row.createCell(l);
                                    _cell.setCellStyle(style2);
                                    _cell.setCellValue("");
                                }
                                cellIndex = listRegionBegin;
                            }

                            Object _data = ((List) value).get(_i);
                            for(int _j = 0; _j < _fields.length ;_j++){
                                Field _field = _fields[_j];
                                String _fieldName = _field.getName();
                                StringBuilder _getMethodName = new StringBuilder("get").append(_fieldName.substring(0, 1).toUpperCase())
                                        .append(_fieldName.substring(1));
                                Method _getMethod = ((List)value).get(0).getClass().getMethod(_getMethodName.toString(), new Class[]{});
                                Object _value = _getMethod.invoke(_data, null);
                                _cell = _row.createCell(cellIndex++);
                                _cell.setCellStyle(style2);
                                _cell.setCellValue(format(_value,pattern));
                            }
                        }
                    }

                    //合并单元格
                    for(int _k = 0 ; _k < listRegionBegin;_k++){
                        sheet.addMergedRegion(new Region(index - listSize +1,(short)_k,index,(short)_k));
                    }
                    continue;
                }else{
                    textValue = format(value,pattern);
                }
                HSSFCell cell = row.createCell(cellIndex);
                cell.setCellStyle(style2);
                // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                if (textValue != null){
                    Matcher matcher = p.matcher(textValue);
                    if (matcher.matches()){
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    }else{
                        cell.setCellValue(textValue);
                    }
                    cell.setCellValue(textValue);
                }
                if(listSize != null && cellIndex >= listRegionEnd){
                    sheet.addMergedRegion(new Region(index - listSize + 1,(short)(cellIndex),index,(short)cellIndex));
                }

                cellIndex++;
            }
        }
        workbook.write(out);
    }

    private String  format(Object value,String datePattern){
        String textValue = "";
        if (value instanceof Boolean){
            boolean bValue = (Boolean) value;
            textValue = "yes";
            if (!bValue){
                textValue = "no";
            }
        }else if (value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
            Date date = (Date) value;
            textValue = sdf.format(date);
        }else{
            // 其它数据类型都当作字符串简单处理
            if(!StringUtils.isEmpty(value)){
                textValue = value.toString();
            }
        }
        return  textValue;
    }




    static class Data {
        String f0;
        String f1;
        List<InnerData> f2 ;
        String f3;
        String f4;

        public Data(int i){
            this.f0 = "f0_"+i;
            this.f1 = i+"";
            f3 = i*i +"";
            f4 = "f4_"+i;
            this.f2 = new ArrayList<InnerData>();
            f2.add(new InnerData(i));
            f2.add(new InnerData(i+1));

        }

        public String getF1() {
            return f1;
        }

        public void setF1(String f1) {
            this.f1 = f1;
        }

        public List<InnerData> getF2() {
            return f2;
        }

        public void setF2(List<InnerData> f2) {
            this.f2 = f2;
        }

        public String getF3() {
            return f3;
        }

        public void setF3(String f3) {
            this.f3 = f3;
        }

        public String getF0() {
            return f0;
        }

        public void setF0(String f0) {
            this.f0 = f0;
        }

        public String getF4() {
            return f4;
        }

        public void setF4(String f4) {
            this.f4 = f4;
        }
    }

    static class InnerData{
        String if1;
        Date if2;

        public InnerData(int i){
            this.if1 = "_f" + i;
            this.if2 = SDateUtil.nextNday(i);
        }

        public String getIf1() {
            return if1;
        }

        public void setIf1(String if1) {
            this.if1 = if1;
        }

        public Date getIf2() {
            return if2;
        }

        public void setIf2(Date if2) {
            this.if2 = if2;
        }
    }

    public static void main(String args[])throws Exception{
        Data data1 = new Data(1);
        Data data2 = new Data(2);
        List<Data> datas = new ArrayList<Data>();
        datas.add(data1);
        datas.add(data2);
        String[] heads = {"标题1","标题2","标题三","标题4"};
        OutputStream out = new FileOutputStream("C:\\Users\\liujinhua\\Desktop\\temp\\test.xls");
        ExportExcel exportExcel = new ExportExcel();
        exportExcel.exportExcel("hello",heads,datas,out);

    }
}


//`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长，主键ID',
//        `` int(11) DEFAULT NULL COMMENT '仪表ID',
//        `` bigint(20) DEFAULT NULL COMMENT '上次抄表时间',
//        `` bigint(20) DEFAULT NULL COMMENT '上上次抄表时间',
//        `` bigint(20) DEFAULT NULL COMMENT '本次抄表时间',
//        `` int(11) DEFAULT NULL COMMENT '上次抄表数值（度数或者吨数）',
//        `` int(11) DEFAULT NULL COMMENT '上上次抄表数值（度数或者吨数）',
//        `` int(11) DEFAULT NULL COMMENT '本次抄表数值（度数或者吨数）',
//        `` varchar(32) DEFAULT NULL COMMENT '抄表员',
//        `` decimal(9,2) DEFAULT NULL COMMENT '预计算水电费应收费用',
//        `` int(11) DEFAULT NULL COMMENT '费用异常预警（1-正常 0-预警）',
//        `` int(11) DEFAULT NULL COMMENT '审核状态（0-不通过 1-通过）',
//        `` varchar(200) DEFAULT NULL COMMENT '审核意见',
//        `` int(11) DEFAULT NULL COMMENT '是否最新记录（0-否 1-是）',
//        `` int(11) DEFAULT NULL COMMENT '是否有效（0-无效 1-有效）',
//        `` int(11) DEFAULT NULL COMMENT '会计年',
//        `` int(11) DEFAULT NULL COMMENT '会计月',
//        `` bigint(20) DEFAULT NULL COMMENT '创建时间',
//        `` bigint(20) DEFAULT NULL COMMENT '更新时间',
//        `` varchar(32) DEFAULT NULL COMMENT '创建人',
//        `` varchar(32) DEFAULT NULL COMMENT '更新人',
//        `` varchar(200) DEFAULT NULL COMMENT '备注',