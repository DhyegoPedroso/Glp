package br.com.glp.dao;

import br.com.glp.model.Caminhao;
import br.com.glp.model.Cliente;
import br.com.glp.model.Endereco;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Dhyego Pedroso
 */
public class CaminhaoDaoImplTest {

    Session session;
    Caminhao caminhao;
    CaminhaoDao caminhaoDao;

    public CaminhaoDaoImplTest() {
        caminhaoDao = new CaminhaoDaoImpl();
    }

    @Test
    public void testPesquisaEntidadeId() {
    }

    @Test
    public void testListaTodos() {
    }

    @Test
    public void testPesquisaPorNome() {

        try {

            session = HibernateUtil.abreSessao();

            List<Caminhao> caminhoes = new ArrayList<>();

            caminhoes = caminhaoDao.pesquisaCaminhaoCliente(3l, session);

            System.out.println("-------------------------------------------");
            System.out.println("-----------Resultado da Pesquisa-----------");
            System.out.println("-------------------------------------------");
            for (Caminhao caminhoe : caminhoes) {

                System.out.println("\n");

                System.out.println("Motorista: " + caminhoe.getNomeMotorista());
                System.out.println("Placa: " + caminhoe.getPlacaCaminhao());

            }
            System.out.println("\n");
            System.out.println("-------------------------------------------");
            System.out.println("--------------Fim da Pesquisa--------------");
            System.out.println("-------------------------------------------");

        } catch (Exception e) {
            session.close();
        }
    }

    @Test
    public void testPesquisaPlaca() {

        try {

            session = HibernateUtil.abreSessao();
            
            Cliente cliente = new Cliente();
            cliente.setId(19l);

            caminhao = new Caminhao("Teste 69", "ABC6969");
            Endereco endereco = new Endereco();
            endereco.setId(15l);
            caminhao.setEndereco(endereco);

            if (cliente.getId() != null) {
                caminhaoDao.salvarCaminhao(caminhao, session);
            }

        } catch (Exception e) {
            session.close();
        }
    }

    @Test
    public void testPesquisaCaminhaoCliente() {
    }

}
