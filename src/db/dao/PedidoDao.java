package db.dao;

import entities.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDao {

    private Connection conn;


    public PedidoDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(Pedido pedido, int idCliente) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO pedido (momento, status, id_cliente) VALUES (?, ?, ?)"
            );

            st.setTimestamp(1, java.sql.Timestamp.valueOf(pedido.getMomento()));
            st.setString(2, pedido.getStatusPedido().name());
            st.setInt(3, idCliente);



            st.executeUpdate();
            System.out.println("Pedido adcionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adcionar pedido: " + e.getMessage());
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
