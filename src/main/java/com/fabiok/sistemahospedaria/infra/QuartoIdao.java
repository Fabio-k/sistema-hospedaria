package com.fabiok.sistemahospedaria.infra;
import com.fabiok.sistemahospedaria.domain.Quarto;

import java.sql.DriverManager;
import java.sql.SQLException;

public class QuartoIdao implements Idao<Quarto> {
	public void save(Quarto quarto){
		var sql = "INSERT INTO quarto(qua_numero, qua_tipo_quarto, qua_capacidade_adultos, qua_capacidade_criancas, qua_status, qua_preco_base) VALUES(?,?,?,?,?,?)";
		var url = "jdbc:sqlite:hospedaria.db";
		try(var conn = DriverManager.getConnection(url); var pstm = conn.prepareStatement(sql)){
			pstm.setString(1, quarto.getNumero());
			pstm.setString(2, quarto.getTipoQuarto());
			pstm.setInt(3, quarto.getCapacidadeAdultos());
			pstm.setInt(4, quarto.getCapacidadeCriancas());
			pstm.setString(5, quarto.getStatus());
			pstm.setBigDecimal(6, quarto.getPreco());
			pstm.executeUpdate();

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}