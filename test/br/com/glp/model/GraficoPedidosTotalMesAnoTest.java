package br.com.glp.model;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Dhyego Pedroso
 */
public class GraficoPedidosTotalMesAnoTest {

    private Session session;
    private PedidoDao pedidoDao;
    private GraficoPedidosTotalMesAno totalMesAno;

    public GraficoPedidosTotalMesAnoTest() {
        pedidoDao = new PedidoDaoImpl();
    }

    @Test
    public void testGetMes() {

        Object[] item;
        GraficoPedidosTotalMesAno grt;

        session = HibernateUtil.abreSessao();

        List resultados = pedidoDao.totalMesPedidos(session);
        List<GraficoPedidosTotalMesAno> totalMesAnos = new ArrayList<>();
        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoPedidosTotalMesAno((int) item[0], Integer.parseInt(item[1].toString()));
            totalMesAnos.add(grt);
        }

        System.out.println("------------------");
        System.out.println("------Inicio------");
        System.out.println("------------------");
        System.out.println("\n");

        for (GraficoPedidosTotalMesAno totalMesAno1 : totalMesAnos) {

            System.out.println("Mês: " + totalMesAno1.getMes());
            System.out.println("Quantidade: " + totalMesAno1.getQuantidade());
            System.out.println("\n");
        }
        System.out.println("------------------");
        System.out.println("--------Fim-------");
        System.out.println("------------------");

    }

}
