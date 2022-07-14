package com.lzy.Model;



public class collect {
    private String collectFolderName;
    private String id;
    private String author;

    public collect() {
    }

    public collect(String collectFolderName, String id, String author) {
        this.collectFolderName = collectFolderName;
        this.id = id;
        this.author = author;
    }

    public String getCollectFolderName() {
        return collectFolderName;
    }

    public void setCollectFolderName(String collectFolderName) {
        this.collectFolderName = collectFolderName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
