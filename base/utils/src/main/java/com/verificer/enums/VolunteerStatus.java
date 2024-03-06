package com.verificer.enums;

/**
 * @author baixiaozheng
 * @desc ${DESCRIPTION}
 * @date 2018/7/19 下午3:56
 */
public enum VolunteerStatus {
    YES(){
        @Override
        public String toString() {
            return "是义工";
        }
    },
    NO(){
        @Override
        public String toString() {
            return "非义工";
        }
    },
    APPLYING(){
        @Override
        public String toString() {
            return "申请中";
        }
    }
}
