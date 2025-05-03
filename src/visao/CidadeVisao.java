
package visao;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CidadeVisao extends FormPadrao{
    JLabel jlUf;
    JLabel jlCep;
    
    JComboBox jcbUf;
    JTextField jtfCep;        
            
            
    
    public CidadeVisao(){
    setTitle("Cadastro de Cidades");
    }

    @Override
    public void inicializarComponentes() {
        jlUf = new JLabel("UF");
        jlUf.setBounds(9, 60, 50, 25);
        jpnForumulario.add(jlUf);
        
        jcbUf = new JComboBox();
        jcbUf.setBounds(9, 80, 70, 25);
        jpnForumulario.add(jcbUf);
        
        jlCep = new JLabel("CEP");
        jlCep.setBounds(90, 60, 100, 25);
        jpnForumulario.add(jlCep);
        
        jtfCep = new JTextField();
        jtfCep.setBounds(90, 80, 150, 25);
        jpnForumulario.add(jtfCep);
                
    }

    @Override
    public void salvarVisao() {
   
    }
}
