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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private DefaultTableModel tableModelPhieu, tableModelSach;
    private ArrayList<PhieuMuon> listPhieuMuon;

    public frmTraCuuPhieuMuon() throws IOException {
        initComponents();
        this.setResizable(false);
        try {
            getData();
        } catch (IOException ex) {
            Logger.getLogger(frmTraCuuPhieuMuon.class.getName()).log(Level.SEVERE, null, ex);
        }

        setJTablePhieu();
        setJDate();
        searchReadTimeTest(); // c?? th??? x??a
        jListMaSinhVien.setVisible(false);
        edtNguoiLap.setText("aaa");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frmMenu.isOpenFrmTraCuuPhieuMuon = false;
            }

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frmMenu.isOpenFrmTraCuuPhieuMuon = false;
            }

        });
    }

    private void getData() throws IOException {
        // ?????c list Phi???u m?????n
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
    }

    private void setJTablePhieu() {
        tableModelPhieu = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModelPhieu.addColumn("S??? phi???u");
        tableModelPhieu.addColumn("Ng??y l???p");
        tableModelPhieu.addColumn("Ng??y h???n tr???");
        tableModelPhieu.addColumn("Ng?????i l???p");
        tableModelPhieu.addColumn("M?? sinh vi??n");
        tableModelPhieu.addColumn("T??n sinh vi??n");
        jTablePhieu.setModel(tableModelPhieu);
        jTablePhieu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableModelSach = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModelSach.addColumn("M?? s??ch");
        tableModelSach.addColumn("T??n s??ch");
        jTableSach.setModel(tableModelSach);
    }

    private void searchReadTimeTest() {  // c?? th??? x??a
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
                for (int i = 0; i < listPhieuMuon.size(); i++) {
                    SinhVien sv = listPhieuMuon.get(i).getSv();
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

    private void hienThiPhieu(Date ngayBatDau, Date ngayKetThuc, String value) throws ParseException {
        setJTablePhieu();
        String maSv = edtMaSinhVien.getText().toString().trim();
        DefaultTableModel modelPhieu = (DefaultTableModel) jTablePhieu.getModel();
        while (modelPhieu.getRowCount() > 0) // x??a hi???n t???i 
        {
            modelPhieu.removeRow(0);
        }
        int dem = 0;
        // Hi???n th??? b???ng phi???u m?????n
        LocalDate dateBatDau = ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateKetThuc = ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        SimpleDateFormat sdm = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < listPhieuMuon.size(); i++) {
            PhieuMuon pm = listPhieuMuon.get(i);
            if (maSv.equals(pm.getSv().getMaSV())) {
                if (value.equals("Ng??y H???n Tr???")) {
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
        if (dem == 0) {
            JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y cu???n s??ch n??o");
        }

        jTablePhieu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                DefaultTableModel modelSach = (DefaultTableModel) jTableSach.getModel();
                while (modelSach.getRowCount() > 0) // x??a hi???n t???i 
                {
                    modelSach.removeRow(0);
                }

                int row = jTablePhieu.getSelectedRow();
                if (row > -1) {
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
                }
            }
        });
    }

    private boolean checkDauVao() {
        String maSV = edtMaSinhVien.getText().toString().trim();
        if (maSV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "M?? sinh vi??n kh??ng ???????c b??? tr???ng");
            return false;
        }
        LocalDate dateBatDau = jDateTuNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateKetThuc = jDateDenNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (dateKetThuc.isBefore(dateBatDau)) {
            JOptionPane.showMessageDialog(this, "Ng??y tr??? kh??ng ???????c tr?????c ng??y m?????n");
            return false;
        }

        boolean check = false;
        for (int i = 0; i < listPhieuMuon.size(); i++) {
            if (listPhieuMuon.get(i).getSv().getMaSV().equals(maSV)) {
                check = true;
            }
        }
        if (check == false) {
            JOptionPane.showMessageDialog(this, "Sinh vi??n c?? m?? n??y kh??ng t???n t???i");
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
        btnTraSach = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(920, 630));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Ng?????i l???p:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 100, 55, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Tra c???u phi???u");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 10, 280, 70);
        getContentPane().add(edtNguoiLap);
        edtNguoiLap.setBounds(100, 100, 117, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("T??? ng??y:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 140, 50, 30);
        getContentPane().add(jDateTuNgay);
        jDateTuNgay.setBounds(100, 140, 137, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("M?? sinh vi??n:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(250, 100, 70, 30);
        getContentPane().add(edtMaSinhVien);
        edtMaSinhVien.setBounds(340, 100, 120, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("?????n ng??y:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(260, 140, 60, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("T??m theo"));
        jPanel1.setPreferredSize(new java.awt.Dimension(260, 66));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jRBNgay.setText("Ng??y L???p      ");
        jRBNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBNgayActionPerformed(evt);
            }
        });
        jPanel3.add(jRBNgay);

        jRBNgayHenTra.setText("Ng??y H???n Tr???");
        jRBNgayHenTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBNgayHenTraActionPerformed(evt);
            }
        });
        jPanel3.add(jRBNgayHenTra);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 20, 120, 60);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(480, 90, 140, 90);

        btnTimKiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKiem.setText("T??m ki???m");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        getContentPane().add(btnTimKiem);
        btnTimKiem.setBounds(670, 100, 140, 30);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Hi???n th??? s??ch theo s??? phi???u"));

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
                "M?? s??ch", "T??n s??ch", "Ng??y tr???"
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

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hi???n th??? phi???u"));

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
                "S??? phi???u", "Ng??y l???p", "Ng??y tr???", "Ng?????i l???p", "M?? sinh vi??n", "T??n sinh vi??n"
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

        jListMaSinhVien.setBorder(javax.swing.BorderFactory.createTitledBorder("JList RealTime M?? sinh vi??n ????? cho d??? test"));
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

        btnTraSach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTraSach.setText("Tr??? s??ch");
        btnTraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraSachActionPerformed(evt);
            }
        });
        getContentPane().add(btnTraSach);
        btnTraSach.setBounds(670, 140, 140, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if (checkDauVao()) {
            try {
                hienThiPhieu(jDateTuNgay.getDate(), jDateDenNgay.getDate(), jRBNgayHenTra.isSelected() ? "Ng??y H???n Tr???" : "Ng??y L???p");
            } catch (ParseException ex) {
                Logger.getLogger(frmTraCuuPhieuMuon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void jRBNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBNgayActionPerformed
        jRBNgay.setSelected(true);
        jRBNgayHenTra.setSelected(false);
    }//GEN-LAST:event_jRBNgayActionPerformed

    private void jRBNgayHenTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBNgayHenTraActionPerformed
        jRBNgayHenTra.setSelected(true);
        jRBNgay.setSelected(false);
    }//GEN-LAST:event_jRBNgayHenTraActionPerformed

    private void btnTraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraSachActionPerformed
        this.setVisible(false);
        if (frmMenu.isOpenFrmPhieuTraSach1 == false) {
            new frmPhieuTraSach1().setVisible(true);
        }
    }//GEN-LAST:event_btnTraSachActionPerformed

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
    private javax.swing.JButton btnTraSach;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRBNgay;
    private javax.swing.JRadioButton jRBNgayHenTra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTablePhieu;
    private javax.swing.JTable jTableSach;
    // End of variables declaration//GEN-END:variables
}
