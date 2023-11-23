package view;

import auxiliar.Pair;
import db.DB;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.dao.FornecedoraDao;
import model.dao.TransportadoraDao;
import model.dao.implementations.FornecedoraDaoJDBC;
import model.dao.implementations.TransportadoraDaoJDBC;
import model.entities.Fornecedora;


public class FornecedoraView extends javax.swing.JFrame {

 private static FornecedoraView fornUnic;

    private FornecedoraView() {
        initComponents();
     try {
         popularComboBox();
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(FornecedoraView.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    public static FornecedoraView getForn() {
        if (fornUnic == null) {
            fornUnic = new FornecedoraView();
        }
        return fornUnic;
    }

    public void mostrar() {
        this.setVisible(true);
        try {
            popularComboBox();
            preencherTabela();
            formatar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransportadoraView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
         Logger.getLogger(FornecedoraView.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    private void formatar() throws ParseException {
        MaskFormatter mascaraCNPJ = new MaskFormatter("##.###.###/####-##");
        mascaraCNPJ.setPlaceholderCharacter('_');
        MaskFormatter mascaraTelefone = new MaskFormatter("(##)#########");
        jFormattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascaraTelefone));
        jFormattedTextFieldCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascaraCNPJ));
    }

    public void preencherTabela() throws ClassNotFoundException {
        FornecedoraDao fornecedoraDao = new FornecedoraDaoJDBC(DB.getConnection());
        List<Fornecedora> fornecedoras = fornecedoraDao.findAll();

        DefaultTableModel model = (DefaultTableModel) tabelaFornecedora.getModel();
        model.setRowCount(0);
        
        for (Fornecedora fornecedora : fornecedoras) {
            Object[] row = {
                fornecedora.getForId(),
                fornecedora.getForCnpj(),
                fornecedora.getForRazaoSocial(),
                fornecedora.getForEmail(),
                fornecedora.getForTelefone(),
                fornecedora.getForLogradouro(),
                fornecedora.getForNumero(),
                fornecedora.getForCep(),
                fornecedora.getForCidade(),
                fornecedora.getForEstado(),
                fornecedora.getTraId(),
                "Alterar",
                "Excluir"
                
            };
            model.addRow(row);
        }
    }
    
    private void popularComboBox() throws ClassNotFoundException {
        TransportadoraDao transportadoraDao = new TransportadoraDaoJDBC(DB.getConnection());
        List<Pair<Integer, String>> transportadoras = transportadoraDao.findAllForCombobox();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("");
        for (Pair<Integer, String> transportadora : transportadoras) {
            model.addElement(transportadora.getSecond());
        }

        jComboBoxTransportadoras.setModel(model);
    }
    
    private void adicionarFornecedora() {
        try {
            TransportadoraDao transportadoraDao = new TransportadoraDaoJDBC(DB.getConnection());
            List<Pair<Integer, String>> fornecedores = transportadoraDao.findAllForCombobox();
            int idTransportadora=0;
            String origem = (String) jComboBoxTransportadoras.getSelectedItem();
            for(Pair<Integer, String> fornecedor : fornecedores){
                if(fornecedor.getSecond().equals(origem)){
                    idTransportadora= fornecedor.getFirst();
                }
            }
            
            String cnpj = jFormattedTextFieldCNPJ.getText();
            String razaoSocial = jTextFieldRazaoSocial.getText();
            String email = jTextFieldEmail.getText();
            String telefone = jFormattedTextFieldTelefone.getText();
            String logradouro = jTextFieldLogradouro.getText();
            int numero = Integer.parseInt(jTextFieldNumero.getText());
            int cep = Integer.parseInt(jTextFieldCep.getText());
            String cidade = jTextFieldCidade.getText();
            String estado = jTextFieldEstado.getText();
            int traId = idTransportadora;
            
            Fornecedora novaFornecedora = new Fornecedora();
            novaFornecedora.setForCnpj(cnpj);
            novaFornecedora.setForRazaoSocial(razaoSocial);
            novaFornecedora.setForEmail(email);
            novaFornecedora.setForTelefone(telefone);
            novaFornecedora.setForLogradouro(logradouro);
            novaFornecedora.setForNumero(numero);
            novaFornecedora.setForCep(cep);
            novaFornecedora.setForCidade(cidade);
            novaFornecedora.setForEstado(estado);
            novaFornecedora.setTraId(traId);

            FornecedoraDao fornecedoraDao = new FornecedoraDaoJDBC(DB.getConnection());
            fornecedoraDao.insert(novaFornecedora);
            
            limparCampos();
            preencherTabela();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número inválido. Certifique-se de fornecer valores numéricos para campos como Número e CEP.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar a transportadora.");
        }
    }

    private void limparCampos() {
        jFormattedTextFieldCNPJ.setText("");
        jTextFieldRazaoSocial.setText("");
        jTextFieldEmail.setText("");
        jFormattedTextFieldTelefone.setText("");
        jTextFieldLogradouro.setText("");
        jTextFieldNumero.setText("");
        jTextFieldCep.setText("");
        jTextFieldCidade.setText("");
        jTextFieldEstado.setText("");
    }
    
    private void editarFornecedora() {
    try {
        int selectedRow = tabelaFornecedora.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma fornecedora para editar.");
            return;
        }
        int forId = (int) tabelaFornecedora.getValueAt(selectedRow, 0);
        FornecedoraDao fornecedoraDao = new FornecedoraDaoJDBC(DB.getConnection());

        Fornecedora fornecedoraExistente = new Fornecedora();
        fornecedoraExistente.setForId(forId);
        fornecedoraExistente = fornecedoraDao.findById(fornecedoraExistente);
        Fornecedora novaFornecedora = new Fornecedora();

        // Criar JTextFields com os valores existentes
        JTextField txtCnpj = new JTextField(fornecedoraExistente.getForCnpj());
        JTextField txtRazaoSocial = new JTextField(fornecedoraExistente.getForRazaoSocial());
        JTextField txtEmail = new JTextField(fornecedoraExistente.getForEmail());
        JTextField txtTelefone = new JTextField(fornecedoraExistente.getForTelefone());
        JTextField txtLogradouro = new JTextField(fornecedoraExistente.getForLogradouro());
        JTextField txtNumero = new JTextField(String.valueOf(fornecedoraExistente.getForNumero()));
        JTextField txtCep = new JTextField(String.valueOf(fornecedoraExistente.getForCep()));
        JTextField txtCidade = new JTextField(fornecedoraExistente.getForCidade());
        JTextField txtEstado = new JTextField(fornecedoraExistente.getForEstado());

   
        // Criar um array de objetos com os campos de texto e JComboBox
        Object[] fields = {
            "CNPJ:", txtCnpj,
            "Razão Social:", txtRazaoSocial,
            "Email:", txtEmail,
            "Telefone:", txtTelefone,
            "Logradouro:", txtLogradouro,
            "Número:", txtNumero,
            "CEP:", txtCep,
            "Cidade:", txtCidade,
            "Estado:", txtEstado,
            "Transportadora:", jComboBoxTransportadoras,
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Editar Fornecedora", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            
            TransportadoraDao transportadoraDao = new TransportadoraDaoJDBC(DB.getConnection());
            List<Pair<Integer, String>> fornecedores = transportadoraDao.findAllForCombobox();
            int idTransportadora=0;
            String origem = (String) jComboBoxTransportadoras.getSelectedItem();
            for(Pair<Integer, String> fornecedor : fornecedores){
                if(fornecedor.getSecond().equals(origem)){
                    idTransportadora= fornecedor.getFirst();
                }
            }
            novaFornecedora.setForId(forId);
            novaFornecedora.setForCnpj(txtCnpj.getText());
            novaFornecedora.setForRazaoSocial(txtRazaoSocial.getText());
            novaFornecedora.setForEmail(txtEmail.getText());
            novaFornecedora.setForTelefone(txtTelefone.getText());
            novaFornecedora.setForLogradouro(txtLogradouro.getText());
            novaFornecedora.setForNumero(Integer.parseInt(txtNumero.getText()));
            novaFornecedora.setForCep(Integer.parseInt(txtCep.getText()));
            novaFornecedora.setForCidade(txtCidade.getText());
            novaFornecedora.setForEstado(txtEstado.getText());
            novaFornecedora.setTraId(idTransportadora);

            fornecedoraDao.update(novaFornecedora);
            preencherTabela();
            popularComboBox();
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Número inválido. Certifique-se de fornecer valores numéricos para campos como Número e CEP.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao editar a fornecedora: " + ex.getMessage());
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

        jLabelCnpj = new javax.swing.JLabel();
        jLabelRazaoSocial = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelTelefone = new javax.swing.JLabel();
        jLabelLogradouro = new javax.swing.JLabel();
        jLabelNumero = new javax.swing.JLabel();
        jLabelCep = new javax.swing.JLabel();
        jLabelCidade = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jTextFieldRazaoSocial = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldLogradouro = new javax.swing.JTextField();
        jTextFieldNumero = new javax.swing.JTextField();
        jTextFieldCep = new javax.swing.JTextField();
        jTextFieldCidade = new javax.swing.JTextField();
        jTextFieldEstado = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFornecedora = new javax.swing.JTable();
        jComboBoxTransportadoras = new javax.swing.JComboBox<>();
        jLabelTransportadora = new javax.swing.JLabel();
        jFormattedTextFieldCNPJ = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fornecedora");
        setPreferredSize(new java.awt.Dimension(1600, 900));
        setSize(new java.awt.Dimension(1600, 900));

        jLabelCnpj.setText("CNPJ :");

        jLabelRazaoSocial.setText("Razão Social : ");

        jLabelEmail.setText("E-mail : ");

        jLabelTelefone.setText("Telefone : ");

        jLabelLogradouro.setText("Logradouro : ");

        jLabelNumero.setText("Número :");

        jLabelCep.setText("CEP : ");

        jLabelCidade.setText("Cidade : ");

        jLabelEstado.setText("Estado :");

        jTextFieldRazaoSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRazaoSocialActionPerformed(evt);
            }
        });

        jTextFieldLogradouro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLogradouroActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrarMouseClicked(evt);
            }
        });

        tabelaFornecedora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CNPJ", "Razão Social", "Email", "Telefone", "Logradouro", "Numero", "CEP", "Cidade", "UF", "ID Transportadora", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaFornecedora.setPreferredSize(new java.awt.Dimension(1600, 900));
        tabelaFornecedora.setRowHeight(35);
        tabelaFornecedora.setShowHorizontalLines(true);
        tabelaFornecedora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFornecedoraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaFornecedora);
        if (tabelaFornecedora.getColumnModel().getColumnCount() > 0) {
            tabelaFornecedora.getColumnModel().getColumn(0).setMinWidth(40);
            tabelaFornecedora.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabelaFornecedora.getColumnModel().getColumn(0).setMaxWidth(40);
            tabelaFornecedora.getColumnModel().getColumn(9).setMinWidth(40);
            tabelaFornecedora.getColumnModel().getColumn(9).setPreferredWidth(40);
            tabelaFornecedora.getColumnModel().getColumn(9).setMaxWidth(40);
        }

        jComboBoxTransportadoras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxTransportadoras.setBorder(null);

        jLabelTransportadora.setText("Transportadora: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCnpj)
                                    .addComponent(jLabelEmail))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextFieldCNPJ, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                    .addComponent(jTextFieldEmail))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelTelefone))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelRazaoSocial))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelLogradouro)
                                            .addComponent(jLabelCep))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                            .addComponent(jTextFieldCep)
                                            .addComponent(jTextFieldLogradouro)))
                                    .addComponent(jLabelEstado))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCidade)
                                    .addComponent(jLabelNumero)
                                    .addComponent(jLabelTransportadora))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxTransportadoras, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextFieldNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextFieldCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldTelefone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCadastrar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1286, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCnpj)
                    .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRazaoSocial)
                    .addComponent(jFormattedTextFieldCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTelefone)
                    .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLogradouro)
                    .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumero)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCep)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelCidade)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEstado)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCadastrar)
                        .addComponent(jLabelTransportadora)
                        .addComponent(jComboBoxTransportadoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldRazaoSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRazaoSocialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRazaoSocialActionPerformed

    private void btnCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMouseClicked
        adicionarFornecedora();
    }//GEN-LAST:event_btnCadastrarMouseClicked

    private void tabelaFornecedoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFornecedoraMouseClicked
        try {
            int row = tabelaFornecedora.rowAtPoint(evt.getPoint());
            int col = tabelaFornecedora.columnAtPoint(evt.getPoint());
            Fornecedora fornecedora = new Fornecedora();
            FornecedoraDao fornecedoraDao = new FornecedoraDaoJDBC(DB.getConnection());
            if (col == tabelaFornecedora.getColumnCount() - 2) {
                editarFornecedora();
            } else if (col == tabelaFornecedora.getColumnCount() - 1) {
                int traId = (int) tabelaFornecedora.getValueAt(row, 0);
                fornecedora.setForId(traId);
                int result = JOptionPane.showConfirmDialog(this, "Confirmar exclusão", "Confirme", JOptionPane.YES_NO_OPTION);
                if(result==0){
                    fornecedoraDao.delete(fornecedora);
                }
            }
            preencherTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FornecedoraView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabelaFornecedoraMouseClicked

    private void jTextFieldLogradouroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLogradouroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLogradouroActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JComboBox<String> jComboBoxTransportadoras;
    private javax.swing.JFormattedTextField jFormattedTextFieldCNPJ;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JLabel jLabelCep;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelCnpj;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelLogradouro;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelRazaoSocial;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JLabel jLabelTransportadora;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCep;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldLogradouro;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldRazaoSocial;
    private javax.swing.JTable tabelaFornecedora;
    // End of variables declaration//GEN-END:variables
}
