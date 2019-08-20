package br.com.glp.dao;

import br.com.glp.model.ItemPedido;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */
public class ItemPedidoDaoImpl extends BaseDaoImpl<ItemPedido, Long> implements ItemPedidoDao {

    @Override
    public ItemPedido pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (ItemPedido) session.get(ItemPedido.class, id);
    }

    @Override
    public List<ItemPedido> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from ItemPedido").list();
    }

    @Override
    public List<ItemPedido> pesquisaPorNome(String nome, Session session) throws HibernateException {
        return null;
    }

}
