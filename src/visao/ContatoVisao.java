package visao;

import controle.ContatoController;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ContatoVisao extends FormPadrao {

    private JLabel jlTelefone, jlEmail;
    private JTextField jtfTelefone, jtfEmail;

    private final ContatoController contatoController = new ContatoController();

    public ContatoVisao() {
        setTitle("Cadastro de Contatos");
        consultaVisao();
    }

    @Override
    public void limparCampos() {
        super.limparCampos(); // limpa jtfDescricao e jtfId
        jtfTelefone.setText("");
        jtfEmail.setText("");
    }

    @Override
    public void inicializarComponentes() {
        // Campo Telefone
        jlTelefone = new JLabel("Telefone");
        jlTelefone.setBounds(10, 85, 100, 25);
        jpnForumulario.add(jlTelefone);

        jtfTelefone = new JTextField();
        jtfTelefone.setBounds(10, 105, 150, 25);
        jpnForumulario.add(jtfTelefone);

        // Campo Email
        jlEmail = new JLabel("Email");
        jlEmail.setBounds(200, 85, 100, 25);
        jpnForumulario.add(jlEmail);

        jtfEmail = new JTextField();
        jtfEmail.setBounds(200, 105, 250, 25);
        jpnForumulario.add(jtfEmail);
    }

    @Override
    public void salvarVisao() {
        String nome = jtfDescricao.getText().trim();
        String telefone = jtfTelefone.getText().trim();
        String email = jtfEmail.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo nome é obrigatório.");
            jtfDescricao.requestFocus();
            return;
        }

        if (!telefone.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(this, "Telefone inválido. Digite 10 ou 11 números.");
            jtfTelefone.requestFocus();
            return;
        }

        if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")) {
            JOptionPane.showMessageDialog(this, "Email inválido.");
            jtfEmail.requestFocus();
            return;
        }

        contatoController.salvarControle(
            jtfId.getText(),
            nome,
            telefone,
            email
        );

        consultaVisao();
    }

    @Override
    public void exluirVisao() {
        contatoController.excluirControle(Integer.parseInt(jtfId.getText()));
        consultaVisao();
    }

    @Override
    public void criarTabela() {
        tabela = utilTabela.criarTabela(
            jpnConsulta,
            new Object[]{60, 200, 150, 250}, // ID, Nome, Telefone, Email
            new Object[]{"center", "left", "left", "left"},
            new Object[]{"ID", "Nome", "Telefone", "Email"}
        );
        modelo = (DefaultTableModel) tabela.getModel();
    }

    @Override
    public void consultaVisao() {
        modelo.setNumRows(0);
        contatoController.consultarControle(jtfConsulta.getText(), modelo);
    }

    @Override
    public void atualizarForumulario() {
        jtfId.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        jtfDescricao.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        jtfTelefone.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        jtfEmail.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
    }
}
