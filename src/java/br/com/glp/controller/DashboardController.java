package br.com.glp.controller;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.ItemPedidoDao;
import br.com.glp.dao.ItemPedidoDaoImpl;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
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
        yAxis.setMax(pegarPedidoQtdeMaxAno());
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
    
      private Long pegarPedidoQtdeMaxAno() {

        try {

            abreSessao();

            Long qtde = pedidoDao.totalQtdeMaxPedido(session);

            return (qtde + 10);

        } catch (Exception e) {
            session.close();
        }

        return null;

    }

    /*
    
    Fim do Grafico do Ano corrente com o total de pedidos
    
     */
 /*
    
    Inicio do Grafico do Ano corrente com o total de produtos
    
     */
    private BarChartModel initBarModelProdutosAno() {

        try {

            abreSessao();

            graficoProdutosTotalAno = new BarChartModel();

            carregarDadosProdutos("P13");
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

            carregarDadosProdutos("P20");
            ChartSeries p20 = new ChartSeries();
            p20.setLabel("P20");
            p20.set("Janeiro", resultadoProdutos.get(0).getQuantidade());
            p20.set("Fevereiro", resultadoProdutos.get(1).getQuantidade());
            p20.set("Março", resultadoProdutos.get(2).getQuantidade());
            p20.set("Abril", resultadoProdutos.get(3).getQuantidade());
            p20.set("Maio", resultadoProdutos.get(4).getQuantidade());
            p20.set("Junho", resultadoProdutos.get(5).getQuantidade());
            p20.set("Julho", resultadoProdutos.get(6).getQuantidade());
            p20.set("Agosto", resultadoProdutos.get(7).getQuantidade());
            p20.set("Setembro", resultadoProdutos.get(8).getQuantidade());
            p20.set("Outubro", resultadoProdutos.get(9).getQuantidade());
            p20.set("Novembro", resultadoProdutos.get(10).getQuantidade());
            p20.set("Dezembro", resultadoProdutos.get(11).getQuantidade());

            carregarDadosProdutos("P45");
            ChartSeries p45 = new ChartSeries();
            p45.setLabel("P45");
            p45.set("Janeiro", resultadoProdutos.get(0).getQuantidade());
            p45.set("Fevereiro", resultadoProdutos.get(1).getQuantidade());
            p45.set("Março", resultadoProdutos.get(2).getQuantidade());
            p45.set("Abril", resultadoProdutos.get(3).getQuantidade());
            p45.set("Maio", resultadoProdutos.get(4).getQuantidade());
            p45.set("Junho", resultadoProdutos.get(5).getQuantidade());
            p45.set("Julho", resultadoProdutos.get(6).getQuantidade());
            p45.set("Agosto", resultadoProdutos.get(7).getQuantidade());
            p45.set("Setembro", resultadoProdutos.get(8).getQuantidade());
            p45.set("Outubro", resultadoProdutos.get(9).getQuantidade());
            p45.set("Novembro", resultadoProdutos.get(10).getQuantidade());
            p45.set("Dezembro", resultadoProdutos.get(11).getQuantidade());

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

            graficoProdutosTotalAno.setTitle("Produtos do Ano");
            graficoProdutosTotalAno.setLegendPosition("nw");
            graficoProdutosTotalAno.setMouseoverHighlight(true);
            graficoProdutosTotalAno.setShowDatatip(true);
            graficoProdutosTotalAno.setShowPointLabels(true);
            graficoProdutosTotalAno.setAnimate(true);
            graficoProdutosTotalAno.setZoom(true);
            Axis yAxis = graficoProdutosTotalAno.getAxis(AxisType.Y);
            yAxis.setLabel("Gender");
            yAxis.setMin(0);
            yAxis.setMax(pegarProdutoQtdeMaxAno());

        } catch (Exception e) {
        }

    }

    private void carregarDadosProdutos(String produto) {

        Object[] item;
        GraficoProdutosTotalMesAno grt;

        List resultados = itemPedidoDao.totalMesProdutos(produto, session);
        resultadoProdutos = new ArrayList<>();
        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoProdutosTotalMesAno((int) item[0], (String) item[1], (long) item[2]);
            resultadoProdutos.add(grt);

        }

        carregarValorSemMesGraficoProduto();

    }

    private void carregarValorSemMesGraficoProduto() {

        try {

            GraficoProdutosTotalMesAno gptma;

            int tamanho = resultadoProdutos.size();
            for (int i = tamanho; i <= 11; i++) {
                gptma = new GraficoProdutosTotalMesAno();
                gptma.setMes(i + 1);
                gptma.setQuantidade(0);
                resultadoProdutos.add(gptma);
            }

        } catch (Exception e) {
        }

    }

    private Long pegarProdutoQtdeMaxAno() {

        try {

            abreSessao();

            Long qtde = itemPedidoDao.totalQtdeMaxProduto(session);

            return (qtde + 10);

        } catch (Exception e) {
            session.close();
        }

        return null;

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
