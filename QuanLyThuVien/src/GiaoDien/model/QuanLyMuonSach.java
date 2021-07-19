package GiaoDien.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyMuonSach {
    static private List<Book> book;
    private List<Book> sachTrongKho;
    private List<Book> sachDaMuon;
    private List<SinhVien> sinhVien;
    private List<Book> thungRac;
    
    public QuanLyMuonSach() {
        book = new ArrayList<Book>();
        sachTrongKho = new ArrayList<Book>();
        sachDaMuon = new ArrayList<Book>();
        thungRac = new ArrayList<Book>();
        sinhVien = new ArrayList<SinhVien>();
    }

    static private String thuMucHT = System.getProperty("user.dir");
    static private String seprator = File.separator;
    static private String path_file_BookJSON = thuMucHT + seprator + "Data" + seprator + "Book.json";

    static private void dataJSonBook() {
        FileReader fr = null;
        try {           
            fr = new FileReader(path_file_BookJSON);
            Gson gson = new Gson();
            
            Type classOfT = new TypeToken<List<Book>>() {
            }.getType();
            
            book = gson.fromJson(fr, classOfT);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuanLyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(QuanLyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Không dùng được generic vì khi getData sẽ ra Objet LinkedTreeNode
    private ArrayList<SinhVien> dataJSonSinhVien(File file) {
        StringBuilder json = new StringBuilder();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bf.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(QuanLyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException ex) {
                    Logger.getLogger(QuanLyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        ArrayList<SinhVien> list = null;
        if (json != null) {
            String jsonData = json.toString();
            Type type = new TypeToken<ArrayList<SinhVien>>() {
            }.getType();
            Gson gson = new Gson();
            list = (ArrayList<SinhVien>) gson.fromJson(jsonData, type);
        }
        if (list == null) {
            return new ArrayList<SinhVien>();
        }
        return list;
    }

    public void writeJson(File file, ArrayList<?> list) {
        Type type = new TypeToken<ArrayList<?>>() {
        }.getType();
        Gson gson = new Gson();
        String json = gson.toJson(list, type);
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter(file));
            bf.write(json);
        } catch (IOException ex) {
            Logger.getLogger(QuanLyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException ex) {
                    Logger.getLogger(QuanLyMuonSach.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

     public String muonSach(String maSach, SinhVien sv, String ngayMuon, String ngayTra) {
        return null;
    }

    public String traSach(String maSach, String maSV) {
        return null;
    }

}
