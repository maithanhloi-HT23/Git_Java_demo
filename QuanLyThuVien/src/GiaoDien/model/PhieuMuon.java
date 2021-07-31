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
public class PhieuMuon {
    private String soPhieu;
    private SinhVien sv;
    private ArrayList<Book> listBook;
    private String ngayMuon;
    private String ngayHenTra;
    private String nguoiLap;

    public PhieuMuon() {
    }

    public PhieuMuon(String soPhieu, SinhVien sv, ArrayList<Book> listBook, String ngayMuon, String ngayHenTra, String nguoiLap) {
        this.soPhieu = soPhieu;
        this.sv = sv;
        this.listBook = listBook;
        this.ngayMuon = ngayMuon;
        this.ngayHenTra = ngayHenTra;
        this.nguoiLap = nguoiLap;
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

    public PhieuMuon(String ngayHenTra) {
        this.ngayHenTra = ngayHenTra;
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

    public String getNgayHenTra() {
        return ngayHenTra;
    }

    public void setNgayHenTra(String ngayHenTra) {
        this.ngayHenTra = ngayHenTra;
    }
}
