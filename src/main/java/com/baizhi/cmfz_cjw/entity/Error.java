package com.baizhi.cmfz_cjw.entity;

public class Error {
    private String error;
    private String tishi;

    public Error() {
    }

    public Error(String error, String tishi) {
        this.error = error;
        this.tishi = tishi;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTishi() {
        return tishi;
    }

    public void setTishi(String tishi) {
        this.tishi = tishi;
    }

    @Override
    public String toString() {
        return "Error{" +
                "error='" + error + '\'' +
                ", tishi='" + tishi + '\'' +
                '}';
    }
}
