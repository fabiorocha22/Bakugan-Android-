package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.sql.Time;

public class TimeDao extends BaseDaoImpl<Time, Integer> {
    public TimeDao(ConnectionSource cs) throws SQLException {
        super(Time.class);
        setConnectionSource(cs);
        initialize();
    }
}