package com.verificer.enums;

/**
 * CfdNodeRole
 *
 * @author Dog Knight
 * @date 2018/09/19 00:24
 * @description
 */
public enum CfdNodeRole {

    ADMIN(){
        @Override
        public String toString() {
            return "管理员";
        }
    },
    STAFF(){
        @Override
        public String toString() {
            return "员工";
        }
    },
    CUSTOMER(){
        @Override
        public String toString() {
            return "普通用户";
        }
    }

}
