package com.verificer.utils.excel;


import com.verificer.utils.SStringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nsm on 2015/7/30.
 */
public class ExcelUtil {
	
	public static Log log = LogFactory.getLog(ExcelUtil.class);

    public static final String FILE_TYPE_XLS = "xls";
    public static final String FILE_TYPE_XLSX = "xlsx";

    /**
     * 从excel文件中逐行读取数据，将每行转换成Map<String,Object>
     * @param filePath
     * @return
     * @throws Exception
     */
    public static List<Map<String,String>> readExcel(String filePath) throws Exception{
        InputStream in = null ;
        List<Map<String,Object>> result = new ArrayList<>();
        in = new FileInputStream(new File(filePath));
        String fileType = "";
        if(filePath.endsWith(".xls")){
            fileType = FILE_TYPE_XLS;
        }else if(filePath.endsWith(".xlsx")){
            fileType = FILE_TYPE_XLSX;
        }else {
            throw new Exception("File must endsWith '.xls' or '.xlsx'");
        }
        return readExcel(in,fileType);

    }

    /**
     * 根据文件名获取该excel文件的文件格式
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String  getFileType(String fileName) throws Exception{
        String fileType = "";
        if(fileName.endsWith(".xls")){
            fileType = FILE_TYPE_XLS;
        }else if(fileName.endsWith(".xlsx")){
            fileType = FILE_TYPE_XLSX;
        }else if(fileName.endsWith(".csv")){
            fileType = FILE_TYPE_XLS;
        }
        return fileType;
    }

    public static List<Map<String,String>> readExcel(InputStream in,String fileType) throws Exception{
        List<Map<String,String>> result = new ArrayList<>();
        try {
            Workbook workbook = null;
            if(fileType.equals(FILE_TYPE_XLS)){
                workbook = new HSSFWorkbook(in);
            }else if(fileType.equals((FILE_TYPE_XLSX))){
                workbook = new XSSFWorkbook(in);
            }else {
                throw new Exception("File must endsWith '.xls' or '.xlsx'");
            }
            Sheet sheet= workbook.getSheetAt(0);
            Row headers = sheet.getRow(0);
            for(int i = 1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
//                if(row == null){
//                    continue;
//                }
                Map<String,String> data = new HashMap<>();
                for(int j = 0 ;j<row.getLastCellNum();j++){
                    Cell cell = row.getCell(j);
                    String header = headers.getCell(j).getStringCellValue();
                    Object cellValue= getCellValue(cell);
                    data.put(header,cellValue == null ? null : cellValue+"");
                }
                result.add(data);
            }
        }finally {
            if(in != null){
                in.close();
            }
        }
        return result;
    }

    /**
     *从excel文件中逐行读取数据，将每行转换成List<String>
     * @param filePath
     * @return
     * @throws Exception
     */
    public static List<List<String>> readExcel2(String filePath) throws Exception{
        InputStream in = null ;
        List<List<String>> result = new ArrayList<>();
        try {
            in = new FileInputStream(new File(filePath));
            readExcel2(filePath,in);
        }finally {
            if(in != null){
                in.close();
            }
        }
        return result;

    }

    /**
     * 从excel文件中逐行读取数据，将每行转换成List<String>
     * @param fileName
     * @param in
     * @return
     * @throws IOException 
     */
    public static List<List<String>> readExcel2(String fileName,InputStream in) throws IOException{
        List<List<String>> result = new ArrayList<>();
        try {
            Workbook workbook = null;
            if(fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook(in);
            }else if(fileName.endsWith(".xlsx")){
                workbook = new XSSFWorkbook(in);
            }else {
                throw new RuntimeException("File must endsWith '.xls' or '.xlsx'");
            }
            Sheet sheet= workbook.getSheetAt(0);
            for(int i = 0;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                List<String> rowData = new ArrayList<>();

                if(row != null){
                    for(int j = 0 ;j<row.getLastCellNum();j++){
                        Cell cell = row.getCell(j);
                        if (null != cell) {
                        	Object cellValue= getCellValue(cell);
                        	rowData.add(cellValue == null ? null : cellValue+"");
						}
                    }
                }

                result.add(rowData);
            }
        }finally {
            if(in != null){
                in.close();
            }
        }
        return result;

    }

    private static Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字


        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("yyyy/m/d;@".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else if ("d/m/yyyy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else if ("yyyy/m/d".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else if("h:mm:ss".equals(cell.getCellStyle().getDataFormatString())){
                    Double time = cell.getNumericCellValue() * 24 * 60 * 60;
                    int hours = new Double(time/60/60).intValue();
                    int minutes =  new Double((time - hours * 60 * 60)/60).intValue();
                    int second = new Double(time%60).intValue();
                    String hoursStr = hours < 10 ? "0"+hours : hours+"";
                    String minutesStr = minutes < 10 ? "0"+minutes : minutes+"";
                    String secondStr = second < 10 ? "0"+second : second+"";
                    value = hoursStr + ":" + minutesStr + ":" + secondStr;
                }else if("h:mm".equals(cell.getCellStyle().getDataFormatString())){
                    Double time = cell.getNumericCellValue() * 24 * 60 * 60;
                    int hours = new Double(time/60/60).intValue();
                    int minutes =  new Double((time - hours * 60 * 60)/60).intValue();
                    int second = 0;
                    String hoursStr = hours < 10 ? "0"+hours : hours+"";
                    String minutesStr = minutes < 10 ? "0"+minutes : minutes+"";
                    String secondStr = second < 10 ? "0"+second : second+"";
                    value = hoursStr + ":" + minutesStr + ":" + secondStr;
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return value;
    }
    
    public static void exportExcel(String title,Class clzs,List datas,OutputStream out) throws IOException
    {
    	//生成一个工作薄
    	HSSFWorkbook workbook = new HSSFWorkbook();
    	//生成一个表格
    	HSSFSheet sheet = workbook.createSheet(title);
    	// 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth((short) 15);
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);
        List<FieldValue> list = fieldLabel(clzs);
        for (int i=0;i<list.size();i++) {
        	HSSFCell cell = row.createCell(i);  
            HSSFRichTextString text = new HSSFRichTextString(list.get(i).getField());  
            cell.setCellValue(text);  
		}
        Object data;
        String getMethodName;
        String fieldValue;
        for (int i = 0; i < datas.size(); i++) {
			row = sheet.createRow(i+1);
			data = datas.get(i);
			for (int j = 0; j < list.size(); j++) {
				 HSSFCell cell = row.createCell(j);  
				fieldValue = list.get(j).getValue();
				getMethodName = "get"  
                        + fieldValue.substring(0, 1).toUpperCase()  
                        + fieldValue.substring(1);  
				
				try {
					Method getMethod = clzs.getMethod(getMethodName,new Class[]{});
					getMethod.setAccessible(true);
					Object value = getMethod.invoke(data, new Class[]{});
					if(value != null)
					{
						cell.setCellValue(value.toString());
					}else{
						cell.setCellValue("");
					}
					
				} catch (Exception e) {
					log.error("数据写入excel异常",e);
				}
				
			}
			
		}
		workbook.write(out);
    }
	private static List<FieldValue> fieldLabel(Class clzs) {
		List<FieldValue> list = new ArrayList<FieldValue>();
		Field[] fields = clzs.getDeclaredFields();
        FieldValue fv;
        String f;
        String v;
        for (Field field : fields) {
        	if(field.isAnnotationPresent(ExcelField.class))
        	{
        		ExcelField feld = field.getAnnotation(ExcelField.class);
        		if(feld !=null){
        			f = feld.value();
            		v = field.getName();
            		fv = new FieldValue(f,v);
            		list.add(fv);
        		}
        	}
		}
        return list;
	}
	
	public static List<String> readExcelPhoneNum(String fileName,InputStream in) throws Exception{
        List<String> result = new ArrayList<>();
        Workbook workbook = null;

        if(fileName.endsWith(".xls")){
            workbook = new HSSFWorkbook(in);
        }else if(fileName.endsWith(".xlsx")){
            workbook = new XSSFWorkbook(in);
        }else {
            throw new Exception("File must endsWith '.xls' or '.xlsx'");
        }
        Sheet sheet= workbook.getSheetAt(0);
        for(int i = 0;i<=sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
            for(int j = 0 ;j<row.getLastCellNum();j++){
                Cell cell = row.getCell(j);
                Object cellValue= getCellValue(cell);
                result.add(cellValue.toString());
            }
        }
        return result;
    }
	public static void exportExcelMergedRegion(String title,Class clzs,List datas,Integer len,OutputStream out) throws IOException
    {
    	//生成一个工作薄
    	HSSFWorkbook workbook = new HSSFWorkbook();
    	//生成一个表格
    	HSSFSheet sheet = workbook.createSheet(title);
    	// 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth((short) 15);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);
        List<FieldValue> list = fieldLabel(clzs);
        for (int i=0;i<list.size();i++) {
        	HSSFCell cell = row.createCell(i);  
            HSSFRichTextString text = new HSSFRichTextString(list.get(i).getField());  
            cell.setCellValue(text);  
		}
        Object data;
        String getMethodName;
        String fieldValue;
        List<HSSFRow> rows = new ArrayList<HSSFRow>();
        Integer rowLen=0;
        HSSFCell cell=null;
        Integer length=0;
        for (int i = 0; i < datas.size();i++) {
        	rows.clear();
			data = datas.get(i);
			try {
				Method getLenMethod = clzs.getMethod("getLen",new Class[]{});
				rowLen = (Integer)getLenMethod.invoke(data, new Class[]{});
			} catch (Exception e) {
				log.error("获取对象含有子对象长度",e);
			}
			for (int k = 0; k < rowLen; k++) {
				length+=1;
				rows.add(sheet.createRow(length));
				
			}
			for (int j = 0; j < list.size(); j++) {
				fieldValue = list.get(j).getValue();
				getMethodName = "get"  
                        + fieldValue.substring(0, 1).toUpperCase()  
                        + fieldValue.substring(1);  
				
				try {
					Method getMethod = clzs.getMethod(getMethodName,new Class[]{});
					Object value = getMethod.invoke(data, new Class[]{});
					if(value != null)
					{
						if(value instanceof List){
							for (int h = 0; h < rowLen; h++) {
								cell = rows.get(h).createCell(j);
								cell.setCellValue(((List)value).get(h).toString());
							}
						}else{
							sheet.addMergedRegion(new CellRangeAddress(length-rowLen+1,length,(short)j,(short)j)); 
							cell = rows.get(0).createCell(j);  
							cell.setCellValue(value.toString());
						}
					}else{
						sheet.addMergedRegion(new CellRangeAddress(length-rowLen+1,length,(short)j,(short)j)); 
						cell = rows.get(0).createCell(j);  
						cell.setCellValue("");
					}
					cell.setCellStyle(cellStyle);
					
				} catch (Exception e) {
					log.error("数据写入excel异常",e);
				}
				
			}
			
		}
		workbook.write(out);
    }

    /**
     *
     * @param fileType excel文件类型(xls或xlsx)
     * @param fileName 文件名。最终用户下载到的文件名为：fileName + time_str + random_str.xls
     * @param headers 列名数组。
     * @param dataRows 数据列表，列表中的元素对应excel中的一行数据，列表中的元素要求为bean。行内的列先后顺序根据bean中字段的先后顺序确定。
     * @return
     * @throws Exception
     */
    public static ResponseEntity<byte[]> exportExcel(String fileType , String fileName, String[] headers, List dataRows)throws Exception{
        if(headers == null || dataRows == null){
            throw new IllegalArgumentException("headers 和 datas 参数不能为空");
        }
       
        StringBuilder finalFileName = new StringBuilder();
        finalFileName.append(fileName == null ? "" : fileName).append("_").append(String.valueOf(System.currentTimeMillis()));
        long random = Long.parseLong(SStringUtils.generateRandomNumSequence(10));
        finalFileName.append("_").append(random).append(".").append(fileType);

        if(FILE_TYPE_XLS.equals(fileType)){
            ExportExcel ex = new ExportExcel();
            OutputStream out = new FileOutputStream(finalFileName.toString());
            ex.exportExcel(fileName, headers, dataRows, out);
            out.close();
        }else if(FILE_TYPE_XLSX.equals(fileType)){
            //导出.xlsx
            File file = new File(finalFileName.toString());
            ExcelHelper helper = new XssfExcelHelper(file);
            helper.writeExcel(headers,dataRows);
        }else {
            throw new IllegalArgumentException("un support file type");
        }
        return download(finalFileName.toString());
    }
    
    public static ResponseEntity<byte[]> exportExcelByMap(String fileName, String[][] headers, List<Map<String,Object>> dataRows)throws Exception{
        if(headers == null || dataRows == null){
            throw new IllegalArgumentException("headers 和 datas 参数不能为空");
        }

        StringBuilder finalFileName = new StringBuilder();
        finalFileName.append(fileName == null ? "" : fileName).append("_").append(String.valueOf(System.currentTimeMillis()));
        long random = Long.parseLong(SStringUtils.generateRandomNumSequence(10));
        finalFileName.append("_").append(random).append(".").append(FILE_TYPE_XLSX);
        //导出.xlsx
        File file = new File(finalFileName.toString());
        ExcelHelper helper = new XssfExcelHelper(file);
        helper.writeExcelByMap(headers,dataRows);
        return download(finalFileName.toString());
    }

    // 进行下载的处理
    public static ResponseEntity<byte[]> download(String fileName) {
        try {
            HttpHeaders headers = new HttpHeaders();
            String rspName = fileName;
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            File file = new File(rspName);
            byte[] bytes = FileUtils.readFileToByteArray(file);
            try {
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
