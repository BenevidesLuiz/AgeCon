package visao;

import javax.swing.JComboBox;
import javax.swing.JLabel;


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

    @Override
    public void salvarVisao() {
  
    }

    @Override
    public void criarTabela() {
   /*
            tabela = utilTabela.criarTabela(
                jpnConsulta, 
                new Object[] {60,750},
                new Object[] {"centro","esquerda"},
       dados >>         new Object[] {"ID","Descrição"}
        );
        modelo = (DefaultTableModel) tabela.getModel();    
        
     */
    
    }

    @Override
    public void consultaVisao() {
   
    }
}
