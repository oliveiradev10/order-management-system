package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import enums.StatusPedido;

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

}
