package br.com.glp.dao;

import br.com.glp.model.GraficoPedidosTotalMesAno;
import br.com.glp.model.Pedido;
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
        Object[] item;
        GraficoPedidosTotalMesAno grt;
        List<GraficoPedidosTotalMesAno> todosGrts = new ArrayList<>();
        Query consulta = session.createQuery("select month(cadastro) as mes, count(id) as total "
                + " from Pedido group by month(cadastro)");

        List resultados = consulta.list();

        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoPedidosTotalMesAno((int) item[0], (long) item[1]);
            todosGrts.add(grt);

        }

        return todosGrts;
    }

    @Override
    public List<GraficoPedidosTotalMesAno> totalPedidoMesAtual(Session session) throws HibernateException {
        Object[] item;
        GraficoPedidosTotalMesAno grt;
        List<GraficoPedidosTotalMesAno> todosGrts = new ArrayList<>();
        Query consulta = session.createQuery("SELECT * FROM Pedido WHERE MONTH(cadastro) =  month(CURRENT_DATE())"
                + ""
                + "select month(cadastro) as mes, count(id) as total "
                + " from Pedido group by month(cadastro)");

        List resultados = consulta.list();

        for (Object resultado : resultados) {
            item = (Object[]) resultado;
            grt = new GraficoPedidosTotalMesAno((int) item[0], (long) item[1]);
            todosGrts.add(grt);

        }

        return todosGrts;
    }
}
