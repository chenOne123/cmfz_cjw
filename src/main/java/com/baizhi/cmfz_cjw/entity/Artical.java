package com.baizhi.cmfz_cjw.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Artical {
    private int id;
    private String title;
    private String insertImg;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    private int gid;

    public Artical() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInsertImg() {
        return insertImg;
    }

    public void setInsertImg(String insertImg) {
        this.insertImg = insertImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "Artical{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", insertImg='" + insertImg + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                ", gid=" + gid +
                '}';
    }
}
