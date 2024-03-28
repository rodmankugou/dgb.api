package com.verificer.tools.db.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;

public interface IPrivateKey {
    @JSONField(serialize = false)
    String loadPrivateKey();
    @JSONField(serialize = false)
    String loadPkFieldName();
}
