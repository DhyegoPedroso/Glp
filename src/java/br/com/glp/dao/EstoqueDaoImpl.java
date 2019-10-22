package br.com.glp.dao;

import br.com.glp.model.Estoque;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class EstoqueDaoImpl extends BaseDaoImpl<Estoque, Long> implements EstoqueDao, Serializable{

    @Override
    public Estoque pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        Estoque estoque = (Estoque) session.get(Estoque.class, session);
        return estoque;
    }

    @Override
    public List<Estoque> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Estoque").list();
    }

    @Override
    public List<Estoque> pesquisaPorNome(String nome, Session session) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
