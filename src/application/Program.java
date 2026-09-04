package application;

import db.dao.ClienteDao;
import db.DB;
import db.dao.ItemPedidoDao;
import db.dao.PedidoDao;
import db.dao.ProdutoDao;
import entities.Cliente;
import entities.ItemPedido;
import entities.Pedido;
import entities.Produto;
import enums.StatusPedido;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        // --- 1. ABERTURA DE CONEXÃO E PREPARAÇÃO DOS DAOS ---

        Connection conn = DB.getConnection();
        ClienteDao clienteDao = new ClienteDao(conn);
        ProdutoDao produtoDao = new ProdutoDao(conn);
        PedidoDao pedidoDao = new PedidoDao(conn);
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao(conn);

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("BEM VINDO! VAMOS FAZER O SEU PEDIDO: ");
        System.out.println("------------------------");

        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite seu email: ");
        String email = sc.nextLine();

        // --- 2. PERSISTÊNCIA DO CLIENTE ---
        Cliente cliente = new Cliente(nome, email);
        int idClienteGerado = clienteDao.insert(cliente); // 1. Captura o ID retornado pelo banco

        LocalDateTime momento = LocalDateTime.now();
        StatusPedido statusPedido = StatusPedido.PROCESSANDO;

        Pedido pedido = new Pedido(momento, statusPedido, cliente);
        int idPedidoGerado = pedidoDao.insert(pedido, idClienteGerado);


        System.out.println("Quantos itens terá o pedido?");
        int quantidadedeItens = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < quantidadedeItens; i++) {

            System.out.println("Nome do produto: ");
            String nomedoProduto = sc.nextLine();

            System.out.println("Preço do produto: ");
            double precodoProduto = sc.nextDouble();

            System.out.println("Quantidade: ");
            int quantidade = sc.nextInt();
            sc.nextLine();

            Produto produto = new Produto(nomedoProduto, precodoProduto);
            int idProdutoGerado = produtoDao.insert(produto);

            ItemPedido itemPedido = new ItemPedido(produto, quantidade);
            pedido.addItem(itemPedido);

            itemPedidoDao.insert(itemPedido, idPedidoGerado, idProdutoGerado);
        }

        pedido.setStatusPedido(StatusPedido.ENVIADO);
        System.out.println(pedido);

        // --- 4. FECHAMENTO DE RECURSOS ---
        DB.closeConnection();
        sc.close();
    }
}