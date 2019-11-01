package br.com.glp.controller;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.ItemPedidoDao;
import br.com.glp.dao.ItemPedidoDaoImpl;
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

    private CartesianChartModel graficoProdutosMes;
    private CartesianChartModel graficoQuantidadeMes;
    private CartesianChartModel graficoProdutosAno;
    private CartesianChartModel graficoQuantidadeAno;
    private Session session;

    private ItemPedidoDao itemPedidoDao;

    @PostConstruct
    public void init() {
        createCombinedModelQuantidadeAno();
        createCombinedModelQuantidadeMes();
        createCombinedModelProdutosAno();
        createCombinedModelProdutosMes();
    }

    public DashboardController() {
        itemPedidoDao = new ItemPedidoDaoImpl();
    }

    private void abreSessao() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    public CartesianChartModel getGraficoProdutosMes() {
        return graficoProdutosMes;
    }

    public CartesianChartModel getGraficoQuantidadeMes() {
        return graficoQuantidadeMes;
    }

    public CartesianChartModel getGraficoProdutosAno() {
        return graficoProdutosAno;
    }

    public CartesianChartModel getGraficoQuantidadeAno() {
        return graficoQuantidadeAno;
    }

    private void createCombinedModelQuantidadeAno() {
        try {

        } catch (Exception e) {
        }
    }

    private void createCombinedModelProdutosAno() {
        try {

        } catch (Exception e) {
        }
    }

    private void createCombinedModelQuantidadeMes() {
        try {

        } catch (Exception e) {
        }
    }

    private void createCombinedModelProdutosMes() {
        try {

        } catch (Exception e) {
        }
    }
}
