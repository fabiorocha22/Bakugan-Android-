package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.Bakugan;

public class BakuganDao extends BaseDaoImpl<Bakugan, Integer> {
    public BakuganDao(ConnectionSource cs) throws SQLException {
        super(Bakugan.class);
        setConnectionSource(cs);
        initialize();
    }
}
