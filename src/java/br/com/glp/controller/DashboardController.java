package br.com.glp.controller;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.model.GraficoPedidosTotalMesAno;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;

/**
 *
 * @author Dhyego Pedroso
 */
@ManagedBean(name = "dashboardC")
@ViewScoped
public class DashboardController implements Serializable {

    private PedidoDao pedidoDao;

    private Session session;

    private CartesianChartModel graficoPedidosTotalAno;

    private void abreSessao() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    @PostConstruct
    public void init() {
        createCombinedModel();
    }

    public DashboardController() {
        pedidoDao = new PedidoDaoImpl();
    }

    public CartesianChartModel getGraficoPedidosTotalAno() {
        return graficoPedidosTotalAno;
    }

    private void createCombinedModel() {

        try {

            abreSessao();

            pedidoDao = new PedidoDaoImpl();

            graficoPedidosTotalAno = new BarChartModel();

            BarChartSeries pedidoMes = new BarChartSeries();
            pedidoMes.setLabel("Quantidade de pedidos realizados");

            List<GraficoPedidosTotalMesAno> resultado = pedidoDao.totalMesPedidos(session);

            pedidoMes.set("Janeiro", resultado.get(0).getQuantidade());
            pedidoMes.set("Fevereiro", resultado.get(1).getQuantidade());
            pedidoMes.set("Março", resultado.get(2).getQuantidade());
            pedidoMes.set("Abril", resultado.get(3).getQuantidade());
            pedidoMes.set("Maio", resultado.get(4).getQuantidade());
            pedidoMes.set("Junho", resultado.get(5).getQuantidade());
            pedidoMes.set("Julho", resultado.get(6).getQuantidade());
            pedidoMes.set("Agosto", resultado.get(7).getQuantidade());
            pedidoMes.set("Setembro", resultado.get(8).getQuantidade());
            pedidoMes.set("Outubro", resultado.get(9).getQuantidade());
            pedidoMes.set("Novembro", resultado.get(10).getQuantidade());
            pedidoMes.set("Dezembro", resultado.get(11).getQuantidade());

            graficoPedidosTotalAno.addSeries(pedidoMes);

            graficoPedidosTotalAno.setTitle("Pedidos do Ano");
            graficoPedidosTotalAno.setLegendPosition("nw");
            graficoPedidosTotalAno.setMouseoverHighlight(true);
            graficoPedidosTotalAno.setShowDatatip(true);
            graficoPedidosTotalAno.setShowPointLabels(true);
            graficoPedidosTotalAno.setAnimate(true);
            graficoPedidosTotalAno.setZoom(true);
            Axis yAxis = graficoPedidosTotalAno.getAxis(AxisType.Y);
            yAxis.setMin(0);
            yAxis.setMax(250);
        } catch (Exception e) {

            session.close();
        }
    }

}
