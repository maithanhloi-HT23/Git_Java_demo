package GiaoDien.model;

import java.time.LocalDate;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class Book {

    private String maSach;
    private String tenSach;
    private String theLoai;
    private String tacGia;
    private String NXB;
    private int soLuong;

    public Book(String ma, String tenSach, String theLoai, String tacGia, String NXB, int soLuong) {
        this.maSach = ma;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.NXB = NXB;
    }

    public Book() {
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String ma) {
        this.maSach = ma;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    @Override
    public String toString() {
        return "Book{" + "maSach=" + maSach + ", tenSach=" + tenSach + ", theLoai=" + theLoai + ", tacGia=" + tacGia + ", NXB=" + NXB + ", soLuong=" + soLuong + '}';
    }

}
