package br.com.glp.dao;

import br.com.glp.model.Produto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Dhyego Pedroso
 */
public class ProdutoDaoImplTest {

    private Produto produto;
    private ProdutoDao produtoDao;
    private Session session;

    public ProdutoDaoImplTest() {
        produtoDao = new ProdutoDaoImpl();
    }

    @Test
    public void testPesquisaEntidadeId() {

        session = HibernateUtil.abreSessao();
        try {
            produto = produtoDao.pesquisaEntidadeId(3L, session);
        } catch (HibernateException hibernateException) {
            System.out.println("erro " + hibernateException.getMessage());
        }

        System.out.println("\n");
        System.out.println("-----------------------------------");
        System.out.println("\n");
        System.out.println("Produto: " + produto.getNomeProduto());
        System.out.println("\n");
        System.out.println("-----------------------------------");
    }

    @Test
    public void testListaTodos() {
        session = HibernateUtil.abreSessao();

        List<Produto> produtos = produtoDao.listaTodos(session);

        System.out.println("\n");
        for (Produto produto1 : produtos) {
            System.out.println("-----------------------------------");
            System.out.println("\n");
            System.out.println("Produto: " + produto1.getNomeProduto());
            System.out.println("Marca: " + produto1.getMarca());
            System.out.println("Situação: " + produto1.getSituacao());
            System.out.println("\n");
        }
    }

    @Test
    public void testPesquisaPorNome() {
    }

    @Test
    public void testListarPorTipo() {
    }

    @Test
    public void testListarPorMarca() {
    }

}
