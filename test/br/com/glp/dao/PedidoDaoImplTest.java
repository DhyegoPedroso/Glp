package br.com.glp.dao;

import br.com.glp.model.Pedido;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
        try {

            session = HibernateUtil.abreSessao();

            List<Pedido> pedidos = pedidoDao.listaTodos(session);

            for (Pedido pedido1 : pedidos) {

                System.out.println("Cliente: " + pedido1.getCliente().getNomeSocial());
                System.out.println("data: " + pedido1.getCadastro());

            }

        } catch (Exception e) {
        }
    }

    @Test
    public void testPesquisaPorNome() {
    }

    @Test
    public void testPesquisaPedidoNomeSocial() {
    }

    @Test
    public void testMaxQtdePedidoAno() {
        
        session = HibernateUtil.abreSessao();
        
        BigInteger qtde = pedidoDao.totalQtdeMaxPedido(session);
        
        Long total = qtde.longValue();
        
        System.out.println("qtde "+total);
    }

    @Test
    public void testListarTodosPedidoDataInicioFim() {

        try {

            session = HibernateUtil.abreSessao();

            Date inicio = new Date(02 - 11 - 19);
            Date fim = new Date(06 - 11 - 19);

            List<Pedido> pedidos = pedidoDao.listarTodosPedidoDataInicioFim(inicio, fim, session);

            for (Pedido pedido1 : pedidos) {

                System.out.println("Cliente: " + pedido1.getCliente().getNomeSocial());
                System.out.println("data: " + pedido1.getCadastro());

            }

        } catch (Exception e) {
        }

    }

    @Test
    public void testTotalMesPedidos() {
    }

    @Test
    public void testTotalPedidoMesAtual() {
        
        
    }
    

    @Test
    public void testTotalPediAno() {
        
        session = HibernateUtil.abreSessao();
        
        BigInteger qtde = pedidoDao.totalPedidoAno(session);
        
        System.out.println(qtde);
    }
    
    

}
