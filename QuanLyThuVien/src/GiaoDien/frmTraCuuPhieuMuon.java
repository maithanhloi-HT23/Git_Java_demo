/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import static GiaoDien.frmPhieuMuonSach.pathPhieuMuon;
import static GiaoDien.frmPhieuMuonSach.pathSach;
import static GiaoDien.frmPhieuMuonSach.pathSinhVien;
import GiaoDien.model.Book;
import GiaoDien.model.PhieuMuon;
import GiaoDien.model.PhieuTra;
import GiaoDien.model.SinhVien;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Loi
 */
public class frmTraCuuPhieuMuon extends javax.swing.JFrame {

    static String pathPhieuMuon = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "PhieuMuon.json";
    static String pathPhieuTra = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "PhieuTra.json";
    private DefaultTableModel tableModelPhieu, tableModelSach;
    private ArrayList<PhieuMuon> listPhieuMuon;
    private ArrayList<PhieuTra> listPhieuTra;
    private boolean isPhieuMuon = true;

    public frmTraCuuPhieuMuon() throws IOException {
        initComponents();
        this.setResizable(false);
        try {
            getData();
            fileTestPhieuTra(); // có thể xóa
            getData(); // có thể xóa
        } catch (IOException ex) {
            Logger.getLogger(frmTraCuuPhieuMuon.class.getName()).log(Level.SEVERE, null, ex);
        }

        setJTablePhieu();
        setJDate();
        searchReadTimeTest(); // có thể xóa
        jListMaSinhVien.setVisible(false);
        edtNguoiLap.setText("aaa");
    }

    private void fileTestPhieuTra() throws IOException { // có thể xóa
        File filePT = new File(pathPhieuTra);
        filePT.createNewFile();
        FileWriter fw = new FileWriter(filePT);

        ArrayList<PhieuTra> list = new ArrayList<>();
        for (int i = 0; i < listPhieuMuon.size(); i++) {
            PhieuTra pt = new PhieuTra(listPhieuMuon.get(i).getSoPhieu(), listPhieuMuon.get(i).getSv(), listPhieuMuon.get(i).getListBook(), "aa", "1/8/2021", "Không ghi chú");
            list.add(pt);
        }
        java.lang.reflect.Type type = new TypeToken<ArrayList<PhieuTra>>() {
        }.getType();
        Gson gson = new Gson();
        gson.toJson(list, type, fw);
        fw.close();
    }

    private void getData() throws IOException {
        // đọc list Phiếu mượn
        File filePM = new File(pathPhieuMuon);
        if (filePM.exists() == false) {
            filePM.createNewFile();
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
                frPM.close();
            }
            if (listPhieuMuon == null) {
                listPhieuMuon = new ArrayList<PhieuMuon>();
            }
        }

        // đọc list Phiếu trả
        File filePT = new File(pathPhieuTra);
        if (filePM.exists() == false) {
            filePM.createNewFile();
        }
        FileReader frPT = null;
        try {
            frPT = new FileReader(pathPhieuTra);
            java.lang.reflect.Type typePhieuTra = new TypeToken<ArrayList<PhieuTra>>() {
            }.getType();
            Gson gson = new Gson();
            listPhieuTra = gson.fromJson(frPT, typePhieuTra);
        } catch (Exception e) {
        } finally {
            if (frPT != null) {
                frPT.close();
            }
            if (listPhieuTra == null) {
                listPhieuTra = new ArrayList<PhieuTra>();
            }
        }
    }

    private void setJTablePhieu() {
        tableModelPhieu = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModelPhieu.addColumn("Số phiếu");
        if (isPhieuMuon == true) {
            tableModelPhieu.addColumn("Ngày lập");
            tableModelPhieu.addColumn("Ngày hẹn trả");
            tableModelPhieu.addColumn("Người lập");
        } else {
            tableModelPhieu.addColumn("Ngày trả");
            tableModelPhieu.addColumn("Người lập");
            tableModelPhieu.addColumn("Ghi chú");
        }
        tableModelPhieu.addColumn("Mã sinh viên");
        tableModelPhieu.addColumn("Tên sinh viên");
        jTablePhieu.setModel(tableModelPhieu);
        jTablePhieu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableModelSach = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModelSach.addColumn("Mã sách");
        tableModelSach.addColumn("Tên sách");
        if (isPhieuMuon == false) {
            tableModelSach.addColumn("Ngày Trả");
        }
        jTableSach.setModel(tableModelSach);
    }

    private void searchReadTimeTest() {  // có thể xóa
        edtMaSinhVien.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!edtMaSinhVien.getText().toString().trim().isEmpty()) {
                    updateChange();
                } else {
                    jListMaSinhVien.setVisible(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!edtMaSinhVien.getText().toString().trim().isEmpty()) {
                    updateChange();
                } else {
                    jListMaSinhVien.setVisible(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!edtMaSinhVien.getText().toString().trim().isEmpty()) {
                    updateChange();
                } else {
                    jListMaSinhVien.setVisible(false);
                }
            }

            protected void updateChange() {
                Vector<String> vector = new Vector<>();
                String text = edtMaSinhVien.getText().toString().trim();
                if (isPhieuMuon) {
                    for (int i = 0; i < listPhieuMuon.size(); i++) {
                        SinhVien sv = listPhieuMuon.get(i).getSv();
                        if (sv.getMaSV().startsWith(text)) {
                            vector.add(sv.getMaSV());
                        }
                    }
                } else {
                    for (int i = 0; i < listPhieuTra.size(); i++) {
                        SinhVien sv = listPhieuTra.get(i).getSv();
                        if (sv.getMaSV().startsWith(text)) {
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
                    jListMaSinhVien.setListData(vector);
                    jListMaSinhVien.setVisible(true);
                } else {
                    jListMaSinhVien.setVisible(false);
                }
            }
        });
    }

    private void setJDate() {
        jDateTuNgay.setDate(new Date());
        jDateTuNgay.setDateFormatString("dd/MM/yyyy");
        JTextFieldDateEditor editorTuNgay = (JTextFieldDateEditor) jDateTuNgay.getDateEditor();
        editorTuNgay.setEditable(false);

        jDateDenNgay.setDate(Date.from(LocalDate.now().plusMonths(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        jDateDenNgay.setDateFormatString("dd/MM/yyyy");
        JTextFieldDateEditor editorDenNgay = (JTextFieldDateEditor) jDateDenNgay.getDateEditor();
        editorDenNgay.setEditable(false);
    }

    private void hienThiPhieu(Date ngayBatDau, Date ngayKetThuc, String value, boolean isPhieuMuon) throws ParseException {
        setJTablePhieu();
        String maSv = edtMaSinhVien.getText().toString().trim();
        DefaultTableModel modelPhieu = (DefaultTableModel) jTablePhieu.getModel();
        while (modelPhieu.getRowCount() > 0) // xóa hiện tại 
        {
            modelPhieu.removeRow(0);
        }
        int dem = 0;
        if (isPhieuMuon) {
            // Hiển thị bảng phiếu mượn
            LocalDate dateBatDau = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dateKetThuc = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            SimpleDateFormat sdm = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < listPhieuMuon.size(); i++) {
                PhieuMuon pm = listPhieuMuon.get(i);
                if (maSv.equals(pm.getSv().getMaSV())) {
                    if (value.equals("Ngày Hẹn Trả")) {
                        Date ngayHenTra = sdm.parse(pm.getNgayHenTra());
                        LocalDate dateHenTra = ngayHenTra.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        if ((dateHenTra.isAfter(dateBatDau)) && (dateHenTra.isBefore(dateKetThuc)) || (dateHenTra.equals(dateBatDau)) || (dateHenTra.equals(dateKetThuc))) {
                            Vector<String> vector = new Vector<String>();
                            vector.add(pm.getSoPhieu());
                            vector.add(pm.getNgayMuon());
                            vector.add(pm.getNgayHenTra());
                            vector.add(pm.getNguoiLap());
                            vector.add(pm.getSv().getMaSV());
                            vector.add(pm.getSv().getTen());
                            modelPhieu.addRow(vector);
                            dem++;
                        }
                    } else {
                        Date ngayLap = sdm.parse(pm.getNgayMuon());
                        LocalDate dateNgayLap = ngayLap.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        if ((dateNgayLap.isAfter(dateBatDau)) && (dateNgayLap.isBefore(dateKetThuc)) || (dateNgayLap.equals(dateBatDau)) || (dateNgayLap.equals(dateKetThuc))) {
                            Vector<String> vector = new Vector<String>();
                            vector.add(pm.getSoPhieu());
                            vector.add(pm.getNgayMuon());
                            vector.add(pm.getNgayHenTra());
                            vector.add(pm.getNguoiLap());
                            vector.add(pm.getSv().getMaSV());
                            vector.add(pm.getSv().getTen());
                            modelPhieu.addRow(vector);
                            dem++;
                        }
                    }
                }
            }
        } else {
            LocalDate dateBatDau = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dateKetThuc = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            SimpleDateFormat sdm = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < listPhieuTra.size(); i++) {
                PhieuTra pt = listPhieuTra.get(i);
                if (maSv.equals(pt.getSv().getMaSV())) {
                    Date ngayTra = sdm.parse(pt.getNgayTra());
                    LocalDate dateTra = ngayTra.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if ((dateTra.isAfter(dateBatDau)) && (dateTra.isBefore(dateKetThuc)) || (dateTra.equals(dateBatDau)) || (dateTra.equals(dateKetThuc))) {
                        Vector<String> vector = new Vector<String>();
                        vector.add(pt.getSoPhieu());
                        vector.add(pt.getNgayTra());
                        vector.add(pt.getNguoiLap());
                        vector.add(pt.getGhiChu());
                        vector.add(pt.getSv().getMaSV());
                        vector.add(pt.getSv().getTen());
                        modelPhieu.addRow(vector);
                        dem++;
                    }
                }
            }
        }
        if (dem == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy cuốn sách nào");
        }

        jTablePhieu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                DefaultTableModel modelSach = (DefaultTableModel) jTableSach.getModel();
                while (modelSach.getRowCount() > 0) // xóa hiện tại 
                {
                    modelSach.removeRow(0);
                }

                int row = jTablePhieu.getSelectedRow();
                if (row > -1) {
                    if (isPhieuMuon) {
                        String soPhieu = jTablePhieu.getValueAt(row, 0).toString();
                        for (int i = 0; i < listPhieuMuon.size(); i++) {
                            PhieuMuon pm = listPhieuMuon.get(i);
                            if (pm.getSoPhieu().equals(soPhieu)) {
                                for (int j = 0; j < pm.getListBook().size(); j++) {
                                    Vector<String> vector = new Vector<>();
                                    vector.add(pm.getListBook().get(j).getMaSach());
                                    vector.add(pm.getListBook().get(j).getTenSach());
                                    modelSach.addRow(vector);
                                }
                            }
                        }
                    } else {
                        String soPhieu = jTablePhieu.getValueAt(row, 0).toString();
                        for (int i = 0; i < listPhieuTra.size(); i++) {
                            PhieuTra pt = listPhieuTra.get(i);
                            if (pt.getSoPhieu().equals(soPhieu)) {
                                for (int j = 0; j < pt.getListBook().size(); j++) {
                                    Vector<String> vector = new Vector<>();
                                    vector.add(pt.getListBook().get(j).getMaSach());
                                    vector.add(pt.getListBook().get(j).getTenSach());
                                    modelSach.addRow(vector);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private boolean checkDauVao() {
        String maSV = edtMaSinhVien.getText().toString().trim();
        if (maSV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên không được bỏ trống");
            return false;
        }
        LocalDate dateBatDau = jDateTuNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateKetThuc = jDateDenNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (dateKetThuc.isBefore(dateBatDau)) {
            JOptionPane.showMessageDialog(this, "Ngày trả không được trước ngày mượn");
            return false;
        }

        boolean check = false;
        for (int i = 0; i < listPhieuMuon.size(); i++) {
            if (listPhieuMuon.get(i).getSv().getMaSV().equals(maSV)) {
                check = true;
            }
        }
        if (check == false) {
            JOptionPane.showMessageDialog(this, "Sinh viên có mã này không tồn tại");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        edtNguoiLap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jDateTuNgay = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        edtMaSinhVien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jRBPhieuMuon = new javax.swing.JRadioButton();
        jRBPhieuTra = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jRBNgay = new javax.swing.JRadioButton();
        jRBNgayHenTra = new javax.swing.JRadioButton();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSach = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablePhieu = new javax.swing.JTable();
        jDateDenNgay = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListMaSinhVien = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(920, 630));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Người lập:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 100, 55, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Tra cứu phiếu");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 10, 280, 70);
        getContentPane().add(edtNguoiLap);
        edtNguoiLap.setBounds(100, 100, 117, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Từ ngày:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 140, 50, 30);
        getContentPane().add(jDateTuNgay);
        jDateTuNgay.setBounds(100, 140, 137, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Mã sinh viên:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(250, 100, 70, 30);
        getContentPane().add(edtMaSinhVien);
        edtMaSinhVien.setBounds(340, 100, 120, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Đến ngày:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(260, 140, 60, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm theo"));
        jPanel1.setPreferredSize(new java.awt.Dimension(260, 66));
        jPanel1.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jRBPhieuMuon.setText("Phiếu Mượn");
        jRBPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPhieuMuonActionPerformed(evt);
            }
        });
        jPanel2.add(jRBPhieuMuon);

        jRBPhieuTra.setText("Phiếu Trả    ");
        jRBPhieuTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPhieuTraActionPerformed(evt);
            }
        });
        jPanel2.add(jRBPhieuTra);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 20, 110, 60);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jRBNgay.setText("Ngày Lập      ");
        jRBNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBNgayActionPerformed(evt);
            }
        });
        jPanel3.add(jRBNgay);

        jRBNgayHenTra.setText("Ngày Hẹn Trả");
        jRBNgayHenTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBNgayHenTraActionPerformed(evt);
            }
        });
        jPanel3.add(jRBNgayHenTra);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(130, 20, 120, 60);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(480, 90, 261, 90);

        btnTimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        getContentPane().add(btnTimKiem);
        btnTimKiem.setBounds(750, 130, 139, 40);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Hiển thị sách theo số phiếu"));

        jTableSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Ngày trả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableSach);

        jScrollPane3.setViewportView(jScrollPane2);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(10, 390, 879, 230);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hiển thị phiếu"));

        jTablePhieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Số phiếu", "Ngày lập", "Ngày trả", "Người lập", "Mã sinh viên", "Tên sinh viên"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTablePhieu);

        jScrollPane4.setViewportView(jScrollPane5);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(10, 180, 879, 205);
        getContentPane().add(jDateDenNgay);
        jDateDenNgay.setBounds(340, 140, 140, 30);

        jListMaSinhVien.setBorder(javax.swing.BorderFactory.createTitledBorder("JList RealTime Mã sinh viên để cho dễ test"));
        jListMaSinhVien.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jListMaSinhVien.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListMaSinhVien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(jListMaSinhVien);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(590, 0, 290, 90);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if (checkDauVao()) {
            try {
                hienThiPhieu(jDateTuNgay.getDate(), jDateDenNgay.getDate(), jRBNgayHenTra.isSelected() ? "Ngày Hẹn Trả" : "Ngày Lập", isPhieuMuon);
            } catch (ParseException ex) {
                Logger.getLogger(frmTraCuuPhieuMuon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void jRBPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPhieuMuonActionPerformed
        jRBPhieuMuon.setSelected(true);
        jRBPhieuTra.setSelected(false);
        
        jRBNgay.setText("Ngày Lập       ");
        jRBNgay.setEnabled(true);
        jRBNgay.setSelected(true);
        jRBNgayHenTra.setVisible(true);
        jRBNgayHenTra.setSelected(false);
        
        isPhieuMuon = true;
    }//GEN-LAST:event_jRBPhieuMuonActionPerformed

    private void jRBPhieuTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPhieuTraActionPerformed
        jRBPhieuTra.setSelected(true);
        jRBPhieuMuon.setSelected(false);
        
        jRBNgay.setText("Ngày Trả       ");
        jRBNgay.setEnabled(false);
        jRBNgay.setSelected(true);
        jRBNgayHenTra.setVisible(false);
        jRBNgayHenTra.setSelected(false);
        
        isPhieuMuon = false;
    }//GEN-LAST:event_jRBPhieuTraActionPerformed

    private void jRBNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBNgayActionPerformed
        jRBNgay.setSelected(true);
        jRBNgayHenTra.setSelected(false);
    }//GEN-LAST:event_jRBNgayActionPerformed

    private void jRBNgayHenTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBNgayHenTraActionPerformed
        jRBNgayHenTra.setSelected(true);
        jRBNgay.setSelected(false);
    }//GEN-LAST:event_jRBNgayHenTraActionPerformed

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
            java.util.logging.Logger.getLogger(frmTraCuuPhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTraCuuPhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTraCuuPhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTraCuuPhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmTraCuuPhieuMuon().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(frmTraCuuPhieuMuon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTextField edtMaSinhVien;
    private javax.swing.JTextField edtNguoiLap;
    private com.toedter.calendar.JDateChooser jDateDenNgay;
    private com.toedter.calendar.JDateChooser jDateTuNgay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jListMaSinhVien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRBNgay;
    private javax.swing.JRadioButton jRBNgayHenTra;
    private javax.swing.JRadioButton jRBPhieuMuon;
    private javax.swing.JRadioButton jRBPhieuTra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTablePhieu;
    private javax.swing.JTable jTableSach;
    // End of variables declaration//GEN-END:variables
}
