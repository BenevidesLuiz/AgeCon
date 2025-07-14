package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoBanco {

    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/agecon";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection abreConexao() {
        Connection con = null;

        try {
            // Carrega o driver JDBC (não obrigatório nas versões mais recentes, mas boa prática)
            Class.forName(DRIVER_CLASS);

            // Conecta ao banco
            con = DriverManager.getConnection(URL, USUARIO, SENHA);
            //Para testar a conexão:
            //JOptionPane.showMessageDialog(null, "Conectado com Sucesso!");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + e.getMessage());
        }

        return con;
    }
}
