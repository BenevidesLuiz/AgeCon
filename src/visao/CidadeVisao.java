package visao;

import controle.CidadeController;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CidadeVisao extends FormPadrao {

    private JLabel jlUf;
    private JLabel jlCep;
    private JComboBox<String> jcbUf;
    private JTextField jtfCep;

    // 1. Controller instanciado, igual ao seu exemplo
    private final CidadeController cidadeControler = new CidadeController();

    public CidadeVisao() {
        setTitle("Cadastro de Cidades");
        consultaVisao(); // Preenche a tabela ao iniciar
    }
    
    @Override
    public void limparCampos() {
        super.limparCampos(); // limpa jtfDescricao e jtfId
        jtfCep.setText("");   // limpa o campo de CEP
        jcbUf.setSelectedIndex(0); // volta o comboBox para "AC" (ou o primeiro estado)
}


    @Override
    public void inicializarComponentes() {
        // O campo 'Nome' (jtfDescricao) já vem do FormPadrao
        
        // Campo UF (sem mudar)
        jlUf = new JLabel("UF");
        jlUf.setBounds(410, 35, 50, 25);
        jpnForumulario.add(jlUf);

        String[] ufs = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        jcbUf = new JComboBox<>(ufs);
        jcbUf.setBounds(410, 55, 100, 25);
        jpnForumulario.add(jcbUf);

        // Campo CEP
        jlCep = new JLabel("CEP");
        jlCep.setBounds(10, 85, 100, 25);
        jpnForumulario.add(jlCep);

        jtfCep = new JTextField();
        jtfCep.setBounds(10, 105, 150, 25);

        jpnForumulario.add(jtfCep);

        jpnForumulario.add(jtfCep);;

    }

    @Override
    public void salvarVisao() {
        String cep = jtfCep.getText().trim();

        // Validação do CEP: deve ter exatamente 8 dígitos numéricos
        if (!cep.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "CEP inválido. Digite exatamente 8 números (ex: 12345678).");
            jtfCep.requestFocus();
            return;
        }

        // Chama o controller para salvar os dados
        cidadeControler.salvarControle(
            jtfId.getText(),
            jtfDescricao.getText(), // Nome da Cidade
            jcbUf.getSelectedItem().toString(),
            cep
        );

    consultaVisao(); // Atualiza a tabela
}

    
    @Override
    public void exluirVisao() {
        // 3. Chama o controller para excluir
        cidadeControler.excluirControle(Integer.parseInt(jtfId.getText()));
        consultaVisao(); // Atualiza a tabela
    }

    @Override
    public void criarTabela() {
        // 4. Cria a tabela com as colunas para Cidade
        tabela = utilTabela.criarTabela(
                jpnConsulta,
                new Object[]{60, 400, 80, 150}, // Larguras: ID, Nome, UF, CEP
                new Object[]{"center", "left", "center", "left"}, // Alinhamentos
                new Object[]{"ID", "Nome", "UF", "CEP"} // Títulos
        );
        modelo = (DefaultTableModel) tabela.getModel();
    }

    @Override
    public void consultaVisao() {
        // 5. Limpa a tabela e chama o controller para consultar
        modelo.setNumRows(0);
        // O controller receberá o texto da busca e o modelo da tabela
        cidadeControler.consultarControle(jtfConsulta.getText(), modelo);
    }

    @Override
    public void atualizarForumulario() {
        // 6. Preenche os campos do formulário com os dados da tabela
        // Coluna 0 = ID, Coluna 1 = Nome, Coluna 2 = UF, Coluna 3 = CEP
        jtfId.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        jtfDescricao.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        jcbUf.setSelectedItem(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        jtfCep.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
    }
}