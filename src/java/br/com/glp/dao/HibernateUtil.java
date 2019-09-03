package br.com.glp.dao;

import br.com.glp.model.Caminhao;
import br.com.glp.model.Cliente;
import br.com.glp.model.Contato;
import br.com.glp.model.Endereco;
import br.com.glp.model.Funcionario;
import br.com.glp.model.ItemPedido;
import br.com.glp.model.Pedido;
import br.com.glp.model.Perfil;
import br.com.glp.model.Pessoa;
import br.com.glp.model.Produto;
import br.com.glp.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Dhyego Pedroso
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {

            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Pessoa.class);
            cfg.addAnnotatedClass(Perfil.class);
            cfg.addAnnotatedClass(Usuario.class);
            cfg.addAnnotatedClass(Funcionario.class);
            cfg.addAnnotatedClass(Cliente.class);
            cfg.addAnnotatedClass(Caminhao.class);
            cfg.addAnnotatedClass(Endereco.class);
            cfg.addAnnotatedClass(Contato.class);
            cfg.addAnnotatedClass(Produto.class);
            cfg.addAnnotatedClass(Pedido.class);
            cfg.addAnnotatedClass(ItemPedido.class);

            cfg.configure("/br/com/glp/dao/hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Erro ao construir session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session abreSessao() {
        return sessionFactory.openSession();

    }
}
