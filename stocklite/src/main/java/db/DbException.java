package db;

import javax.swing.JOptionPane;

public class DbException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DbException(String msg){
        super(msg);
        JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a operação:\n" + msg, "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
    }
}
