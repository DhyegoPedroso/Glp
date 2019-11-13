package br.com.glp.dao;

import br.com.glp.model.GraficoProdutosTotalMesAno;
import br.com.glp.model.ItemPedido;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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

    @Override
    public List<ItemPedido> pesquisaProdutoDataInicioFim(String nomeProduto, Date inicio, Date fim, Session session) throws HibernateException {
        Query consulta = session.createQuery(
                "select i from ItemPedido i join i.pedido ip "
                + "where i.produto.nomeProduto  like :nomeProduto "
                + "and ip.cadastro between :dataInicio and :dataFinal "
        );
        consulta.setParameter("dataFinal", fim);
        consulta.setParameter("dataInicio", inicio);
        consulta.setParameter("nomeProduto", "%" + nomeProduto + "%");
        return consulta.list();
    }

    @Override
    public List<ItemPedido> listarTodosProdutosDataInicioFim(Date inicio, Date fim, Session session) throws HibernateException {
        Query consulta = session.createQuery(
                "select i from ItemPedido i join i.pedido ip "
                + "where ip.cadastro between :dataInicio and :dataFinal "
        );
        consulta.setParameter("dataFinal", fim);
        consulta.setParameter("dataInicio", inicio);
        return consulta.list();
    }

    @Override
    public List<GraficoProdutosTotalMesAno> totalMesProdutos(String produto, Session session) throws HibernateException {
        Query consulta = session.createQuery("SELECT month(p.cadastro) as mes, pt.nomeProduto as produto, count(ip.produto) as quantidade "
                + "FROM ItemPedido ip "
                + "JOIN ip.pedido p "
                + "JOIN ip.produto pt "
                + "WHERE pt.nomeProduto like :produto "
                + "GROUP BY month(p.cadastro), pt.nomeProduto "
                + "ORDER BY pt.nomeProduto, p.cadastro ");
        consulta.setParameter("produto", produto);

        return consulta.list();
    }

    @Override
    public Long totalQtdeMaxProduto(Session session) throws HibernateException {
        Query consulta = session.createQuery("SELECT COUNT(ip.produto) as quantidade "
                + "FROM ItemPedido ip "
                + "JOIN ip.pedido p "
                + "JOIN ip.produto pt "
                + "GROUP BY month(p.cadastro), pt.nomeProduto "
                + "ORDER BY quantidade DESC ");
        consulta.setMaxResults(1);
        return (Long) consulta.uniqueResult();
    }

}
