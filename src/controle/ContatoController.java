package controle;

import dao.ContatoDao;
import interfaces.InterfaceControle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ContatoModelo;

/**
 * Controller responsável por intermediar as ações da CidadeVisao
 * e as operações de persistência de dados do CidadeDao.
 */
public class ContatoController implements InterfaceControle {

    private final ContatoDao contatoDao = new ContatoDao();

    /**
     * Salva uma cidade no banco de dados.
     * 
     * @param valor Espera os parâmetros na seguinte ordem:
     *              [0] ID (String vazia ou inteiro),
     *              [1] Nome (String),
     *              [2] UF (String),
     *              [3] CEP (String)
     */
    @Override
    public void salvarControle(Object... valor) {
        try {
            ContatoModelo contato = new ContatoModelo();

            int id = valor[0].toString().isEmpty() ? 0 : Integer.parseInt(valor[0].toString());
            contato.setId(id);
            contato.setNome((String) valor[1]);
            contato.setTelefone((String) valor[2]);
            contato.setEmail((String) valor[3]);
            
            contatoDao.salvarDao(contato);
        } catch (Exception e) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, "Erro ao salvar contato", e);
        }
    }

    /**
     * Exclui uma cidade com base no ID.
     * 
     * @param id ID da cidade
     */
    @Override
    public void excluirControle(int id) {
        try {
            contatoDao.excluirDao(id);
        } catch (Exception e) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, "Erro ao excluir contato", e);
        }
    }

    /**
     * Realiza uma consulta no banco de dados.
     * 
     * @param valor Espera os parâmetros na seguinte ordem:
     *              [0] Texto da busca (String),
     *              [1] Modelo da tabela (DefaultTableModel)
     */
    @Override
    public void consultarControle(Object... valor) {
        try {
            contatoDao.consultarDao(valor);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, "Erro ao consultar contato", ex);
        }
    }

    /**
     * Este método da interface não se aplica para cidades.
     * A entidade Cidade não possui ComboBox dependente.
     */
    @Override
    public void carregarComboBox() {
        throw new UnsupportedOperationException("Este método não é aplicável para ContatoController.");
    }
}
