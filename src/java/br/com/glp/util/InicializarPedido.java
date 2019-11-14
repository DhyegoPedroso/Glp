package br.com.glp.util;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.model.Caminhao;
import br.com.glp.model.Cliente;
import br.com.glp.model.Pedido;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public class InicializarPedido {

    Session session;

    public void inicializarPedido() throws ParseException {
        session = HibernateUtil.abreSessao();
        for (int i = 0; i < 2000; i++) {

            try {

                Cliente cliente = new Cliente();
                cliente = gerarCliente(3,10);

                Pedido pedido = new Pedido(gerarDataCadastro(), cliente, gerarCaminhao(cliente), gerarNotaFiscal(100000000, 999999999));
                PedidoDao pedidoDao = new PedidoDaoImpl();
                pedidoDao.salvarOuAlterar(pedido, session);

            } catch (Exception e) {
            }

        }
        session.close();

    }

    public static Cliente gerarCliente(int incio, int fim) {
        Cliente cliente = new Cliente();
        cliente.setId(incio + (Long) Math.round(Math.random() * (fim - incio)));
        return cliente;
    }

    public static Caminhao gerarCaminhao(Cliente cliente) {
        Caminhao caminhao = new Caminhao();

        if (cliente.getId() == 3) {
            caminhao.setId(1 + (Long) Math.round(Math.random() * (3 - 1)));
        } else if (cliente.getId() == 4) {
            caminhao.setId(4 + (Long) Math.round(Math.random() * (6 - 4)));
        } else if (cliente.getId() == 5) {
            caminhao.setId(7 + (Long) Math.round(Math.random() * (9 - 7)));
        } else if (cliente.getId() == 6) {
            caminhao.setId(10 + (Long) Math.round(Math.random() * (12 - 10)));
        } else if (cliente.getId() == 7) {
            caminhao.setId(13 + (Long) Math.round(Math.random() * (15 - 13)));
        } else if (cliente.getId() == 8) {
            caminhao.setId(16 + (Long) Math.round(Math.random() * (18 - 16)));
        } else if (cliente.getId() == 9) {
            caminhao.setId(19 + (Long) Math.round(Math.random() * (21 - 19)));
        } else{
            caminhao.setId(22 + (Long) Math.round(Math.random() * (24 - 22)));
        }

        return caminhao;
    }

    public static String gerarNotaFiscal(int incio, int fim) {
        String notaFiscal = String.valueOf(incio + (int) Math.round(Math.random() * (fim - incio)));
        return notaFiscal;
    }

    public static String gerarDia(int incio, int fim) {
        String dia = String.valueOf(incio + (int) Math.round(Math.random() * (fim - incio)));
        if (dia.length() == 1) {
            dia = "0" + dia;
        }
        return dia;
    }

    public static String gerarMes(int incio, int fim) {
        String mes = String.valueOf(incio + (int) Math.round(Math.random() * (fim - incio)));
        if (mes.length() == 1) {
            mes = "0" + mes;
        }
        return mes;
    }

    public static String gerarAno(int incio, int fim) {
        String ano = String.valueOf(incio + (int) Math.round(Math.random() * (fim - incio)));
        return ano;
    }

    public static String gerarHora(int incio, int fim) {
        String hora = String.valueOf(incio + (int) Math.round(Math.random() * (fim - incio)));
        if (hora.length() == 1) {
            hora = "0" + hora;
        }
        return hora;
    }

    public static String gerarMinuto(int incio, int fim) {
        String minuto = String.valueOf(incio + (int) Math.round(Math.random() * (fim - incio)));
        if (minuto.length() == 1) {
            minuto = "0" + minuto;
        }
        return minuto;
    }

    public static Date gerarDataCadastro() throws ParseException {

        String ano = gerarAno(2019, 2019);
        String mes = gerarMes(01, 12);
        String dia = gerarDia(01, 28);
        String hora = gerarHora(0, 23);
        String minuto = gerarMinuto(0, 59);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = formato.parse(dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto + ":00");

        return data;
    }

}
