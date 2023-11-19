/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import auxiliar.Pair;
import db.DB;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.Estoque_ProdutoDao;
import model.dao.FornecedoraDao;
import model.dao.implementations.Estoque_ProdutoDaoJDBC;
import model.dao.implementations.FornecedoraDaoJDBC;
import model.entities.Estoque_Produto;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author silvi
 */
public class EstoqueView extends javax.swing.JFrame {

    /**
     * Creates new form EstoqueView
     * @throws java.lang.ClassNotFoundException
     */
    public EstoqueView() throws ClassNotFoundException {
        initComponents();
        popularComboBox();
    }
    private static EstoqueView estoUnic;
    
    public static EstoqueView getEsto() throws ClassNotFoundException {
        if (estoUnic == null) {
            estoUnic = new EstoqueView();
        }
        return estoUnic;
    }
    public void mostrar() throws ParseException {
        this.setVisible(true);
        atualizarTabela();
        formatar();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTextNome = new javax.swing.JLabel();
        jLabelIntegerQuantidade = new javax.swing.JLabel();
        jLabelFloatPreco = new javax.swing.JLabel();
        jLabelIntFornecedor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldCategoria = new javax.swing.JTextField();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jTextFieldPreco = new javax.swing.JTextField();
        jLabelStringCategoria = new javax.swing.JLabel();
        jLabelStringLocal = new javax.swing.JLabel();
        jLabelDateEntrada = new javax.swing.JLabel();
        jLabelDateValidade = new javax.swing.JLabel();
        jTextFieldLocal = new javax.swing.JTextField();
        jComboBoxFornecedores = new javax.swing.JComboBox<>();
        btnCadastrar = new javax.swing.JButton();
        jTextFieldEntrada = new javax.swing.JFormattedTextField();
        jTextFieldValidade = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(1600, 900));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabelTextNome.setText("Nome");

        jLabelIntegerQuantidade.setText("Quantidade");

        jLabelFloatPreco.setText("Preço");

        jLabelIntFornecedor.setText("Fornecedor");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Preço", "Categoria", "Razão Social do Fornecedor", "Quantidade", "Local", "Data de Entrada", "Data de Saida", "ID do Produto", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabelStringCategoria.setText("Categoria");

        jLabelStringLocal.setText("Local");

        jLabelDateEntrada.setText("Data de Entrada");

        jLabelDateValidade.setText("Data de Validade");

        jTextFieldLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLocalActionPerformed(evt);
            }
        });

        jComboBoxFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFornecedoresActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTextNome)
                            .addComponent(jLabelFloatPreco)
                            .addComponent(jLabelStringCategoria))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelIntFornecedor)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNome)
                    .addComponent(jTextFieldPreco)
                    .addComponent(jTextFieldCategoria)
                    .addComponent(jComboBoxFornecedores, 0, 141, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDateValidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldValidade))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDateEntrada)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldEntrada))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelIntegerQuantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelStringLocal)
                        .addGap(73, 73, 73)
                        .addComponent(jTextFieldLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCadastrar)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1223, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTextNome)
                    .addComponent(jLabelIntegerQuantidade)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFloatPreco)
                    .addComponent(jTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStringLocal)
                    .addComponent(jTextFieldLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStringCategoria)
                    .addComponent(jTextFieldCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDateEntrada)
                    .addComponent(jTextFieldEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIntFornecedor)
                    .addComponent(jComboBoxFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDateValidade)
                    .addComponent(btnCadastrar)
                    .addComponent(jTextFieldValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLocalActionPerformed

    private void jComboBoxFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFornecedoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFornecedoresActionPerformed

    private void formatar() throws ParseException {
        MaskFormatter mascaraData = new MaskFormatter("####-##-##");
        mascaraData.setPlaceholderCharacter('_');
        jTextFieldEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascaraData));
        jTextFieldValidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascaraData));
    }

    private void atualizarTabela() {
        try {
            Estoque_ProdutoDao estoqueProdutoDao = new Estoque_ProdutoDaoJDBC(DB.getConnection());
            List<Estoque_Produto> listaProdutos = estoqueProdutoDao.findAll();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            for (Estoque_Produto produto : listaProdutos) {
                model.addRow(new Object[]{
                    produto.getEstId(),
                    produto.getProNome(),
                    produto.getProPreco(),
                    produto.getProCategoria(),
                    produto.getForRazaoSocial(),
                    produto.getEstQuantidade(),
                    produto.getEstLocal(),
                    produto.getEstDataEntrada(),
                    produto.getEstDataValidade(),
                    produto.getProId(),
                    "Alterar",
                    "Excluir"
                });
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar tabela: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        jTextFieldNome.setText("");
        jTextFieldPreco.setText("");
        jTextFieldCategoria.setText("");
        jTextFieldQuantidade.setText("");
        jTextFieldLocal.setText("");
        jTextFieldEntrada.setValue("");
        jTextFieldValidade.setValue("");
        jComboBoxFornecedores.setSelectedIndex(0);
    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        try {
            
            FornecedoraDao fornecedorDao = new FornecedoraDaoJDBC(DB.getConnection());
            List<Pair<Integer, String>> fornecedores = fornecedorDao.findAllForCombobox();
            int idFornecedor=0;
            String origem = (String) jComboBoxFornecedores.getSelectedItem();
            for(Pair<Integer, String> fornecedor : fornecedores){
                if(fornecedor.getSecond().equals(origem)){
                    idFornecedor= fornecedor.getFirst();
                }
            }
            
            String nome = jTextFieldNome.getText();
            float preco = Float.parseFloat(jTextFieldPreco.getText());
            String categoria = jTextFieldCategoria.getText();
            int quantidade = Integer.parseInt(jTextFieldQuantidade.getText());
            String local = jTextFieldLocal.getText();
            String dataEntradaStr = jTextFieldEntrada.getText();
            String dataValidadeStr = jTextFieldValidade.getText();

            Estoque_Produto novoProduto = new Estoque_Produto();
            novoProduto.setProNome(nome);
            novoProduto.setProPreco(preco);
            novoProduto.setProCategoria(categoria);
            novoProduto.setEstQuantidade(quantidade);
            novoProduto.setEstLocal(local);
            novoProduto.setForId(idFornecedor);
            Date dataEntrada = converterStringParaDate(dataEntradaStr);
            Date dataValidade = converterStringParaDate(dataValidadeStr);

            novoProduto.setEstDataEntrada(dataEntrada);
            novoProduto.setEstDataValidade(dataValidade);

            Estoque_ProdutoDao estoqueProdutoDao = new Estoque_ProdutoDaoJDBC(DB.getConnection());
            estoqueProdutoDao.insert(novoProduto, 1);
            
            atualizarTabela();
            jTable1.revalidate();
            jTable1.repaint();
            
            limparCampos();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void jTextFieldValidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValidadeActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int row = jTable1.rowAtPoint(evt.getPoint());
            int col = jTable1.columnAtPoint(evt.getPoint());
            Estoque_Produto estoque = new Estoque_Produto();
            Estoque_ProdutoDao estoqueDao = new Estoque_ProdutoDaoJDBC(DB.getConnection());
            if (col == jTable1.getColumnCount() - 2) {
                estoque.setProId((int) jTable1.getValueAt(row, 9));
                estoque.setEstDataEntrada((Date) jTable1.getValueAt(row, 7));
                estoque.setEstDataValidade((Date) jTable1.getValueAt(row, 8));
                estoqueDao.delete(estoque, 1);

            } else if (col == jTable1.getColumnCount() - 1) {
                int id = (int) jTable1.getValueAt(row, 9);
                 String entrada = (String) jTable1.getValueAt(row, 7);
                String validade = (String) jTable1.getValueAt(row, 8);
                editarProdutoEstoque(id, entrada, validade);
            }
            atualizarTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransportadoraView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    public void editarEstoque(){}
            
    public static Date converterStringParaDate(String dataString) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.parse(dataString);
        } catch (ParseException e) {
            return null;
        }
    }
    
    private void popularComboBox() throws ClassNotFoundException {
        FornecedoraDao fornecedorDao = new FornecedoraDaoJDBC(DB.getConnection());
        List<Pair<Integer, String>> fornecedores = fornecedorDao.findAllForCombobox();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (Pair<Integer, String> fornecedor : fornecedores) {
            model.addElement(fornecedor.getSecond());
        }

        jComboBoxFornecedores.setModel(model);
    }

    
    private void editarProdutoEstoque(int id, String entrada, String validade) {
    try {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
            return;
        }
        int estId = (int) jTable1.getValueAt(selectedRow, 0);
        Estoque_ProdutoDao estoqueProdutoDao = new Estoque_ProdutoDaoJDBC(DB.getConnection());

        Estoque_Produto produtoExistente = new Estoque_Produto();
        produtoExistente.setEstId(estId);
        produtoExistente = estoqueProdutoDao.findById(produtoExistente);
        Estoque_Produto novoProduto = new Estoque_Produto();
            
            novoProduto.setEstId(id);
            novoProduto.setOrigemDataEntrada(converterStringParaDate(entrada));
            novoProduto.setOrigemDataValidade(converterStringParaDate(validade));

        
        JTextField txtNome = new JTextField(produtoExistente.getProNome());
        JTextField txtPreco = new JTextField(String.valueOf(produtoExistente.getProPreco()));
        JTextField txtCategoria = new JTextField(produtoExistente.getProCategoria());
        JTextField txtQuantidade = new JTextField(String.valueOf(produtoExistente.getEstQuantidade()));
        JTextField txtLocal = new JTextField(produtoExistente.getEstLocal());
        JTextField txtDataEntrada = new JTextField(produtoExistente.getEstDataEntrada().toString());
        JTextField txtDataValidade = new JTextField(produtoExistente.getEstDataValidade().toString());

        // Crie um array de objetos com os campos de texto
        Object[] fields = {
            "Nome:", txtNome,
            "Preço:", txtPreco,
            "Categoria:", txtCategoria,
            "Quantidade:", txtQuantidade,
            "Local:", txtLocal,
            "Data de Entrada:", txtDataEntrada,
            "Data de Validade:", txtDataValidade
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Editar Produto no Estoque", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            novoProduto.setProId(id);
            novoProduto.setProNome(txtNome.getText());
            novoProduto.setProPreco(Float.parseFloat(txtPreco.getText()));
            novoProduto.setProCategoria(txtCategoria.getText());
            novoProduto.setEstQuantidade(Integer.parseInt(txtQuantidade.getText()));
            novoProduto.setEstLocal(txtLocal.getText());
            novoProduto.setEstDataEntrada(converterStringParaDate(txtDataEntrada.getText()));
            novoProduto.setEstDataValidade(converterStringParaDate(txtDataValidade.getText()));

            estoqueProdutoDao.update(novoProduto,1);

            atualizarTabela();
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Número inválido. Certifique-se de fornecer valores numéricos para campos como Preço e Quantidade.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao editar o produto no estoque: " + ex.getMessage());
    }
}

    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JComboBox<String> jComboBoxFornecedores;
    private javax.swing.JLabel jLabelDateEntrada;
    private javax.swing.JLabel jLabelDateValidade;
    private javax.swing.JLabel jLabelFloatPreco;
    private javax.swing.JLabel jLabelIntFornecedor;
    private javax.swing.JLabel jLabelIntegerQuantidade;
    private javax.swing.JLabel jLabelStringCategoria;
    private javax.swing.JLabel jLabelStringLocal;
    private javax.swing.JLabel jLabelTextNome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCategoria;
    private javax.swing.JFormattedTextField jTextFieldEntrada;
    private javax.swing.JTextField jTextFieldLocal;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPreco;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JFormattedTextField jTextFieldValidade;
    // End of variables declaration//GEN-END:variables
}
