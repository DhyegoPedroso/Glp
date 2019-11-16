package br.com.glp.dao;

import br.com.glp.model.GraficoPedidosTotalMesAno;
import br.com.glp.model.Pedido;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */
public interface PedidoDao extends BaseDao<Pedido, Long> {

    public List<Pedido> pesquisaPedidoNomeSocial(String nomeSocial, Session session) throws HibernateException;

    public List<Pedido> pesquisaPedidoClienteDataInicioFim(String nomeSocial, Date inicio, Date fim, Session session) throws HibernateException;

    public List<Pedido> listarTodosPedidoDataInicioFim(Date inicio, Date fim, Session session) throws HibernateException;

    public List<GraficoPedidosTotalMesAno> totalMesPedidos(Session session) throws HibernateException;

    public Long totalQtdeMaxPedido(Session session) throws HibernateException;

    public BigInteger totalPedidoAno(Session session) throws HibernateException;
}
