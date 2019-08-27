package br.com.glp.util;

import br.com.glp.dao.FuncionarioDao;
import br.com.glp.dao.FuncionarioDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PerfilDao;
import br.com.glp.dao.PerfilDaoImpl;
import br.com.glp.dao.ProdutoDao;
import br.com.glp.dao.ProdutoDaoImpl;
import br.com.glp.model.Contato;
import br.com.glp.model.Endereco;
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

    Session sessao = HibernateUtil.abreSessao();

    public void iniciarPerfils() {

        PerfilDao perfilDao = new PerfilDaoImpl();
        FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();

        //Cadastrar Funcionario Administrador
        Perfil perfilAdmin = new Perfil("ROLE_ADMIN", "usuario como permissão de administrador", "Administrador");
        Contato contatoAdmin = new Contato("(48) 3030-3030", "(48) 99999-9999", "Admin@Admin.com", true);
        Endereco enderecoAdmin = new Endereco("Admin", 100, "Admin", "Admin", "Admin", "Admin", "Admin", "Admin");
        Usuario usuarioAdmin = new Usuario("admin", "21232f297a57a5a743894a0e4a801fc3", true, perfilAdmin);
        Funcionario funcionarioAdmin = new Funcionario("3.652.652-9", "333.333.333-33", "123456789", "Masculino", usuarioAdmin, "Admin Admin", enderecoAdmin, contatoAdmin, new Date());

        contatoAdmin.setPessoa(funcionarioAdmin);
        enderecoAdmin.setPessoa(funcionarioAdmin);
        usuarioAdmin.setFuncionario(funcionarioAdmin);
        perfilDao.salvarOuAlterar(perfilAdmin, sessao);
        funcionarioDao.salvarOuAlterar(funcionarioAdmin, sessao);

        //Cadastrar Funcionario Conferente
        Perfil perfilConferente = new Perfil("ROLE_CONFERENTE", "usuario logado com perfil básico", "Conferente");
        Contato contatoConferente = new Contato("(48) 3030-3030", "(48) 99999-9999", "Conferente@Conferente.com", true);
        Endereco enderecoConferrente = new Endereco("Conferente", 100, "Conferente", "Conferente", "Conferente", "Conferente", "Conferente", "Conferente");
        Usuario usuarioConferente = new Usuario("conferente", "f62b219d91a2381d23993fa36c5b77f8", true, perfilConferente);
        Funcionario funcionarioConferente = new Funcionario("3.652.652-9", "333.333.333-33", "123456789", "Masculino", usuarioConferente, "Conferente Conferente", enderecoConferrente, contatoConferente, new Date());

        contatoConferente.setPessoa(funcionarioConferente);
        enderecoConferrente.setPessoa(funcionarioConferente);
        usuarioConferente.setFuncionario(funcionarioConferente);
        perfilDao.salvarOuAlterar(perfilConferente, sessao);
        funcionarioDao.salvarOuAlterar(funcionarioConferente, sessao);

    }

    public void inicializarProdutos() {

        ProdutoDao produtoDao = new ProdutoDaoImpl();

        //Cadastrar P13 Marca UltraGaz
        Produto p13Cheio = new Produto(0, "P13", "UltraGaz", "Cheio");
        produtoDao.salvarOuAlterar(p13Cheio, sessao);

        Produto p13Vazio = new Produto(0, "P13", "UltraGaz", "Vazio");
        produtoDao.salvarOuAlterar(p13Vazio, sessao);

        Produto p13Avariado = new Produto(0, "P13", "UltraGaz", "Avariado");
        produtoDao.salvarOuAlterar(p13Avariado, sessao);

        //Cadastrar P20 Marca UltraGaz
        Produto p20Cheio = new Produto(0, "P20", "UltraGaz", "Cheio");
        produtoDao.salvarOuAlterar(p20Cheio, sessao);

        Produto p20Vazio = new Produto(0, "P20", "UltraGaz", "Vazio");
        produtoDao.salvarOuAlterar(p20Vazio, sessao);

        Produto p20Avariado = new Produto(0, "P20", "UltraGaz", "Avariado");
        produtoDao.salvarOuAlterar(p20Avariado, sessao);

        //Cadastrar P45 Marca UltraGaz
        Produto P45Cheio = new Produto(0, "P45", "UltraGaz", "Cheio");
        produtoDao.salvarOuAlterar(P45Cheio, sessao);

        Produto P45Vazio = new Produto(0, "P45", "UltraGaz", "Vazio");
        produtoDao.salvarOuAlterar(P45Vazio, sessao);

        Produto P45Avariado = new Produto(0, "P45", "UltraGaz", "Avariado");
        produtoDao.salvarOuAlterar(P45Avariado, sessao);
    }

}
