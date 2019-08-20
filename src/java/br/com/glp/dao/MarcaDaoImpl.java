package br.com.glp.dao;

import br.com.glp.model.Marca;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public class MarcaDaoImpl extends BaseDaoImpl<Marca, Long> implements MarcaDao {

    @Override
    public Marca pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Marca) session.get(Marca.class, id);
    }

    @Override
    public List<Marca> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Marca").list();
    }

    @Override
    public List<Marca> pesquisaPorNome(String nomeMarca, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Marca m where m.nomeMarca like :nomeMarca");
        consulta.setParameter("nomeMarca", nomeMarca + "%");
        return consulta.list();
    }

}
