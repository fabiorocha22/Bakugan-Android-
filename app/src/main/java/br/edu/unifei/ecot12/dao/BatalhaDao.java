package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.Batalha;

public class BatalhaDao extends BaseDaoImpl<Batalha, Integer> {
    public BatalhaDao(ConnectionSource cs) throws SQLException {
        super(Batalha.class);
        setConnectionSource(cs);
        initialize();
    }
}
