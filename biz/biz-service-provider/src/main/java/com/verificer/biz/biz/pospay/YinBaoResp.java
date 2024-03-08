package com.verificer.biz.biz.pospay;

import java.util.List;

public class YinBaoResp {
    private String status;
    private List<String> messages;
    private String data;

    public boolean isSuc(){
        return "success".equalsIgnoreCase(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
