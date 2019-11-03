package br.com.glp.controller;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.ItemPedidoDao;
import br.com.glp.dao.ItemPedidoDaoImpl;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.model.ItemPedido;
import br.com.glp.model.Pedido;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
@ManagedBean(name = "relatorioC")
@ViewScoped
public class RelatorioController {

    private Session session;

    private PedidoDao pedidoDao;
    private ItemPedidoDao itemPedidoDao;

    private List<Pedido> pedidos;
    private List<ItemPedido> itemPedidos;

    private DataModel<Pedido> modelPedido;
    private DataModel<ItemPedido> modelItemPedido;

    private Pedido pedido;
    private ItemPedido itemPedido;

    private Date dataInicio;
    private Date dataFinal;
    private String tipoPesquisa;
    private String nomeCliente;
    private String nomeProduto;

    private Boolean qualPesquisa;

    public RelatorioController() {
        pedidoDao = new PedidoDaoImpl();
        itemPedidoDao = new ItemPedidoDaoImpl();
    }

    private void abreSessao() {
        if (session == null || !session.isOpen()) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    public void pesquisarPedido() {
    }

//       
//      Gettes e Settes
//
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public PedidoDao getPedidoDao() {
        if (pedido == null) {
            pedido = new Pedido();
        }
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public DataModel<Pedido> getModelPedido() {
        return modelPedido;
    }

    public void setModelPedido(DataModel<Pedido> modelPedido) {
        this.modelPedido = modelPedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getTipoPesquisa() {
        return tipoPesquisa;
    }

    public void setTipoPesquisa(String tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Boolean getQualPesquisa() {
        return qualPesquisa;
    }

    public void setQualPesquisa(Boolean qualPesquisa) {
        this.qualPesquisa = qualPesquisa;
    }

    public ItemPedidoDao getItemPedidoDao() {
        return itemPedidoDao;
    }

    public void setItemPedidoDao(ItemPedidoDao itemPedidoDao) {
        this.itemPedidoDao = itemPedidoDao;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

    public DataModel<ItemPedido> getModelItemPedido() {
        return modelItemPedido;
    }

    public void setModelItemPedido(DataModel<ItemPedido> modelItemPedido) {
        this.modelItemPedido = modelItemPedido;
    }

    public ItemPedido getItemPedido() {
        if (itemPedido == null) {
            itemPedido = new ItemPedido();
        }
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

}
