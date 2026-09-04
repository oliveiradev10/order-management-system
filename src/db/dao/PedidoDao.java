package db.dao;

import entities.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PedidoDao {

    private Connection conn;


    public PedidoDao(Connection conn) {
        this.conn = conn;
    }

    public int insert(Pedido pedido, int idCliente) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO pedido (momento, status, id_cliente) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setTimestamp(1, java.sql.Timestamp.valueOf(pedido.getMomento()));
            st.setString(2, pedido.getStatusPedido().name());
            st.setInt(3, idCliente);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    System.out.println("Pedido adcionado com sucesso! ID: " + idGerado);
                    return idGerado;
                }
            }
            throw new SQLException("Erro: Nenhnuma linha afetada ou ID não retornado.");

        } catch (SQLException e) {
            System.out.println("Erro ao adcionar pedido: " + e.getMessage());
            return 0;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
