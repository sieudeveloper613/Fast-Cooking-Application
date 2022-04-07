package com.example.project_team7.Model;

public class MonAn {
    String img, ten, cachLam, nguyenLieu, id, imgNL;

    public MonAn() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgNL() {
        return imgNL;
    }

    public void setImgNL(String imgNL) {
        this.imgNL = imgNL;
    }

    public MonAn(String img, String ten, String cachLam, String nguyenLieu, String id, String imgNL) {
        this.img = img;
        this.ten = ten;
        this.cachLam = cachLam;
        this.nguyenLieu = nguyenLieu;
        this.id = id;
        this.img = imgNL;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCachLam() {
        return cachLam;
    }

    public void setCachLam(String cachLam) {
        this.cachLam = cachLam;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }
}
