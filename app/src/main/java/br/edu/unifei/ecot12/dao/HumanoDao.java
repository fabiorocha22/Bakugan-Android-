package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.Humano;

public class HumanoDao extends BaseDaoImpl<Humano, Integer> {
    public HumanoDao(ConnectionSource cs) throws SQLException {
        super(Humano.class);
        setConnectionSource(cs);
        initialize();
    }
}
