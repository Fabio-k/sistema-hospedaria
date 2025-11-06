package com.fabiok.sistemahospedaria.infra;

import java.util.List;

public interface Idao <T>{
	void save(T entity);
	List<T> findAll();
}