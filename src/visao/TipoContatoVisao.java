
package visao;

import controle.TipoContatoControler;
import javax.swing.table.DefaultTableModel;

public class TipoContatoVisao extends FormPadrao{
    public TipoContatoVisao(){
    setTitle("Cadastro de Tipos De Contatos");
    consultaVisao();
    }

    @Override
    public void inicializarComponentes() {

    }
    
    TipoContatoControler tcc = new TipoContatoControler();
    
    @Override
    public void salvarVisao() {
        tcc.salvarControle(jtfId.getText(), jtfDescricao.getText());
    }

    @Override
    public void criarTabela() {
        tabela = utilTabela.criarTabela(
                jpnConsulta, 
                new Object[] {60,750},
                new Object[] {"centro","esquerda"},
                new Object[] {"ID","Descrição"}
        );
        modelo = (DefaultTableModel) tabela.getModel();
    }

    @Override
    public void consultaVisao() {
        modelo.setNumRows(0);
        tcc.consultarControle(jtfConsulta.getText(), modelo);
       }

    @Override
    public void atualizarForumulario() {
        jtfId.setText( (String)tabela.getValueAt(tabela.getSelectedRow(), 0).toString() );
        jtfDescricao.setText((String)tabela.getValueAt(tabela.getSelectedRow(), 1).toString() );
    }

    @Override
    public void exluirVisao() {
        tcc.excluirControle( Integer.parseInt(jtfId.getText()));
    }

 
}
