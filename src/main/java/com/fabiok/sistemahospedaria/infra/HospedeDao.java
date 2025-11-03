package com.fabiok.sistemahospedaria.infra;


import com.fabiok.sistemahospedaria.domain.Endereco;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;

import java.sql.Date;
import java.sql.SQLException;


public class HospedeDao implements Idao<Hospede> {
    private String sql = "INSERT INTO hospede (hos_nome_completo, hos_cpf,hos_data_nascimento, hos_telefone, hos_email, hos_end_id) VALUES (?, ?, ?, ?, ?,?);";
    private IdaoRelation<Endereco> enderecoDao = new EnderecoDao();
    @Override
    public void save(Hospede entity) {
        try (var conn = SqliteConnection.getConnection()){
            conn.setAutoCommit(false);

            int enderecoId = enderecoDao.save(conn, entity.getEndereco());
            try(var pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, entity.getNomeCompleto());
                pstm.setString(2, entity.getCpf());
                pstm.setDate(3, Date.valueOf(entity.getDataNascimento()));
                pstm.setString(4, entity.getTelefone());
                pstm.setString(5, entity.getEmail());
                pstm.setInt(6, enderecoId);
                pstm.executeUpdate();
            }
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Boolean existsByCpf(String cpf){
        String sql = "SELECT 1 FROM hospede WHERE hos_cpf = ?;";
        try (var conn = SqliteConnection.getConnection(); var psmt = conn.prepareStatement(sql);){
            psmt.setString(1, cpf.replaceAll("\\D", ""));
            try(var rs = psmt.executeQuery()){
                return rs.next();
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
