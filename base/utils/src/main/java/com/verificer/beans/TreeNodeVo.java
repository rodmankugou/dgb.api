package com.verificer.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 35336 on 2021/1/7.
 */

public class TreeNodeVo<T> implements Serializable {

    private List<TreeNodeVo<T>> children;
    private T vo;


    public List<TreeNodeVo<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNodeVo<T>> children) {
        this.children = children;
    }

    public T getVo() {
        return vo;
    }

    public void setVo(T vo) {
        this.vo = vo;
    }
}
