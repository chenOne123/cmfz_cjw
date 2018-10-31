package com.baizhi.cmfz_cjw.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Guru {
    private int id;
    private String headPic;
    private String dharmaName;
    private int statusa;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private int gid;
    private List<Artical> articalList;

    public Guru() {
    }

    public Guru(int id, String headPic, String dharmaName, int statusa, Date createDate, int gid, List<Artical> articalList) {
        this.id = id;
        this.headPic = headPic;
        this.dharmaName = dharmaName;
        this.statusa = statusa;
        this.createDate = createDate;
        this.gid = gid;
        this.articalList = articalList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadPic() {
        return headPic;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public int getStatusa() {
        return statusa;
    }

    public void setStatusa(int statusa) {
        this.statusa = statusa;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Artical> getArticalList() {
        return articalList;
    }

    public void setArticalList(List<Artical> articalList) {
        this.articalList = articalList;
    }

    @Override
    public String toString() {
        return "Guru{" +
                "id=" + id +
                ", headPic='" + headPic + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", statusa=" + statusa +
                ", createDate=" + createDate +
                ", gid=" + gid +
                ", articalList=" + articalList +
                '}';
    }
}
