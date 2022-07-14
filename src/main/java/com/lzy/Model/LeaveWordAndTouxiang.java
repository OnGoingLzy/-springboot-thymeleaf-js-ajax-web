package com.lzy.Model;

public class LeaveWordAndTouxiang {
    private String sendaccount;
    private String acceptaccount;
    private String content;
    private String senderTouxaing;
    private String senderName;

    public LeaveWordAndTouxiang(String sendaccount, String acceptaccount, String content, String senderTouxaing, String senderName) {
        this.sendaccount = sendaccount;
        this.acceptaccount = acceptaccount;
        this.content = content;
        this.senderTouxaing = senderTouxaing;
        this.senderName = senderName;
    }

    public LeaveWordAndTouxiang() {
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSendaccount() {
        return sendaccount;
    }

    public void setSendaccount(String sendaccount) {
        this.sendaccount = sendaccount;
    }

    public String getAcceptaccount() {
        return acceptaccount;
    }

    public void setAcceptaccount(String acceptaccount) {
        this.acceptaccount = acceptaccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderTouxaing() {
        return senderTouxaing;
    }

    public void setSenderTouxaing(String senderTouxaing) {
        this.senderTouxaing = senderTouxaing;
    }
}
