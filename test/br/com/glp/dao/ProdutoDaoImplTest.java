package br.com.glp.dao;

import br.com.glp.model.Produto;
import java.util.List;
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
        produto = produtoDao.pesquisaEntidadeId(3L, session);

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

        for (Produto produto1 : produtos) {
            System.out.println("\n");
            System.out.println("-----------------------------------");
            System.out.println("\n");
            System.out.println("Produto: "+produto1.getNomeProduto());
            System.out.println("Marca: "+produto1.getMarca().getNomeMarca());
            System.out.println("Situação: "+produto1.getSituacao().getNomeSituacao());
            System.out.println("\n");
            System.out.println("-----------------------------------");
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
