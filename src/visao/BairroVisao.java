package visao;

import controle.BairroControler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.CidadeModelo;

public class BairroVisao extends FormPadrao {

    private JLabel jlCidade, jlReferencias;
    private JComboBox<CidadeModelo> jcbCidade;
    private JTextArea jtaReferencias;

    private final BairroControler bc = new BairroControler();

    public BairroVisao() {
        setTitle("Cadastro de Bairros");
        inicializarComponentes();
        criarTabela();
        carregarComboCidades();
        consultaVisao();
    }

    @Override
    public void inicializarComponentes() {
        // Cidade
        jlCidade = new JLabel("Cidade");
        jlCidade.setBounds(10, 60, 100, 25);
        jpnForumulario.add(jlCidade);

        jcbCidade = new JComboBox<>();
        jcbCidade.setBounds(10, 80, 200, 25);
        jpnForumulario.add(jcbCidade);

        // Referências
        jlReferencias = new JLabel("Referências");
        jlReferencias.setBounds(220, 60, 100, 25);
        jpnForumulario.add(jlReferencias);

        jtaReferencias = new JTextArea();
        jtaReferencias.setLineWrap(true);
        jtaReferencias.setWrapStyleWord(true);
        jtaReferencias.setBounds(220, 80, 300, 60);
        jpnForumulario.add(jtaReferencias);
    }

    @Override
    public void limparCampos() {
        super.limparCampos();
        jcbCidade.setSelectedIndex(0);
        jtaReferencias.setText("");
    }

    @Override
    public void salvarVisao() {
        if (jtfDescricao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome do bairro é obrigatório.");
            jtfDescricao.requestFocus();
            return;
        }

        if (jcbCidade.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma cidade.");
            jcbCidade.requestFocus();
            return;
        }

        CidadeModelo cidadeSelecionada = (CidadeModelo) jcbCidade.getSelectedItem();

        bc.salvarControle(
            jtfId.getText(),
            jtfDescricao.getText(),
            cidadeSelecionada.getId(),
            jtaReferencias.getText()
        );

        consultaVisao();
    }

    @Override
    public void exluirVisao() {
        if (jtfId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um bairro para excluir.");
            return;
        }

        bc.excluirControle(Integer.parseInt(jtfId.getText()));
        consultaVisao();
        limparCampos();
    }

    @Override
    public void criarTabela() {
        tabela = utilTabela.criarTabela(
            jpnConsulta,
            new Object[]{60, 300, 100, 400},
            new Object[]{"center", "left", "center", "left"},
            new Object[]{"ID", "Nome", "Cidade", "Referências"}
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
        jtaReferencias.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());

        String nomeCidade = tabela.getValueAt(tabela.getSelectedRow(), 2).toString();
        selecionarCidadePorNome(nomeCidade);
    }

    private void carregarComboCidades() {
        bc.carregarCidades(jcbCidade);
    }

    private void selecionarCidadePorNome(String nomeCidade) {
        for (int i = 0; i < jcbCidade.getItemCount(); i++) {
            CidadeModelo item = jcbCidade.getItemAt(i);
            if (item.getNome().equalsIgnoreCase(nomeCidade)) {
                jcbCidade.setSelectedIndex(i);
                return;
            }
        }
    }
}
