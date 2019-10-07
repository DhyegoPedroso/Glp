package br.com.glp.dao;

import br.com.glp.model.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public interface UsuarioDao extends BaseDao<Usuario, Long> {

    public Usuario pesquisaPorLogin(String login, Session session) throws HibernateException;

    public List<String> pesquisarPorLoginAutoComplete(String login, Session session) throws HibernateException;

}
