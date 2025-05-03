
package visao;

import controle.TipoContatoControler;

public class TipoContatoVisao extends FormPadrao{
    public TipoContatoVisao(){
    setTitle("Cadastro de Tipos De Contatos");
    }

    @Override
    public void inicializarComponentes() {

    }
    
    TipoContatoControler tcc = new TipoContatoControler();
    
    @Override
    public void salvarVisao() {
        tcc.salvarControle(jtfId.getText(), jtfDescricao.getText());
    }
}
