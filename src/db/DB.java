package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static Connection conn = null;

    // Método para abrir a conexão
    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Parâmetros de acesso ao PostgreSQL
                String url = "jdbc:postgresql://localhost:5432/sistema_pedidos"; // ou o nome da sua base
                String user = "postgres";
                String password = "33374982"; // coloque a senha do seu pgAdmin

                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão estabelecida com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao conectar: " + e.getMessage());
            }
        }
        return conn;
    }

    // Método para fechar a conexão com segurança
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}