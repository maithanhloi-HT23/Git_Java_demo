package GiaoDien.model;

import java.util.ArrayList;
import java.util.List;

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
    private String soDienThoai;
    private List<Book> sachDangMuon;

    public SinhVien(String maSV,String lop, String ten, String soDienThoai) {
        this.maSV = maSV;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.lop = lop;
        sachDangMuon = new ArrayList<Book>();
    }

    public SinhVien() {
    }
    
    public void addBook(Book b){
        sachDangMuon.add(b);
    }
    
    public void removeBook(int index){
        sachDangMuon.remove(index);
    }

    public List<Book> getSachDangMuon() {
        return sachDangMuon;
    }

    public void setSachDangMuon(List<Book> sachDangMuon) {
        this.sachDangMuon = sachDangMuon;
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

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
