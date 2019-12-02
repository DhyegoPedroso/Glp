package br.com.glp.util;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.ProdutoDao;
import br.com.glp.dao.ProdutoDaoImpl;
import br.com.glp.model.Produto;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public class InicializarProduto {

    Session sessao;

    public void inicializarProdutos() {

        sessao = HibernateUtil.abreSessao();

        ProdutoDao produtoDao = new ProdutoDaoImpl();

        //Cadastrar P13 Marca UltraGaz
        Produto p13Cheio = new Produto(1500, "P13", "UltraGaz", "Cheio");
        produtoDao.salvarOuAlterar(p13Cheio, sessao);

        Produto p13Vazio = new Produto(1250, "P13", "UltraGaz", "Vazio");
        produtoDao.salvarOuAlterar(p13Vazio, sessao);

        Produto p13Avariado = new Produto(250, "P13", "UltraGaz", "Avariado");
        produtoDao.salvarOuAlterar(p13Avariado, sessao);

        //Cadastrar P20 Marca UltraGaz
        Produto p20Cheio = new Produto(850, "P20", "UltraGaz", "Cheio");
        produtoDao.salvarOuAlterar(p20Cheio, sessao);

        Produto p20Vazio = new Produto(550, "P20", "UltraGaz", "Vazio");
        produtoDao.salvarOuAlterar(p20Vazio, sessao);

        Produto p20Avariado = new Produto(169, "P20", "UltraGaz", "Avariado");
        produtoDao.salvarOuAlterar(p20Avariado, sessao);

        //Cadastrar P45 Marca UltraGaz
        Produto P45Cheio = new Produto(950, "P45", "UltraGaz", "Cheio");
        produtoDao.salvarOuAlterar(P45Cheio, sessao);

        Produto P45Vazio = new Produto(490, "P45", "UltraGaz", "Vazio");
        produtoDao.salvarOuAlterar(P45Vazio, sessao);

        Produto P45Avariado = new Produto(80, "P45", "UltraGaz", "Avariado");
        produtoDao.salvarOuAlterar(P45Avariado, sessao);

        sessao.close();
    }

}
