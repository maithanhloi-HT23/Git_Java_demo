/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien.model;

import java.util.ArrayList;

/**
 *
 * @author Loi
 */
public class PhieuTra {
    private String soPhieu; 
    private SinhVien sv;
    private ArrayList<Book> listBook;
    private String nguoiLap;
    private String ngayTra;
    private String ghiChu;

    public PhieuTra() {
    }

    public PhieuTra(String soPhieu, SinhVien sv, ArrayList<Book> listBook, String nguoiLap, String ngayTra, String ghiChu) {
        this.soPhieu = soPhieu;
        this.sv = sv;
        this.listBook = listBook;
        this.nguoiLap = nguoiLap;
        this.ngayTra = ngayTra;
        this.ghiChu = ghiChu;
    }
    
    

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public SinhVien getSv() {
        return sv;
    }

    public void setSv(SinhVien sv) {
        this.sv = sv;
    }

    public ArrayList<Book> getListBook() {
        return listBook;
    }

    public void setListBook(ArrayList<Book> listBook) {
        this.listBook = listBook;
    }

    public String getNguoiLap() {
        return nguoiLap;
    }

    public void setNguoiLap(String nguoiLap) {
        this.nguoiLap = nguoiLap;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
}

