package com.fabiok.sistemahospedaria.infra;

import com.fabiok.sistemahospedaria.application.dto.FiltroHospedeDto;
import com.fabiok.sistemahospedaria.application.dto.PageResponse;

import java.util.List;
import java.util.Optional;

public interface Idao <T>{
	void save(T entity);
	PageResponse<T> findAll(FiltroHospedeDto filtroHospedeDto);
	Optional<T> findById(Integer id);
	void update(T entity);
	void delete(Integer id);
}