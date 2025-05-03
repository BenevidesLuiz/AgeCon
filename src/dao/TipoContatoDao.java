package dao;

import interfaces.InterfaceDao;
import java.sql.SQLException;
import javax.swing.JComboBox;
import modelo.TipoContatoModelo;

public class TipoContatoDao implements InterfaceDao{

    @Override
    public void salvarDao(Object... valor) {
        TipoContatoModelo tcm = (TipoContatoModelo) valor[0];
        
        
        System.out.println("Estou no Dao salvei no bd"+tcm.getDescricao()); 
   }

    @Override
    public void excluirDao(int id) {
   
    }

    @Override
    public void consultarDao(Object... valor) throws SQLException {
   
    }

    @Override
    public void carregarDao(JComboBox itens) throws SQLException {
    
    }
    
}
