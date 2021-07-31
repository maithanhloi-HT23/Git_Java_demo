/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
class Lop {

    private String maLop;
    private String tenLop;

    public Lop(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

}

public class frmLop extends javax.swing.JFrame {

    List<Lop> list;
    Lop lop;
    DefaultTableModel modelTable;
    JFrame jframe = new JFrame();
    int selectedRowIndex;//Vi tri click chuot tren table
    String macu;
    boolean ktThem;
    final String thuMucHT = System.getProperty("user.dir");
    final String seprator = File.separator;
    final String path_file_CSV = thuMucHT + seprator + "Data" + seprator + "Lop.csv";

    public frmLop() {
        initComponents();
        list = new ArrayList<>();
        getDataLopCSV();
        modelTable = (DefaultTableModel) tbLop.getModel();
        KhoaMo(false);
        layDataTbale();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        lbThongBao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLop = new javax.swing.JTable();
        cmdGhi = new javax.swing.JButton();
        cmdKhong = new javax.swing.JButton();
        cmdThem = new javax.swing.JButton();
        cmdSua = new javax.swing.JButton();
        cmdXoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ LỚP");

        lbThongBao.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbThongBao.setText(".");

        tbLop.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lớp", "Tên lớp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLopMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbLop);

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

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setText("Mã lớp :");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setText("Tên lớp:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdThem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(cmdSua, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(cmdXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                    .addComponent(txtTen))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(cmdKhong)
                        .addGap(62, 62, 62))))
            .addGroup(layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdSua)
                                .addComponent(cmdXoa))
                            .addComponent(cmdThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdKhong, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(cmdGhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public List<Lop> getList() {
        return list;
    }

    private void KhoaMo(boolean b) {
        txtMa.setEditable(b);
        txtTen.setEditable(b);

        cmdThem.setEnabled(!b);
        cmdSua.setEnabled(!b);
        cmdXoa.setEnabled(!b);

        cmdGhi.setEnabled(b);
        cmdKhong.setEnabled(b);

        tbLop.setEnabled(!b);

    }

    private void XoaTrang() {
        txtMa.setText("");
        txtTen.setText("");
    }

    private void getDataLopCSV() {
        FileReader fr = null;

        try {
            fr = new FileReader(path_file_CSV);
            CSVReader csvReader = new CSVReader(fr);
            String[] line;
            String[] header = csvReader.readNext();
            while ((line = csvReader.readNext()) != null) {
                list.add(new Lop(line[0], line[1]));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmLop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frmLop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvValidationException ex) {
            Logger.getLogger(frmLop.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(frmLop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void setDataLopCSV() {
        FileWriter fw = null;

        try {
            fw = new FileWriter(path_file_CSV);
            CSVWriter csvWriter = new CSVWriter(fw,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] header = {"Ma lop", "Ten lop"};
            csvWriter.writeNext(header);

            for (Lop item : list) {
                csvWriter.writeNext(new String[]{item.getMaLop(), item.getTenLop()});
            }

        } catch (IOException ex) {
            Logger.getLogger(frmLop.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(frmLop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void layDataTbale() {
        if (modelTable.getRowCount() > 0) {
            modelTable.setRowCount(0);
        }
        for (Lop item : list) {
            modelTable.addRow(new Object[]{item.getMaLop(), item.getTenLop()});
        }
        lbThongBao.setText("Có " + tbLop.getRowCount() + " lớp.");
    }

    private void tbLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLopMouseClicked
        selectedRowIndex = tbLop.getSelectedRow();

        txtMa.setText(tbLop.getValueAt(selectedRowIndex, 0).toString());
        txtTen.setText(tbLop.getValueAt(selectedRowIndex, 1).toString());
    }//GEN-LAST:event_tbLopMouseClicked

    private int TimLop(String macu) {
        int i;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getMaLop().equals(macu)) {
                return i;
            }
        }
        return -1;
    }

    private void cmdGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGhiActionPerformed
        if (txtMa.getText().equals("")) {
            JOptionPane.showMessageDialog(jframe, "Chưa nhập mã chuyên mục.");
        }
        if (txtTen.getText().equals("")) {
            JOptionPane.showMessageDialog(jframe, "Chưa nhập chuyên mục.");
        }

        if (ktThem == true) {
            if (KtTrungThem(txtMa.getText()) == true) {
                JOptionPane.showMessageDialog(jframe, "Trùng mã chuyên mục thêm !", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                lop = new Lop(txtMa.getText(), txtTen.getText());
                list.add(lop);
                JOptionPane.showMessageDialog(jframe, "Thêm thành công.");
            }
        } else {
            if (KtTrungSua(macu, txtMa.getText()) == true) {
                JOptionPane.showMessageDialog(jframe, "Trùng chuyên mục sửa !", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int index = TimLop(macu);
                list.get(index).setMaLop(txtMa.getText());
                list.get(index).setTenLop(txtTen.getText());
                JOptionPane.showMessageDialog(jframe, "Sửa thành công.");
            }
        }

        KhoaMo(false);
        layDataTbale();
        setDataLopCSV();
    }//GEN-LAST:event_cmdGhiActionPerformed

    private boolean KtTrungSua(String macu, String mamoi) {
        int index = TimLop(macu);
        int i;
        for (i = 0; i < list.size(); i++) {
            if (i == index) {
                continue;
            } else {
                if (list.get(i).getMaLop().equals(macu)) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean KtTrungThem(String macu) {
        for (Lop item : list) {
            if (item.getMaLop().equals(macu)) {
                return true;
            }
        }
        return false;
    }

    private void cmdKhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKhongActionPerformed
        KhoaMo(false);

        txtMa.setText(tbLop.getValueAt(selectedRowIndex, 0).toString());
        txtTen.setText(tbLop.getValueAt(selectedRowIndex, 1).toString());
    }//GEN-LAST:event_cmdKhongActionPerformed

    private void cmdThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdThemActionPerformed
        KhoaMo(true);
        XoaTrang();
        ktThem = true;
    }//GEN-LAST:event_cmdThemActionPerformed

    private void cmdSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSuaActionPerformed
        if (txtMa.getText().equals("")) {
            return;
        }
        KhoaMo(true);
        macu = txtMa.getText();
        ktThem = false;
    }//GEN-LAST:event_cmdSuaActionPerformed

    private void cmdXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdXoaActionPerformed
        if (txtMa.getText().equals("")) {
            return;
        }
        //tùy chỉnh văn bản cho nút lệnh
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(jframe, "Bạn có muốn xóa chuyên mục nay không ?",
                "MESSAGE",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
        if (n == 0) {
            list.remove(TimLop(txtMa.getText()));
            layDataTbale();
            setDataLopCSV();
            XoaTrang();
        }
    }//GEN-LAST:event_cmdXoaActionPerformed

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
            java.util.logging.Logger.getLogger(frmLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdGhi;
    private javax.swing.JButton cmdKhong;
    private javax.swing.JButton cmdSua;
    private javax.swing.JButton cmdThem;
    private javax.swing.JButton cmdXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbThongBao;
    private javax.swing.JTable tbLop;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}