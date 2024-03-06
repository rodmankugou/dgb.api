package com.verificer.utils;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/28.
 */
public class StaffVo implements Serializable {
    private Long id;
    private String username;
    private String nickname;
    private String roleName;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
