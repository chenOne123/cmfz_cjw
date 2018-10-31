package com.baizhi.cmfz_cjw.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bri;
    private String province;
    private int shu;
    private String pwd;
    private String phone;

    public User() {
    }

    public User(Integer id, String name, Date bri, String province) {
        this.id = id;
        this.name = name;
        this.bri = bri;
        this.province = province;
    }

    public User(Integer id, String name, Date bri, String province, int shu, String pwd, String phone) {
        this.id = id;
        this.name = name;
        this.bri = bri;
        this.province = province;
        this.shu = shu;
        this.pwd = pwd;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getShu() {
        return shu;
    }

    public void setShu(int shu) {
        this.shu = shu;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBri() {
        return bri;
    }

    public void setBri(Date bri) {
        this.bri = bri;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bri=" + bri +
                ", province='" + province + '\'' +
                ", shu=" + shu +
                ", pwd='" + pwd + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
