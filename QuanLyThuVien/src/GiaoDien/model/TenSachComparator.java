/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien.model;

import java.util.Comparator;

/**
 *
 * @author Loi
 */
public class TenSachComparator implements Comparator<Book>{

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getTenSach().compareTo(o2.getTenSach());
    }
    
}
