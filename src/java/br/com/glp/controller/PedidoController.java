package br.com.glp.controller;

import br.com.glp.dao.ClienteDao;
import br.com.glp.dao.ClienteDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.dao.ProdutoDao;
import br.com.glp.dao.ProdutoDaoImpl;
import br.com.glp.model.Caminhao;
import br.com.glp.model.Cliente;
import br.com.glp.model.ItemPedido;
import br.com.glp.model.Pedido;
import br.com.glp.model.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso e Pedrão
 */
@ManagedBean(name = "pedidoC")
@ViewScoped
public class PedidoController implements Serializable {

    private boolean mostrar_Toolbar;
    private Session session;

    private PedidoDao pedidoDao;
    private ClienteDao clienteDao;

    private List<Pedido> pedidos;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Produto> produtosEntrada;
    private List<Produto> produtosSaida;

    private DataModel<Pedido> modelPedido;
    private DataModel<Produto> modelProdutoEntrada;
    private DataModel<Pedido> modelProdutoSaida;

    private ItemPedido itemPedido;
    private Pedido pedido;
    private Produto produto;
    private Cliente cliente;
    private Caminhao caminhao;

    private String movimentacao;

    public PedidoController() {
        pedidoDao = new PedidoDaoImpl();
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
        limpar();
    }

    public void novaPesquisa() {
        mostrar_Toolbar = !mostrar_Toolbar;
        limpar();
    }

    public void preparaAlterar() {
        mostrar_Toolbar = !mostrar_Toolbar;
        limpar();
    }

    public void carregarParaAlterar() {
        mostrar_Toolbar = !mostrar_Toolbar;
        pedido = modelPedido.getRowData();
    }

    public void pesquisar() {
        pedidoDao = new PedidoDaoImpl();
        try {
            abreSessao();

            if (!pedido.getCliente().getNome().equals("")) {
                pedidos = pedidoDao.pesquisaPorNome(pedido.getCliente().getNome(), session);
            } else {
                pedidos = pedidoDao.listaTodos(session);
            }
            modelPedido = new ListDataModel(pedidos);
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa Pedido:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void salvar() {
        try {

            cliente.setId(cliente.getId());

            JOptionPane.showMessageDialog(null, "ID: " + cliente.getId());

//            abreSessao();
//            pedido.setCadastro(new Date());
//            pedido.setCliente(cliente);
//
//            pedidoDao.salvarOuAlterar(pedido, session);
//            Mensagem.salvar("Pedido: " + pedido.getCliente());
//            pedido = null;
        } catch (HibernateException ex) {
            System.err.println("Erro ao Salvar pedido:\n" + ex.getMessage());
        } finally {
            session.close();
        }

    }

    public void excluir() {
        abreSessao();
    }

    public void addItemProduto() {

        if (movimentacao.equalsIgnoreCase("Entrada")) {
            if (produtosEntrada == null) {
                produtosEntrada = new ArrayList<>();
            }
            produtosEntrada.add(produto);
        } else {
            if (produtosSaida == null) {
                produtosSaida = new ArrayList<>();
            }
            produtosSaida.add(produto);
        }

    }

    public void reset() {
        setMovimentacao("Selecione uma Situação");
    }

    public List<String> completeCliente(String query) {
        abreSessao();
        List<String> autoCompletes = new ArrayList<>();
        try {
            ClienteDao clienteDao = new ClienteDaoImpl();
            clientes = clienteDao.pesquisaPorNome(query, session);

            for (Cliente cliente1 : clientes) {
                autoCompletes.add("ID: " + cliente1.getId() + " - " + cliente1.getNome() + " - CNPJ: " + cliente1.getCnpj());
            }

        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar cliente");
        } finally {
            session.close();
        }
        return autoCompletes;
    }

    public List<String> completeProduto(String query) {
        abreSessao();
        List<String> autoCompletes = new ArrayList<>();
        try {
            ProdutoDao produtoDao = new ProdutoDaoImpl();
            produtos = produtoDao.pesquisaPorNome(query, session);

            for (Produto produto1 : produtos) {
                autoCompletes.add("ID: " + produto1.getId() + " - " + produto1.getNomeProduto() + " - Marca: " + produto1.getMarca() + " - Situação: " + produto1.getSituacao());
            }

        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar cliente");
        } finally {
            session.close();
        }
        return autoCompletes;
    }

    public void limpar() {
        pedido = new Pedido();
        cliente = new Cliente();
        produto = new Produto();
        caminhao = new Caminhao();
    }

//    Gett e Settes
    public Pedido getPedido() {
        if (pedido == null) {
            pedido = new Pedido();
        }
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public Caminhao getCaminhao() {
        if (caminhao == null) {
            caminhao = new Caminhao();
        }
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public String getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(String movimentacao) {
        this.movimentacao = movimentacao;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public PedidoDao getPedidoDao() {
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

    public DataModel<Produto> getModelProdutoEntrada() {
        return modelProdutoEntrada;
    }

    public void setModelProdutoEntrada(DataModel<Produto> modelProdutoEntrada) {
        this.modelProdutoEntrada = modelProdutoEntrada;
    }

    public DataModel<Pedido> getModelProdutoSaida() {
        return modelProdutoSaida;
    }

    public void setModelProdutoSaida(DataModel<Pedido> modelProdutoSaida) {
        this.modelProdutoSaida = modelProdutoSaida;
    }

    public List<Produto> getProdutosEntrada() {
        return produtosEntrada;
    }

    public void setProdutosEntrada(List<Produto> produtosEntrada) {
        this.produtosEntrada = produtosEntrada;
    }

    public List<Produto> getProdutosSaida() {
        return produtosSaida;
    }

    public void setProdutosSaida(List<Produto> produtosSaida) {
        this.produtosSaida = produtosSaida;
    }

}
