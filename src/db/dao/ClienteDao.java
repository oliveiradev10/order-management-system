package db.dao;

import entities.Cliente;

import java.sql.*;

public class ClienteDao {
    private Connection conn;

    public ClienteDao(Connection conn) {
        this.conn = conn;
    }

    public int insert(Cliente cliente) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO cliente (nome, email) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, cliente.getName());
            st.setString(2, cliente.getEmail());

            int rowsAffected = st.executeUpdate();

            // 1. Ler o ID gerado pelo PostgreSQL
            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    System.out.println("Cliente salvo no banco com sucesso! ID: " + idGerado);
                    return idGerado; // Devolve o ID do cliente
                }
            }
            throw new SQLException("Erro: Cliente não foi inserido ou ID não retornado.");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
            return 0; // Retorno padrão em caso de erro
        } finally {
            // 2. Fechamento seguro de rs e st
            if (rs != null) {
                try {
                    rs.close();
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