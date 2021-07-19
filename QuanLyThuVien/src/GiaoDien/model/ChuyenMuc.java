/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien.model;

/**
 *
 * @author Loi
 */
public class ChuyenMuc {
    private String maMuc;
    private String tenMuc;

    public ChuyenMuc(String maMuc, String tenMuc) {
        this.maMuc = maMuc;
        this.tenMuc = tenMuc;
    }

    public ChuyenMuc() {
    }

    public String getMaMuc() {
        return maMuc;
    }

    public void setMaMuc(String maMuc) {
        this.maMuc = maMuc;
    }

    public String getTenMuc() {
        return tenMuc;
    }

    public void setTenMuc(String tenMuc) {
        this.tenMuc = tenMuc;
    }

    @Override
    public String toString() {
        return "ChuyenMuc{" + "maMuc=" + maMuc + ", tenMuc=" + tenMuc + '}';
    }
    
    
}
