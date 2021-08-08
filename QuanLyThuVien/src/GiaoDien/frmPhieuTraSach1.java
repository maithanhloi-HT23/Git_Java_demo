/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import GiaoDien.model.Book;
import GiaoDien.model.PhieuMuon;
import GiaoDien.model.PhieuTra;
import GiaoDien.model.SinhVien;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class frmPhieuTraSach1 extends javax.swing.JFrame {

    static String pathPhieuTra = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "PhieuTra.json";
    static String pathSinhVien = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "SinhVien.json";
    static String pathPhieuMuon = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "PhieuMuon.json";
    static String pathSach = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "Book.csv";
    private ArrayList<PhieuMuon> listPhieuMuon;
    private ArrayList<PhieuTra> listPhieuTra;
    private ArrayList<Book> listSach;
    private ArrayList<Book> listSachDaThem;
    private SinhVien svHienTai;
    private DefaultTableModel tableModelPhieuMuon, tableModelSachDaThem;

    public frmPhieuTraSach1() {
        initComponents();
        this.setResizable(false);
        getDataBook();
        getDataPM();
        setJTable();
        searchReadTime();
        setText();
        jListMa.setVisible(false);
        jListTen.setVisible(false);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frmMenu.isOpenFrmPhieuTraSach1 = false;
            }

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frmMenu.isOpenFrmPhieuTraSach1 = false;
            }
            
        });
    }

    private void getDataBook() {
        frmSach frmS = new frmSach();
        listSach = frmS.getList();
    }

    private void getDataPM() {
        File filePM = new File(pathPhieuMuon);
        if (filePM.exists() == false) {
            try {
                filePM.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(frmPhieuMuonSach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        FileReader frPM = null;
        try {
            frPM = new FileReader(pathPhieuMuon);

            java.lang.reflect.Type typePhieuMuon = new TypeToken<ArrayList<PhieuMuon>>() {
            }.getType();
            Gson gson = new Gson();
            listPhieuMuon = gson.fromJson(frPM, typePhieuMuon);

        } catch (Exception e) {
        } finally {
            if (frPM != null) {
                try {
                    frPM.close();
                } catch (IOException ex) {
                    Logger.getLogger(frmPhieuMuonSach.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void updateDataJson(String path, ArrayList<PhieuMuon> list) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            java.lang.reflect.Type type = new TypeToken<ArrayList<?>>() {
            }.getType();
            Gson gson = new Gson();
            gson.toJson(list, type, fw);
        } catch (Exception e) {
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(frmPhieuMuonSach.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void updateSachCSV(String path, ArrayList<Book> list) {
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter(path));
            bf.write("Ma sach,Ten sach,The loai,Tac gia,Nha XB,So luong\n");
            for (int i = 0; i < listSach.size(); i++) {
                Book b = listSach.get(i);
                bf.write(b.getMaSach() + "," + b.getTenSach() + "," + b.getTheLoai() + "," + b.getTacGia() + "," + b.getNXB() + "," + String.valueOf(b.getSoLuong()));
                bf.write("\n");
            }
        } catch (Exception e) {
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException ex) {
                    Logger.getLogger(frmPhieuMuonSach.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void setJTable() {
        tableModelPhieuMuon = new DefaultTableModel();
        tableModelPhieuMuon.addColumn("Số phiếu");
        tableModelPhieuMuon.addColumn("Ngày lập");
        tableModelPhieuMuon.addColumn("Ngày trả");
        tableModelPhieuMuon.addColumn("Người lập");
        tableModelPhieuMuon.addColumn("Mã sinh viên");
        tableModelPhieuMuon.addColumn("Tên sinh viên");
        jTablePhieuMuon.setModel(tableModelPhieuMuon);

        tableModelSachDaThem = new DefaultTableModel();
        tableModelSachDaThem.addColumn("Mã sách");
        tableModelSachDaThem.addColumn("Tên sách");

        JTableSachDuocThem.setModel(tableModelSachDaThem);
        clickItemJTablePhieuMuon();
    }

    private void setText(){
        edtSoPhieu.setText("");
        edtNgayLap.setText("");
        edtNguoiLap.setText("");
        edtNgayHenTra.setText("");
        
        edtSoPhieu.setEditable(false);
        edtNgayLap.setEditable(false);
        edtNguoiLap.setEditable(false);
        edtNgayHenTra.setEditable(false);
    }
    
    private void searchReadTime() {
        edtMaSv.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!edtMaSv.getText().toString().trim().isEmpty()) {
                    updateChange();
                } else {
                    jListMa.setVisible(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!edtMaSv.getText().toString().trim().isEmpty()) {
                    updateChange();
                } else {
                    jListMa.setVisible(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!edtMaSv.getText().toString().trim().isEmpty()) {
                    updateChange();
                } else {
                    jListMa.setVisible(false);
                }
            }

            protected void updateChange() {
                Vector<String> vector = new Vector<>();
                String text = edtMaSv.getText().toString().trim();
                for (int i = 0; i < listPhieuMuon.size(); i++) {
                    SinhVien sv = listPhieuMuon.get(i).getSv();
                    if (sv.getMaSV().startsWith(text)) {
                        boolean check = true;
                        for(int j = 0;j<vector.size();j++){
                            if(vector.get(j).equals(sv.getMaSV())){
                                check = false;
                                break;
                            }
                        }
                        if(check){
                            vector.add(sv.getMaSV());
                        }    
                    }
                }
                vector.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                if (vector.size() > 0) {
                    jListMa.setListData(vector);
                    jListMa.setVisible(true);
                } else {
                    jListMa.setVisible(false);
                }
            }
        });

        edtTenSv.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!edtTenSv.getText().toString().trim().isEmpty()) {
                    updateChange();
                } else {
                    jListTen.setVisible(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!edtTenSv.getText().toString().trim().isEmpty()) {
                    updateChange();
                } else {
                    jListTen.setVisible(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!edtTenSv.getText().toString().trim().isEmpty()) {
                    updateChange();
                } else {
                    jListTen.setVisible(false);
                }
            }

            protected void updateChange() {
                Vector<String> vector = new Vector<>();
                String text = edtTenSv.getText().toString().trim();
                for (int i = 0; i < listPhieuMuon.size(); i++) {
                    SinhVien sv = listPhieuMuon.get(i).getSv();
                    if (sv.getTen().startsWith(text)) {
                        boolean check = true;
                        for (int j = 0; j < vector.size(); j++) {
                            if (vector.get(j).equals(sv.getTen())) {
                                check = false;
                            }
                        }
                        if (check) {
                            vector.add(sv.getTen());
                        }
                    }
                }
                vector.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                if (vector.size() > 0) {
                    jListTen.setListData(vector);
                    jListTen.setVisible(true);
                } else {
                    jListTen.setVisible(false);
                }
            }
        });
    }

    private void hienThiPhieu() {
        DefaultTableModel model = (DefaultTableModel) jTablePhieuMuon.getModel();
        while (model.getRowCount() > 0) // xóa hiện tại 
        {
            model.removeRow(0);
        }
        if (svHienTai != null) {
            for (int k = 0; k < listPhieuMuon.size(); k++) {
                Vector<String> vector = new Vector<>();
                PhieuMuon pm = listPhieuMuon.get(k);
                if (listPhieuMuon.get(k).getSv().getMaSV().equals(svHienTai.getMaSV())) {
                    vector.add(pm.getSoPhieu());
                    vector.add(pm.getNguoiLap());
                    vector.add(pm.getNgayHenTra());
                    vector.add(pm.getNguoiLap());
                    vector.add(pm.getSv().getMaSV());
                    vector.add(pm.getSv().getTen());
                    model.addRow(vector);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!");
        }
    }

    private void clickItemJTablePhieuMuon() {
        jTablePhieuMuon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                DefaultTableModel modelSach = (DefaultTableModel) JTableSachDuocThem.getModel();
                while (modelSach.getRowCount() > 0) // xóa hiện tại 
                {
                    modelSach.removeRow(0);
                }

                int row = jTablePhieuMuon.getSelectedRow();
                if (row > -1) {
                    String soPhieu = jTablePhieuMuon.getValueAt(row, 0).toString();
                    PhieuMuon p = listPhieuMuon.get(0);
                    for (int i = 0; i < listPhieuMuon.size(); i++) {
                        PhieuMuon pm = listPhieuMuon.get(i);
                        if (pm.getSoPhieu().equals(soPhieu)) {
                            for (int j = 0; j < pm.getListBook().size(); j++) {
                                Vector<String> vector = new Vector<>();
                                vector.add(pm.getListBook().get(j).getMaSach());
                                vector.add(pm.getListBook().get(j).getTenSach());
                                modelSach.addRow(vector);
                            }
                            p = pm;
                        }
                    }
                    edtSoPhieu.setText(soPhieu);
                    edtNgayLap.setText(p.getNgayMuon());
                    edtNguoiLap.setText(p.getNguoiLap());
                    edtNgayHenTra.setText(p.getNgayHenTra());
                    txtSoSach.setText("Số sách của phiếu này là: " + p.getListBook().size());
                }
            }
        });
    }

    @SuppressWarnings("unchecked")

    private void clickTim() {
        String maSv = edtMaSv.getText().toString().trim();
        if (!maSv.isEmpty()) {
            boolean check = true;
            for (int i = 0; i < listPhieuMuon.size(); i++) {
                SinhVien sv = listPhieuMuon.get(i).getSv();
                if (sv.getMaSV().equals(maSv)) {
                    edtTenSv.setText(sv.getTen());
                    svHienTai = sv;
                    edtTenSv.setText(sv.getTen());
                    check = false;
                }
            }
            if (check) {
                JOptionPane.showMessageDialog(this, "Sinh viên này không mượn sách");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        edtMaSv = new javax.swing.JTextField();
        btnTimSV = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListMa = new javax.swing.JList<>();
        edtTenSv = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListTen = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        txtSoPhieu = new javax.swing.JLabel();
        edtSoPhieu = new javax.swing.JTextField();
        txtNguoiLap = new javax.swing.JLabel();
        edtNguoiLap = new javax.swing.JTextField();
        txtNgayLap = new javax.swing.JLabel();
        edtNgayLap = new javax.swing.JTextField();
        txtNgayHenTra = new javax.swing.JLabel();
        edtNgayHenTra = new javax.swing.JTextField();
        btnTraTat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableSachDuocThem = new javax.swing.JTable();
        btnGhiPhieuTra = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePhieuMuon = new javax.swing.JTable();
        txtSoSach = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setText("QUẢN LÝ PHIẾU TRẢ SÁCH");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Mã sv:");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        edtMaSv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnTimSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTimSV.setText("Tìm sv");
        btnTimSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSVActionPerformed(evt);
            }
        });

        jListMa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jListMa.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Mã sách 1", "Mã sách 2", "Mã sách 3", "Mã sách 4", "Mã sách 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListMaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jListMa);

        edtTenSv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Tên sv:");
        jLabel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jListTen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jListTen.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Mã sách 1", "Mã sách 2", "Mã sách 3", "Mã sách 4", "Mã sách 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListTenMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jListTen);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6)
                            .addComponent(edtMaSv)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtTenSv)
                            .addComponent(jScrollPane7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimSV)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(edtMaSv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(edtTenSv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu trả sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSoPhieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoPhieu.setText("Số phiếu:");

        edtSoPhieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNguoiLap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNguoiLap.setText("Người lập:");

        edtNguoiLap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNgayLap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNgayLap.setText("Ngày lập:");

        edtNgayLap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNgayHenTra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNgayHenTra.setText("Ngày hẹn trả:");

        edtNgayHenTra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnTraTat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTraTat.setText("Trả tất");
        btnTraTat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraTatActionPerformed(evt);
            }
        });

        JTableSachDuocThem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTableSachDuocThem);

        btnGhiPhieuTra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGhiPhieuTra.setText("Ghi Phiếu Trả");
        btnGhiPhieuTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGhiPhieuTraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSoPhieu)
                                .addGap(18, 18, 18)
                                .addComponent(edtSoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNgayLap)
                                .addGap(18, 18, 18)
                                .addComponent(edtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNguoiLap)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edtNguoiLap, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNgayHenTra)
                                .addGap(18, 18, 18)
                                .addComponent(edtNgayHenTra, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGhiPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnTraTat, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(193, 193, 193))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNguoiLap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtNguoiLap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtSoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtNgayHenTra, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNgayHenTra, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnTraTat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGhiPhieuTra)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tất cả phiếu đã mượn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jTablePhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTablePhieuMuon);

        txtSoSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSoSach, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoSach, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(674, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 648, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSVActionPerformed
        clickTim();
        hienThiPhieu();
    }//GEN-LAST:event_btnTimSVActionPerformed

    private void jListMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMaMouseClicked
        int index = jListMa.locationToIndex(evt.getPoint());
        if (index >= 0) {
            String s = (String) jListMa.getModel().getElementAt(index);
            edtMaSv.setText(s);
            clickTim();
            jListMa.setVisible(false);
            for(int i = 0;i<listPhieuMuon.size();i++){
                if(listPhieuMuon.get(i).getSv().getMaSV().equals(s)){
                    svHienTai = listPhieuMuon.get(i).getSv();
                }
            }
            hienThiPhieu();
        }
    }//GEN-LAST:event_jListMaMouseClicked

    private void jListTenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListTenMouseClicked
        int index = jListTen.locationToIndex(evt.getPoint());
        if (index >= 0) {
            String s = (String) jListTen.getModel().getElementAt(index);
            edtTenSv.setText(s);
            for (int i = 0; i < listPhieuMuon.size(); i++) {
                SinhVien sv = listPhieuMuon.get(i).getSv();
                if (sv.getTen().equals(s)) {
                    edtMaSv.setText(sv.getMaSV());
                    jListMa.setVisible(false);
                    svHienTai = sv;
                }
            }
            jListTen.setVisible(false);
            hienThiPhieu();
        }
    }//GEN-LAST:event_jListTenMouseClicked

    private void btnGhiPhieuTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiPhieuTraActionPerformed
        // cập nhật lại sách + list Phiếu Mượn.
        if (svHienTai != null) {
            int a[] = JTableSachDuocThem.getSelectedRows();
            if(a.length == 0){
                JOptionPane.showMessageDialog(this, "Chưa chọn cuốn sách nào để trả!");
                return;
            }
            String soPhieu = jTablePhieuMuon.getValueAt(jTablePhieuMuon.getSelectedRow(), 0).toString();

            for (int i = 0; i < a.length; i++) { // trả sách
                String maSach = JTableSachDuocThem.getValueAt(a[i], 0).toString();
                boolean check = true;
                for (int j = 0; j < listSach.size(); j++) {
                    if (listSach.get(j).getMaSach().equals(maSach)) {
                        listSach.get(j).setSoLuong(listSach.get(j).getSoLuong() + 1);
                        check = false;
                    }
                }
                if (check) {
                    for (int k = 0; k < listPhieuMuon.size(); k++) {
                        if (listPhieuMuon.get(k).getSoPhieu().equals(soPhieu)) {
                            for (int l = 0; l < listPhieuMuon.get(k).getListBook().size(); l++) {
                                if (maSach.equals(listPhieuMuon.get(k).getListBook().get(l).getMaSach())) {
                                    listSach.add(listPhieuMuon.get(k).getListBook().get(l)); // chỗ này có thể gây lỗi
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < listPhieuMuon.size(); i++) {
                if (listPhieuMuon.get(i).getSoPhieu().equals(soPhieu)) {
                    if (a.length == listPhieuMuon.get(i).getListBook().size()) { // chọn tất
                        listPhieuMuon.remove(i);
                    } else {
                        for (int j = 0; j < a.length; j++) {
                            String maSach = JTableSachDuocThem.getValueAt(a[j], 0).toString();
                            for (int k = 0; k < listPhieuMuon.get(i).getListBook().size(); k++) {
                                if (maSach.equals(listPhieuMuon.get(i).getListBook().get(k).getMaSach())) {
                                    listPhieuMuon.get(i).getListBook().remove(k);
                                    k--;
                                }
                            }
                        }
                    }
                }
            }

            updateSachCSV(pathSach, listSach);
            updateDataJson(pathPhieuMuon, listPhieuMuon);
            hienThiPhieu();
            
            edtSoPhieu.setText("");
            edtNgayLap.setText("");
            edtNguoiLap.setText("");
            edtNgayHenTra.setText("");            
        }else{
            JOptionPane.showMessageDialog(this, "Chưa chọn sinh viên!");
        }
    }//GEN-LAST:event_btnGhiPhieuTraActionPerformed

    private void btnTraTatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraTatActionPerformed
        JTableSachDuocThem.selectAll();
    }//GEN-LAST:event_btnTraTatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPhieuTraSach1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPhieuTraSach1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPhieuTraSach1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPhieuTraSach1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPhieuTraSach1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableSachDuocThem;
    private javax.swing.JButton btnGhiPhieuTra;
    private javax.swing.JButton btnTimSV;
    private javax.swing.JButton btnTraTat;
    private javax.swing.JTextField edtMaSv;
    private javax.swing.JTextField edtNgayHenTra;
    private javax.swing.JTextField edtNgayLap;
    private javax.swing.JTextField edtNguoiLap;
    private javax.swing.JTextField edtSoPhieu;
    private javax.swing.JTextField edtTenSv;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jListMa;
    private javax.swing.JList<String> jListTen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTablePhieuMuon;
    private javax.swing.JLabel txtNgayHenTra;
    private javax.swing.JLabel txtNgayLap;
    private javax.swing.JLabel txtNguoiLap;
    private javax.swing.JLabel txtSoPhieu;
    private javax.swing.JLabel txtSoSach;
    // End of variables declaration//GEN-END:variables
}
