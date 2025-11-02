package com.fabiok.sistemahospedaria.infra;

import java.sql.Connection;
import java.sql.SQLException;

public interface IdaoRelation<T> {
    public int save(Connection conn, T entity) throws SQLException;
}
