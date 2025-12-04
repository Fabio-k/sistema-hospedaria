package com.fabiok.sistemahospedaria.infra;


import com.fabiok.sistemahospedaria.application.dto.FiltroHospedeDto;
import com.fabiok.sistemahospedaria.application.dto.PageResponse;
import com.fabiok.sistemahospedaria.domain.Endereco;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;
import com.fabiok.sistemahospedaria.domain.hospede.HospedeStatus;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;


public class HospedePostgresDao implements HospedeDao {
    private IdaoRelation<Endereco> enderecoDao = new EnderecoPostgresDao();

    @Override
    public void save(Hospede entity) {
		String sql = "INSERT INTO hospede (hos_nome_completo, hos_cpf,hos_data_nascimento, hos_telefone, hos_email, hos_status) VALUES (?, ?, ?, ?, ?,?);";
        try (var conn = PostgresConnection.getConnection()){
            conn.setAutoCommit(false);

            try(var pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, entity.getNomeCompleto());
                pstm.setString(2, entity.getCpf());
                pstm.setDate(3, Date.valueOf(entity.getDataNascimento()));
                pstm.setString(4, entity.getTelefone());
                pstm.setString(5, entity.getEmail());
                pstm.setString(6, entity.getStatus().toString());
                pstm.executeUpdate();
                try(var rs = pstm.getGeneratedKeys()) {
                    if(rs.next()) entity.setId(rs.getInt(1));
                }
            }
            entity.getEndereco().setHospedeId(entity.getId());
            enderecoDao.save(conn, entity.getEndereco());
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Hospede entity) {
        String sql = """
            UPDATE hospede
            SET
                hos_nome_completo = ?,
                hos_cpf = ?,
                hos_data_nascimento = ?,
                hos_telefone = ?,
                hos_email = ?,
                hos_status = ?
            WHERE hos_id = ?;
        """;

        try (var conn = PostgresConnection.getConnection()) {
            conn.setAutoCommit(false);

            enderecoDao.update(conn, entity.getEndereco());
            try (var pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, entity.getNomeCompleto());
                pstm.setString(2, entity.getCpf());
                pstm.setDate(3, Date.valueOf(entity.getDataNascimento()));
                pstm.setString(4, entity.getTelefone());
                pstm.setString(5, entity.getEmail());
                pstm.setString(6, entity.getStatus().toString());
                pstm.setInt(7, entity.getId());
                pstm.executeUpdate();
            }
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE from hospede WHERE hos_id = ?;";
        try(var conn = PostgresConnection.getConnection(); var pstm = conn.prepareStatement(sql)){
                    pstm.setInt(1, id);
                    pstm.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean existsByCpf(String cpf, Integer id) {
        String sql;
        boolean isUpdate = id != null;

        if (isUpdate) {
            sql = "SELECT 1 FROM hospede WHERE hos_cpf = ? AND hos_id != ?;";
        } else {
            sql = "SELECT 1 FROM hospede WHERE hos_cpf = ?;";
        }

        try (var conn = PostgresConnection.getConnection();
             var psmt = conn.prepareStatement(sql)) {

            psmt.setString(1, cpf.replaceAll("\\D", ""));
            if (isUpdate) psmt.setInt(2, id);

            try (var rs = psmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public PageResponse<Hospede> findAll(FiltroHospedeDto filtroHospedeDto) {
        StringBuilder whereString = new StringBuilder(
                " WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if(filtroHospedeDto.termo() != null && !filtroHospedeDto.termo().isBlank()){
            whereString.append(" AND (h.hos_nome_completo ILIKE ? ");
            whereString.append(" OR h.hos_email LIKE ? ");
            whereString.append(" OR h.hos_cpf LIKE ? ");
            whereString.append(" OR h.hos_telefone LIKE ?) ");
            params.add("%" + filtroHospedeDto.termo() + "%");
            params.add("%" + filtroHospedeDto.termo() + "%");
            params.add("%" + filtroHospedeDto.termo() + "%");
            params.add("%" + filtroHospedeDto.termo() + "%");
        }

        if(filtroHospedeDto.minIdade() != null){
            whereString.append(" AND h.hos_data_nascimento <= ? ");
            params.add(Date.valueOf(LocalDate.now().minusYears(filtroHospedeDto.minIdade())));
        }

        if(filtroHospedeDto.maxIdade() != null){
            whereString.append(" AND h.hos_data_nascimento >= ? ");
            params.add(Date.valueOf(LocalDate.now().minusYears(filtroHospedeDto.maxIdade())));
        }

        if(filtroHospedeDto.status() != null){
            String[] statusList = filtroHospedeDto.status().split(",");
            String placeholders = String.join(",", Collections.nCopies(statusList.length, "?"));
            whereString.append(" AND h.hos_status IN (").append(placeholders).append(")");
            params.addAll(Arrays.asList(statusList));
        }

        int size = filtroHospedeDto.size() != null && filtroHospedeDto.size() > 0
                ? filtroHospedeDto.size()
                : 10;

        int page = filtroHospedeDto.page() != null && filtroHospedeDto.page() > 0
                ? filtroHospedeDto.page()
                : 1;

        int offset = (page - 1) * size;

        String sql = "SELECT * FROM hospede h JOIN endereco e ON e.end_hos_id = h.hos_id " + whereString.toString() + " ORDER BY UPPER(hos_nome_completo) LIMIT ? OFFSET ? ";

        try (var conn = PostgresConnection.getConnection(); var psmt = conn.prepareStatement(sql);){
            Integer totalCount = getSize(whereString, params);
            params.add(size);
            params.add(offset);

            for(int i = 0; i < params.size(); i++){
                if(params.get(i) instanceof Date){
                    psmt.setDate(i+ 1, (Date) params.get(i));
                    continue;
                }
                psmt.setObject(i + 1, params.get(i));
            }
            try(var rs = psmt.executeQuery()){
                List<Hospede> hospedes = new ArrayList<>();
				while (rs.next()) {
					hospedes.add(gerarHospede(rs));
				}

                int totalPages = (int) Math.ceil((double) totalCount / size);
				return new PageResponse<Hospede>(hospedes, page, size,
                        totalCount, totalPages,
                        page < totalPages,
                        page > 1);
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
	}

	public Optional<Hospede> findById(Integer id){
		String sql = "SELECT * FROM hospede h JOIN endereco e ON h.hos_id = e.end_hos_id WHERE hos_id = ?;";
		try (var conn = PostgresConnection.getConnection(); var psmt = conn.prepareStatement(sql);){
			psmt.setInt(1, id);
            try(var rs = psmt.executeQuery()){
                Hospede hospede = null;
				if (rs.next()) {
					hospede = gerarHospede(rs);
                    return Optional.of(hospede);
                }
				return Optional.empty();
            }
        }catch (SQLException e){
            e.printStackTrace();
            return Optional.empty();
        }

	}

    private Hospede gerarHospede(ResultSet rs) throws SQLException{
		Endereco endereco = new Endereco(rs.getInt("end_id"), rs.getString("end_cep"), 
		rs.getString("end_logradouro"), rs.getString("end_cidade"), rs.getString("end_bairro"), rs.getString("end_numero"), rs.getString("end_complemento"), rs.getString("end_estado"));
        endereco.setHospedeId(rs.getInt("end_hos_id"));

        HospedeStatus hospedeStatus = rs.getString("hos_status").equals(HospedeStatus.ATIVO.toString()) ? HospedeStatus.ATIVO : HospedeStatus.INATIVO;
					
		return new Hospede(rs.getInt("hos_id"), rs.getString("hos_nome_completo"), rs.getString("hos_cpf"), 
		rs.getDate("hos_data_nascimento").toLocalDate(), rs.getString("hos_telefone"), rs.getString("hos_email"), endereco, hospedeStatus);
	}

    private Integer getSize(StringBuilder whereString, List<Object> params) throws SQLException {
        String sql = "SELECT COUNT(*) FROM hospede h " + whereString.toString();

        try (var conn = PostgresConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            for(int i = 0; i < params.size(); i++){
                if(params.get(i) instanceof Date){
                    stmt.setDate(i+ 1, (Date) params.get(i));
                    continue;
                }
                stmt.setObject(i + 1, params.get(i));
            }
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}
