package br.com.glp.dao;

import br.com.glp.model.Estoque;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public class EstoqueDaoImpl extends BaseDaoImpl<Estoque, Long> implements EstoqueDao {

    @Override
    public Estoque pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Estoque) session.get(Estoque.class, id);
    }

    @Override
    public List<Estoque> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Estoque").list();
    }

    @Override
    public List<Estoque> pesquisaPorNome(String nome, Session session) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Estoque> ultimaSituacaoEstoque(Session session) throws HibernateException {
        Query consulta = session.createQuery("FROM Estoque e ORDER BY e.id DESC");
        consulta.setMaxResults(1);
        return consulta.list();
    }

    @Override
    public Long ultimoIdEstoque(Session session) throws HibernateException {
        Query consulta = session.createQuery("SELECT MAX(e.id) FROM Estoque e");
        return (Long) consulta.uniqueResult();
    }
}
