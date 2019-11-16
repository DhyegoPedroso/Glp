package br.com.glp.controller;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.ItemPedidoDao;
import br.com.glp.dao.ItemPedidoDaoImpl;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.model.GraficoPedidosTotalMesAno;
import br.com.glp.model.GraficoProdutosTotalMesAno;
import java.io.Serializable;
import java.math.BigInteger;
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

    private BarChartModel graficoPedidos;
    private BarChartModel graficoProdutos;
    private BarChartModel graficoSituacoes;

    List<GraficoPedidosTotalMesAno> resultadoPedidos;
    List<GraficoProdutosTotalMesAno> resultadoProdutos;
    List<GraficoProdutosTotalMesAno> resultadoSituacoes;

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
        createGraficoPedidos();
        createGraficoProdutos();
        createGraficoSituacoes();
    }

    /*
    
    Inicio do Grafico do Ano corrente com o total de pedidos
    
     */
    private BarChartModel initBarModelPedidosAno() {

        try {

            abreSessao();

            graficoPedidos = new BarChartModel();
            ChartSeries pedidoMes = new ChartSeries();

            carregarDadosPedido();
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

            graficoPedidos.addSeries(pedidoMes);

        } catch (Exception e) {
            session.close();
        }

        return graficoPedidos;
    }

    private void createGraficoPedidos() {
        createGraficoPedido();
    }

    private void createGraficoPedido() {

        try {

            abreSessao();

            graficoPedidos = initBarModelPedidosAno();

            BigInteger qtde = pedidoDao.totalPedidoAno(session);

            graficoPedidos.setTitle("Pedidos do Ano");
            graficoPedidos.setLegendPosition("nw");
            graficoPedidos.setMouseoverHighlight(true);
            graficoPedidos.setShowDatatip(true);
            graficoPedidos.setShowPointLabels(true);
            graficoPedidos.setAnimate(true);
            graficoPedidos.setZoom(true);
            Axis xAxis = graficoPedidos.getAxis(AxisType.X);
            xAxis.setLabel("Total de Pedidos no Ano: " + qtde + "  ");
            Axis yAxis = graficoPedidos.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setMax(pegarQtdeMaxPedido());

        } catch (Exception e) {
            session.close();
        }

    }

    private void carregarDadosPedido() {

        Object[] item;
        GraficoPedidosTotalMesAno grt;

        List resultados = pedidoDao.totalMesPedidos(session);
        resultadoPedidos = new ArrayList<>();
        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoPedidosTotalMesAno((int) item[0], (long) item[1]);
            resultadoPedidos.add(grt);

        }

        carregarMesSemValorPedido();

    }

    private void carregarMesSemValorPedido() {

        GraficoPedidosTotalMesAno gptma;

        int tamanho = resultadoPedidos.size();
        for (int i = tamanho; i <= 12; i++) {
            gptma = new GraficoPedidosTotalMesAno();
            gptma.setMes(i + 1);
            gptma.setQuantidade(0);
            resultadoPedidos.add(gptma);
        }
    }

    private Long pegarQtdeMaxPedido() {

        try {

            abreSessao();

            Long qtde = pedidoDao.totalQtdeMaxPedido(session);

            return (qtde + 50);

        } catch (Exception e) {
            session.close();
        }

        return null;

    }

    /*
    Fim do Grafico do Ano corrente com o total de pedidos
     */
//    ---------------------------
//    ---------------------------
//    ---------------------------
    /*
    Inicio do Grafico do Ano corrente com o total de produtos
     */
    private BarChartModel initBarModelProdutos() {

        try {

            abreSessao();

            graficoProdutos = new BarChartModel();

            carregarDadosProduto("P13");
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

            carregarDadosProduto("P20");
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

            carregarDadosProduto("P45");
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

            graficoProdutos.addSeries(p13);
            graficoProdutos.addSeries(p20);
            graficoProdutos.addSeries(p45);

        } catch (Exception e) {
            session.close();
        }

        return graficoProdutos;
    }

    private void createGraficoProdutos() {
        createGraficoProduto();
    }

    private void createGraficoProduto() {

        try {

            abreSessao();

            BigInteger qtde = itemPedidoDao.totalProdutoAno(session);

            graficoProdutos = initBarModelProdutos();

            graficoProdutos.setTitle("Produtos do Ano");
            graficoProdutos.setLegendPosition("nw");
            graficoProdutos.setMouseoverHighlight(true);
            graficoProdutos.setShowDatatip(true);
            graficoProdutos.setShowPointLabels(true);
            graficoProdutos.setAnimate(true);
            graficoProdutos.setZoom(true);
            Axis xAxis = graficoProdutos.getAxis(AxisType.X);
            xAxis.setLabel("Total de Produtos no Ano: " + qtde + "  ");
            Axis yAxis = graficoProdutos.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setMax(pegarQtdeMaxProduto());

        } catch (Exception e) {
            session.close();
        }

    }

    private void carregarDadosProduto(String produto) {

        Object[] item;
        GraficoProdutosTotalMesAno grt;

        List resultados = itemPedidoDao.totalMesProdutos(produto, session);
        resultadoProdutos = new ArrayList<>();
        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoProdutosTotalMesAno((int) item[0], (String) item[1], (long) item[2]);
            resultadoProdutos.add(grt);

        }

        carregarMesSemValorProduto();

    }

    private void carregarMesSemValorProduto() {

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

    private Long pegarQtdeMaxProduto() {

        try {

            abreSessao();

            Long qtde = itemPedidoDao.totalQtdeMaxProduto(session);

            return (qtde + 50);

        } catch (Exception e) {
            session.close();
        }

        return null;

    }

    /*
    Fim do Grafico do Ano corrente com o total de produtos
     */
//    ---------------------------
//    ---------------------------
//    ---------------------------
    /*
    Inicio do Grafico do Ano corrente com o total de situações
     */
    private BarChartModel initBarModelSituacoes() {

        try {

            abreSessao();

            graficoSituacoes = new BarChartModel();

            carregarDadosSituacao("Cheio");
            ChartSeries cheio = new ChartSeries();
            cheio.setLabel("Cheio");
            cheio.set("Janeiro", resultadoSituacoes.get(0).getQuantidade());
            cheio.set("Fevereiro", resultadoSituacoes.get(1).getQuantidade());
            cheio.set("Março", resultadoSituacoes.get(2).getQuantidade());
            cheio.set("Abril", resultadoSituacoes.get(3).getQuantidade());
            cheio.set("Maio", resultadoSituacoes.get(4).getQuantidade());
            cheio.set("Junho", resultadoSituacoes.get(5).getQuantidade());
            cheio.set("Julho", resultadoSituacoes.get(6).getQuantidade());
            cheio.set("Agosto", resultadoSituacoes.get(7).getQuantidade());
            cheio.set("Setembro", resultadoSituacoes.get(8).getQuantidade());
            cheio.set("Outubro", resultadoSituacoes.get(9).getQuantidade());
            cheio.set("Novembro", resultadoSituacoes.get(10).getQuantidade());
            cheio.set("Dezembro", resultadoSituacoes.get(11).getQuantidade());

            carregarDadosSituacao("Vazio");
            ChartSeries vazio = new ChartSeries();
            vazio.setLabel("Vazio");
            vazio.set("Janeiro", resultadoSituacoes.get(0).getQuantidade());
            vazio.set("Fevereiro", resultadoSituacoes.get(1).getQuantidade());
            vazio.set("Março", resultadoSituacoes.get(2).getQuantidade());
            vazio.set("Abril", resultadoSituacoes.get(3).getQuantidade());
            vazio.set("Maio", resultadoSituacoes.get(4).getQuantidade());
            vazio.set("Junho", resultadoSituacoes.get(5).getQuantidade());
            vazio.set("Julho", resultadoSituacoes.get(6).getQuantidade());
            vazio.set("Agosto", resultadoSituacoes.get(7).getQuantidade());
            vazio.set("Setembro", resultadoSituacoes.get(8).getQuantidade());
            vazio.set("Outubro", resultadoSituacoes.get(9).getQuantidade());
            vazio.set("Novembro", resultadoSituacoes.get(10).getQuantidade());
            vazio.set("Dezembro", resultadoSituacoes.get(11).getQuantidade());

            carregarDadosSituacao("Avariado");
            ChartSeries avariado = new ChartSeries();
            avariado.setLabel("Avariado");
            avariado.set("Janeiro", resultadoSituacoes.get(0).getQuantidade());
            avariado.set("Fevereiro", resultadoSituacoes.get(1).getQuantidade());
            avariado.set("Março", resultadoSituacoes.get(2).getQuantidade());
            avariado.set("Abril", resultadoSituacoes.get(3).getQuantidade());
            avariado.set("Maio", resultadoSituacoes.get(4).getQuantidade());
            avariado.set("Junho", resultadoSituacoes.get(5).getQuantidade());
            avariado.set("Julho", resultadoSituacoes.get(6).getQuantidade());
            avariado.set("Agosto", resultadoSituacoes.get(7).getQuantidade());
            avariado.set("Setembro", resultadoSituacoes.get(8).getQuantidade());
            avariado.set("Outubro", resultadoSituacoes.get(9).getQuantidade());
            avariado.set("Novembro", resultadoSituacoes.get(10).getQuantidade());
            avariado.set("Dezembro", resultadoSituacoes.get(11).getQuantidade());

            graficoSituacoes.addSeries(cheio);
            graficoSituacoes.addSeries(vazio);
            graficoSituacoes.addSeries(avariado);

        } catch (Exception e) {
            session.close();
        }

        return graficoSituacoes;
    }

    private void createGraficoSituacoes() {
        createGraficoSituacao();
    }

    private void createGraficoSituacao() {

        try {

            graficoSituacoes = initBarModelSituacoes();

            graficoSituacoes.setTitle("Situações do Ano");
            graficoSituacoes.setLegendPosition("nw");
            graficoSituacoes.setMouseoverHighlight(true);
            graficoSituacoes.setShowDatatip(true);
            graficoSituacoes.setShowPointLabels(true);
            graficoSituacoes.setAnimate(true);
            graficoSituacoes.setZoom(true);
            Axis yAxis = graficoSituacoes.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setMax(pegarQtdeMaxSituacao());

        } catch (Exception e) {
        }

    }

    private void carregarDadosSituacao(String situacao) {

        Object[] item;
        GraficoProdutosTotalMesAno grt;

        List resultados = itemPedidoDao.totalMesSituacoes(situacao, session);
        resultadoSituacoes = new ArrayList<>();
        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoProdutosTotalMesAno((int) item[0], (String) item[1], (long) item[2]);
            resultadoSituacoes.add(grt);

        }

        carregarMesSemValorSituacao();

    }

    private void carregarMesSemValorSituacao() {

        try {

            GraficoProdutosTotalMesAno gptma;

            int tamanho = resultadoSituacoes.size();
            for (int i = tamanho; i <= 11; i++) {
                gptma = new GraficoProdutosTotalMesAno();
                gptma.setMes(i + 1);
                gptma.setQuantidade(0);
                resultadoSituacoes.add(gptma);
            }

        } catch (Exception e) {
        }

    }

    private Long pegarQtdeMaxSituacao() {

        try {

            abreSessao();

            Long qtde = itemPedidoDao.totalQtdeMaxProduto(session);

            return (qtde + 50);

        } catch (Exception e) {
            session.close();
        }

        return null;

    }

    /*
    Fim do Grafico do Ano corrente com o total de situações
     */
//    ---------------------------
//    ---------------------------
//    ---------------------------
    /*    
   Inicio dos Getters and Setters
     */
    public BarChartModel getGraficoPedidosTotalAno() {
        return graficoPedidos;
    }

    public BarChartModel getGraficoProdutosTotalAno() {
        return graficoProdutos;
    }

    public BarChartModel getGraficoSituacoes() {
        return graficoSituacoes;
    }

}
