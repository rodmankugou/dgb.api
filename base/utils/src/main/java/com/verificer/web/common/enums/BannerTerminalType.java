package com.verificer.web.common.enums;

/**
 */
public enum BannerTerminalType {
	PC(){
		@Override
		public String toString() {
			return "网页端";
		}
	},
	APP(){
		@Override
		public String toString() {
			return "APP端";
		}
	},
	APP_INVITE (){
		@Override
		public String toString() {
			return "app活动使用的banner";
		}
	},
	ACTIVITY(){
		@Override
		public String toString() {
			return "活动Banner";
		}
	}
}
