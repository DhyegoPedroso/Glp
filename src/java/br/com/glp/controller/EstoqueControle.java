package br.com.glp.controller;

import br.com.glp.dao.EstoqueDao;
import br.com.glp.dao.EstoqueDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.ProdutoDao;
import br.com.glp.dao.ProdutoDaoImpl;
import br.com.glp.model.Estoque;
import br.com.glp.model.Produto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Alunos
 */
@ManagedBean(name = "estoqueC")
@ViewScoped
public class EstoqueControle implements Serializable {

    private Session session;
    private Produto produto;
    private Estoque estoque;
    
    private EstoqueDao estoqueDao;
    private ProdutoDao produtoDao;
    
    private DataModel<Produto> modelProdutos;
    private List<Produto> produtos;
    private boolean mostrar_toolbar;

    public EstoqueControle() {
        pesquisar();
        produtoDao = new ProdutoDaoImpl();
    }

    private void abreSessao() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    public void abrir() {
        
        try {
        
        abreSessao();
        
        EstoqueDao estoqueDao = new EstoqueDaoImpl();
        
        estoque.setData(new Date());
        estoque.setSituacao("Aberto");
        estoqueDao.salvarOuAlterar(estoque, session);
            
        } catch (Exception e) {
            session.close();
        }
        
        
    }


    public void pesquisar() {
//        mostrar_toolbar = !mostrar_toolbar;
        produtoDao = new ProdutoDaoImpl();
        try {
            abreSessao();
            produtos = produtoDao.listaTodos(session);
            modelProdutos = new ListDataModel(produtos);
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa Produto:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    //Gettes e Setters
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isMostrar_toolbar() {
        return mostrar_toolbar;
    }

    public void setMostrar_toolbar(boolean mostrar_toolbar) {
        this.mostrar_toolbar = mostrar_toolbar;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public DataModel<Produto> getModelProdutos() {
        return modelProdutos;
    }

    public void setModelProdutos(DataModel<Produto> modelProdutos) {
        this.modelProdutos = modelProdutos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Estoque getEstoque() {
        if (estoque == null) {
            estoque = new Estoque();
        }
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

}
