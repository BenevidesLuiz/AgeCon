package visao;

public class ContatoVisao extends FormPadrao{
    
    public ContatoVisao(){
    setTitle("Cadastro de Contatos");
    }

    @Override
    public void inicializarComponentes() {

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
        campos::        new Object[] {"ID","Descrição"}
        );
        modelo = (DefaultTableModel) tabela.getModel();
        */
    
    }

    @Override
    public void consultaVisao() {
        modelo.setNumRows(0);
    }
}
