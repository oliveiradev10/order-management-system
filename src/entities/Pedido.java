package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import enums.StatusPedido;
import java.time.format.DateTimeFormatter;

public class Pedido {

    private LocalDateTime momento;
    private StatusPedido statusPedido;
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();


    public Pedido(LocalDateTime momento, StatusPedido statusPedido, Cliente cliente) {
        this.momento = momento;
        this.statusPedido = statusPedido;
        this.cliente = cliente;
    }

    public LocalDateTime getMomento() {
        return momento;
    }

    public void setMomento(LocalDateTime momento) {
        this.momento = momento;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void addItem(ItemPedido item) {
      itens.add(item);

    }

    public void removeItem(ItemPedido item){
        itens.remove(item);
    }


    public double total(){
        double soma = 0;
        for (ItemPedido item : itens) {
            soma += item.subTotal();
        }
            return soma;


    }

    public int quantidadeItens(){

        int quantidade = 0;

        for(ItemPedido item : itens) {
            quantidade += item.getQuantidade();
        }
        return quantidade;

    }


    @Override
    public String toString() {

        String listaItens = "";

        for(ItemPedido item : itens) {
            listaItens += item + "\n";
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return "Hora do pedido: "
                + momento.format(formato)
                + "\nStatus do pedido: "
                + statusPedido
                + "\nCliente: "
                + getCliente().getName()
                + "\nEmail: "
                + cliente.getEmail()
                + "\nItens do pedido:\n"
                + listaItens
                + "\nQuantidade de itens: "
                + quantidadeItens()
                + "\nTotal: "
                + total();

    }




}
