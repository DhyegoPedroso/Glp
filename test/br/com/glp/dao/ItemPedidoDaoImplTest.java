package br.com.glp.dao;

import br.com.glp.model.GraficoProdutosTotalMesAno;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Dhyego Pedroso
 */
public class ItemPedidoDaoImplTest {

    private Session session;
    private ItemPedidoDao itemPedidoDao;

    public ItemPedidoDaoImplTest() {
        itemPedidoDao = new ItemPedidoDaoImpl();
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
    public void testPesquisaProdutoDataInicioFim() {
    }

    @Test
    public void testMaxQtdeProduto() {

        session = HibernateUtil.abreSessao();

        BigInteger qtde = itemPedidoDao.totalQtdeMaxProduto(session);

        System.out.println("Qtde: " + qtde);

    }

    @Test
    public void testTotalMesProdutos() {

        Object[] item;
        GraficoProdutosTotalMesAno grt;

        session = HibernateUtil.abreSessao();

        List resultados = itemPedidoDao.totalMesProdutos("P13", session);
        List<GraficoProdutosTotalMesAno> totalMesAnos = new ArrayList<>();
        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoProdutosTotalMesAno((int) item[0], (String) item[1], (int) item[2]);
            totalMesAnos.add(grt);
        }

        System.out.println("------------------");
        System.out.println("------Inicio------");
        System.out.println("------------------");
        System.out.println("\n");

        for (GraficoProdutosTotalMesAno totalMesAno1 : totalMesAnos) {

            System.out.println("Mês: " + totalMesAno1.getMes());
            System.out.println("Produto: " + totalMesAno1.getProduto());
            System.out.println("Quantidade: " + totalMesAno1.getQuantidade());
            System.out.println("\n");
        }
        System.out.println("------------------");
        System.out.println("--------Fim-------");
        System.out.println("------------------");

        resultados = itemPedidoDao.totalMesProdutos("P20", session);
        totalMesAnos = new ArrayList<>();
        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoProdutosTotalMesAno((int) item[0], (String) item[1], (int) item[2]);
            totalMesAnos.add(grt);
        }

        System.out.println("------------------");
        System.out.println("------Inicio------");
        System.out.println("------------------");
        System.out.println("\n");

        for (GraficoProdutosTotalMesAno totalMesAno1 : totalMesAnos) {

            System.out.println("Mês: " + totalMesAno1.getMes());
            System.out.println("Produto: " + totalMesAno1.getProduto());
            System.out.println("Quantidade: " + totalMesAno1.getQuantidade());
            System.out.println("\n");
        }
        System.out.println("------------------");
        System.out.println("--------Fim-------");
        System.out.println("------------------");

        resultados = itemPedidoDao.totalMesProdutos("P45", session);
        totalMesAnos = new ArrayList<>();
        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoProdutosTotalMesAno((int) item[0], (String) item[1], (int) item[2]);
            totalMesAnos.add(grt);
        }

        System.out.println("------------------");
        System.out.println("------Inicio------");
        System.out.println("------------------");
        System.out.println("\n");

        for (GraficoProdutosTotalMesAno totalMesAno1 : totalMesAnos) {

            System.out.println("Mês: " + totalMesAno1.getMes());
            System.out.println("Produto: " + totalMesAno1.getProduto());
            System.out.println("Quantidade: " + totalMesAno1.getQuantidade());
            System.out.println("\n");
        }
        System.out.println("------------------");
        System.out.println("--------Fim-------");
        System.out.println("------------------");

    }

    @Test
    public void testTotalProdutoAno() {

        session = HibernateUtil.abreSessao();

        BigInteger qtde = itemPedidoDao.totalProdutoAno(session);

        System.out.println(qtde);
    }

    @Test
    public void testQtdemaxSituacai() {

        session = HibernateUtil.abreSessao();

        BigInteger qtde = itemPedidoDao.totalQtdeMaxProduto(session);

        System.out.println(qtde);
    }

}
