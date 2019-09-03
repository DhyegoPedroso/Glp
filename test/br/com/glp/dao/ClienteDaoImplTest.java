package br.com.glp.dao;

import br.com.glp.model.Caminhao;
import br.com.glp.model.Cliente;
import java.util.ArrayList;
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

//        Contato contato = new Contato(1, "(51) 3038-6156", "(51) 89888-6156", "teste@teste.com", true);
//        Contato contato2 = new Contato(1, "(51) 3038-6156", "(51) 89888-6156", "teste@teste.com", true);
//        Endereco endereco = new Endereco(2, "Rua", 132, "Complemento", "bairro", "88811111", "cidade", "uf", "pais");
//        Endereco endereco2 = new Endereco(2, "Rua", 132, "Complemento", "bairro", "88811111", "cidade", "uf", "pais");

        List<Caminhao> caminhoes = new ArrayList<>();

//        cliente = new Cliente("51.561.561/5615-61", "Empresa Do Cliente", "Cliente", endereco, contato, new Date());

//        Caminhao c1 = new Caminhao(1, "Pedro Carreteiro", "AB1C234", cliente);
//        Caminhao c2 = new Caminhao(1, "Leo Batuqueiro", "AB1C234", cliente);
//        Caminhao c3 = new Caminhao(1, "Rossi Italiano", "AB1C234", cliente);
//        caminhoes.add(c1);
//        caminhoes.add(c2);
//        caminhoes.add(c3);

//        cliente.setEndereco(endereco);
//        endereco.setPessoa(cliente);
//        cliente.setContato(contato);
//        contato.setPessoa(cliente);
//        cliente.setCaminhoes(caminhoes);

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
