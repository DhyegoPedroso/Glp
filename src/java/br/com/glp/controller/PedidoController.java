package br.com.glp.controller;

import br.com.glp.dao.CaminhaoDao;
import br.com.glp.dao.CaminhaoDaoImpl;
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
    private List<Caminhao> caminhoes;
    private List<ItemPedido> itemProdutos;

    private DataModel<Pedido> modelPedido;
    private DataModel<ItemPedido> modelItemProdutos;

    private ItemPedido itemPedido;
    private Pedido pedido;
    private Produto produto;
    private Cliente cliente;
    private Caminhao caminhao;

    private String movimentacao;
    private String nomePesquisa;

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
        cliente = pedido.getCliente();
        caminhao = pedido.getCaminhao();
        modelItemProdutos = new ListDataModel<>(pedido.getItemPedidos());
    }

    public void pesquisar() {
        pedidoDao = new PedidoDaoImpl();
        try {
            abreSessao();

            if (!getNomePesquisa().equals("")) {
                pedidos = pedidoDao.pesquisaPedidoNomeSocial(getNomePesquisa(), session);
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

            abreSessao();

            if (pedido.getId() == null) {
                pedido.setCadastro(new Date());
            }
            pedido.setCliente(cliente);
            pedido.setItemPedidos(itemProdutos);
            pedido.setCaminhao(caminhao);

            updateEstoque();

            pedidoDao.salvarOuAlterar(pedido, session);

            limpar();
            Mensagem.salvar("Pedido");
        } catch (HibernateException ex) {
            System.err.println("Erro ao Salvar pedido:\n" + ex.getMessage());
            Mensagem.mensagemError("Erro ao tentar salvar o pedido");
        } finally {
            session.close();
        }

    }

    public void updateEstoque() {
        ProdutoDao produtoDao = new ProdutoDaoImpl();
        for (int item = 0; item < itemProdutos.size(); item++) {

            produto = produtoDao.pesquisaEntidadeId(itemProdutos.get(item).getProduto().getId(), session);

            if (!itemProdutos.get(item).getMovimentação().equalsIgnoreCase("Entrada")) {
                produto.setQuantidade(produto.getQuantidade() - itemProdutos.get(item).getQuantidade());
                produtoDao.salvarOuAlterar(produto, session);
            } else {
                produto.setQuantidade(produto.getQuantidade() + itemProdutos.get(item).getQuantidade());
                produtoDao.salvarOuAlterar(produto, session);
            }
        }
    }

    public void excluir() {
        abreSessao();
    }

    public List<String> completeCliente(String query) {
        List<String> autoCompletes = new ArrayList<>();
        abreSessao();
        try {
            clienteDao = new ClienteDaoImpl();
            clientes = clienteDao.pesquisarNomeSocial(query, session);

            for (Cliente cliente1 : clientes) {
                autoCompletes.add(cliente1.getId() + " - " + cliente1.getNomeSocial());
            }

        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar cliente");
        } finally {
            session.close();
        }
        return autoCompletes;
    }

    public Cliente carregarDadosCliente() {

        try {

            String[] textoSeparado1 = cliente.getNomeSocial().split(" - ");
            cliente.setId(Long.parseLong(textoSeparado1[0]));

            abreSessao();
            clienteDao = new ClienteDaoImpl();

            cliente = clienteDao.pesquisaEntidadeId(cliente.getId(), session);

            cliente.setCnpj(cliente.getCnpj());
            cliente.setNomeSocial(cliente.getNomeSocial());

        } catch (HibernateException e) {
            System.err.println("Erro ao carregar Cliente");
        } finally {
            session.close();
        }

        return cliente;
    }

    public List<String> completeProduto(String query) {
        List<String> autoCompletes = new ArrayList<>();
        abreSessao();
        try {
            ProdutoDao produtoDao = new ProdutoDaoImpl();
            produtos = produtoDao.pesquisaPorNome(query, session);

            for (Produto produto1 : produtos) {
                autoCompletes.add(produto1.getId() + " - " + produto1.getNomeProduto() + " - Marca: " + produto1.getMarca() + " - Situação: " + produto1.getSituacao());
            }

        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar cliente");
        } finally {
            session.close();
        }
        return autoCompletes;
    }

    public Produto carregarDadosProduto() {

        try {

            String[] textoSeparado1 = produto.getNomeProduto().split(" - ");
            produto.setId(Long.parseLong(textoSeparado1[0]));

            abreSessao();
            ProdutoDao produtoDao = new ProdutoDaoImpl();

            produto = produtoDao.pesquisaEntidadeId(produto.getId(), session);
            produto.setNomeProduto(produto.getNomeProduto());

        } catch (HibernateException e) {
            System.err.println("Erro ao carregar Produto");
        } finally {
            session.close();
        }
        return produto;
    }

    public void addItemProduto() {

        if (itemProdutos == null) {
            itemProdutos = new ArrayList<>();
        }

        if (modelItemProdutos == null) {
            modelItemProdutos = new ListDataModel<>(itemProdutos);
        }

        if (itemPedido.getMovimentação().equalsIgnoreCase("")) {
            Mensagem.selecioneMovimentacao();
        } else if (!itemPedido.getMovimentação().equalsIgnoreCase("Entrada") && itemPedido.getQuantidade() > produto.getQuantidade()) {
            Mensagem.estoqueInsuficiente(itemPedido.getQuantidade(), produto.getQuantidade());
        } else {
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemPedido.getQuantidade());
            itemPedido.setPedido(pedido);
            itemProdutos.add(itemPedido);

            itemPedido = new ItemPedido();
            produto = new Produto();
        }

    }

    public List<String> completeCaminhao(String query) {
        List<String> autoCompletes = new ArrayList<>();
        abreSessao();
        try {
            CaminhaoDao caminhaoDao = new CaminhaoDaoImpl();
//            caminhoes = caminhaoDao.pesquisaPlaca(query, session);
            caminhoes = caminhaoDao.pesquisaCaminhaoCliente(cliente.getId(), session);

            for (Caminhao caminhao1 : caminhoes) {
                autoCompletes.add(caminhao1.getId() + " - " + caminhao1.getPlacaCaminhao() + " - " + caminhao1.getNomeMotorista());
            }

        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar Caminhao");
        } finally {
            session.close();
        }
        return autoCompletes;
    }

    public Produto carregarDadosCaminhao() {

        try {

            String[] textoSeparado1 = caminhao.getPlacaCaminhao().split(" - ");
            caminhao.setId(Long.parseLong(textoSeparado1[0]));

            abreSessao();
            CaminhaoDao caminhaoDao = new CaminhaoDaoImpl();

            caminhao = caminhaoDao.pesquisaEntidadeId(caminhao.getId(), session);
            caminhao.setPlacaCaminhao(caminhao.getPlacaCaminhao());
            caminhao.setNomeMotorista(caminhao.getNomeMotorista());

        } catch (HibernateException e) {
            System.err.println("Erro ao carregar Produto");
        } finally {
            session.close();
        }
        return produto;
    }

    public void limpar() {
        pedido = new Pedido();
        cliente = new Cliente();
        produto = new Produto();
        caminhao = new Caminhao();
        itemProdutos = new ArrayList<>();
        setMovimentacao("Selecione uma Situação");
        modelItemProdutos = new ListDataModel<>(itemProdutos);
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

    public List<Caminhao> getCaminhoes() {
        return caminhoes;
    }

    public void setCaminhoes(List<Caminhao> caminhoes) {
        this.caminhoes = caminhoes;
    }

    public List<ItemPedido> getItemProdutos() {
        return itemProdutos;
    }

    public void setItemProdutos(List<ItemPedido> itemProdutos) {
        this.itemProdutos = itemProdutos;
    }

    public DataModel<ItemPedido> getModelItemProdutos() {
        return modelItemProdutos;
    }

    public void setModelItemProdutos(DataModel<ItemPedido> modelItemProdutos) {
        this.modelItemProdutos = modelItemProdutos;
    }

    public String getNomePesquisa() {
        return nomePesquisa;
    }

    public void setNomePesquisa(String nomePesquisa) {
        this.nomePesquisa = nomePesquisa;
    }

}
