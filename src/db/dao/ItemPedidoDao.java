package db.dao;

import entities.Cliente;
import entities.ItemPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemPedidoDao {

    private Connection conn;

    public ItemPedidoDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(ItemPedido item, int idPedido, int idProduto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO item_pedido (quantidade, preco, id_pedido, id_produto) VALUES (?, ?, ?, ?)"
            );

            st.setInt(1, item.getQuantidade());
            st.setDouble(2, item.getProduto().getPreco());
            st.setInt(3, idPedido);
            st.setInt(4, idProduto);

            st.executeUpdate();
            System.out.println("Item inserido no pedido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar item do pedido: " + e.getMessage());
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
