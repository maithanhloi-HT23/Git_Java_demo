/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import GiaoDien.model.Book;
import GiaoDien.model.PhieuMuon;
import GiaoDien.model.SinhVien;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class frmPhieuMuonSach extends javax.swing.JFrame {

    static String pathPhieuMuon = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "PhieuMuon.json";
    static String pathSinhVien = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "SinhVien.json";
    static String pathSach = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "Book.csv";
    private List<SinhVien> listSinhVien;
    private List<PhieuMuon> listPhieuMuon;
    private List<Book> listSach;
    private List<Book> listSachDaThem;
    private SinhVien svHienTai;
    private DefaultTableModel tableModelSach, tableModelSachDaThem;

    public frmPhieuMuonSach() throws IOException {
        initComponents();
        listSachDaThem = new ArrayList<Book>();
        listPhieuMuon = new ArrayList<>();
        getDataSinhVien();
        getDataBook();
        //getDataPM();
        setJCombox();
        setJTable();
        setJDate();
        searchReadTime();
        edtSoPhieu.setText((listPhieuMuon.size() + 1) + "");
        edtSoPhieu.setEditable(false);
        jListMa.setVisible(false);
        jListTen.setVisible(false);
        JCBSach.setSelectedItem("Tất cả sách");
        hienThiSach("Tất cả sách");
        hienThiSachDaThem();

        edtNguoiLap.setText("aaa");
    }

    private void getDataSinhVien() {
        frmSinhVien frmsv = new frmSinhVien();
        listSinhVien = frmsv.getList();
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

    private void updateDataCSV(String path, ArrayList<Book> list) {
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

    private void setJCombox() {
        Vector<String> vectorSach = new Vector<String>();
        vectorSach.add("Tất cả sách");
        for (int i = 0; i < listSach.size(); i++) {
            Book book = listSach.get(i);
            boolean check = true;
            for (int j = 0; j < vectorSach.size(); j++) {
                if (book.getTheLoai().equals(vectorSach.get(j))) {
                    check = false;
                    break;
                }
            }
            if (check) {
                vectorSach.add(book.getTheLoai());
            }
        }
        vectorSach.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        DefaultComboBoxModel modelSach = new DefaultComboBoxModel(vectorSach);
        JCBSach.setModel(modelSach);
    }

    private void setJTable() {
        tableModelSach = new DefaultTableModel();
        tableModelSach.addColumn("Mã sách");
        tableModelSach.addColumn("Tên sách");
        tableModelSach.addColumn("Tác giả");
        tableModelSach.addColumn("Thể loại");
        tableModelSach.addColumn("Nhà xuất bản");
        tableModelSach.addColumn("Số lượng");
        JTableSach.setModel(tableModelSach);

        tableModelSachDaThem = new DefaultTableModel();
        tableModelSachDaThem.addColumn("Mã sách");
        tableModelSachDaThem.addColumn("Tên sách");
        JTableSachDuocThem.setModel(tableModelSachDaThem);
    }

    private void setJDate() {
        jDateNgayLap.setDate(new Date());
        jDateNgayLap.setDateFormatString("dd/MM/yyyy");
        jDateNgayLap.setEnabled(false);

        jDateNgayTra.setDate(Date.from(LocalDate.now().plusMonths(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        jDateNgayTra.setDateFormatString("dd/MM/yyyy");
        JTextFieldDateEditor editorNgayTra = (JTextFieldDateEditor) jDateNgayTra.getDateEditor();
        editorNgayTra.setEditable(false);
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
                for (int i = 0; i < listSinhVien.size(); i++) {
                    SinhVien sv = listSinhVien.get(i);
                    if (sv.getMaSV().startsWith(text)) {
                        vector.add(sv.getMaSV());
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
                for (int i = 0; i < listSinhVien.size(); i++) {
                    SinhVien sv = listSinhVien.get(i);
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

    private void hienThiSach(String value) {
        DefaultTableModel model = (DefaultTableModel) JTableSach.getModel();
        while (model.getRowCount() > 0) // xóa hiện tại 
        {
            model.removeRow(0);
        }
        int dem = 0;
        if (value.equals("Tất cả sách")) {
            for (int i = 0; i < listSach.size(); i++) {
                Book book = listSach.get(i);
                Vector<String> vector = new Vector<>();
                vector.add(book.getMaSach());
                vector.add(book.getTenSach());
                vector.add(book.getTacGia());
                vector.add(book.getTheLoai());
                vector.add(book.getNXB());
                vector.add(String.valueOf(book.getSoLuong()));
                model.addRow(vector);
                dem++;
            }
        } else {
            for (int i = 0; i < listSach.size(); i++) {
                Book book = listSach.get(i);
                if (book.getTheLoai().equals(value)) {
                    Vector<String> vector = new Vector<>();
                    vector.add(book.getMaSach());
                    vector.add(book.getTenSach());
                    vector.add(book.getTacGia());
                    vector.add(book.getTheLoai());
                    vector.add(book.getNXB());
                    vector.add(String.valueOf(book.getSoLuong()));
                    model.addRow(vector);
                    dem++;
                }
            }
        }
        txtSoSach.setText("Có " + dem + " quyển sách");
    }

    private void hienThiSachDaThem() {
        DefaultTableModel model = (DefaultTableModel) JTableSachDuocThem.getModel();
        while (model.getRowCount() > 0) // xóa hiện tại 
        {
            model.removeRow(0);
        }
        for (int i = 0; i < listSachDaThem.size(); i++) {
            Book book = listSachDaThem.get(i);
            Vector<String> vector = new Vector<>();
            vector.add(book.getMaSach());
            vector.add(book.getTenSach());
            model.addRow(vector);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        edtMaSv = new javax.swing.JTextField();
        btnTimSV = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListMa = new javax.swing.JList<>();
        edtTenSv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListTen = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        edtSoPhieu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edtNguoiLap = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableSachDuocThem = new javax.swing.JTable();
        btnGhiPhieuMuon = new javax.swing.JButton();
        btnXoaSach = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        jDateNgayLap = new com.toedter.calendar.JDateChooser();
        jDateNgayTra = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        JCBSach = new javax.swing.JComboBox<>();
        btnTatCa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTableSach = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        txtSoSach = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã sv:");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

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
        jScrollPane4.setViewportView(jListMa);

        edtTenSv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên sv:");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

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
        jScrollPane5.setViewportView(jListTen);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                            .addComponent(edtMaSv)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtTenSv)
                            .addComponent(jScrollPane5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimSV)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edtMaSv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(edtTenSv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.getAccessibleContext().setAccessibleParent(this);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lập phiếu mượn sách cho sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Số phiếu:");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        edtSoPhieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ngày lập:");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Người lập:");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        edtNguoiLap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Ngày hẹn trả:");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        JTableSachDuocThem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Tiếng Việt 1"}
            },
            new String [] {
                "Mã sách", "Tên sách"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JTableSachDuocThem);

        btnGhiPhieuMuon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnGhiPhieuMuon.setText("Ghi phiếu mượn");
        btnGhiPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGhiPhieuMuonActionPerformed(evt);
            }
        });

        btnXoaSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaSach.setText("Xóa Sách");
        btnXoaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSachActionPerformed(evt);
            }
        });

        btnXoaTrang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaTrang.setText("Xóa Trắng");
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(edtSoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnXoaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(10, 10, 10)
                                .addComponent(jDateNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edtNguoiLap, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGhiPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(edtSoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(edtNguoiLap))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(jDateNgayTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGhiPhieuMuon)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("QUẢN LÝ PHIẾU MƯỢN SÁCH");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Mục:");

        JCBSach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JCBSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thể loại 1", "Thể loại 2", "Thể loại 3", "Thể loại 4" }));
        JCBSach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCBSachItemStateChanged(evt);
            }
        });

        btnTatCa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTatCa.setText("Tất Cả");
        btnTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatCaActionPerformed(evt);
            }
        });

        JTableSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Tiếng Việt 1", "SGK", "Bộ GD và DT", "Kim đồng",  new Integer(52)}
            },
            new String [] {
                "Mã sách", "Tên sách", "Thể loại", "Tác giả", "Nhà xuất bản", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(JTableSach);

        jScrollPane2.setViewportView(jScrollPane3);

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setText("Thêm Sách");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtSoSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCBSach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTatCa)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtSoSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem)
                        .addGap(25, 25, 25))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JCBSach, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTatCa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoSach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clickTim() {
        String maSv = edtMaSv.getText().toString().trim();
        if (!maSv.isEmpty()) {
            boolean check = true;
            for (int i = 0; i < listSinhVien.size(); i++) {
                SinhVien sv = listSinhVien.get(i);
                if (sv.getMaSV().equals(maSv)) {
                    edtTenSv.setText(sv.getTen());
                    svHienTai = sv;
                    edtTenSv.setText(sv.getTen());
                    check = false;
                }
            }
            if (check) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
            }
        }
    }

    private void btnTimSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSVActionPerformed
        clickTim();
    }//GEN-LAST:event_btnTimSVActionPerformed

    private void JCBSachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCBSachItemStateChanged
        String theLoai = (String) JCBSach.getSelectedItem();
        hienThiSach(theLoai);
    }//GEN-LAST:event_JCBSachItemStateChanged

    private void btnTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatCaActionPerformed
        JCBSach.setSelectedItem("Tất cả sách");
        hienThiSach("Tất cả sách");
    }//GEN-LAST:event_btnTatCaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int[] a = JTableSach.getSelectedRows();
        for (int i = 0; i < a.length; i++) {
            String maSach = (String) JTableSach.getValueAt(a[i], 0);
            boolean check = true;
            for (int j = 0; j < listSachDaThem.size(); j++) {
                Book b = listSachDaThem.get(j);
                if (b.getMaSach().equals(maSach)) {
                    JOptionPane.showMessageDialog(this, "Cuốn sách mã " + maSach + " đã được thêm");
                    check = false;
                    break;
                }
            }
            if (check) {
                for (int j = 0; j < listSach.size(); j++) {
                    Book b = listSach.get(j);
                    if (maSach.equals(b.getMaSach())) {
                        listSachDaThem.add(b);
                        if (b.getSoLuong() == 1) {
                            listSach.remove(j);
                        } else {
                            b.setSoLuong(b.getSoLuong() - 1);
                        }
                    }
                }
            }
        }
        String theLoai = (String) JCBSach.getSelectedItem();
        hienThiSach(theLoai);
        hienThiSachDaThem();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSachActionPerformed
        int a[] = JTableSachDuocThem.getSelectedRows();
        ArrayList<Book> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            String maSach = (String) JTableSachDuocThem.getValueAt(a[i], 0);
            Book b = listSachDaThem.get(a[i]);
            list.add(b);
            boolean check = true;
            for (int j = 0; j < listSach.size(); j++) {
                Book b2 = listSach.get(j);
                if (b2.getMaSach().equals(maSach)) {
                    b2.setSoLuong(b2.getSoLuong() + 1);
                    check = false;
                }
            }
            if (check) {
                listSach.add(b);
                b.setSoLuong(1);
            }
        }
        for (int j = 0; j < list.size(); j++) {
            listSachDaThem.remove(list.get(j));
        }
        String theLoai = (String) JCBSach.getSelectedItem();
        hienThiSach(theLoai);
        hienThiSachDaThem();
    }//GEN-LAST:event_btnXoaSachActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        for (int i = 0; i < listSachDaThem.size(); i++) {
            String maSach = (String) JTableSachDuocThem.getValueAt(i, 0);
            Book b = listSachDaThem.get(i);
            boolean check = true;
            for (int j = 0; j < listSach.size(); j++) {
                Book b2 = listSach.get(j);
                if (b2.getMaSach().equals(maSach)) {
                    b2.setSoLuong(b2.getSoLuong() + 1);
                    check = false;
                }
            }
            if (check) {
                listSach.add(b);
                b.setSoLuong(1);
            }
        }
        listSachDaThem.clear();
        String theLoai = (String) JCBSach.getSelectedItem();
        hienThiSach(theLoai);
        hienThiSachDaThem();
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private boolean checkDinhDang() {
        if (edtSoPhieu.getText().toString().isEmpty()) {
            edtSoPhieu.setText((listPhieuMuon.size() + 1) + "");
            edtSoPhieu.setEditable(false);
            return false;
        }
        if (edtNguoiLap.getText().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Người lập không được bỏ trống");
            return false;
        }
        Calendar dateNgayMuon = jDateNgayLap.getCalendar();
        Calendar dateNgayTra = jDateNgayTra.getCalendar();
        if (dateNgayTra.before(dateNgayMuon)) {
            jDateNgayTra.setDate(Date.from(LocalDate.now().plusMonths(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            JOptionPane.showMessageDialog(this, "Ngày trả không được trước ngày mượn (Đã đưa về mặc định)");
            return false;
        }
        if ((dateNgayMuon.get(Calendar.MONTH) == dateNgayTra.get(Calendar.MONTH)) && (dateNgayMuon.get(Calendar.DAY_OF_MONTH) == dateNgayTra.get(Calendar.DAY_OF_MONTH)) && (dateNgayMuon.get(Calendar.YEAR) == dateNgayTra.get(Calendar.YEAR))) {
            jDateNgayTra.setDate(Date.from(LocalDate.now().plusMonths(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            JOptionPane.showMessageDialog(this, "Ngày trả không được trùng ngày mượn (Đã đưa về mặc định)");
            return false;
        }
        if (listSachDaThem.size() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa thêm cuốn sách nào");
            return false;
        }
        if (svHienTai == null) {
            JOptionPane.showMessageDialog(this, "Chưa xác định được sinh viên mượn sách");
            return false;
        }
        return true;
    }

    private void btnGhiPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiPhieuMuonActionPerformed
        if (checkDinhDang()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String ngayMuon = sdf.format(new Date());
            String ngayTra = sdf.format(jDateNgayTra.getDate());
            String soPhieu = edtSoPhieu.getText().toString().trim();
            String nguoiLap = edtNguoiLap.getText().toString().trim();

            PhieuMuon pm = new PhieuMuon(soPhieu, svHienTai, (ArrayList<Book>) listSachDaThem, ngayMuon, ngayTra, nguoiLap);
            listPhieuMuon.add(pm);
            updateDataJson(pathPhieuMuon, (ArrayList<PhieuMuon>) listPhieuMuon);
            updateDataCSV(pathSach, (ArrayList<Book>) listSach);
            edtSoPhieu.setText((listPhieuMuon.size() + 1) + "");
            JOptionPane.showMessageDialog(this, "Ghi phiếu thành công");

            edtMaSv.setText("");
            svHienTai = null;
            jDateNgayTra.setDate(Date.from(LocalDate.now().plusMonths(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            listSachDaThem.clear();
            hienThiSachDaThem();
        }
    }//GEN-LAST:event_btnGhiPhieuMuonActionPerformed

    private void jListMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMaMouseClicked
        int index = jListMa.locationToIndex(evt.getPoint());
        if (index >= 0) {
            String s = (String) jListMa.getModel().getElementAt(index);
            edtMaSv.setText(s);
            clickTim();
            jListMa.setVisible(false);
        }
    }//GEN-LAST:event_jListMaMouseClicked

    private void jListTenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListTenMouseClicked
        int index = jListTen.locationToIndex(evt.getPoint());
        if (index >= 0) {
            String s = (String) jListTen.getModel().getElementAt(index);
            edtTenSv.setText(s);
            int demTrungTen = 0;
            for (int i = 0; i < listSinhVien.size(); i++) {
                SinhVien sv = listSinhVien.get(i);
                if (sv.getTen().equals(s)) {
                    edtMaSv.setText(sv.getMaSV());
                    jListMa.setVisible(false);
                    svHienTai = sv;
                    demTrungTen++;
                }
            }
            if (demTrungTen > 1) { // trùng tên
                frmTrungTenSinhVien frm = new frmTrungTenSinhVien(this);
                Vector<String> listMaSinhVien = new Vector<>();
                for (int j = 0; j < listSinhVien.size(); j++) {
                    SinhVien sv = listSinhVien.get(j);
                    if (sv.getTen().equals(s)) {
                        listMaSinhVien.add(sv.getMaSV());
                    }
                }
                listMaSinhVien.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                frm.clickItemJList(listMaSinhVien, edtMaSv, jListMa, (ArrayList<SinhVien>) listSinhVien, svHienTai);

                frm.setVisible(true);
            }
            jListTen.setVisible(false);
        }
    }//GEN-LAST:event_jListTenMouseClicked

    public SinhVien getSvHienTai() {
        return svHienTai;
    }

    public void setSvHienTai(SinhVien svHienTai) {
        this.svHienTai = svHienTai;
    }

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
            java.util.logging.Logger.getLogger(frmPhieuMuonSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPhieuMuonSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPhieuMuonSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPhieuMuonSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmPhieuMuonSach().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(frmPhieuMuonSach.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCBSach;
    private javax.swing.JTable JTableSach;
    private javax.swing.JTable JTableSachDuocThem;
    private javax.swing.JButton btnGhiPhieuMuon;
    private javax.swing.JButton btnTatCa;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimSV;
    private javax.swing.JButton btnXoaSach;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JTextField edtMaSv;
    private javax.swing.JTextField edtNguoiLap;
    private javax.swing.JTextField edtSoPhieu;
    private javax.swing.JTextField edtTenSv;
    private com.toedter.calendar.JDateChooser jDateNgayLap;
    private com.toedter.calendar.JDateChooser jDateNgayTra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jListMa;
    private javax.swing.JList<String> jListTen;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel txtSoSach;
    // End of variables declaration//GEN-END:variables
}
