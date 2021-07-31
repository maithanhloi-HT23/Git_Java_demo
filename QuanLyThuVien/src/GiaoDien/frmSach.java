/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import GiaoDien.model.Book;
import GiaoDien.model.ChuyenMuc;
import GiaoDien.model.MaSachComparator;
import GiaoDien.model.SoLuongComparator;
import GiaoDien.model.TenSachComparator;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Loi
 */
public class frmSach extends javax.swing.JFrame {

    /**
     * Creates new form frmSach
     */
    List<Book> list;
    List<ChuyenMuc> chuyenMuc;
    boolean ktThem;
    String macu = "";
    DefaultTableModel modelTable;
    JFrame jframe = new JFrame();
    final String thuMucHT = System.getProperty("user.dir");
    final String seprator = File.separator;
    final String path_file_CSV = thuMucHT + seprator + "Data" + seprator + "Book.csv";

    public frmSach() {
        initComponents();
        list = new ArrayList<Book>();
        getDataToCSVBook();
        modelTable = (DefaultTableModel) tbDanhMuc.getModel();
        getComboData();
        KhoaMo(false);
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public void KhoaMo(boolean b) {
        txtMaSach.setEditable(b);
        txtNhaXB.setEditable(b);
        txtTacGia.setEditable(b);
        txtTenSach.setEditable(b);
        txtSoLuong.setEditable(b);
        cboTheLoai.setEnabled(b);

        cmdThem.setEnabled(!b);
        cmdSua.setEnabled(!b);
        cmdXoa.setEnabled(!b);
        cmdSapXep.setEnabled(!b);

        cmdGhi.setEnabled(b);
        cmdKhong.setEnabled(b);

        cboxMaS.setEnabled(!b);
        cboxTenS.setEnabled(!b);
        cboxSlg.setEnabled(!b);
        tbDanhMuc.setEnabled(!b);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhMuc = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        combTim = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        txtTenSach = new javax.swing.JTextField();
        txtNhaXB = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cmdThem = new javax.swing.JButton();
        cmdSua = new javax.swing.JButton();
        cmdXoa = new javax.swing.JButton();
        lbThongBao = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTacGia = new javax.swing.JTextField();
        cmdALL = new javax.swing.JButton();
        cboxMaS = new javax.swing.JCheckBox();
        cboxTenS = new javax.swing.JCheckBox();
        cboxSlg = new javax.swing.JCheckBox();
        cmdGhi = new javax.swing.JButton();
        cmdKhong = new javax.swing.JButton();
        cmdSapXep = new javax.swing.JToggleButton();
        cboTheLoai = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("frmSach"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel1.setText("DANH MỤC QUẢN LÝ SÁCH");

        tbDanhMuc.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tbDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Thể loại", "Tác giả", "Nhà XB", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDanhMuc.setRowHeight(25);
        tbDanhMuc.setShowGrid(true);
        tbDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhMucMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDanhMuc);
        if (tbDanhMuc.getColumnModel().getColumnCount() > 0) {
            tbDanhMuc.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbDanhMuc.getColumnModel().getColumn(1).setPreferredWidth(300);
            tbDanhMuc.getColumnModel().getColumn(2).setPreferredWidth(250);
            tbDanhMuc.getColumnModel().getColumn(3).setPreferredWidth(200);
            tbDanhMuc.getColumnModel().getColumn(4).setPreferredWidth(180);
            tbDanhMuc.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setText("Thể loại :");

        combTim.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        combTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combTimActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Mã sách :");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setText("Tên sách :");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setText("Thể loai :");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setText("Nhà XB :");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("Số lượng :");

        txtMaSach.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        txtTenSach.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        txtNhaXB.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        cmdThem.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        cmdThem.setText("Thêm");
        cmdThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdThemActionPerformed(evt);
            }
        });

        cmdSua.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        cmdSua.setText("Sửa");
        cmdSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSuaActionPerformed(evt);
            }
        });

        cmdXoa.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        cmdXoa.setText("Xóa");
        cmdXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdXoaActionPerformed(evt);
            }
        });

        lbThongBao.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbThongBao.setText(".");
        lbThongBao.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setText("Tác Giả :");

        txtTacGia.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        cmdALL.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        cmdALL.setText("Tất cả");
        cmdALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdALLActionPerformed(evt);
            }
        });

        cboxMaS.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        cboxMaS.setText("Mã sách");

        cboxTenS.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        cboxTenS.setText("Tên sách");

        cboxSlg.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        cboxSlg.setText("Số lượng");

        cmdGhi.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        cmdGhi.setText("Ghi");
        cmdGhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGhiActionPerformed(evt);
            }
        });

        cmdKhong.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        cmdKhong.setText("Không");
        cmdKhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKhongActionPerformed(evt);
            }
        });

        cmdSapXep.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        cmdSapXep.setText("Sắp xếp ");
        cmdSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSapXepActionPerformed(evt);
            }
        });

        cboTheLoai.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(cmdKhong)
                        .addGap(91, 91, 91))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(cmdThem, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(cmdSua, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(cmdXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel4)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel3)
                                                    .addGap(7, 7, 7))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addGap(8, 8, 8))))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtNhaXB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                            .addComponent(txtTacGia, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTenSach, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaSach, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSoLuong)
                                            .addComponent(cboTheLoai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(34, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmdSapXep)
                                    .addComponent(cboxTenS))
                                .addGap(21, 21, 21)
                                .addComponent(cboxSlg)
                                .addGap(20, 20, 20)
                                .addComponent(cboxMaS)
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(56, 56, 56)
                .addComponent(combTim, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(cmdALL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(combTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdALL, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxTenS)
                            .addComponent(cboxSlg)
                            .addComponent(cboxMaS))
                        .addGap(18, 18, 18)
                        .addComponent(cmdSapXep)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addComponent(jLabel5))
                            .addComponent(cboTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNhaXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdThem)
                            .addComponent(cmdSua)
                            .addComponent(cmdXoa))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdKhong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdGhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void XoaTrang() {
        txtMaSach.setText("");
        txtNhaXB.setText("");
        txtTacGia.setText("");
        txtTenSach.setText("");
        txtSoLuong.setText("");
    }

    public int TimSach(String macu) {
        int i;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getMaSach().equals(macu)) {
                return i;
            }
        }
        return -1;
    }

    private void combTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combTimActionPerformed
        String str = (String) combTim.getSelectedItem();
        ShowDataTable(str);
    }//GEN-LAST:event_combTimActionPerformed

    public void DataTable() {
        if (modelTable.getRowCount() > 0) {
            modelTable.setRowCount(0);
        }
        for (Book item : list) {
            modelTable.addRow(new Object[]{
                item.getMaSach(), item.getTenSach(), item.getTheLoai(), item.getTacGia(), item.getNXB(), item.getSoLuong()
            });
        }
        lbThongBao.setText("Có " + list.size() + " quển sách.");
    }

    private void cmdALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdALLActionPerformed
        DataTable();
    }//GEN-LAST:event_cmdALLActionPerformed
    int selectedRowIndex;
    private void tbDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhMucMouseClicked
        selectedRowIndex = tbDanhMuc.getSelectedRow();

        txtMaSach.setText(tbDanhMuc.getValueAt(selectedRowIndex, 0).toString());
        txtTenSach.setText(tbDanhMuc.getValueAt(selectedRowIndex, 1).toString());
        cboTheLoai.setSelectedItem(tbDanhMuc.getValueAt(selectedRowIndex, 2).toString());
        txtTacGia.setText(tbDanhMuc.getValueAt(selectedRowIndex, 3).toString());
        txtNhaXB.setText(tbDanhMuc.getValueAt(selectedRowIndex, 4).toString());
        txtSoLuong.setText(tbDanhMuc.getValueAt(selectedRowIndex, 5).toString());
    }//GEN-LAST:event_tbDanhMucMouseClicked

    boolean KtTrungThem(String maSach) {
        for (Book item : list) {
            if (item.getMaSach().equals(maSach)) {
                return true;
            }
        }
        return false;
    }

    boolean KtTrungSua(String macu, String mamoi) {
        int index = TimSach(macu);
        int i;
        for (i = 0; i < list.size(); i++) {
            if (i == index) {
                continue;
            } else {
                if (list.get(i).getMaSach().equals(mamoi)) {
                    return true;
                }
            }
        }
        return false;
    }
    private void cmdSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSapXepActionPerformed
        if (cboxMaS.isSelected()) {
            Collections.sort(list, new MaSachComparator());
        }

        if (cboxTenS.isSelected()) {
            Collections.sort(list, new TenSachComparator());
        }

        if (cboxSlg.isSelected()) {
            Collections.sort(list, new SoLuongComparator());
        }

        if (cboxMaS.isSelected() && cboxTenS.isSelected()) {
            Collections.sort(list, new TenSachComparator().thenComparing(new MaSachComparator()));
        }

        if (cboxMaS.isSelected() && cboxSlg.isSelected()) {
            Collections.sort(list, new SoLuongComparator().thenComparing(new MaSachComparator()));
        }

        if (cboxSlg.isSelected() && cboxTenS.isSelected()) {
            Collections.sort(list, new SoLuongComparator().thenComparing(new TenSachComparator()));
        }

        if (cboxSlg.isSelected() && cboxTenS.isSelected() && cboxMaS.isSelected()) {
            Collections.sort(list, new SoLuongComparator().thenComparing(new TenSachComparator()).thenComparing(new MaSachComparator()));
        }
        setDataCSVBook();
        DataTable();

    }//GEN-LAST:event_cmdSapXepActionPerformed

    private void cmdKhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKhongActionPerformed
        KhoaMo(false);

        txtMaSach.setText(tbDanhMuc.getValueAt(selectedRowIndex, 0).toString());
        txtNhaXB.setText(tbDanhMuc.getValueAt(selectedRowIndex, 4).toString());
        txtTacGia.setText(tbDanhMuc.getValueAt(selectedRowIndex, 3).toString());
        txtTenSach.setText(tbDanhMuc.getValueAt(selectedRowIndex, 1).toString());
        cboTheLoai.setSelectedIndex(combTim.getSelectedIndex());
        txtSoLuong.setText(tbDanhMuc.getValueAt(selectedRowIndex, 5).toString());
    }//GEN-LAST:event_cmdKhongActionPerformed

    private void cmdGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGhiActionPerformed

        if (txtMaSach.getText().equals("")) {
            JOptionPane.showMessageDialog(jframe, "Chưa nhập mã sách.");
        }
        if (txtTenSach.getText().equals("")) {
            JOptionPane.showMessageDialog(jframe, "Chưa nhập tên sách.");
        }
        if (txtSoLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(jframe, "Chưa nhập số lượng sách.");
        }

        if (ktThem == true) {
            if (KtTrungThem(txtMaSach.getText()) == true) {
                JOptionPane.showMessageDialog(jframe, "Trùng mã sách thêm!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Book book = new Book(txtMaSach.getText(), txtTenSach.getText(), cboTheLoai.getItemAt(cboTheLoai.getSelectedIndex()), txtTacGia.getText(), txtNhaXB.getText(), Integer.parseInt(txtSoLuong.getText()));
                list.add(book);
                JOptionPane.showMessageDialog(jframe, "Thêm thành công.");
            }
        } else {
            if (KtTrungSua(macu, txtMaSach.getText()) == true) {
                JOptionPane.showMessageDialog(jframe, "Trùng mã sách sửa !", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                list.get(TimSach(macu)).setMaSach(txtMaSach.getText());
                list.get(TimSach(macu)).setTenSach(txtTenSach.getText());
                list.get(TimSach(macu)).setTacGia(txtTacGia.getText());
                list.get(TimSach(macu)).setTheLoai(cboTheLoai.getItemAt(cboTheLoai.getSelectedIndex()));
                list.get(TimSach(macu)).setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                list.get(TimSach(macu)).setNXB(txtNhaXB.getText());
                JOptionPane.showMessageDialog(jframe, "Sửa thành công.");
            }
        }

        KhoaMo(false);
        setDataCSVBook();
        modelTable.setRowCount(0);
        ShowDataTable(cboTheLoai.getItemAt(cboTheLoai.getSelectedIndex()));
    }//GEN-LAST:event_cmdGhiActionPerformed

    private void cmdXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdXoaActionPerformed
        if ("".equals(txtMaSach.getText())) {
            return;
        }

        //tùy chỉnh văn bản cho nút lệnh
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(jframe, "Bạn có muốn xóa quyển sách nay không ?",
                "MESSAGE",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
        if (n == 0) {
            list.remove(TimSach(txtMaSach.getText()));
            ShowDataTable(cboTheLoai.getItemAt(cboTheLoai.getSelectedIndex()));
            setDataCSVBook();
            XoaTrang();
        }
    }//GEN-LAST:event_cmdXoaActionPerformed

    private void cmdSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSuaActionPerformed
        if (txtMaSach.getText().equals("")) {
            return;
        }
        KhoaMo(true);
        macu = txtMaSach.getText();
        ktThem = false;
    }//GEN-LAST:event_cmdSuaActionPerformed

    private void cmdThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdThemActionPerformed
        KhoaMo(true);
        XoaTrang();
        ktThem = true;
    }//GEN-LAST:event_cmdThemActionPerformed

    public void getComboData() {
        frmChuyenMuc frCM = new frmChuyenMuc();
        chuyenMuc = frCM.getList();
        for (ChuyenMuc item : chuyenMuc) {
            combTim.addItem(item.getTenMuc());
            cboTheLoai.addItem(item.getTenMuc());
        }

    }

    public void ShowDataTable(String theL) {
        List<Book> book = new ArrayList<>();
        for (Book item : list) {
            if (item.getTheLoai().equals(theL)) {
                book.add(item);
            }
        }

        modelTable.setRowCount(0);
        for (Book item : book) {
            modelTable.addRow(new Object[]{
                item.getMaSach(), item.getTenSach(), item.getTheLoai(), item.getTacGia(), item.getNXB(), item.getSoLuong()
            });
        }
        lbThongBao.setText("Có " + book.size() + " quển sách.");

    }

    /**
     * @param args the command line arguments
     */
    private void getDataToCSVBook() {
        FileReader fr = null;
        try {
            fr = new FileReader(path_file_CSV);
            CSVReader csvReader = new CSVReader(fr);
            String[] line;
            String[] header = csvReader.readNext();
            while ((line = csvReader.readNext()) != null) {
                list.add(new Book(line[0], line[1], line[2], line[3], line[4], Integer.parseInt(line[5])));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmSach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frmSach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvValidationException ex) {
            Logger.getLogger(frmSach.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(frmSach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setDataCSVBook() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path_file_CSV);
            CSVWriter csvWriter = new CSVWriter(fw,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] header = {"Ma sach", "Ten sach", "The loai", "Tac gia", "Nha XB", "So luong"};
            csvWriter.writeNext(header);

            for (Book item : list) {
                csvWriter.writeNext(new String[]{
                    item.getMaSach(),
                    item.getTenSach(),
                    item.getTheLoai(),
                    item.getTacGia(),
                    item.getNXB(),
                    String.valueOf(item.getSoLuong())
                });
            }
        } catch (IOException ex) {
            Logger.getLogger(frmChuyenMuc.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(frmChuyenMuc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            java.util.logging.Logger.getLogger(frmSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new frmSach().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JCheckBox cboxMaS;
    private javax.swing.JCheckBox cboxSlg;
    private javax.swing.JCheckBox cboxTenS;
    private javax.swing.JButton cmdALL;
    private javax.swing.JButton cmdGhi;
    private javax.swing.JButton cmdKhong;
    private javax.swing.JToggleButton cmdSapXep;
    private javax.swing.JButton cmdSua;
    private javax.swing.JButton cmdThem;
    private javax.swing.JButton cmdXoa;
    private javax.swing.JComboBox<String> combTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbThongBao;
    private javax.swing.JTable tbDanhMuc;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNhaXB;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
