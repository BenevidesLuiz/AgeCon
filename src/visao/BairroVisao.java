package visao;

import controle.BairroControler;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.CidadeModelo; // IMPORTANTE: Adicione este import

public class BairroVisao extends FormPadrao {

    JLabel jlCidade, jlReferencias;
    JComboBox jcbCidade; // Remova o <String> para podermos adicionar objetos CidadeModelo
    JTextField jtfReferencias;

    // 1. APENAS DECLARE A VARIÁVEL AQUI
    BairroControler bc;

    public BairroVisao() {
        // 2. INICIALIZE O CONTROLLER AQUI, ANTES DE TUDO
        this.bc = new BairroControler();
        
        setTitle("Cadastro de Bairros");
        
        // Agora o restante do código pode ser executado com segurança
        consultaVisao();
    }

    @Override
    public void inicializarComponentes() {
        // ... (código dos JLabels e JTextFields) ...
        jlCidade = new JLabel("Cidade");
        jlCidade.setBounds(10, 60, 100, 25);
        jpnForumulario.add(jlCidade);
        
        jcbCidade = new JComboBox(); // Sem <String>
        jcbCidade.setBounds(10, 80, 200, 25);
        jpnForumulario.add(jcbCidade);
        
        // Esta linha agora funcionará!
        bc.carregarCidades(jcbCidade); 

        // ... (resto do código do método) ...
        jlReferencias = new JLabel("Referências");
        jlReferencias.setBounds(220, 60, 100, 25);
        jpnForumulario.add(jlReferencias);

        jtfReferencias = new JTextField();
        jtfReferencias.setBounds(220, 80, 300, 25);
        jpnForumulario.add(jtfReferencias);
    }
    
    
    @Override
    public void salvarVisao() {
        // Pega o objeto CidadeModelo selecionado no ComboBox
        CidadeModelo cidadeSelecionada = (CidadeModelo) jcbCidade.getSelectedItem();
        
        bc.salvarControle(
            jtfId.getText(),
            jtfDescricao.getText(),
            cidadeSelecionada.getId(), // Envia o ID da cidade, não o nome!
            jtfReferencias.getText()
        );
        consultaVisao();
    }
    
    @Override
    public void atualizarForumulario() {
        jtfId.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        jtfDescricao.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        jtfReferencias.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
        
        // Lógica para selecionar a cidade correta no ComboBox
        int idCidadeTabela = (int) tabela.getValueAt(tabela.getSelectedRow(), 2);
        for (int i = 0; i < jcbCidade.getItemCount(); i++) {
            CidadeModelo cidadeItem = (CidadeModelo) jcbCidade.getItemAt(i);
            if (cidadeItem.getId() == idCidadeTabela) {
                jcbCidade.setSelectedIndex(i);
                break;
            }
        }
    }

    @Override
    public void criarTabela() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void consultaVisao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void exluirVisao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}