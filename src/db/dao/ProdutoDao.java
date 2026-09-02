package db.dao;

import entities.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDao {

    private Connection conn;

    public ProdutoDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO produto (nome, preco) VALUES (?, ?)"
            );

            st.setString(1, produto.getNome());
            st.setDouble(2, produto.getPreco());

            st.executeUpdate();
            System.out.println("Produto salvo no banco com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
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