package com.lzy.Model;

public class ShenheShipin {
    private String vname;
    private String title;
    private String tag;
    private String author;
    private int id;
    private String lujing;

    public ShenheShipin(String vname, String title, String tag, String author, int id, String lujing) {
        this.vname = vname;
        this.title = title;
        this.tag = tag;
        this.author = author;
        this.id = id;
        this.lujing = lujing;
    }

    public ShenheShipin() {
    }

    public String getLujing() {
        return lujing;
    }

    public void setLujing(String lujing) {
        this.lujing = lujing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
