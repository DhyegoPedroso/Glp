package br.com.glp.controller;

import br.com.glp.dao.CaminhaoDao;
import br.com.glp.dao.CaminhaoDaoImpl;
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
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Leo
 */
@ManagedBean(name = "clienteC")
@ViewScoped
public class ClienteControle implements Serializable {

    private Session session;
    private boolean mostrar_toolbar;

    private ClienteDao clienteDao;

    private Endereco endereco;

    private Cliente cliente;
    private List<Cliente> clientes;
    private DataModel<Cliente> modelClientes;

    private Caminhao caminhao;
    private List<Caminhao> caminhoes;
    private DataModel<Caminhao> modelCaminhoes;

    private Contato contato;

    private boolean skip;

    private boolean alterar;
    private boolean excluir;

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
        endereco = cliente.getEndereco();
        contato = endereco.getContato();

        caminhoes = endereco.getCaminhoes();
        modelCaminhoes = new ListDataModel<>(endereco.getCaminhoes());
    }

    public Boolean carregarCaminhao() {

        if (caminhoes == null) {
            caminhoes = new ArrayList();
        }

        if (modelCaminhoes == null) {
            modelCaminhoes = new ListDataModel<>(caminhoes);
        }

        caminhao = modelCaminhoes.getRowData();
        endereco = caminhao.getEndereco();
        caminhoes = endereco.getCaminhoes();

        return alterar = true;
    }

    public boolean isSkip() {
        return skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void pesquisar() {
        clienteDao = new ClienteDaoImpl();
        try {
            abreSessao();

            if (!cliente.getNome().equals("")) {
                clientes = clienteDao.pesquisaPorNome(cliente.getNome(), session);
            } else {
                clientes = clienteDao.listaTodos(session);
            }

            modelClientes = new ListDataModel(clientes);
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa Cliente:\n" + ex.getMessage());
            Mensagem.mensagemError("Erro ao pesquisar cliente");
        } finally {
            session.close();
        }
    }

    public void limpar() {
        cliente = new Cliente();
        contato = new Contato();
        endereco = new Endereco();
        caminhao = new Caminhao();
        caminhoes = new ArrayList<>();
    }

    public void limparCaminhao() {
        caminhao = new Caminhao();
    }

    public void excluir() {
        cliente = modelClientes.getRowData();
        abreSessao();
        try {
            clienteDao.remover(cliente, session);
            clientes.remove(cliente);
            modelClientes = new ListDataModel(clientes);
            Mensagem.excluir("Cliente " + cliente.getNome());
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir: " + e.getMessage());
            Mensagem.mensagemError("Erro ao excluir cliente " + cliente.getNome());
        } finally {
            session.close();
        }
    }

    public void excluirCaminhao() {

        caminhao = modelCaminhoes.getRowData();
        abreSessao();

        try {

            CaminhaoDao caminhaoDao = new CaminhaoDaoImpl();
            caminhaoDao.remover(caminhao, session);
            caminhoes.remove(caminhao);
            Mensagem.excluir("Motorista " + caminhao.getNomeMotorista());
            limparCaminhao();
            excluir = true;
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
            endereco.setCliente(cliente);
            cliente.setEndereco(endereco);

            contato.setEndereco(endereco);
            endereco.setContato(contato);

            clienteDao.salvarOuAlterar(cliente, session);
            Mensagem.salvar("Cliente: " + cliente.getNome());
            limpar();

        } catch (HibernateException ex) {
            System.err.println("Erro ao Salvar Cliente:\n" + ex.getMessage());
            Mensagem.mensagemError("Erro ao salvar cliente");
        } catch (Exception e) {
            System.out.println("Erro no salvar clienteDao Controle "
                    + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void addCaminhao() {

        CaminhaoDao caminhaoDao = new CaminhaoDaoImpl();

        if (caminhoes == null) {
            caminhoes = new ArrayList();
        }

        if (modelCaminhoes == null) {
            modelCaminhoes = new ListDataModel<>(caminhoes);
        }

        if (!alterar) {
            caminhao.setEndereco(endereco);
            endereco.setCaminhoes(caminhoes);
            caminhoes.add(caminhao);
        }

        if (endereco.getId() != null && excluir) {
            try {
                abreSessao();
                endereco.setId(endereco.getId());
                caminhao.setEndereco(endereco);
                caminhaoDao.salvarCaminhao(caminhao, session);
            } catch (HibernateException e) {
                session.close();
            }
        }

        excluir = false;
        alterar = false;
        modelCaminhoes = new ListDataModel<>(caminhoes);
        caminhao = new Caminhao();
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

    public List<Caminhao> getCaminhoes() {
        if (caminhoes == null) {
            caminhoes = new ArrayList<>();
        }
        return caminhoes;
    }

    public void setCaminhoes(List<Caminhao> caminhoes) {
        this.caminhoes = caminhoes;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

}
