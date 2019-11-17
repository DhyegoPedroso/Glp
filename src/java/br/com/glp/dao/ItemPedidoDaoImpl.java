package br.com.glp.dao;

import br.com.glp.model.GraficoProdutosTotalMesAno;
import br.com.glp.model.ItemPedido;
import java.math.BigInteger;
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
                + "ORDER BY p.cadastro ");
        consulta.setParameter("produto", produto);

        return consulta.list();
    }

    @Override
    public BigInteger totalQtdeMaxProduto(Session session) throws HibernateException {
        Query consulta = session.createSQLQuery("SELECT "
                + "COUNT(ip.id) AS quantidade "
                + "FROM "
                + "ItemPedido ip "
                + "JOIN "
                + "pedido p on ip.idPedido = p.id "
                + "JOIN "
                + "produto pt on ip.idProduto = pt.id "
                + "WHERE "
                + "YEAR(CURRENT_DATE()) "
                + "GROUP BY MONTH(p.cadastro) , pt.nomeProduto "
                + "ORDER BY quantidade DESC limit 1 ");
        return (BigInteger) consulta.uniqueResult();
    }

    @Override
    public List<GraficoProdutosTotalMesAno> totalMesSituacoes(String situacao, Session session) throws HibernateException {
        Query consulta = session.createQuery("SELECT month(p.cadastro) as mes, pt.situacao as situacao, count(ip.produto) as quantidade "
                + "FROM ItemPedido ip "
                + "JOIN ip.pedido p "
                + "JOIN ip.produto pt "
                + "WHERE pt.situacao like :situacao "
                + "GROUP BY month(p.cadastro), pt.situacao "
                + "ORDER BY p.cadastro ");
        consulta.setParameter("situacao", situacao);
        return consulta.list();
    }

    @Override
    public BigInteger totalProdutoAno(Session session) throws HibernateException {
        Query consulta = session.createSQLQuery("select count(itempedido.id) from itempedido join pedido "
                + "on itempedido.idPedido = pedido.id where year(current_date())");
        return (BigInteger) consulta.uniqueResult();
    }

    @Override
    public BigInteger totalQtdeMaxSituacoes(Session session) throws HibernateException {
        Query consulta = session.createSQLQuery("SELECT "
                + "COUNT(ip.id) AS quantidade "
                + "FROM "
                + "ItemPedido ip "
                + "JOIN "
                + "pedido p on ip.idPedido = p.id "
                + "JOIN "
                + "produto pt on ip.idProduto = pt.id "
                + "WHERE "
                + "YEAR(CURRENT_DATE()) "
                + "GROUP BY MONTH(p.cadastro) , pt.situacao "
                + "ORDER BY quantidade DESC limit 1");
        return (BigInteger) consulta.uniqueResult();
    }

}
