package controle;

import dao.CidadeDao;
import interfaces.InterfaceControle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CidadeModelo;

/**
 * Controller responsável por intermediar as ações da CidadeVisao
 * e as operações de persistência de dados do CidadeDao.
 */
public class CidadeController implements InterfaceControle {

    private final CidadeDao cidadeDao = new CidadeDao();

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
            CidadeModelo cidade = new CidadeModelo();

            int id = valor[0].toString().isEmpty() ? 0 : Integer.parseInt(valor[0].toString());
            cidade.setId(id);
            cidade.setNome((String) valor[1]);
            cidade.setUf((String) valor[2]);
            cidade.setCep((String) valor[3]);

            cidadeDao.salvarDao(cidade);
        } catch (Exception e) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, "Erro ao salvar cidade", e);
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
            cidadeDao.excluirDao(id);
        } catch (Exception e) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, "Erro ao excluir cidade", e);
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
            cidadeDao.consultarDao(valor);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, "Erro ao consultar cidades", ex);
        }
    }

    /**
     * Este método da interface não se aplica para cidades.
     * A entidade Cidade não possui ComboBox dependente.
     */
    @Override
    public void carregarComboBox() {
        throw new UnsupportedOperationException("Este método não é aplicável para CidadeController.");
    }
}
