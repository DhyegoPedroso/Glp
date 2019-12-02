package br.com.glp.util;

import br.com.glp.dao.ClienteDao;
import br.com.glp.dao.ClienteDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.model.Caminhao;
import br.com.glp.model.Cliente;
import br.com.glp.model.Contato;
import br.com.glp.model.Endereco;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public class InicializarCliente {
    
    Session sessao;
    
    public void inicializarClientes(){
        inicializarCliente1();
        inicializarCliente2();
        inicializarCliente3();
        inicializarCliente4();
        inicializarCliente5();
        inicializarCliente6();
        inicializarCliente7();
        inicializarCliente8();
        
    }

    public void inicializarCliente1() {

        sessao = HibernateUtil.abreSessao();
        
        List<Caminhao> caminhoes = new ArrayList<>();
        Caminhao caminhao1 = new Caminhao();
        caminhao1.setNomeMotorista("Renato dos Santos");
        caminhao1.setPlacaCaminhao("MKJ9852");
        caminhoes.add(caminhao1);
        Caminhao caminhao2 = new Caminhao();
        caminhao2.setNomeMotorista("Roberto da Silva");
        caminhao2.setPlacaCaminhao("MOJ4125");
        caminhoes.add(caminhao2);
        Caminhao caminhao3 = new Caminhao();
        caminhao3.setNomeMotorista("Ruan Oliveira");
        caminhao3.setPlacaCaminhao("MGF8214");
        caminhoes.add(caminhao3);
        
        Contato contato = new Contato();
        contato.setCelular("(48) 98455-8521");
        contato.setEmail("Rossi@gmail.com");
        contato.setTelefone("(48) 3033-8541");
        contato.setWhatsapp(true);
        
        Endereco endereco = new Endereco();
        endereco.setBairro("Barreiros");
        endereco.setCep("88854-400");
        endereco.setCidade("São José");
        endereco.setComplemento("Posto de Saúde");
        endereco.setLogradouro("Rua: da Chefeia");
        endereco.setNumero(254);
        endereco.setPais("Brasil");
        endereco.setUf("SC");
        endereco.setUnidade("01");

        Cliente cliente = new Cliente();
        cliente.setNome("Rossi Evangelista");
        cliente.setNomeSocial("Rossi Italiano");
        cliente.setCnpj("16.515.615/6156-15");
        cliente.setDtCadastro(new Date());
        
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        endereco.setCaminhoes(caminhoes);
        caminhao1.setEndereco(endereco);
        caminhao2.setEndereco(endereco);
        caminhao3.setEndereco(endereco);
        contato.setEndereco(endereco);
        endereco.setContato(contato);
        
        ClienteDao clienteDao = new ClienteDaoImpl();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        sessao.close();
    }

    public void inicializarCliente2() {

        sessao = HibernateUtil.abreSessao();
        
        List<Caminhao> caminhoes = new ArrayList<>();
        Caminhao caminhao1 = new Caminhao();
        caminhao1.setNomeMotorista("Lucas Lima");
        caminhao1.setPlacaCaminhao("MAS8417");
        caminhoes.add(caminhao1);
        Caminhao caminhao2 = new Caminhao();
        caminhao2.setNomeMotorista("Lorenzo Alves");
        caminhao2.setPlacaCaminhao("MGF9874");
        caminhoes.add(caminhao2);
        Caminhao caminhao3 = new Caminhao();
        caminhao3.setNomeMotorista("Luiz Nascimento");
        caminhao3.setPlacaCaminhao("MWE8745");
        caminhoes.add(caminhao3);
        
        Contato contato = new Contato();
        contato.setCelular("(48) 98955-8221");
        contato.setEmail("Leo@gmail.com");
        contato.setTelefone("(48) 2065-8541");
        contato.setWhatsapp(true);
        
        Endereco endereco = new Endereco();
        endereco.setBairro("Caminho Novo");
        endereco.setCep("88147-490");
        endereco.setCidade("Palhoça");
        endereco.setComplemento("Camelão");
        endereco.setLogradouro("Rua: do Otaku");
        endereco.setNumero(698);
        endereco.setPais("Brasil");
        endereco.setUf("SC");
        endereco.setUnidade("01");

        Cliente cliente = new Cliente();
        cliente.setNome("Leonardo Filho");
        cliente.setNomeSocial("Leo Estronda");
        cliente.setCnpj("84.515.185/4165-12");
        cliente.setDtCadastro(new Date());
        
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        endereco.setCaminhoes(caminhoes);
        caminhao1.setEndereco(endereco);
        caminhao2.setEndereco(endereco);
        caminhao3.setEndereco(endereco);
        contato.setEndereco(endereco);
        endereco.setContato(contato);
        
        ClienteDao clienteDao = new ClienteDaoImpl();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        sessao.close();
    }

    public void inicializarCliente3() {

        sessao = HibernateUtil.abreSessao();
        
        List<Caminhao> caminhoes = new ArrayList<>();
        Caminhao caminhao1 = new Caminhao();
        caminhao1.setNomeMotorista("Percy Silva");
        caminhao1.setPlacaCaminhao("MNH7895");
        caminhoes.add(caminhao1);
        Caminhao caminhao2 = new Caminhao();
        caminhao2.setNomeMotorista("Patrick Almeida");
        caminhao2.setPlacaCaminhao("MMP6854");
        caminhoes.add(caminhao2);
        Caminhao caminhao3 = new Caminhao();
        caminhao3.setNomeMotorista("Pietro Ribeiro");
        caminhao3.setPlacaCaminhao("MXA9578");
        caminhoes.add(caminhao3);
        
        Contato contato = new Contato();
        contato.setCelular("(48) 96247-2154");
        contato.setEmail("Pedro@gmail.com");
        contato.setTelefone("(48) 3205-2154");
        contato.setWhatsapp(true);
        
        Endereco endereco = new Endereco();
        endereco.setBairro("Bela Vista");
        endereco.setCep("88962-851");
        endereco.setCidade("Palhoça");
        endereco.setComplemento("Igreja");
        endereco.setLogradouro("Rua: do Peixoto");
        endereco.setNumero(874);
        endereco.setPais("Brasil");
        endereco.setUf("SC");
        endereco.setUnidade("01");

        Cliente cliente = new Cliente();
        cliente.setNome("Pedro Mattos");
        cliente.setNomeSocial("Pedro Soldado");
        cliente.setCnpj("15.615.615/1561-56");
        cliente.setDtCadastro(new Date());
        
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        endereco.setCaminhoes(caminhoes);
        caminhao1.setEndereco(endereco);
        caminhao2.setEndereco(endereco);
        caminhao3.setEndereco(endereco);
        contato.setEndereco(endereco);
        endereco.setContato(contato);
        
        ClienteDao clienteDao = new ClienteDaoImpl();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        sessao.close();
    }

    public void inicializarCliente4() {

        sessao = HibernateUtil.abreSessao();
        
        List<Caminhao> caminhoes = new ArrayList<>();
        Caminhao caminhao1 = new Caminhao();
        caminhao1.setNomeMotorista("Darci Pereira");
        caminhao1.setPlacaCaminhao("MHJ2365");
        caminhoes.add(caminhao1);
        Caminhao caminhao2 = new Caminhao();
        caminhao2.setNomeMotorista("Danilo Neto");
        caminhao2.setPlacaCaminhao("MTR1145");
        caminhoes.add(caminhao2);
        Caminhao caminhao3 = new Caminhao();
        caminhao3.setNomeMotorista("Damião Junior");
        caminhao3.setPlacaCaminhao("MGH3214");
        caminhoes.add(caminhao3);
        
        Contato contato = new Contato();
        contato.setCelular("(48) 98632-7412");
        contato.setEmail("Dhyego@gmail.com");
        contato.setTelefone("(48) 3033-6510");
        contato.setWhatsapp(true);
        
        Endereco endereco = new Endereco();
        endereco.setBairro("Sertão do Maruim");
        endereco.setCep("88122-400");
        endereco.setCidade("São José");
        endereco.setComplemento("Rio Pequeno");
        endereco.setLogradouro("Rua: Mathias Schell");
        endereco.setNumero(132);
        endereco.setPais("Brasil");
        endereco.setUf("SC");
        endereco.setUnidade("01");

        Cliente cliente = new Cliente();
        cliente.setNome("Dhyego Pedroso");
        cliente.setNomeSocial("Dhyego Tech");
        cliente.setCnpj("89.854.128/4165-82");
        cliente.setDtCadastro(new Date());
        
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        endereco.setCaminhoes(caminhoes);
        caminhao1.setEndereco(endereco);
        caminhao2.setEndereco(endereco);
        caminhao3.setEndereco(endereco);
        contato.setEndereco(endereco);
        endereco.setContato(contato);
        
        ClienteDao clienteDao = new ClienteDaoImpl();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        sessao.close();
    }

    public void inicializarCliente5() {

        sessao = HibernateUtil.abreSessao();
        
        List<Caminhao> caminhoes = new ArrayList<>();
        Caminhao caminhao1 = new Caminhao();
        caminhao1.setNomeMotorista("Silvio Martinz");
        caminhao1.setPlacaCaminhao("MAS9214");
        caminhoes.add(caminhao1);
        Caminhao caminhao2 = new Caminhao();
        caminhao2.setNomeMotorista("Adriano Martinz");
        caminhao2.setPlacaCaminhao("MPO3201");
        caminhoes.add(caminhao2);
        Caminhao caminhao3 = new Caminhao();
        caminhao3.setNomeMotorista("José Martinz");
        caminhao3.setPlacaCaminhao("MFR1717");
        caminhoes.add(caminhao3);
        
        Contato contato = new Contato();
        contato.setCelular("(48) 98517-6214");
        contato.setEmail("Tech-S@gmail.com");
        contato.setTelefone("(48) 6532-8541");
        contato.setWhatsapp(true);
        
        Endereco endereco = new Endereco();
        endereco.setBairro("Jd. Eldorado");
        endereco.setCep("32101-400");
        endereco.setCidade("Palhoça");
        endereco.setComplemento("Posto de Saúde");
        endereco.setLogradouro("Rua: Ministro Raul Machado");
        endereco.setNumero(254);
        endereco.setPais("Brasil");
        endereco.setUf("SC");
        endereco.setUnidade("01");

        Cliente cliente = new Cliente();
        cliente.setNome("Ricardo Martinz");
        cliente.setNomeSocial("Familia Martinz");
        cliente.setCnpj("58.343.847/5147-33");
        cliente.setDtCadastro(new Date());
        
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        endereco.setCaminhoes(caminhoes);
        caminhao1.setEndereco(endereco);
        caminhao2.setEndereco(endereco);
        caminhao3.setEndereco(endereco);
        contato.setEndereco(endereco);
        endereco.setContato(contato);
        
        ClienteDao clienteDao = new ClienteDaoImpl();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        sessao.close();
    }

    public void inicializarCliente6() {

        sessao = HibernateUtil.abreSessao();
        
        List<Caminhao> caminhoes = new ArrayList<>();
        Caminhao caminhao1 = new Caminhao();
        caminhao1.setNomeMotorista("Fernando Ferreiro");
        caminhao1.setPlacaCaminhao("MFF9851");
        caminhoes.add(caminhao1);
        Caminhao caminhao2 = new Caminhao();
        caminhao2.setNomeMotorista("Diego Lima");
        caminhao2.setPlacaCaminhao("MPP8430");
        caminhoes.add(caminhao2);
        Caminhao caminhao3 = new Caminhao();
        caminhao3.setNomeMotorista("Bruno Senna");
        caminhao3.setPlacaCaminhao("MAA1247");
        caminhoes.add(caminhao3);
        
        Contato contato = new Contato();
        contato.setCelular("(48) 98541-0021");
        contato.setEmail("Vitamina@gmail.com");
        contato.setTelefone("(48) 2187-1033");
        contato.setWhatsapp(true);
        
        Endereco endereco = new Endereco();
        endereco.setBairro("Capoeiras");
        endereco.setCep("32154-021");
        endereco.setCidade("Florianopolis");
        endereco.setComplemento("Rua sem Saida");
        endereco.setLogradouro("Rua: Carneiro");
        endereco.setNumero(321);
        endereco.setPais("Brasil");
        endereco.setUf("SC");
        endereco.setUnidade("01");

        Cliente cliente = new Cliente();
        cliente.setNome("Fernando Meira");
        cliente.setNomeSocial("Speed Entega");
        cliente.setCnpj("02.632.758/8541-99");
        cliente.setDtCadastro(new Date());
        
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        endereco.setCaminhoes(caminhoes);
        caminhao1.setEndereco(endereco);
        caminhao2.setEndereco(endereco);
        caminhao3.setEndereco(endereco);
        contato.setEndereco(endereco);
        endereco.setContato(contato);
        
        ClienteDao clienteDao = new ClienteDaoImpl();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        sessao.close();
    }

    public void inicializarCliente7() {

        sessao = HibernateUtil.abreSessao();
        
        List<Caminhao> caminhoes = new ArrayList<>();
        Caminhao caminhao1 = new Caminhao();
        caminhao1.setNomeMotorista("Rocher Oliveira");
        caminhao1.setPlacaCaminhao("MEQ8596");
        caminhoes.add(caminhao1);
        Caminhao caminhao2 = new Caminhao();
        caminhao2.setNomeMotorista("Fabricio Souza");
        caminhao2.setPlacaCaminhao("MJR3245");
        caminhoes.add(caminhao2);
        Caminhao caminhao3 = new Caminhao();
        caminhao3.setNomeMotorista("Valdir Aguiar");
        caminhao3.setPlacaCaminhao("MVC5512");
        caminhoes.add(caminhao3);
        
        Contato contato = new Contato();
        contato.setCelular("(48) 95214-0021");
        contato.setEmail("EntregaMaster@gmail.com");
        contato.setTelefone("(48) 2314-0012");
        contato.setWhatsapp(true);
        
        Endereco endereco = new Endereco();
        endereco.setBairro("Bela Vista");
        endereco.setCep("32101-400");
        endereco.setCidade("São José");
        endereco.setComplemento("Perto da Havan");
        endereco.setLogradouro("Rua: Ministro Raul Machado");
        endereco.setNumero(254);
        endereco.setPais("Brasil");
        endereco.setUf("SC");
        endereco.setUnidade("01");

        Cliente cliente = new Cliente();
        cliente.setNome("Amilton Damasco");
        cliente.setNomeSocial("Entrega Master");
        cliente.setCnpj("58.321.030/8541-63");
        cliente.setDtCadastro(new Date());
        
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        endereco.setCaminhoes(caminhoes);
        caminhao1.setEndereco(endereco);
        caminhao2.setEndereco(endereco);
        caminhao3.setEndereco(endereco);
        contato.setEndereco(endereco);
        endereco.setContato(contato);
        
        ClienteDao clienteDao = new ClienteDaoImpl();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        sessao.close();
    }

    public void inicializarCliente8() {

        sessao = HibernateUtil.abreSessao();
        
        List<Caminhao> caminhoes = new ArrayList<>();
        Caminhao caminhao1 = new Caminhao();
        caminhao1.setNomeMotorista("Silvio Jr.");
        caminhao1.setPlacaCaminhao("MLB1937");
        caminhoes.add(caminhao1);
        Caminhao caminhao2 = new Caminhao();
        caminhao2.setNomeMotorista("Thiago Nascimento");
        caminhao2.setPlacaCaminhao("MLQ5436");
        caminhoes.add(caminhao2);
        Caminhao caminhao3 = new Caminhao();
        caminhao3.setNomeMotorista("Eduardo Pereira");
        caminhao3.setPlacaCaminhao("MOZ8399");
        caminhoes.add(caminhao3);
        
        Contato contato = new Contato();
        contato.setCelular("(48) 98531-3154");
        contato.setEmail("TeleEntrega@gmail.com");
        contato.setTelefone("(48) 2230-8124");
        contato.setWhatsapp(true);
        
        Endereco endereco = new Endereco();
        endereco.setBairro("J. Eldorado");
        endereco.setCep("31475-541");
        endereco.setCidade("Palhoça");
        endereco.setComplemento("Proximo ao Colegio");
        endereco.setLogradouro("Rua: Mon Senhor");
        endereco.setNumero(10);
        endereco.setPais("Brasil");
        endereco.setUf("SC");
        endereco.setUnidade("01");

        Cliente cliente = new Cliente();
        cliente.setNome("Adriano Martins");
        cliente.setNomeSocial("Tele Entrega Speed");
        cliente.setCnpj("54.847.125/9841-66");
        cliente.setDtCadastro(new Date());
        
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        endereco.setCaminhoes(caminhoes);
        caminhao1.setEndereco(endereco);
        caminhao2.setEndereco(endereco);
        caminhao3.setEndereco(endereco);
        contato.setEndereco(endereco);
        endereco.setContato(contato);
        
        ClienteDao clienteDao = new ClienteDaoImpl();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        sessao.close();
    }
    
}
