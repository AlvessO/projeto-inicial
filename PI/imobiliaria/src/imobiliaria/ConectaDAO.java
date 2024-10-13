/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package imobiliaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author aln
 */

public class ConectaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/imobiliaria";
    private static final String USER = "root";
    private static final String PASSWORD = "456";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        if (conn != null) {
            System.out.println("Conexão estabelecida com sucesso!");
        }
        return conn; // conexão estabelecida retorno
    }
}

