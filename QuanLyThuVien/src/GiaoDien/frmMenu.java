/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import javax.swing.JFrame;

/**
 *
 * @author Loi
 */
public class frmMenu extends javax.swing.JFrame {

    /**
     * Creates new form frmMenu
     */
    public frmMenu() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ChuyenMuc = new javax.swing.JMenuItem();
        Sach = new javax.swing.JMenuItem();
        SinhVien = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jMenu1.setText("Hệ Thống");
        jMenu1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jMenuItem1.setText("Kết Thúc");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Danh Mục");
        jMenu2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        ChuyenMuc.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ChuyenMuc.setText("Chuyên Mục");
        ChuyenMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenMucActionPerformed(evt);
            }
        });
        jMenu2.add(ChuyenMuc);

        Sach.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        Sach.setText("Sách");
        Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SachActionPerformed(evt);
            }
        });
        jMenu2.add(Sach);

        SinhVien.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        SinhVien.setText("Sinh Viên");
        SinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SinhVienActionPerformed(evt);
            }
        });
        jMenu2.add(SinhVien);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Quản lý phiếu");
        jMenu3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 847, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SachActionPerformed
       new frmSach().setVisible(true);
    }//GEN-LAST:event_SachActionPerformed

    private void SinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SinhVienActionPerformed
       new frmSinhVien().setVisible(true);
    }//GEN-LAST:event_SinhVienActionPerformed

    private void ChuyenMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenMucActionPerformed
        new frmChuyenMuc().setVisible(true);
    }//GEN-LAST:event_ChuyenMucActionPerformed

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
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ChuyenMuc;
    private javax.swing.JMenuItem Sach;
    private javax.swing.JMenuItem SinhVien;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
