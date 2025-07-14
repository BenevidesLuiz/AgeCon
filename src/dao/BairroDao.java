package dao;

import interfaces.InterfaceDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.BairroModelo;


public class BairroDao implements InterfaceDao{
    
    String sql;
    PreparedStatement stm;
    ResultSet resultado;
    
        
    
    @Override
    public void salvarDao(Object... valor) {
        BairroModelo brm = (BairroModelo) valor[0];

        if (brm.getId() == 0) {
            sql = "INSERT INTO bairro (nome, cidade_id, referencias) VALUES (?, ?, ?)";
            JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
        } else {
            sql = "UPDATE bairro SET nome=?, cidade_id=?, referencias=? WHERE id=?";
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
        }

        try {
            stm = ConexaoBanco.abreConexao().prepareStatement(sql);

            stm.setString(1, brm.getNome());
            stm.setInt(2, brm.getCidade_id());
            stm.setString(3, brm.getReferencias());

            if (brm.getId() > 0) {
                stm.setInt(4, brm.getId()); // parâmetro para o WHERE
            }

            stm.execute();
            stm.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar: " + e);
        }
}


    @Override
    public void excluirDao(int id) {
        sql = "DELETE FROM bairro WHERE id=?";
        try {
        stm = ConexaoBanco.abreConexao().prepareStatement(sql);
        stm.setInt(1,id);
        stm.execute();
        stm.close();
        
        JOptionPane.showMessageDialog(null,"Registo Excluido com sucesso.");
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Ocorreu um erro ao tentar exluir o registro "+e);
        }
    }

   @Override
public void consultarDao(Object... valor) throws SQLException {

    DefaultTableModel tabela = (DefaultTableModel) valor[1];
    String filtro = (String) valor[0];

    // Consulta com join para pegar nome da cidade
    if ("".equals(filtro)) {
        sql = "SELECT b.id, b.nome, b.cidade_id, b.referencias, c.nome AS cidade_nome " +
              "FROM bairro b LEFT JOIN cidade c ON b.cidade_id = c.id";
    } else {
        sql = "SELECT b.id, b.nome, b.cidade_id, b.referencias, c.nome AS cidade_nome " +
              "FROM bairro b LEFT JOIN cidade c ON b.cidade_id = c.id " +
              "WHERE b.nome LIKE ?";
    }

    stm = ConexaoBanco.abreConexao().prepareStatement(sql);
    
    if (!"".equals(filtro)) {
        stm.setString(1, "%" + filtro + "%");
    }

    resultado = stm.executeQuery();

    while (resultado.next()) {
        tabela.addRow(new Object[]{
            resultado.getInt("id"),
            resultado.getString("nome"),
            resultado.getString("cidade_nome"), // Mostra o nome da cidade no modelo para a tabela
            resultado.getString("referencias")
        });
    }

    resultado.close();
    stm.close();
}

@Override
public void carregarDao(JComboBox itens) throws SQLException {
    itens.removeAllItems();

    sql = "SELECT id, nome FROM cidade ORDER BY nome";
    stm = ConexaoBanco.abreConexao().prepareStatement(sql);
    resultado = stm.executeQuery();

    while (resultado.next()) {
        // Você pode criar um objeto para armazenar id e nome no JComboBox, para facilitar pegar o id depois
        itens.addItem(new Item(resultado.getInt("id"), resultado.getString("nome")));
    }

    resultado.close();
    stm.close();
}

// Classe para representar o item do JComboBox com id e nome
public class Item {
    private int id;
    private String nome;

    public Item(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome; // exibe o nome na lista do JComboBox
    }

    public int getId() {
        return id;
    }
}

    
}
