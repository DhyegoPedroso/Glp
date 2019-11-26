package br.com.glp.dao;

import br.com.glp.model.GraficoPedidosTotalMesAno;
import br.com.glp.model.Pedido;
import br.com.glp.model.RelatorioPedido;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */
public class PedidoDaoImpl extends BaseDaoImpl<Pedido, Long> implements PedidoDao {

    @Override
    public Pedido pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Pedido) session.get(Pedido.class, id);
    }

    @Override
    public List<Pedido> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Pedido").list();
    }

    @Override
    public List<Pedido> pesquisaPorNome(String nomeSocial, Session session) throws HibernateException {
        return null;
    }

    @Override
    public List<Pedido> pesquisaPedidoNomeSocial(String nomeSocial, Session session) throws HibernateException {
        Query consulta = session.createQuery("select p from Pedido p join p.cliente c where c.nomeSocial like :nomeSocial");
        consulta.setParameter("nomeSocial", "%" + nomeSocial + "%");
        return consulta.list();
    }

    @Override
    public List<Pedido> pesquisaPedidoClienteDataInicioFim(String nomeSocial, Date inicio, Date fim, Session session) throws HibernateException {
        Query consulta = session.createQuery(
                "select p from Pedido p join p.cliente c "
                + "where c.nomeSocial like :nomeSocial "
                + "and p.cadastro between :dataInicio and :dataFinal "
        );
        consulta.setParameter("dataFinal", fim);
        consulta.setParameter("dataInicio", inicio);
        consulta.setParameter("nomeSocial", "%" + nomeSocial + "%");
        return consulta.list();
    }

    @Override
    public List<Pedido> listarTodosPedidoDataInicioFim(Date inicio, Date fim, Session session) throws HibernateException {
        Query consulta = session.createQuery(
                "select p from Pedido p join p.cliente c "
                + "where p.cadastro between :dataInicio and :dataFinal "
        );
        consulta.setParameter("dataFinal", fim);
        consulta.setParameter("dataInicio", inicio);
        return consulta.list();
    }

    @Override
    public List<GraficoPedidosTotalMesAno> totalMesPedidos(Session session) throws HibernateException {
        Query consulta = session.createQuery("select month(cadastro) as mes, count(id) as total "
                + " from Pedido group by month(cadastro)");

        return consulta.list();
    }

    @Override
    public BigInteger totalQtdeMaxPedido(Session session) throws HibernateException {
        Query consulta = session.createSQLQuery("select count(id) as quantidade "
                + "from Pedido "
                + "where year(current_date()) "
                + "group by month(cadastro) "
                + "order by quantidade desc limit 1 ");
        return (BigInteger) consulta.uniqueResult();
    }

    @Override
    public BigInteger totalPedidoAno(Session session) throws HibernateException {
        Query consulta = session.createSQLQuery("SELECT COUNT(id) FROM Pedido p WHERE year(current_date()) ");
        return (BigInteger) consulta.uniqueResult();
    }

    @Override
    public List<RelatorioPedido> listarTodoMobile(Session session) throws HibernateException {
        List pedidos = session.createQuery("Select p.cadastro, c.nome as cliente, ca.nomeMotorista as motorista, ca.placaCaminhao as placa,"
                + " c.cnpj as cnpj from Pedido p join p.cliente c join p.caminhao ca where p.id < 10").list();

        List<RelatorioPedido> relatorios = new ArrayList<>();

        if (!pedidos.isEmpty()) {
            Object[] item;
            RelatorioPedido relatorioPedido;
            for (Object pedido : pedidos) {
                item = (Object[]) pedido;
                relatorioPedido = new RelatorioPedido(item[0].toString(), item[1].toString(), item[2].toString(), item[3].toString(), item[4].toString());
                relatorios.add(relatorioPedido);
            }
        }

        return relatorios;
    }
}
