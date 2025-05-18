package dao;

import model.Aluno;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    // Inserir novo aluno
    public boolean insert(Aluno aluno) {
        String sql = "INSERT INTO Alunos (nome_aluno, matricula, data_nascimento) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNomeAluno());
            stmt.setString(2, aluno.getMatricula());
            stmt.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(AlunoDAO.class.getName()).log(java.util.logging.Level.SEVERE, "Erro ao cadastrar aluno", e);
            return false;
        }
    }


    // Atualizar dados do aluno
    public void update(Aluno aluno) throws SQLException {
        String sql = "UPDATE Alunos SET nome_aluno = ?, matricula = ?, data_nascimento = ? WHERE id_aluno = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNomeAluno());
            stmt.setString(2, aluno.getMatricula());
            stmt.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            stmt.setInt(4, aluno.getIdAluno());

            stmt.executeUpdate();
        }
    }

    // Deletar aluno pelo ID
    public void delete(int idAluno) throws SQLException {
        String sql = "DELETE FROM Alunos WHERE id_aluno = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            stmt.executeUpdate();
        }
    }

    // Buscar aluno pelo ID
    public Aluno findById(int idAluno) throws SQLException {
        String sql = "SELECT * FROM Alunos WHERE id_aluno = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToAluno(rs);
                } else {
                    return null;
                }
            }
        }
    }

    // Buscar todos os alunos
    public List<Aluno> findAll() throws SQLException {
        String sql = "SELECT * FROM Alunos";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                alunos.add(mapRowToAluno(rs));
            }
        }
        return alunos;
    }

    // Mapeia ResultSet para objeto Aluno
    private Aluno mapRowToAluno(ResultSet rs) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setIdAluno(rs.getInt("id_aluno"));
        aluno.setNomeAluno(rs.getString("nome_aluno"));
        aluno.setMatricula(rs.getString("matricula"));
        aluno.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        return aluno;
    }
}
