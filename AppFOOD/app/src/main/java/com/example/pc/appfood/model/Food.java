package com.example.pc.appfood.model;

public class Food {
    String name;
    int gia;
    int id;
    int idnhahang;
    String hinhaanh;

    public Food(String name, int gia, int id, int idnhahang, String hinhaanh) {
        this.name = name;
        this.gia = gia;
        this.id = id;
        this.idnhahang = idnhahang;
        this.hinhaanh = hinhaanh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdnhahang() {
        return idnhahang;
    }

    public void setIdnhahang(int idnhahang) {
        this.idnhahang = idnhahang;
    }

    public String getHinhaanh() {
        return hinhaanh;
    }

    public void setHinhaanh(String hinhaanh) {
        this.hinhaanh = hinhaanh;
    }
}
