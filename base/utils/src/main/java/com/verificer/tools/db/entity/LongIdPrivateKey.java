package com.verificer.tools.db.entity;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class LongIdPrivateKey extends IdPrivateKey{

    protected abstract Long getId();

    @Override
    @JSONField(serialize = false)
    public String loadPrivateKey() {
        return getId().toString();
    }
}
