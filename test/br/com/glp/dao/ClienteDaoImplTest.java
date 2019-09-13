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

        Contato contato = new Contato("(66) 6666-6666", "(66) 66666-6666", "teste06@teste06.com", true);
        Endereco endereco = new Endereco("Endereço 06", 132, "Endereço 06", "Endereço 06", "66666-666", "Endereço 06", "Endereço 06", "Endereço 06");
        Caminhao c1 = new Caminhao("Pedro Endereço 06", "6666666");
        caminhoes.add(c1);
        c1.setEndereco(endereco);
        c1 = new Caminhao("Leo Endereço 06", "6666666");
        caminhoes.add(c1);
        c1.setEndereco(endereco);
        c1 = new Caminhao("Rossi Endereço 06", "6666666");
        caminhoes.add(c1);
        c1.setEndereco(endereco);

        endereco.setContato(contato);
        contato.setEndereco(endereco);
        endereco.setCaminhoes(caminhoes);
        enderecos.add(endereco);

        Contato contato2 = new Contato("(77) 7777-7777", "(77) 77777-7777", "teste07@teste07.com", true);
        Endereco endereco2 = new Endereco("Endereço 07", 132, "Endereço 07", "Endereço 07", "77772-777", "Endereço 07", "Endereço 07", "Endereço 07");
        Caminhao c2 = new Caminhao("Pedro Endereço 07", "7777777");
        caminhoes.add(c2);
        c2.setEndereco(endereco2);
        c2 = new Caminhao("Leo Endereço 07", "7777777");
        caminhoes.add(c2);
        c2.setEndereco(endereco2);
        c2 = new Caminhao("Rossi Endereço 07", "7777777");
        caminhoes.add(c2);
        c2.setEndereco(endereco2);

        endereco2.setContato(contato2);
        contato2.setEndereco(endereco2);
        endereco2.setCaminhoes(caminhoes);
        enderecos.add(endereco2);

        cliente = new Cliente("51.561.561/5615-61", "Empresa Do Cliente");
        cliente.setNome("Cliente06");
        cliente.setDtCadastro(new Date());
        cliente.setEnderecos(enderecos);
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
