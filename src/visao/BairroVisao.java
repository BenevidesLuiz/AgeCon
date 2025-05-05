package visao;

import controle.BairroControler;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;


public class BairroVisao extends FormPadrao{
     
    JLabel jlBairro;
    JComboBox jcbBairro;
    
    
    public BairroVisao(){
        setTitle("Cadastro de Bairros");    
    }

    
    @Override
    public void inicializarComponentes() {
        jlBairro = new JLabel("Cidade");
        jlBairro.setBounds(9, 60, 50, 25);
        jpnForumulario.add(jlBairro);
        
        jcbBairro = new JComboBox();
        jcbBairro.setBounds(9, 80, 250, 25);
        jpnForumulario.add(jcbBairro);
    }
    
   BairroControler bc = new BairroControler();
    
    
    
    @Override
    public void salvarVisao() {
        //bc.salvarControle(jtfId.getText(), jtfDescricao.getText());
    }

    @Override
    public void criarTabela() {
   
            tabela = utilTabela.criarTabela(
    jpnConsulta, 
    new Object[] {60, 250, 150, 350}, // Larguras das colunas: id, nome, cidade_id, referencias
    new Object[] {"centro", "esquerda", "centro", "esquerda"}, // Alinhamentos
    new Object[] {"ID", "Nome", "Cidade ID", "Referências"} // Cabeçalhos
    );

    modelo = (DefaultTableModel) tabela.getModel(); 
    
    }

    @Override
    public void consultaVisao() {
        modelo.setNumRows(0);
       // tcc.consultarControle(jtfConsulta.getText(), modelo);
    }

    @Override
    public void atualizarForumulario() {
    
    }

    @Override
    public void exluirVisao() {
    
    }
}
