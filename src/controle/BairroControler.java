package controle;

import dao.BairroDao;
import dao.CidadeDao;
import interfaces.InterfaceControle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import modelo.BairroModelo;

public class BairroControler implements InterfaceControle {

    private final BairroDao bairroDao = new BairroDao();
    private final CidadeDao cidadeDao = new CidadeDao();

    @Override
    public void salvarControle(Object... valor) {
        BairroModelo bairro = new BairroModelo();

        try {
            int id = valor[0].toString().isEmpty() ? 0 : Integer.parseInt(valor[0].toString());
            bairro.setId(id);
            bairro.setNome((String) valor[1]);
            bairro.setCidade_id((int) valor[2]);
            bairro.setReferencias((String) valor[3]);

            bairroDao.salvarDao(bairro);
        } catch (Exception e) {
            Logger.getLogger(BairroControler.class.getName()).log(Level.SEVERE, "Erro ao salvar bairro", e);
        }
    }

    @Override
    public void excluirControle(int id) {
        try {
            bairroDao.excluirDao(id);
        } catch (Exception e) {
            Logger.getLogger(BairroControler.class.getName()).log(Level.SEVERE, "Erro ao excluir bairro", e);
        }
    }

<<<<<<< HEAD
    /**
     * Método ajustado para consultar e preencher o DefaultTableModel diretamente
     * 
     * @param filtro Texto para filtro de busca (ex: nome)
     * @param modelo DefaultTableModel que será preenchido para exibição na tabela
     */
    public void consultarControle(String filtro, DefaultTableModel modelo) {
        try {
            bairroDao.consultarDao(filtro, modelo);
=======
    @Override
    public void consultarControle(Object... valor) {
        try {
            bairroDao.consultarDao(valor);
>>>>>>> e31d81004d2b48d840391e7bdfe4e8788b4aa961
        } catch (SQLException ex) {
            Logger.getLogger(BairroControler.class.getName()).log(Level.SEVERE, "Erro ao consultar bairro", ex);
        }
    }

<<<<<<< HEAD
    @Override
    public void consultarControle(Object... valor) {
        throw new UnsupportedOperationException("Use consultarControle(String filtro, DefaultTableModel modelo) para consultas.");
    }

=======
>>>>>>> e31d81004d2b48d840391e7bdfe4e8788b4aa961
    /**
     * Carrega as cidades no JComboBox informado, usando o CidadeDao.
     * 
     * @param box JComboBox a ser populado
     */
    public void carregarCidades(JComboBox box) {
        try {
            cidadeDao.carregarComboBox(box);
        } catch (Exception ex) {
            Logger.getLogger(BairroControler.class.getName()).log(Level.SEVERE, "Erro ao carregar cidades", ex);
        }
    }

    @Override
    public void carregarComboBox() {
        throw new UnsupportedOperationException("Use o método carregarCidades(JComboBox box) para carregar cidades.");
    }
}
