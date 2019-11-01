package br.com.glp.dao;

import br.com.glp.model.Estoque;
import java.sql.Time;
import java.util.Date;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Dhyego Pedroso
 */
public class EstoqueDaoImplTest {

    Session session;
    Estoque estoque;
    EstoqueDao estoqueDao;

    public EstoqueDaoImplTest() {
        estoqueDao = new EstoqueDaoImpl();
    }

    @Test
    public void testPesquisaEntidadeId() {
    }

    @Test
    public void testListaTodos() {

        try {

            session = HibernateUtil.abreSessao();
            
            estoque = new Estoque();

            estoque.setData(new Date());
            estoque.setHora(new Date());
            estoque.setSituacao("Aberto");
            estoqueDao.salvarOuAlterar(estoque, session);

        } catch (Exception e) {
            session.close();
        }
    }

    @Test
    public void testPesquisaPorNome() {
    }

    @Test
    public void testUltimaSituacaoEstoque() {

        try {

            session = HibernateUtil.abreSessao();

            Long id = estoqueDao.ultimoIdEstoque(session);

            System.out.println("id: " + id);

        } catch (Exception e) {
        }

    }

}
