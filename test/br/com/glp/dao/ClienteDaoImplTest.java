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

        Contato contato = new Contato("(11) 1111-1111", "(11) 11111-1111", "teste01@teste01.com", true);
        Endereco endereco = new Endereco("Endereço 01", 132, "Endereço 01", "Endereço 01", "11111-111", "Endereço 01", "Endereço 01", "Endereço 01");
        Caminhao c1 = new Caminhao("Pedro Endereço 01", "1111111");
        caminhoes.add(c1);
        c1.setEndereco(endereco);
        c1 = new Caminhao("Leo Endereço 01", "1111111");
        caminhoes.add(c1);
        c1.setEndereco(endereco);
        c1 = new Caminhao("Rossi Endereço 01", "1111111");
        caminhoes.add(c1);
        c1.setEndereco(endereco);

        //Contato e Endereço
        endereco.setContato(contato);
        contato.setEndereco(endereco);

        //Endereço e Caminhão
        endereco.setCaminhoes(caminhoes);
        enderecos.add(endereco);

        Contato contato2 = new Contato("(22) 2222-2222", "(22) 22222-2222", "teste02@teste02.com", true);
        Endereco endereco2 = new Endereco("Endereço 02", 132, "Endereço 02", "Endereço 02", "22222-222", "Endereço 02", "Endereço 02", "Endereço 02");
        Caminhao c2 = new Caminhao("Pedro Endereço 02", "2222222");
        caminhoes.add(c2);
        c2.setEndereco(endereco2);
        c2 = new Caminhao("Leo Endereço 02", "2222222");
        caminhoes.add(c2);
        c2.setEndereco(endereco2);
        c2 = new Caminhao("Rossi Endereço 02", "2222222");
        caminhoes.add(c2);
        c2.setEndereco(endereco2);

        //Contato e Endereço
        endereco2.setContato(contato2);
        contato2.setEndereco(endereco2);

        //Endereço e Caminhão
        endereco2.setCaminhoes(caminhoes);
        enderecos.add(endereco2);

        cliente = new Cliente("51.561.561/5615-61", "Empresa Do Cliente");
        cliente.setNome("Cliente");
        cliente.setDtCadastro(new Date());
        cliente.setEnderecos(enderecos);

        //Endereço e Cliente
        endereco.setCliente(cliente);
        endereco2.setCliente(cliente);

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
