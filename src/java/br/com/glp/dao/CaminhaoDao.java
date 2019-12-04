package br.com.glp.dao;

import br.com.glp.model.Caminhao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author rossi
 */
public interface CaminhaoDao extends BaseDao<Caminhao, Long> {
    
    public List<Caminhao> pesquisaPlaca(String placaCaminhao, Session session) throws HibernateException;
    
    public List<Caminhao> pesquisaCaminhaoCliente(Long idCliente, Session session) throws HibernateException;
    
    public void salvarCaminhao(Caminhao caminhao, Session session) throws HibernateException;
    
}
