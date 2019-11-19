package br.com.glp.util;

import br.com.glp.dao.FuncionarioDao;
import br.com.glp.dao.FuncionarioDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PerfilDao;
import br.com.glp.dao.PerfilDaoImpl;
import br.com.glp.dao.ProdutoDao;
import br.com.glp.dao.ProdutoDaoImpl;
import br.com.glp.model.Funcionario;
import br.com.glp.model.Perfil;
import br.com.glp.model.Produto;
import br.com.glp.model.Usuario;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public class InicializarSistema {

    Session sessao;

    public void iniciarPerfils() {

        sessao = HibernateUtil.abreSessao();

        PerfilDao perfilDao = new PerfilDaoImpl();
        FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();

        //Cadastrar Funcionario Administrador
        Perfil perfilAdmin = new Perfil("ROLE_ADMIN", "usuario como permissão de administrador", "Administrador");
        Usuario usuarioAdmin = new Usuario("admin", "admin", true, perfilAdmin);

        Funcionario funcionarioAdmin = new Funcionario();
        funcionarioAdmin.setNome("Admin");
        funcionarioAdmin.setCpf("053.362.321.84");
        funcionarioAdmin.setDtCadastro(new Date());
        funcionarioAdmin.setMatricula("0123456789");
        funcionarioAdmin.setRg("3.962.625");
        funcionarioAdmin.setUsuario(usuarioAdmin);

//        usuarioAdmin.setFuncionario(funcionarioAdmin);
        perfilDao.salvarOuAlterar(perfilAdmin, sessao);
        funcionarioDao.salvarOuAlterar(funcionarioAdmin, sessao);

//        //Cadastrar Funcionario Conferente
        Perfil perfilConferente = new Perfil("ROLE_CONFERENTE", "usuario logado com perfil básico", "Conferente");
        Usuario usuarioConferente = new Usuario("conferente", "conferente", true, perfilConferente);

        Funcionario funcionarioConferente = new Funcionario();
        funcionarioConferente.setNome("Conferente");
        funcionarioConferente.setCpf("053.362.321.84");
        funcionarioConferente.setDtCadastro(new Date());
        funcionarioConferente.setMatricula("0123456789");
        funcionarioConferente.setRg("3.962.625");
        funcionarioConferente.setUsuario(usuarioConferente);

//        usuarioConferente.setFuncionario(funcionarioConferente);
        perfilDao.salvarOuAlterar(perfilConferente, sessao);
        funcionarioDao.salvarOuAlterar(funcionarioConferente, sessao);
        sessao.close();
    }

}
