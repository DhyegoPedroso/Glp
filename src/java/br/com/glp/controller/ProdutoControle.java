package br.com.glp.controller;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.MarcaDao;
import br.com.glp.dao.MarcaDaoImpl;
import br.com.glp.dao.ProdutoDao;
import br.com.glp.dao.ProdutoDaoImpl;
import br.com.glp.dao.SituacaoDao;
import br.com.glp.dao.SituacaoDaoImpl;
import br.com.glp.model.Marca;
import br.com.glp.model.Produto;
import br.com.glp.model.Situacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Leo
 */
@ManagedBean(name = "produtoC")
@ViewScoped
public class ProdutoControle implements Serializable {

    private Session session;
    private boolean mostrar_toolbar;

    private Produto produto;
    private Marca marca;
    private Situacao situacao;

    private ProdutoDao produtoDao;

    private DataModel<Produto> modelProdutos;
    private List<Produto> produtos;
    private List<SelectItem> marcas;
    private List<SelectItem> situacoes;

//    @PostConstruct
//    public void inicializar() {
//        carregaMarcas();
//        carregaSituacoes();
//    }

    public ProdutoControle() {
        produtoDao = new ProdutoDaoImpl();
    }

    private void abreSessao() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    public void novo() {
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
        marca = produto.getMarca();
        situacao = produto.getSituacao();
    }

    public void pesquisar() {
        produtoDao = new ProdutoDaoImpl();
        try {
            abreSessao();

            if (!produto.getNomeProduto().equals("")) {
                produtos = produtoDao.pesquisaPorNome(produto.getNomeProduto(), session);
            } else {
                produtos = produtoDao.listaTodos(session);
            }
            modelProdutos = new ListDataModel(produtos);
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa Produto:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    private void carregaMarcas() {
        List<Marca> todasMarcas;
        try {
            abreSessao();
            marcas = new ArrayList();

            MarcaDao marcaDao = new MarcaDaoImpl();
            todasMarcas = marcaDao.listaTodos(session);
            todasMarcas.stream().forEach((perf) -> {
                marcas.add(new SelectItem(perf.getId(), perf.getNomeMarca()));
            });
        } catch (HibernateException hi) {
            System.out.println("Erro ao carregar os marca " + hi.getMessage());
        } finally {
            session.close();
        }
    }

    public void salvarMarca() {
        abreSessao();
        try {
            MarcaDao marcaDao = new MarcaDaoImpl();
            marcaDao.salvarOuAlterar(marca, session);
            carregaMarcas();
        } catch (HibernateException ex) {
            System.err.println("Erro ao Salvar nova Marca:\n" + ex.getMessage());
        } finally {
        }
    }

    public void salvarSituacao() {
        abreSessao();
        try {
            SituacaoDao situacaoDao = new SituacaoDaoImpl();
            situacaoDao.salvarOuAlterar(situacao, session);
            carregaSituacoes();
        } catch (HibernateException ex) {
            System.err.println("Erro ao Salvar nova Situação:\n" + ex.getMessage());
        } finally {
        }
    }

    private void carregaSituacoes() {
        List<Situacao> todasSituacoes;
        try {
            abreSessao();
            situacoes = new ArrayList();

            SituacaoDao situacaoDao = new SituacaoDaoImpl();
            todasSituacoes = situacaoDao.listaTodos(session);
            todasSituacoes.stream().forEach((perf) -> {
                situacoes.add(new SelectItem(perf.getId(), perf.getNomeSituacao()));
            });
        } catch (HibernateException hi) {
            System.out.println("Erro ao carregar as situações " + hi.getMessage());
        } finally {
            session.close();
        }
    }

    public void cancelarModal() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public void limpar() {
        produto = new Produto();
        marca = new Marca();
        situacao = new Situacao();
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

            Mensagem.salvar("Produto ");
        } catch (HibernateException ex) {
            Mensagem.mensagemError("Erro ao salvar\nTente novamente");
        } finally {
            produto = new Produto();
            session.close();
        }
    }

    public void limparTela() {
        limpar();
    }

    //Gettes e Setters
    public boolean isMostrar_toolbar() {
        return mostrar_toolbar;
    }

    public void setMostrar_toolbar(boolean mostrar_toolbar) {
        this.mostrar_toolbar = mostrar_toolbar;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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

    public Marca getMarca() {
        if (marca == null) {
            marca = new Marca();
        }
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Situacao getSituacao() {
        if (situacao == null) {
            situacao = new Situacao();
        }
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
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

    public List<SelectItem> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<SelectItem> marcas) {
        this.marcas = marcas;
    }

    public List<SelectItem> getSituacoes() {
        return situacoes;
    }

    public void setSituacoes(List<SelectItem> situacoes) {
        this.situacoes = situacoes;
    }

}
