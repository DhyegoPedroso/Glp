package br.com.glp.dao;

import br.com.glp.model.Cliente;
import br.com.glp.model.Contato;
import br.com.glp.model.Endereco;
import java.util.Date;
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

        Contato contato = new Contato("5156156156", "51898886156", "teste@teste.com", true);
        Endereco endereco = new Endereco("Rua", 132, "Complemento", "bairro", "88811111", "cidade", "uf", "pais");

        cliente = new Cliente("44515654841502", "Teste salvar Cliente", "Teste", endereco, contato, new Date());

        cliente.setEndereco(endereco);
        endereco.setPessoa(cliente);
        cliente.setContato(contato);
        contato.setPessoa(cliente);

        clienteDao.salvarOuAlterar(cliente, session);

    }

    @Test
    public void testPesquisaEntidadeId() {
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
