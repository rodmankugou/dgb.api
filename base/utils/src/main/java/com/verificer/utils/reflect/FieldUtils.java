package com.verificer.utils.reflect;

import java.lang.reflect.Field;

public class FieldUtils {
    private static final ClassCache classCache = new ClassCache();

    public static Object readVal(Object obj,String filedName){
        if(obj == null)
            throw new RuntimeException("Parameter obj can not be null");
        try {
            Field[] fields =  classCache.getFields(obj);
            for(Field field : fields){

                if(field.getName().equals(filedName)){
                    field.setAccessible(true);
                    return field.get(obj);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        throw new RuntimeException("Not Filed "+filedName+" in obj,obj class is " +obj.getClass() );
    }

    public static Field[] getFields(Object obj){
        return classCache.getFields(obj);
    }
}
