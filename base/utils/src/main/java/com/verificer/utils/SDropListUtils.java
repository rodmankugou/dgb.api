package com.verificer.utils;

import com.verificer.beans.DropListVo;
import com.verificer.utils.reflect.ClassCache;
import com.verificer.utils.reflect.FieldUtils;
import com.verificer.utils.reflect.SBeanUtils;

import java.lang.reflect.Field;
import java.util.*;

public class SDropListUtils {



    public static List<DropListVo> toDropList(List<?> beans, String codeField, String nameField){
        List<DropListVo> rst = new LinkedList<>();
        if(beans.size() == 0 || beans == null){
            return rst;
        }

        for(Object bean : beans){
            DropListVo entity = new DropListVo();
            try {
                entity.setId(String.valueOf(FieldUtils.readVal(bean,codeField)));
                entity.setName(String.valueOf(FieldUtils.readVal(bean,nameField)));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(),e);
            }
            rst.add(entity);
        }

        return rst;
    }

    static class Status{
        private Long id;
        private String name;

        public Status(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
    public static void main(String args[]){
        List<Status> statuses = Arrays.asList(
                new Status(0L,"INIT"),
                new Status(1L,"SUC"),
                new Status(2L,"FAILED")
        );
        List<Status> list = new LinkedList<>();
        list.add(new Status(0L,"INIT"));
        list.add(new Status(1L,"SUC"));
        list.add(new Status(2L,"FAILED"));
        System.out.println(toDropList(list,"id","name"));
//


    }
}
