package br.com.glp.dao;

import br.com.glp.model.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Long> implements UsuarioDao {

    @Override
    public Usuario pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Usuario) session.get(Usuario.class, id);
    }

    @Override
    public List<Usuario> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Usuario").list();
    }

    @Override
    public List<Usuario> pesquisaPorNome(String nome, Session session) throws HibernateException {
        return null;
    }

}
