package dao;

import interfaces.InterfaceDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.CidadeModelo; // Certifique-se de que o modelo CidadeModelo existe

public class CidadeDao implements InterfaceDao {

    String sql;
    PreparedStatement stm;
    ResultSet resultado;

    @Override
    public void salvarDao(Object... valor) {
        // Recebe o objeto CidadeModelo em vez de vários parâmetros soltos
        CidadeModelo cidade = (CidadeModelo) valor[0];

        // Define a instrução SQL com base no ID da cidade
        if (cidade.getId() == 0) {
            sql = "INSERT INTO cidade (nome, uf, cep) VALUES (?, ?, ?)";
        } else {
            sql = "UPDATE cidade SET nome = ?, uf = ?, cep = ? WHERE id = ?";
        }

        try {
            // Usa a classe de conexão padrão do seu projeto
            stm = ConexaoBanco.abreConexao().prepareStatement(sql);

            // Define os parâmetros usando os getters do modelo
            stm.setString(1, cidade.getNome());
            stm.setString(2, cidade.getUf());
            stm.setString(3, cidade.getCep());

            // Se for uma atualização (UPDATE), define o ID no WHERE
            if (cidade.getId() != 0) {
                stm.setInt(4, cidade.getId());
            }

            stm.executeUpdate();
            stm.close();

            // Exibe a mensagem de sucesso APÓS a execução no banco
            JOptionPane.showMessageDialog(null, "Cidade salva com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar cidade: " + e.getMessage());
        }
    }

    @Override
    public void excluirDao(int id) {
        sql = "DELETE FROM cidade WHERE id = ?";
        try {
            stm = ConexaoBanco.abreConexao().prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Cidade excluída com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cidade: " + e.getMessage());
        }
    }

    @Override
    public void consultarDao(Object... valor) throws SQLException {
        String pesquisa = (String) valor[0];
        DefaultTableModel tabela = (DefaultTableModel) valor[1];
        
        // Limpa a tabela antes de preencher com novos dados
        tabela.setRowCount(0);

        // O ideal é usar prepared statement para evitar SQL Injection
        sql = "SELECT * FROM cidade WHERE nome LIKE ? ORDER BY nome";

        stm = ConexaoBanco.abreConexao().prepareStatement(sql);
        stm.setString(1, "%" + pesquisa + "%");

        resultado = stm.executeQuery();

        while (resultado.next()) {
            tabela.addRow(new Object[]{
                resultado.getInt("id"),
                resultado.getString("nome"),
                resultado.getString("uf"),
                resultado.getString("cep")
            });
        }

        resultado.close();
        stm.close();
    }

    public void carregarComboBox(JComboBox box) { // Renomeei para o nome que estava no seu original
        sql = "SELECT * FROM cidade ORDER BY nome";
        
        // Limpa o ComboBox antes de carregar
        box.removeAllItems();
        box.addItem("Selecione uma cidade"); // Item opcional

        try {
            stm = ConexaoBanco.abreConexao().prepareStatement(sql);
            resultado = stm.executeQuery();

            while (resultado.next()) {
                CidadeModelo cidade = new CidadeModelo();
                cidade.setId(resultado.getInt("id"));
                cidade.setNome(resultado.getString("nome"));
                cidade.setUf(resultado.getString("uf"));
                cidade.setCep(resultado.getString("cep"));

                // Adiciona o objeto inteiro ao ComboBox
                box.addItem(cidade);
            }

            resultado.close();
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar cidades no ComboBox: " + e.getMessage());
        }
    }

    @Override
    public void carregarDao(JComboBox itens) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}