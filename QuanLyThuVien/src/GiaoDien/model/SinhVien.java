package GiaoDien.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class SinhVien {

    private String maSV;
    private String ten;
    private String lop;
    private String SDT;

    public SinhVien(String maSV, String ten, String lop, String SDT) {
        this.maSV = maSV;
        this.ten = ten;
        this.SDT = SDT;
        this.lop = lop;
    }

    public SinhVien() {
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    @Override
    public String toString() {
        return "SinhVien{" + "maSV=" + maSV + ", ten=" + ten + ", lop=" + lop + ", SDT=" + SDT + '}';
    }
}
