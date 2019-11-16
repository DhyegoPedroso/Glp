package br.com.glp.dao;

import br.com.glp.model.GraficoProdutosTotalMesAno;
import br.com.glp.model.ItemPedido;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */
public interface ItemPedidoDao extends BaseDao<ItemPedido, Long> {

    public List<ItemPedido> pesquisaProdutoDataInicioFim(String nomeProduto, Date inicio, Date fim, Session session) throws HibernateException;

    public List<ItemPedido> listarTodosProdutosDataInicioFim(Date inicio, Date fim, Session session) throws HibernateException;

    public List<GraficoProdutosTotalMesAno> totalMesProdutos(String produto, Session session) throws HibernateException;

    public List<GraficoProdutosTotalMesAno> totalMesSituacoes(String situacao, Session session) throws HibernateException;

    public Long totalQtdeMaxProduto(Session session) throws HibernateException;

    public BigInteger totalProdutoAno(Session session) throws HibernateException;

}
