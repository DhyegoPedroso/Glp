package br.com.glp.controller;

import br.com.glp.dao.FuncionarioDao;
import br.com.glp.dao.FuncionarioDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PerfilDao;
import br.com.glp.dao.PerfilDaoImpl;
import br.com.glp.model.Contato;
import br.com.glp.model.Endereco;
import br.com.glp.model.Funcionario;
import br.com.glp.model.Perfil;
import br.com.glp.model.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
 * @author Leo
 */
@ManagedBean(name = "funcionarioC")
@ViewScoped
public class FuncionarioControl implements Serializable {

    private Session session;
    private boolean mostrar_toolbar;

    private Funcionario funcionario;
    private Endereco endereco;
    private Contato contato;
    private Usuario usuario;
    private Perfil perfil;

    private FuncionarioDao funcionarioDao;

    private DataModel<Funcionario> modelFuncionarios;
    private List<SelectItem> perfils;
    private List<Funcionario> funcionarios;

    @PostConstruct
    public void inicializar() {
        carregaPerfil();
    }

    public FuncionarioControl() {
        funcionarioDao = new FuncionarioDaoImpl();
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
        funcionario = modelFuncionarios.getRowData();
//        contato = funcionario.getContato();
//        endereco = funcionario.getEndereco();
        usuario = funcionario.getUsuario();
        perfil = usuario.getPerfil();
    }

    public void pesquisar() {
        funcionarioDao = new FuncionarioDaoImpl();
        try {
            abreSessao();

            if (!funcionario.getNome().equals("")) {
                funcionarios = funcionarioDao.pesquisaPorNome(funcionario.getNome(), session);
            } else {
                funcionarios = funcionarioDao.listaTodos(session);
            }

            modelFuncionarios = new ListDataModel(funcionarios);
//            pesqNome = null;
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa Funcionario:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    private void carregaPerfil() {
        List<Perfil> todosPerfis;
        try {
            abreSessao();
            perfils = new ArrayList();

            PerfilDao perfilDao = new PerfilDaoImpl();
            todosPerfis = perfilDao.listaTodos(session);
            todosPerfis.stream().forEach((perf) -> {
                perfils.add(new SelectItem(perf.getId(), perf.getNome()));
            });
        } catch (HibernateException hi) {
            System.out.println("Erro ao carregar os perfil " + hi.getMessage());
        } finally {
            session.close();
        }
    }

    public void limpar() {
        funcionario = new Funcionario();
        contato = new Contato();
        perfil = new Perfil();
        endereco = new Endereco();
        usuario = new Usuario();
    }

    public void excluir() {
        funcionario = modelFuncionarios.getRowData();
        abreSessao();
        try {
            funcionarioDao.remover(funcionario, session);
            funcionarios.remove(funcionario);
            modelFuncionarios = new ListDataModel(funcionarios);
            Mensagem.excluir("Funcionario");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    private static String convertPasswordToMD5(String senha) throws NoSuchAlgorithmException {
        String retorno = "";
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes(), 0, senha.length());
            retorno = new BigInteger(1, md.digest()).toString(16);

        } catch (Exception e) {
            System.out.println("Falha ao criptografar " + e.getMessage());
        }
        return retorno;
    }

    public void salvar() throws NoSuchAlgorithmException {

        try {
            abreSessao();

//            funcionario.setEndereco(endereco);
//            endereco.setPessoa(funcionario);
//            funcionario.setContato(contato);
//            contato.setPessoa(funcionario);

            funcionario.setUsuario(usuario);
            usuario.setFuncionario(funcionario);

            String senha = usuario.getSenha();

            usuario.setEnable(true);
            usuario.setPerfil(perfil);
            usuario.setSenha(convertPasswordToMD5(senha));

            funcionario.setDtCadastro(new Date());

            funcionarioDao.salvarOuAlterar(funcionario, session);
            Mensagem.salvar("Funcionario: " + funcionario.getNome());
            funcionario = null;
            endereco = null;
            contato = null;
            usuario = null;

        } catch (HibernateException ex) {
            System.err.println("Erro ao Salvar funcionario:\n" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Erro no salvar funcionarioDao Controle "
                    + e.getMessage());
        } finally {
            session.close();
        }
        limpar();

    }

    public void limparTela() {
        limpar();
    }

    public Funcionario getFuncionario() {
        if (funcionario == null) {
            funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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

    public DataModel<Funcionario> getModelFuncionarios() {
        return modelFuncionarios;
    }

    public void setModelFuncionarios(DataModel<Funcionario> modelFuncionarios) {
        this.modelFuncionarios = modelFuncionarios;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<SelectItem> getPerfils() {
        return perfils;
    }

    public void setPerfils(List<SelectItem> perfils) {
        this.perfils = perfils;
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

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        if (perfil == null) {
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public FuncionarioDao getFuncionarioDao() {
        return funcionarioDao;
    }

    public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }

//    public String getPesqNome() {
//        return pesqNome;
//    }
//
//    public void setPesqNome(String pesqNome) {
//        this.pesqNome = pesqNome;
//    }
}
