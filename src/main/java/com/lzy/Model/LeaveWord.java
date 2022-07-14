package com.lzy.Model;

public class LeaveWord {
    private String sendaccount;
    private String acceptaccount;
    private String content;
    private String msgid;

    public LeaveWord(String sendaccount, String acceptaccount, String content, String msgid) {
        this.sendaccount = sendaccount;
        this.acceptaccount = acceptaccount;
        this.content = content;
        this.msgid = msgid;
    }

    public LeaveWord() {
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
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


}
