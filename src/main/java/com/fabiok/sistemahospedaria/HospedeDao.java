package com.fabiok.sistemahospedaria;


import com.fabiok.sistemahospedaria.infra.SqliteConnection;

import java.sql.Date;
import java.sql.SQLException;


public class HospedeDao implements Idao<Hospede>{
    private String sql = "INSERT INTO hospede (hos_nome_completo, hos_cpf,hos_data_nascimento, hos_telefone, hos_email) VALUES (?, ?, ?, ?, ?);";

    @Override
    public void save(Hospede entity) {
        try (var conn = SqliteConnection.getConnection(); var pstm = conn.prepareStatement(sql)){
            pstm.setString(1, entity.getNomeCompleto());
            pstm.setString(2, entity.getCpf());
            pstm.setDate(3, Date.valueOf(entity.getDataNascimento()));
            pstm.setString(4, entity.getTelefone());
            pstm.setString(5, entity.getEmail());
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
