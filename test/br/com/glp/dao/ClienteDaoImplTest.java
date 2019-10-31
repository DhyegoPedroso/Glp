package br.com.glp.dao;

import br.com.glp.model.Caminhao;
import br.com.glp.model.Cliente;
import br.com.glp.model.Contato;
import br.com.glp.model.Endereco;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Dhyego Pedroso
 */
public class ClienteDaoImplTest {

    private Cliente cliente;
    private ClienteDao clienteDao;
    private Session session;

    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

    @Test
    public void testsalvar() {
        session = HibernateUtil.abreSessao();
        List<Caminhao> caminhoes = new ArrayList<>();
        List<Endereco> enderecos = new ArrayList<>();

    }

    @Test
    public void testPesquisaEntidadeId() {

        try {
            session = HibernateUtil.abreSessao();

            cliente = clienteDao.pesquisaEntidadeId(6l, session);

            System.out.println("ID: " + cliente.getId());

        } catch (Exception e) {
        }

    }

    @Test
    public void testListaTodos() {
    }

    @Test
    public void testPesquisaPorNome() {
    }

    @Test
    public void testPesquisarCNPJ() {
    }

    @Test
    public void testPesquisarNomeSocial() {
    }

}
