package db.dao;

import entities.Produto;

import java.sql.*;

public class ProdutoDao {

    private Connection conn;

    public ProdutoDao(Connection conn) {
        this.conn = conn;
    }

    public int insert(Produto produto) {
        PreparedStatement st = null;
        ResultSet rs = null;


        try {
            st = conn.prepareStatement(
                    "INSERT INTO produto (nome, preco) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, produto.getNome());
            st.setDouble(2, produto.getPreco());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    System.out.println("Produto salvo no banco com sucesso! ID: " + idGerado);
                    return idGerado;
                }
            }
            throw new SQLException("Erro: Produto não foi inserido ou ID não gerado. ");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
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