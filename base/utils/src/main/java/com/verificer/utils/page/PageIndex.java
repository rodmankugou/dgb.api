package com.verificer.utils.page;

import java.io.Serializable;

public class PageIndex implements Serializable {
    private int sIdx;
    private int eIdx;

    public PageIndex() {
    }

    public PageIndex(int sIdx, int eIdx) {
        this.sIdx = sIdx;
        this.eIdx = eIdx;
    }

    public int getsIdx() {
        return sIdx;
    }

    public void setsIdx(int sIdx) {
        this.sIdx = sIdx;
    }

    public int geteIdx() {
        return eIdx;
    }

    public void seteIdx(int eIdx) {
        this.eIdx = eIdx;
    }

    @Override
    public String toString() {
        return "PageIndex{" +
                "sIdx=" + sIdx +
                ", eIdx=" + eIdx +
                '}';
    }
}
