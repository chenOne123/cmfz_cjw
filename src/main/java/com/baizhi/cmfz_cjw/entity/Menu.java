package com.baizhi.cmfz_cjw.entity;

import java.util.List;

public class Menu {
    private int id;
    private String title;
    private int parentId;
    private String iconCls;
    private String url;
    private List<Menu> mList;

    public Menu() {
    }

    public Menu(int id, String title, int parentId, String iconCls, String url) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.iconCls = iconCls;
        this.url = url;
    }

    public List<Menu> getmList() {
        return mList;
    }

    public void setmList(List<Menu> mList) {
        this.mList = mList;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", parentId=" + parentId +
                ", iconCls='" + iconCls + '\'' +
                ", url='" + url + '\'' +
                ", mList=" + mList +
                '}';
    }
}
