package br.com.glp.controller;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.model.Pedido;
import br.com.glp.model.Produto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso e Pedrão
 */

@ManagedBean(name = "pedidoC")
@ViewScoped
public class PedidoController {
    
    private boolean mostrar_Toolbar;
    private Session session;
    private PedidoDao PedidoDao;

    private Pedido Pedido;
    private Produto produto;
    
    private List<Produto> produtos;
    private DataModel<Produto> modelProduto;
    
    
    public PedidoController() {
        PedidoDao = new PedidoDaoImpl();
    }
    
     private void abreSessao() {
        if (session == null || !session.isOpen()) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }
       public void novo() {
        mostrar_Toolbar = !mostrar_Toolbar;
    }

    public void novaPesquisa() {
        mostrar_Toolbar = !mostrar_Toolbar;
    }

    public void preparaAlterar() {
        mostrar_Toolbar = !mostrar_Toolbar;
    }

    public void pesquisar() {
        abreSessao();
    }

    public void salvar() {
        abreSessao();
    }

    public void excluir() {
        abreSessao();
    }

    public void alterarEquipamento() {
        mostrar_Toolbar = !mostrar_Toolbar;
        produto = modelProduto.getRowData();

    }

    public void carregarParaAlterar() {
        mostrar_Toolbar = !mostrar_Toolbar;
        produto = modelProduto.getRowData();

    }

    public Produto getProduto() {
        if (produto == null) {
            produto = new Produto();
        }
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    

    public boolean isMostrar_Toolbar() {
        return mostrar_Toolbar;
    }

    public void setMostrar_Toolbar(boolean mostrar_Toolbar) {
        this.mostrar_Toolbar = mostrar_Toolbar;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public DataModel<Produto> getModelProduto() {
        return modelProduto;
    }

    public void setModelProduto(DataModel<Produto> modelProduto) {
        this.modelProduto = modelProduto;
    }
    
}
