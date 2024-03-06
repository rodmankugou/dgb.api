package com.verificer.utils.excel;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liujinhua on 2017/9/5.
 */
public abstract class ExcelHelper {
    /**
     * 对象序列化版本号名称
     */
    public static final String UID = "serialVersionUID";

    /**
     * 将指定excel文件中的数据转换成数据列表
     *
     * @param clazz
     *            数据类型
     * @param sheetNo
     *            工作表编号
     * @param hasTitle
     *            是否带有标题
     * @return 返回转换后的数据列表
     * @throws Exception
     */
    public <T> List<T> readExcel(Class<T> clazz, int sheetNo, boolean hasTitle)
            throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            fieldNames[i] = fieldName;
        }
        return readExcel(clazz, fieldNames, sheetNo, hasTitle);
    }

    /**
     * 将指定excel文件中的数据转换成数据列表
     *
     * @param clazz
     *            数据类型
     * @param fieldNames
     *            属性列表
     * @param hasTitle
     *            是否带有标题
     * @return 返回转换后的数据列表
     * @throws Exception
     */
    public <T> List<T> readExcel(Class<T> clazz, String[] fieldNames,
                                 boolean hasTitle) throws Exception {
        return readExcel(clazz, fieldNames, 0, hasTitle);
    }

    /**
     * 抽象方法：将指定excel文件中的数据转换成数据列表，由子类实现
     *
     * @param clazz
     *            数据类型
     * @param fieldNames
     *            属性列表
     * @param sheetNo
     *            工作表编号
     * @param hasTitle
     *            是否带有标题
     * @return 返回转换后的数据列表
     * @throws Exception
     */
    public abstract <T> List<T> readExcel(Class<T> clazz, String[] fieldNames,
                                          int sheetNo, boolean hasTitle) throws Exception;

    /**
     * 写入数据到指定excel文件中
     *
     * @param dataModels
     *            数据列表
     * @throws Exception
     */
    public <T> void writeExcel(String[] headers, List<T> dataModels)
            throws Exception {
        Class<T> clazz = null;
        String[] fieldNames = null;
        if(dataModels.size() > 0){
            clazz = (Class<T>) dataModels.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();
            fieldNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                fieldNames[i] = fieldName;
            }
        }
        writeExcel(clazz, dataModels, fieldNames,headers );
    }

    /**
     * 写入数据到指定excel文件中
     *
     * @param clazz
     *            数据类型
     * @param dataModels
     *            数据列表
     * @param fieldNames
     *            属性列表
     * @throws Exception
     */
    public <T> void writeExcel(File file,Class<T> clazz, List<T> dataModels,
                               String[] fieldNames) throws Exception {
        writeExcel(clazz, dataModels, fieldNames, fieldNames);
    }

    /**
     * 抽象方法：写入数据到指定excel文件中，由子类实现
     *
     * @param clazz
     *            数据类型
     * @param dataModels
     *            数据列表
     * @param fieldNames
     *            属性列表
     * @param titles
     *            标题列表
     * @throws Exception
     */
    public abstract <T> void writeExcel(Class<T> clazz, List<T> dataModels,
                                        String[] fieldNames, String[] titles) throws Exception;

    /**
     * 判断属性是否为日期类型
     *
     * @param clazz
     *            数据类型
     * @param fieldName
     *            属性名
     * @return 如果为日期类型返回true，否则返回false
     */
    protected <T> boolean isDateType(Class<?> clazz, String fieldName) {
        boolean flag = false;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            Object typeObj = field.getType().newInstance();
            flag = typeObj instanceof Date;
        } catch (Exception e) {
            // 把异常吞掉直接返回false
        }
        return flag;
    }

    /**
     * 根据类型将指定参数转换成对应的类型
     *
     * @param value
     *            指定参数
     * @param type
     *            指定类型
     * @return 返回类型转换后的对象
     */
    protected <T> Object parseValueWithType(String value, Class<?> type) {
        Object result = null;
        try { // 根据属性的类型将内容转换成对应的类型
            if (Boolean.TYPE == type) {
                result = Boolean.parseBoolean(value);
            } else if (Byte.TYPE == type) {
                result = Byte.parseByte(value);
            } else if (Short.TYPE == type) {
                result = Short.parseShort(value);
            } else if (Integer.TYPE == type) {
                result = Integer.parseInt(value);
            } else if (Long.TYPE == type) {
                result = Long.parseLong(value);
            } else if (Float.TYPE == type) {
                result = Float.parseFloat(value);
            } else if (Double.TYPE == type) {
                result = Double.parseDouble(value);
            } else {
                result = (Object) value;
            }
        } catch (Exception e) {
            // 把异常吞掉直接返回null
        }
        return result;
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<Employee>();
        for(long i = 0 ; i < 40;i++){
            employees.add(new Employee(i, "Jones", 40, "Manager", 2975));
        }
        String[] titles = new String[]{"工号", "姓名", "年龄", "职称", "薪资（美元）", "入职时间"};
        String[] fieldNames = new String[]{"id", "name", "age", "job",
                "salery", "addtime"};
        try {

            File file3 = new File("E:\\POI2007"+System.currentTimeMillis()+".xlsx");
            ExcelHelper eh3 = new XssfExcelHelper(file3);
            eh3.writeExcel(titles, employees);
//            eh3.writeExcel(Employee.class, employees, fieldNames, titles);
//            List<Employee> list3 = eh3.readExcel(Employee.class, fieldNames,
//                    true);
//            System.out.println("-----------------POI2007.xls-----------------");
//            for (Employee user : list3) {
//                System.out.println(user);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Employee {
        private long id;
        private String name;
        private int age;
        private String job;
        private double salery;
        private Date addtime;

        public Employee() {

        }

        public Employee(long id, String name, int age, String job, double salery) {
            super();
            this.id = id;
            this.name = name;
            this.age = age;
            this.job = job;
            this.salery = salery;
            this.addtime = new Date();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public double getSalery() {
            return salery;
        }

        public void setSalery(double salery) {
            this.salery = salery;
        }

        public Date getAddtime() {
            return addtime;
        }

        public void setAddtime(Date addtime) {
            this.addtime = addtime;
        }

        @Override
        public String toString() {
            return "Employee [id=" + id + ", name=" + name + ", age=" + age
                    + ", job=" + job + ", salery=" + salery + ", addtime="
                    + addtime + "]";
        }
    }

	public abstract <T> void writeExcelByMap(String[][] headers, List<Map<String, Object>> dataRows) throws Exception;


}
