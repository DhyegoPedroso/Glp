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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
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
    private Pedido pedido;
    private Produto produto;
    private ItemPedido itemPedido;
    private List<ItemPedido> itemPedidos;
    private List<SelectItem> produtos;
    private List<SelectItem> situacoes;
    private List<Pedido> pedidos;
    private List<Cliente> clientes;
    private DataModel<Pedido> modelPedido;
    private Cliente cliente;
    private ClienteDao clienteDao;
    private Caminhao caminhao;

    public PedidoController() {
        pedidoDao = new PedidoDaoImpl();
    }
    
//    @PostConstruct
//    public void inicializar() {
//       carregaSituacao();
//        carregaTipo();
//    }

    private void abreSessao() {
        if (session == null || !session.isOpen()) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
       
    }

    public void novo() {
        if (pedido == null) {
            getPedido();
            pedido.setCadastro(new Date());
            
        }
        
        
        mostrar_Toolbar = !mostrar_Toolbar;
    }

    public void novaPesquisa() {
        mostrar_Toolbar = !mostrar_Toolbar;
    }

    public void preparaAlterar() {
        mostrar_Toolbar = !mostrar_Toolbar;
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
            abreSessao();
            pedido.setCadastro(new Date());
            pedido.setCliente(cliente);

            pedidoDao.salvarOuAlterar(pedido, session);
            Mensagem.salvar("Pedido: " + pedido.getCliente());
            pedido = null;
        } catch (HibernateException ex) {
          System.err.println("Erro ao Salvar pedido:\n" + ex.getMessage());
         } finally {
            session.close();
        }
        
         
    }
    
    public void limpar(){
        
    }
    
    public List<Cliente> pesquisaCliente(String query) {
        abreSessao();
        clienteDao = new ClienteDaoImpl();
        try {
            clientes = clienteDao.pesquisaPorNome(query, session);
        } catch (HibernateException he) {
            System.out.println("Erro no cliente " + he.getMessage());
        } finally {
            session.close();
        }
        return clientes;
    }
    
    public void atualizarCliente(){
        clienteDao = new ClienteDaoImpl();
        try {
            abreSessao();
            cliente = clienteDao.pesquisaEntidadeId(cliente.getId(), session);
        } catch (HibernateException ex) {
            System.out.println("Erro ao pesqusiar por id para atualizar o cliente " + ex.getMessage());
        }finally{
            session.close();
        }
    }
    
       
    private void carregaTipo() {
        List<Produto> todosProdutos;
        try {
           abreSessao();
            produtos = new ArrayList();

            ProdutoDao produtoDao = new ProdutoDaoImpl();
         todosProdutos = produtoDao.listaTodos(session);
           todosProdutos.stream().forEach((prod) -> {
                produtos.add(new SelectItem(prod.getId(), prod.getNomeProduto()));
           });
        } catch (HibernateException hi) {
            System.out.println("Erro ao carregar os produtos " + hi.getMessage());
       } finally {
            session.close();
       }
    }
    
     private void carregaSituacao() {
        List<Produto> tipoSituacao;
        try {
            abreSessao();
            situacoes = new ArrayList();

            ProdutoDao produtoDao = new ProdutoDaoImpl();
            tipoSituacao = produtoDao.listaTodos(session);
            tipoSituacao.stream().forEach((sit) -> {
                situacoes.add(new SelectItem(sit.getId(), sit.getSituacao()));
            });
        } catch (HibernateException hi) {
            System.out.println("Erro ao carregar os tipos de situacao " + hi.getMessage());
        } finally {
            session.close();
        }
    }
    
//    public void carregarDadosCliente(){
//        try{
//            
//        }
//    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }
     
     
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void excluir() {
        abreSessao();
    }

    public List<SelectItem> getSituacoes() {
        return situacoes;
    }

    public void setSituacoes(List<SelectItem> situacoes) {
        this.situacoes = situacoes;
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
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<SelectItem> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<SelectItem> produtos) {
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

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
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

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

}
