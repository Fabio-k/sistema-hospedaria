package com.fabiok.sistemahospedaria.infra;

import java.util.List;
import java.util.Optional;

public interface Idao <T>{
	void save(T entity);
	List<T> findAll();
	Optional<T> findById(Integer id);
	void update(T entity);
	void delete(Integer id);
}