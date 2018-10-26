package com.baizhi.cmfz_cjw.entity;

public class Chapter {
    private String id;
    private String name;
    private String url;
    private int size;
    private String duration;
    private int aid;

    public Chapter() {
    }

    public Chapter(String id, String name, String url, int size, String duration, int aid) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.size = size;
        this.duration = duration;
        this.aid = aid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", size='" + size + '\'' +
                ", duration='" + duration + '\'' +
                ", aid=" + aid +
                '}';
    }
}
