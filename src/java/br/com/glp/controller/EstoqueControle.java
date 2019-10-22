/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.glp.controller;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.ProdutoDao;
import br.com.glp.dao.ProdutoDaoImpl;
import br.com.glp.model.Estoque;
import br.com.glp.model.Produto;
import java.io.Serializable;
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
public class EstoqueControle implements Serializable{

    private Session session;
    private boolean mostrar_toolbar;
    private Produto produto;
    private Estoque estoque;
    private ProdutoDao produtoDao;
    private DataModel<Produto> modelProdutos;
    private List<Produto> produtos;

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

    public void fechar() {
        mostrar_toolbar = !mostrar_toolbar;
        salvar();
        limpar();
    }

    public void abrir() {
        mostrar_toolbar = !mostrar_toolbar;
        limpar();
    }

    public void novaPesquisa() {
        mostrar_toolbar = !mostrar_toolbar;
        limpar();
    }

    public void preparaAlterar() {
        mostrar_toolbar = !mostrar_toolbar;
        limpar();
    }

    public void carregarParaAlterar() {
        mostrar_toolbar = !mostrar_toolbar;
        produto = modelProdutos.getRowData();
    }

    public void limpar() {
        produto = new Produto();
        produto.setMarca("Selecione uma Marca");
        produto.setSituacao("Selecione uma Situação");

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

    public void excluir() {
        produto = modelProdutos.getRowData();
        abreSessao();
        try {
            produtoDao.remover(produto, session);
            produtos.remove(produto);
            modelProdutos = new ListDataModel(produtos);
            Mensagem.excluir("Produto");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void salvar() {
        try {
            abreSessao();
            produtoDao.salvarOuAlterar(produto, session);
            limpar();

        } catch (HibernateException ex) {
            Mensagem.mensagemError("Erro ao salvar\nTente novamente");
        } finally {
            produto = new Produto();
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

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
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

}
