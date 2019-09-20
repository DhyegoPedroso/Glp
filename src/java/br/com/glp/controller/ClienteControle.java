package br.com.glp.controller;

import br.com.glp.dao.ClienteDao;
import br.com.glp.dao.ClienteDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.model.Caminhao;
import br.com.glp.model.Cliente;
import br.com.glp.model.Contato;
import br.com.glp.model.Endereco;
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
 * @author Leo
 */
@ManagedBean(name = "clienteC")
@ViewScoped
public class ClienteControle implements Serializable {

    private Session session;
    private boolean mostrar_toolbar;
    private boolean mostrarTabelaEndereco;
    private boolean mostrarTabelaCaminhao;

    private ClienteDao clienteDao;

    private Endereco endereco;
    private List<Endereco> enderecos;
    private DataModel<Endereco> modelEnderecos;

    private Cliente cliente;
    private List<Cliente> clientes;
    private DataModel<Cliente> modelClientes;

    private Caminhao caminhao;
    private List<Caminhao> caminhoes;
    private DataModel<Caminhao> modelCaminhoes;

    private Contato contato;

    public ClienteControle() {
        clienteDao = new ClienteDaoImpl();
    }

    private void abreSessao() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    public boolean isAddCaminhao() {
        return this.endereco == null;
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
        cliente = modelClientes.getRowData();
        enderecos = cliente.getEnderecos();
    }

    public void carregarTabelaEndereco() {
        endereco = modelEnderecos.getRowData();
    }

    public void carregarTabelaCaminhao() {
        caminhao = modelCaminhoes.getRowData();

    }

    public void pesquisar() {
        clienteDao = new ClienteDaoImpl();
        try {
            abreSessao();

            if (!cliente.getNome().equals("")) {
                clientes = clienteDao.pesquisaPorNome(cliente.getNome(), session);
            } else {
                Mensagem.campoVazio("O campo Cliente");
            }

            modelClientes = new ListDataModel(clientes);
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa Cliente:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void limpar() {
        cliente = new Cliente();
        contato = new Contato();
        endereco = new Endereco();
        caminhao = new Caminhao();
    }

    public void excluir() {
        cliente = modelClientes.getRowData();
        abreSessao();
        try {
            clienteDao.remover(cliente, session);
            clientes.remove(cliente);
            modelClientes = new ListDataModel(clientes);
            Mensagem.excluir("Cliente");
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

            if (cliente.getId() == null) {
                cliente.setDtCadastro(new Date());
            }

            clienteDao.salvarOuAlterar(cliente, session);
            Mensagem.salvar("Cliente: " + cliente.getNome());
            cliente = null;
            endereco = null;
            contato = null;

        } catch (HibernateException ex) {
            System.err.println("Erro ao Salvar Cliente:\n" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Erro no salvar clienteDao Controle "
                    + e.getMessage());
        } finally {
            session.close();
        }
        limpar();
    }

    public void addEndereco() {

        if (enderecos == null) {
            enderecos = new ArrayList<>();
        }
        enderecos.add(endereco);
    }
    
     public void createNew() {
        if(enderecos.contains(endereco)) {
            Mensagem.campoExiste("Endereço ");
        } 
        else {
            enderecos.add(endereco);
            endereco = new Endereco();
        }
    }
     
    public String reinit() {
        endereco = new Endereco();
         
        return null;
    }

    public void limparTela() {
        limpar();
    }

    //    
    //Getters e Setters
    //
    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public DataModel<Cliente> getModelClientes() {
        return modelClientes;
    }

    public void setModelClientes(DataModel<Cliente> modelClientes) {
        this.modelClientes = modelClientes;
    }

    public DataModel<Endereco> getModelEnderecos() {
        return modelEnderecos;
    }

    public void setModelEnderecos(DataModel<Endereco> modelEnderecos) {
        this.modelEnderecos = modelEnderecos;
    }

    public DataModel<Caminhao> getModelCaminhoes() {
        return modelCaminhoes;
    }

    public void setModelCaminhoes(DataModel<Caminhao> modelCaminhoes) {
        this.modelCaminhoes = modelCaminhoes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setFuncionarios(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean isMostrar_toolbar() {
        return mostrar_toolbar;
    }

    public void setMostrar_toolbar(boolean mostrar_toolbar) {
        this.mostrar_toolbar = mostrar_toolbar;
    }

    public boolean isMostrarTabelaEndereco() {
        return mostrarTabelaEndereco;
    }

    public void setMostrarTabelaEndereco(boolean mostrarTabelaEndereco) {
        this.mostrarTabelaEndereco = mostrarTabelaEndereco;
    }

    public boolean isMostrarTabelaCaminhao() {
        return mostrarTabelaCaminhao;
    }

    public void setMostrarTabelaCaminhao(boolean mostrarTabelaCaminhao) {
        this.mostrarTabelaCaminhao = mostrarTabelaCaminhao;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Contato getContato() {
        if (contato == null) {
            contato = new Contato();
        }
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Endereco> getEnderecos() {
        if (enderecos == null) {
            enderecos = new ArrayList<>();
        }
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Caminhao> getCaminhoes() {
        if (caminhoes == null) {
            caminhoes = new ArrayList<>();
        }
        return caminhoes;
    }

    public void setCaminhoes(List<Caminhao> caminhoes) {
        this.caminhoes = caminhoes;
    }

}
