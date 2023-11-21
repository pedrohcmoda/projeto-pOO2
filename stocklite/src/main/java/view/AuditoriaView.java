package view;

import db.DB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.implementations.AuditoriaDaoJDBC;
import model.entities.Auditoria;
import model.dao.AuditoriaDao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author phcun
 */
public class AuditoriaView extends javax.swing.JFrame {

    /**
     * Creates new form AuditoriaView
     */
    public AuditoriaView() {
        initComponents();
    }
    
    private static AuditoriaView audiUnic;
    
    public static AuditoriaView getAudi() {
        if (audiUnic == null) {
            audiUnic = new AuditoriaView();
        }
        return audiUnic;
    }

    public void mostrar() {
        this.setVisible(true);
        try {
            preencherTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuditoriaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAuditoria = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(1600, 900));

        tabelaAuditoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID da Auditoria", "ID do Funcionario", "ID do Produto", "Ação Realizada", "Quantidade Alterada", "Data de Alteração"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaAuditoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAuditoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaAuditoria);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaAuditoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAuditoriaMouseClicked
        try {
            preencherTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuditoriaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelaAuditoriaMouseClicked

    
    public void preencherTabela() throws ClassNotFoundException {
        AuditoriaDao auditoriaDao = new AuditoriaDaoJDBC(DB.getConnection());
        List<Auditoria> auditorias = auditoriaDao.findAll();

        
        if (auditorias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lista vazia", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tabelaAuditoria.getModel();
        model.setRowCount(0);
        
        for (Auditoria auditoria : auditorias) {
            String acao;
            acao = switch (auditoria.getAcao()) {
                case 1 -> "Inserção";
                case 2 -> "Atualização";
                case 3 -> "Remoção";
                default -> "Ação Não Reconhecida";
            };
            Object[] row = {
                auditoria.getAudId(),
                auditoria.getFunId(),
                auditoria.getProId(),
                acao,
                auditoria.getQuantidade(),
                auditoria.getDatahora()
            };
            model.addRow(row);
        }
    }
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
            java.util.logging.Logger.getLogger(AuditoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuditoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuditoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuditoriaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuditoriaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaAuditoria;
    // End of variables declaration//GEN-END:variables
}
