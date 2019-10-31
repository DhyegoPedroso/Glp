package br.com.glp.dao;

import br.com.glp.model.Pedido;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Dhyego Pedroso
 */
public class PedidoDaoImplTest {

    Session session;
    Pedido pedido;
    PedidoDao pedidoDao;

    public PedidoDaoImplTest() {
        pedidoDao = new PedidoDaoImpl();
    }

    @Test
    public void testSalvar() {

        try {
            session = HibernateUtil.abreSessao();

            List<Pedido> pedidos = new ArrayList<>();

            pedidoDao = new PedidoDaoImpl();

            pedidos = pedidoDao.pesquisaPedidoNomeSocial("Drag", session);

            System.out.println("-------------------------------------------");
            System.out.println("-----------Resultado da Pesquisa-----------");
            System.out.println("-------------------------------------------");

            for (Pedido pedido1 : pedidos) {

                System.out.println("\n");

                System.out.println("Nome Social: " + pedido1.getCliente().getNomeSocial());
                System.out.println("CNPJ: " + pedido1.getCliente().getCnpj());
                System.out.println("Nota Fiscal: " + pedido1.getNotaFiscal());
                System.out.println("Data do Pedido: " + pedido1.getCadastro());
                System.out.println("Motorista: " + pedido1.getCaminhao().getNomeMotorista());
                System.out.println("Placa: " + pedido1.getCaminhao().getPlacaCaminhao());
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
    public void testPesquisaEntidadeId() {

        try {

            session = HibernateUtil.abreSessao();
            pedido = pedidoDao.pesquisaEntidadeId(10L, session);
            System.out.println("Cliente: " + pedido.getId());

        } catch (Exception e) {
            session.close();
        }

    }

    @Test
    public void testListaTodos() {
    }

    @Test
    public void testPesquisaPorNome() {
    }

}
