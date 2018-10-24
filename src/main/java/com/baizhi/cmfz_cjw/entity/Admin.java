package com.baizhi.cmfz_cjw.entity;

public class Admin {
    private int id;
    private String name;
    private String password;

    public Admin() {
    }

    public Admin(int id, String name, String passwrod) {
        this.id = id;
        this.name = name;
        this.password = passwrod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwrod='" + password + '\'' +
                '}';
    }
}
