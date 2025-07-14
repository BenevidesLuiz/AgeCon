
package controle;

import dao.TipoContatoDao;
import interfaces.InterfaceControle;
import modelo.TipoContatoModelo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TipoContatoControler implements InterfaceControle{

    TipoContatoModelo tcm = new TipoContatoModelo(); 
    TipoContatoDao tcd = new TipoContatoDao();
    
    
    @Override
    public void salvarControle(Object... valor) {
       if("".equals(valor[0])){
           tcm.setId(0);
       }else{
            tcm.setId(Integer.parseInt(valor[0].toString()));
       }
       
       tcm.setDescricao((String) valor[1]);
       
       tcd.salvarDao(tcm);
    
    }

    @Override
    public void excluirControle(int id) {
        tcd.excluirDao(id);
    }

    @Override
    public void carregarComboBox() {

    }

    @Override
    public void consultarControle(Object... valor) {
        try {
            tcd.consultarDao(valor);
        } catch (SQLException e) {
            Logger.getLogger(TipoContatoControler.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
}
