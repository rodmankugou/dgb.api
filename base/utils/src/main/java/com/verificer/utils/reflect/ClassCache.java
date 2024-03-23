package com.verificer.utils.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClassCache {
    private final ReentrantReadWriteLock L = new ReentrantReadWriteLock();
    private final Lock WLock = L.writeLock();
    private final Lock RLock = L.readLock();
    private Map<Class<?>,Field[]> cache = new HashMap<>();

    public  Field[] getFields(Object obj){
        if(obj == null)
            throw new RuntimeException("obj can not be null");

        Class<?> clazz = obj.getClass();
        RLock.lock();
        try {
            Field[] fields = cache.get(clazz);
            if (fields != null)
                return fields;
        } finally {
            RLock.unlock();
        }

        WLock.lock();
        try {
            Field[] fields = cache.get(clazz);
            if (fields != null)
                return fields;

            fields = getAllFields(clazz);
            cache.put(clazz,fields);
            return fields;
        } finally {
            WLock.unlock();
        }
    }

    private   Field[] getAllFields(Class<?> type){
        Field[] fields = type.getDeclaredFields();
        Class<?> superClass = type.getSuperclass();
        if (superClass != null) {
            Field[] superFields = getAllFields(superClass);
            Field[] combinedFields = new Field[fields.length + superFields.length];
            System.arraycopy(fields, 0, combinedFields, 0, fields.length);
            System.arraycopy(superFields, 0, combinedFields, fields.length, superFields.length);
            fields = combinedFields;
        }
        return fields;
    }
}
