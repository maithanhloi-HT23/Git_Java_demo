/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien.model;

import java.util.List;

/**
 *
 * @author Loi
 */
public class PhieuMuon {
    private String soPhieu;
    private SinhVien sv;
    private List<Book> listBook;
    private String ngayMuon;
    private String ngayHen;
    private String ngayTra;
    private boolean trangThai;

    public PhieuMuon() {
    }

    public PhieuMuon(String soPhieu, SinhVien sv, List<Book> listBook, String ngayMuon, String ngayHen, String ngayTra, boolean trangThai) {
        this.soPhieu = soPhieu;
        this.sv = sv;
        this.listBook = listBook;
        this.ngayMuon = ngayMuon;
        this.ngayHen = ngayHen;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
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

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }

    public void addBook(Book b){
        listBook.add(b);
    }
    
    public void removeBook(int index){
        listBook.remove(index);
    }
    
    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(String ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

   
}
