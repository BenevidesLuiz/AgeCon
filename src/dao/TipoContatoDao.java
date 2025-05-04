package dao;

import interfaces.InterfaceDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import modelo.TipoContatoModelo;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class TipoContatoDao implements InterfaceDao{
    
    String sql;
    PreparedStatement stm;
    ResultSet resultado;
    
        
    
    @Override
    public void salvarDao(Object... valor) {
        TipoContatoModelo tcm = (TipoContatoModelo) valor[0];
        
        //descobrir se é uma inclusao ou alteração!
        if(tcm.getId() == 0){
            sql = "INSERT INTO tipo_contato (descricao) VALUES (?)"; 
        }else{
            sql = "UPDATE tipo_contato SET descricao=? WHERE id_tipo_contato=?";        
        }
        
        try{
            stm = ConexaoBanco.abreConexao().prepareStatement(sql);
            
            stm.setString(1, tcm.getDescricao());
            
            if(tcm.getId() > 0) stm.setInt(2, tcm.getId());
            
            
            stm.execute();
            
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Registrado Com Sucesso!!!");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao Registrar! |Mensagem De Erro >> " + e);
        }
        
   }

    @Override
    public void excluirDao(int id) {
   
    }

    @Override
    public void consultarDao(Object... valor) throws SQLException {
        
        
        DefaultTableModel tabela = (DefaultTableModel) valor[1];
        
        if("".equals((String) valor[0])){
          sql = "SELECT * FROM tipo_contato";  
        }else{
            sql = "SELECT * FROM tipo_contato WHERE descricao LIKE '%"+valor[0]+"%'";
        }
        
        stm = ConexaoBanco.abreConexao().prepareStatement(sql);
        resultado = stm.executeQuery(); 
        
        while(resultado.next()){
            tabela.addRow(
                 new Object[]{
                     resultado.getInt("id"),
                     resultado.getString("descricao")
                 }
            );
        }
        stm.close();
    
    }

    @Override
    public void carregarDao(JComboBox itens) throws SQLException {
    
    }
    
}
