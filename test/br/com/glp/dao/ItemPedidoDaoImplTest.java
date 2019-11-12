package br.com.glp.dao;

import br.com.glp.model.GraficoProdutosTotalMesAno;
import java.util.ArrayList;
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
    public void testListarTodosProdutosDataInicioFim() {
    }

    @Test
    public void testTotalMesProdutos() {

        Object[] item;
        GraficoProdutosTotalMesAno grt;


            session = HibernateUtil.abreSessao();

            List resultados = itemPedidoDao.totalMesProdutos(session);
            List<GraficoProdutosTotalMesAno> totalMesAnos = new ArrayList<>();
            for (Object resultado : resultados) {
                item = (Object[]) resultado;
                grt = new GraficoProdutosTotalMesAno((int) item[0], (String) item[1], (long) item[2]);
                totalMesAnos.add(grt);
            }

            System.out.println("------------------");
            System.out.println("------Inicio------");
            System.out.println("------------------");
            System.out.println("\n");

            for (GraficoProdutosTotalMesAno totalMesAno1 : totalMesAnos) {

                System.out.println("Mês: " + totalMesAno1.getMes());
                System.out.println("Quantidade: " + totalMesAno1.getQuantidade());
                System.out.println("\n");
            }
            System.out.println("------------------");
            System.out.println("--------Fim-------");
            System.out.println("------------------");

    }

}
