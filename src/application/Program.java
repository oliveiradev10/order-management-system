package application;

import entities.Cliente;
import entities.ItemPedido;
import entities.Pedido;
import entities.Produto;
import enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;



public class Program {
    public static void main(String[] args) {


        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("BEM VINDO! VAMOS FAZER O SEU PEDIDO: ");
        System.out.println("------------------------");

        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite seu email: ");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(nome, email);

        LocalDateTime momento = LocalDateTime.now();
        StatusPedido statusPedido = StatusPedido.PROCESSANDO;

        Pedido pedido = new Pedido(momento, statusPedido, cliente);

        System.out.println("Quantos itens terá o pedido?");
        int quantidadedeItens = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < quantidadedeItens; i++){

            System.out.println("Nome do produto: ");
            String nomedoProduto = sc.nextLine();

            System.out.println("Preço do produto: ");
            double precodoProduto = sc.nextDouble();

            System.out.println("Quantidade: ");
            int quantidade = sc.nextInt();
            sc.nextLine();


            Produto produto = new Produto(nomedoProduto, precodoProduto);
            ItemPedido itemPedido = new ItemPedido(produto, quantidade);
            pedido.addItem(itemPedido);



        }
        pedido.setStatusPedido(StatusPedido.ENVIADO);
        System.out.println(pedido);



        sc.close();
    }
}

