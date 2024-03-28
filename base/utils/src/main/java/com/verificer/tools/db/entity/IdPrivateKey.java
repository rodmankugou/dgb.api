package com.verificer.tools.db.entity;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class IdPrivateKey implements IPrivateKey{

    @Override
    @JSONField(serialize = false)
    public String loadPkFieldName() {
        return "id";
    }
}
