package visao;

import controle.BairroControler;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class BairroVisao extends FormPadrao {

    private JLabel jlCidade;
    private JLabel jlReferencias;
    private JComboBox<String> jcbCidade;
    private JTextArea jtaReferencias;

    // Instancia do controlador (garanta que está aqui)
    private final BairroControler bc = new BairroControler();

    public BairroVisao() {
        setTitle("Cadastro de Bairros");
        consultaVisao();          // Atualiza a tabela
        carregarComboCidades();   // Carrega o combo depois que tudo foi criado
    }

    @Override
    public void limparCampos() {
        super.limparCampos();  // Limpa jtfDescricao e jtfId
        jcbCidade.setSelectedIndex(0);
        jtaReferencias.setText("");
    }

    @Override
    public void inicializarComponentes() {
    // O campo 'Nome' (jtfDescricao) já vem do FormPadrao
    // Ajustando a posição dos campos para melhor visualização
    
    // Campo ID já vem do FormPadrao - reposicionar
    
    // Label e Combo Cidade
    jlCidade = new JLabel("Cidade");
    jlCidade.setBounds(10, 55, 100, 25);
    jpnForumulario.add(jlCidade);

    jcbCidade = new JComboBox<>();
    jcbCidade.setBounds(10, 72, 300, 25);
    jpnForumulario.add(jcbCidade);

    // Label e TextArea Referências
    jlReferencias = new JLabel("Referências");
    jlReferencias.setBounds(10, 100, 100, 25);
    jpnForumulario.add(jlReferencias);

    jtaReferencias = new JTextArea();
    jtaReferencias.setLineWrap(true);
    jtaReferencias.setWrapStyleWord(true);
    jtaReferencias.setBounds(3, 120, 600, 30);
    jpnForumulario.add(jtaReferencias);
    }


    @Override
    public void salvarVisao() {
        // Validação básica
        if (jtfDescricao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome do bairro é obrigatório.");
            jtfDescricao.requestFocus();
            return;
        }

        if (jcbCidade.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma cidade.");
            jcbCidade.requestFocus();
            return;
        }

        // Passa os valores para o controller
        bc.salvarControle(
                jtfId.getText(),
                jtfDescricao.getText(),
                jcbCidade.getSelectedIndex(), // Você pode precisar passar o ID correto, veja o ajuste abaixo
                jtaReferencias.getText()
        );

        consultaVisao(); // Atualiza tabela
    }

    @Override
    public void exluirVisao() {
        if (jtfId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um bairro para excluir.");
            return;
        }

        bc.excluirControle(Integer.parseInt(jtfId.getText()));
        consultaVisao();
    }

    @Override
    public void criarTabela() {
        tabela = utilTabela.criarTabela(
                jpnConsulta,
                new Object[]{60, 300, 100, 400},
                new Object[]{"center", "left", "center", "left"},
                new Object[]{"ID", "Nome", "Cidade_ID", "Referências"}
        );
        modelo = (DefaultTableModel) tabela.getModel();
    }

    @Override
    public void consultaVisao() {
        modelo.setNumRows(0);
        bc.consultarControle(jtfConsulta.getText(), modelo);
    }

    @Override
    public void atualizarForumulario() {
        if (tabela.getSelectedRow() < 0) return;

        jtfId.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        jtfDescricao.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        // Ajuste para setar a cidade correta no combo (precisa de um método que localize índice pelo ID)
        int cidadeId = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        selecionarCidadePorId(cidadeId);
        jtaReferencias.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
    }

    // Método para carregar cidades no combo, chamado no construtor
    public void carregarComboCidades() {
        bc.carregarCidades(jcbCidade);
    }

    // Método para selecionar cidade no combo pelo ID (supondo que os itens do combo sejam IDs ou você pode criar um Map)
    private void selecionarCidadePorId(int id) {
        for (int i = 0; i < jcbCidade.getItemCount(); i++) {
            // Supondo que o item do combo seja um objeto ou string que contenha o id
            // Aqui um exemplo simples se o combo armazenar strings "ID - Nome"
            String item = jcbCidade.getItemAt(i);
            if (item.startsWith(id + " -")) {
                jcbCidade.setSelectedIndex(i);
                return;
            }
        }
        jcbCidade.setSelectedIndex(-1);
    }
}
