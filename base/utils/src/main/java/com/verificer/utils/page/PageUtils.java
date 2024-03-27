package com.verificer.utils.page;

import com.verificer.beans.PageQueryVo;
import com.verificer.utils.check.SCheckUtil;

public class PageUtils {

    public static PageIndex page(PageQueryVo pageVo,int size) {
        int page = pageVo.getPage() == null ? 1 : pageVo.getPage();
        int pageSize = pageVo.getPageSize() == null ? 10 : pageVo.getPageSize();
        SCheckUtil.lgThanAndNotNull(page,true,1,"page");
        SCheckUtil.lgThanAndNotNull(pageSize,true,1,"pageSize");

        int sIndex = (page-1)*pageSize;
        int eIndex = sIndex+pageSize-1;

        if(sIndex >= size)
            return null;

        if(eIndex >= size)
            eIndex = size-1;

        return new PageIndex(sIndex,eIndex);
    }


    public static void main(String args[]){
        System.out.println(page(new PageQueryVo(1,10),3));
        System.out.println(page(new PageQueryVo(2,3),10));
        System.out.println(page(new PageQueryVo(5,3),10));
    }
}
