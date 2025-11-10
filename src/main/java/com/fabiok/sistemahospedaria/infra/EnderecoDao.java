package com.fabiok.sistemahospedaria.infra;

import com.fabiok.sistemahospedaria.domain.Endereco;

import java.sql.Connection;
import java.sql.SQLException;

public class EnderecoDao implements IdaoRelation<Endereco> {
    @Override
    public int save(Connection conn, Endereco entity) throws SQLException{
        String sql = "INSERT INTO endereco (end_logradouro, end_numero, end_cep, end_bairro, end_complemento, end_cidade, end_estado, end_hos_id) VALUES (?,?,?,?,?,?,?,?)";
        try(var pstm = conn.prepareStatement(sql)){
            pstm.setString(1, entity.getLogradouro());
            pstm.setString(2, entity.getNumero());
            pstm.setString(3, entity.getCep());
            pstm.setString(4, entity.getBairro());
            pstm.setString(5, entity.getComplemento());
            pstm.setString(6, entity.getCidade());
            pstm.setString(7, entity.getEstado());
			pstm.setInt(8, entity.getHospedeId());
            pstm.executeUpdate();
            try(var rs = pstm.getGeneratedKeys()){
                if(rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Erro ao tentar salvar endereço");
    }

    @Override
    public void update(Connection conn, Endereco entity) throws SQLException {
        String sql = """
        UPDATE endereco
        SET
            end_logradouro = ?,
            end_numero = ?,
            end_cep = ?,
            end_bairro = ?,
            end_complemento = ?,
            end_cidade = ?,
            end_estado = ?
        WHERE end_id = ?;
        """;

        try(var pstm = conn.prepareStatement(sql)){
            pstm.setString(1, entity.getLogradouro());
            pstm.setString(2, entity.getNumero());
            pstm.setString(3, entity.getCep());
            pstm.setString(4, entity.getBairro());
            pstm.setString(5, entity.getComplemento());
            pstm.setString(6, entity.getCidade());
            pstm.setString(7, entity.getEstado());
            pstm.setInt(8, entity.getId());
            pstm.executeUpdate();
        }
    }


}
