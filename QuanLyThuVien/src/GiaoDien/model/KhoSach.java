/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Loi
 */
public class KhoSach {    
    private Book book;
    private List<SinhVien> arrSV;

    public KhoSach() {
        this.book = new Book();
        this.arrSV = new ArrayList<SinhVien>();
    }

    public KhoSach(Book book, List<SinhVien> arrSV) {
        this.book = book;
        this.arrSV = arrSV;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void addSinhVien(SinhVien sv){
        this.arrSV.add(sv);
    }
    
    public void removeSinhVien(int index){
        this.arrSV.remove(index);
    }

    public List<SinhVien> getArrSV() {
        return arrSV;
    }

    public void setArrSV(List<SinhVien> arrSV) {
        this.arrSV = arrSV;
    }
    
    
}
