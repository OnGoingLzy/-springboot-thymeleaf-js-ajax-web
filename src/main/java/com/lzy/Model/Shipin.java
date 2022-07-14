package com.lzy.Model;


public class Shipin {

    private int id;
    private String url;
    private String name;
    private String author;
    private String tag;
    private String title;

    public Shipin() {
    }

    public Shipin(int id, String url, String name, String author, String tag, String title) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.author = author;
        this.tag = tag;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
