package br.com.glp.dao;

import br.com.glp.model.Situacao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public class SituacaoDaoImpl extends BaseDaoImpl<Situacao, Long> implements SituacaoDao{

     @Override
    public Situacao pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Situacao) session.get(Situacao.class, id);
    }

    @Override
    public List<Situacao> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Situacao").list();
    }

    @Override
    public List<Situacao> pesquisaPorNome(String nomeSituacao, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Situacao s where s.nomeSituacao like :nomeSituacao");
        consulta.setParameter("nomeSituacao", nomeSituacao + "%");
        return consulta.list();
    }
    
}
