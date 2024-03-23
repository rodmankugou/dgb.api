package com.verificer.beans;

import com.verificer.security.login.UserInfoSerializer;
import com.verificer.security.login.exception.UnSerializableException;
import com.verificer.utils.FastJson;
import com.verificer.utils.web.BlowfishUtils;
import com.verificer.utils.web.SecurityConf;

import java.io.Serializable;
import java.io.UncheckedIOException;
import java.util.Date;

public class UserIdentity implements UserInfoSerializer<UserIdentity> {

    private Long id;

    private Boolean hasActivation;

    private Integer role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public UserIdentity(Long id) {
        this.id = id;
    }

    public UserIdentity() {
    }



    @Override
    public String serialize(UserIdentity obj) {
        return FastJson.toJson(obj);
    }

    @Override
    public UserIdentity unSerialize(String value) throws UnSerializableException{
        UserIdentity identity = FastJson.fromJson(value,UserIdentity.class);
        return identity;
    }
}
