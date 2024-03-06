package com.verificer.enums;

/**
 * Created by 35336 on 2020/12/30.
 */
public enum ClientEnum {
    WEB("web"),APP("app");

    private String name;
    ClientEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
