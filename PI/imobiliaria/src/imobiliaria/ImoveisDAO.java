/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imobiliaria;


import java.util.List; 
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aln
 */
public class ImoveisDAO {

    // cadastrar imoveis no banco de dados imobiliaria
    public boolean cadastrarImovel(ImoveisDTO imovel) {
        try (Connection conn = ConectaDAO.getConnection()) {
            String sql = "INSERT INTO imoveis (imovel, endereco, preco) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, imovel.getImovel());
                stmt.setString(2, imovel.getEndereco());
                stmt.setDouble(3, imovel.getPreco());

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;  // retorno do cadastro
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
// pesquisar imoveis no banco de dados imobiliaria
public List<ImoveisDTO> pesquisarImoveisPorTipo(String tipoImovel) {
    List<ImoveisDTO> imoveisList = new ArrayList<>();
    try (Connection conn = ConectaDAO.getConnection()) {
        String sql = "SELECT * FROM imoveis WHERE imovel = ?"; // consulta banco
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipoImovel); 
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ImoveisDTO imovelDTO = new ImoveisDTO();
                imovelDTO.setProprietario(rs.getString("proprietario"));
                imovelDTO.setEndereco(rs.getString("endereco"));
                imovelDTO.setPreco(rs.getDouble("valor_imovel"));
                imovelDTO.setAluguel(rs.getDouble("aluguel"));
                imovelDTO.setImovel(rs.getString("imovel"));
                imoveisList.add(imovelDTO);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return imoveisList;
}

    
    // verificar usuarios no banco de dados imobiliaria
    public boolean verificarCredenciais(String nome, String senha) {
        try (Connection conn = ConectaDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE nome = ? AND senha = ?")) {

            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // retorno caso êxito
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
