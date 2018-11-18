package com.example.pc.appfood.model;

import java.io.Serializable;

public class Res implements Serializable {
    public   int Id;
    public  String diachi;
    public  String tghoatdong;
    public  String hinhanh;
    public   String resname;

    public Res(int id, String diachi, String tghoatdong, String hinhanh, String resname) {
        Id = id;
        this.diachi = diachi;
        this.tghoatdong = tghoatdong;
        this.hinhanh = hinhanh;
        this.resname = resname;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTghoatdong() {
        return tghoatdong;
    }

    public void setTghoatdong(String tghoatdong) {
        this.tghoatdong = tghoatdong;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }
}
