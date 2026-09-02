package db.dao;

import entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDao {
    private Connection conn;

    public ClienteDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(Cliente cliente) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO cliente (nome, email) VALUES (?, ?)"
            );

            st.setString(1, cliente.getName());
            st.setString(2, cliente.getEmail());

            st.executeUpdate();
            System.out.println("Cliente salvo no banco de dados com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
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
