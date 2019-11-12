package br.com.glp.controller;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.ItemPedidoDao;
import br.com.glp.dao.ItemPedidoDaoImpl;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.dao.ProdutoDaoImpl;
import br.com.glp.model.GraficoPedidosTotalMesAno;
import br.com.glp.model.GraficoProdutosTotalMesAno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Dhyego Pedroso
 */
@ManagedBean(name = "dashboardC")
@ViewScoped
public class DashboardController implements Serializable {

    private PedidoDao pedidoDao;
    private ItemPedidoDao itemPedidoDao;

    private Session session;

    private BarChartModel graficoPedidosTotalAno;
    private BarChartModel graficoProdutosTotalAno;

    List<GraficoPedidosTotalMesAno> resultadoPedidos;
    List<GraficoProdutosTotalMesAno> resultadoProdutos;

    public DashboardController() {
        pedidoDao = new PedidoDaoImpl();
        itemPedidoDao = new ItemPedidoDaoImpl();
    }

    private void abreSessao() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    @PostConstruct
    public void init() {
        createGraficoPedidoAnos();
        createGraficoProdutoAnos();
    }

    /*
    
    Inicio do Grafico do Ano corrente com o total de pedidos
    
     */
    private BarChartModel initBarModelPedidosAno() {

        Object[] item;
        GraficoPedidosTotalMesAno grt;

        try {

            abreSessao();

            pedidoDao = new PedidoDaoImpl();
            graficoPedidosTotalAno = new BarChartModel();
            ChartSeries pedidoMes = new ChartSeries();

            List resultados = pedidoDao.totalMesPedidos(session);
            resultadoPedidos = new ArrayList<>();
            for (Object resultado : resultados) {
                item = (Object[]) resultado;
                grt = new GraficoPedidosTotalMesAno((int) item[0], (long) item[1]);
                resultadoPedidos.add(grt);

            }
            carregarValorSemMesGraficoPedido();

            pedidoMes.setLabel("Pedidos do Ano");
            pedidoMes.set("Janeiro", resultadoPedidos.get(0).getQuantidade());
            pedidoMes.set("Fevereiro", resultadoPedidos.get(1).getQuantidade());
            pedidoMes.set("Março", resultadoPedidos.get(2).getQuantidade());
            pedidoMes.set("Abril", resultadoPedidos.get(3).getQuantidade());
            pedidoMes.set("Maio", resultadoPedidos.get(4).getQuantidade());
            pedidoMes.set("Junho", resultadoPedidos.get(5).getQuantidade());
            pedidoMes.set("Julho", resultadoPedidos.get(6).getQuantidade());
            pedidoMes.set("Agosto", resultadoPedidos.get(7).getQuantidade());
            pedidoMes.set("Setembro", resultadoPedidos.get(8).getQuantidade());
            pedidoMes.set("Outubro", resultadoPedidos.get(9).getQuantidade());
            pedidoMes.set("Novembro", resultadoPedidos.get(10).getQuantidade());
            pedidoMes.set("Dezembro", resultadoPedidos.get(11).getQuantidade());

            graficoPedidosTotalAno.addSeries(pedidoMes);

        } catch (Exception e) {
            session.close();
        }

        return graficoPedidosTotalAno;
    }

    private void createGraficoPedidoAnos() {
        createGraficoPedidoAno();
    }

    private void createGraficoPedidoAno() {

        graficoPedidosTotalAno = initBarModelPedidosAno();

        graficoPedidosTotalAno.setTitle("Pedidos do Ano");
        graficoPedidosTotalAno.setLegendPosition("nw");
        graficoPedidosTotalAno.setMouseoverHighlight(true);
        graficoPedidosTotalAno.setShowDatatip(true);
        graficoPedidosTotalAno.setShowPointLabels(true);
        graficoPedidosTotalAno.setAnimate(true);
        graficoPedidosTotalAno.setZoom(true);
        Axis yAxis = graficoPedidosTotalAno.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }

    private void carregarValorSemMesGraficoPedido() {

        GraficoPedidosTotalMesAno gptma;

        int tamanho = resultadoPedidos.size();
        for (int i = tamanho; i <= 12; i++) {
            gptma = new GraficoPedidosTotalMesAno();
            gptma.setMes(i + 1);
            gptma.setQuantidade(0);
            resultadoPedidos.add(gptma);
        }
    }

    /*
    
    Fim do Grafico do Ano corrente com o total de pedidos
    
     */
 /*
    
    Inicio do Grafico do Ano corrente com o total de produtos
    
     */
    private BarChartModel initBarModelProdutosAno() {

        Object[] item;
        GraficoProdutosTotalMesAno grt;

        try {

            abreSessao();

            graficoProdutosTotalAno = new BarChartModel();

            List resultados = itemPedidoDao.totalMesProdutos(session);
            resultadoProdutos = new ArrayList<>();
            for (Object resultado : resultados) {
                item = (Object[]) resultado;
                grt = new GraficoProdutosTotalMesAno((int) item[0], (String) item[1], (long) item[2]);
                resultadoProdutos.add(grt);

            }

            resultadoProdutos = itemPedidoDao.totalMesProdutos(session);
            carregarValorSemMesGraficoProduto();

            ChartSeries p13 = new ChartSeries();
            p13.setLabel("P13");
            p13.set("Janeiro", resultadoProdutos.get(0).getQuantidade());
            p13.set("Fevereiro", resultadoProdutos.get(1).getQuantidade());
            p13.set("Março", resultadoProdutos.get(2).getQuantidade());
            p13.set("Abril", resultadoProdutos.get(3).getQuantidade());
            p13.set("Maio", resultadoProdutos.get(4).getQuantidade());
            p13.set("Junho", resultadoProdutos.get(5).getQuantidade());
            p13.set("Julho", resultadoProdutos.get(6).getQuantidade());
            p13.set("Agosto", resultadoProdutos.get(7).getQuantidade());
            p13.set("Setembro", resultadoProdutos.get(8).getQuantidade());
            p13.set("Outubro", resultadoProdutos.get(9).getQuantidade());
            p13.set("Novembro", resultadoProdutos.get(10).getQuantidade());
            p13.set("Dezembro", resultadoProdutos.get(11).getQuantidade());

            ChartSeries p20 = new ChartSeries();
            p20.setLabel("P20");
            p20.set("Janeiro", resultadoProdutos.get(12).getQuantidade());
            p20.set("Fevereiro", resultadoProdutos.get(13).getQuantidade());
            p20.set("Março", resultadoProdutos.get(14).getQuantidade());
            p20.set("Abril", resultadoProdutos.get(15).getQuantidade());
            p20.set("Maio", resultadoProdutos.get(16).getQuantidade());
            p20.set("Junho", resultadoProdutos.get(17).getQuantidade());
            p20.set("Julho", resultadoProdutos.get(18).getQuantidade());
            p20.set("Agosto", resultadoProdutos.get(19).getQuantidade());
            p20.set("Setembro", resultadoProdutos.get(20).getQuantidade());
            p20.set("Outubro", resultadoProdutos.get(21).getQuantidade());
            p20.set("Novembro", resultadoProdutos.get(22).getQuantidade());
            p20.set("Dezembro", resultadoProdutos.get(23).getQuantidade());

            ChartSeries p45 = new ChartSeries();
            p45.setLabel("P45");
            p45.set("Janeiro", resultadoProdutos.get(24).getQuantidade());
            p45.set("Fevereiro", resultadoProdutos.get(25).getQuantidade());
            p45.set("Março", resultadoProdutos.get(26).getQuantidade());
            p45.set("Abril", resultadoProdutos.get(27).getQuantidade());
            p45.set("Maio", resultadoProdutos.get(28).getQuantidade());
            p45.set("Junho", resultadoProdutos.get(29).getQuantidade());
            p45.set("Julho", resultadoProdutos.get(30).getQuantidade());
            p45.set("Agosto", resultadoProdutos.get(31).getQuantidade());
            p45.set("Setembro", resultadoProdutos.get(32).getQuantidade());
            p45.set("Outubro", resultadoProdutos.get(33).getQuantidade());
            p45.set("Novembro", resultadoProdutos.get(34).getQuantidade());
            p45.set("Dezembro", resultadoProdutos.get(35).getQuantidade());

            graficoProdutosTotalAno.addSeries(p13);
            graficoProdutosTotalAno.addSeries(p20);
            graficoProdutosTotalAno.addSeries(p45);

        } catch (Exception e) {
            session.close();
        }

        return graficoProdutosTotalAno;
    }

    private void createGraficoProdutoAnos() {
        createGraficoProdutoAno();
    }

    private void createGraficoProdutoAno() {

        try {

            graficoProdutosTotalAno = initBarModelProdutosAno();

            graficoProdutosTotalAno.setTitle("Pedidos do Ano");
            graficoProdutosTotalAno.setLegendPosition("nw");
            graficoProdutosTotalAno.setMouseoverHighlight(true);
            graficoProdutosTotalAno.setShowDatatip(true);
            graficoProdutosTotalAno.setShowPointLabels(true);
            graficoProdutosTotalAno.setAnimate(true);
            graficoProdutosTotalAno.setZoom(true);
            Axis yAxis = graficoProdutosTotalAno.getAxis(AxisType.Y);
            yAxis.setLabel("Gender");
            yAxis.setMin(0);
            yAxis.setMax(100);

        } catch (Exception e) {
        }

    }

    private void carregarValorSemMesGraficoProduto() {

        try {

            GraficoProdutosTotalMesAno gptma;

            int tamanho = resultadoProdutos.size();
            for (int i = tamanho; i <= 36; i++) {
                gptma = new GraficoProdutosTotalMesAno();
                gptma.setMes(i + 1);
                gptma.setQuantidade(0);
                resultadoProdutos.add(gptma);
            }

        } catch (Exception e) {
        }

    }

    /*
    
    Fim do Grafico do Ano corrente com o total de produtos
    
     */

 /*    
    
   Inicio dos Getters and Setters
   
     */
    public BarChartModel getGraficoPedidosTotalAno() {
        return graficoPedidosTotalAno;
    }

    public BarChartModel getGraficoProdutosTotalAno() {
        return graficoProdutosTotalAno;
    }

}
