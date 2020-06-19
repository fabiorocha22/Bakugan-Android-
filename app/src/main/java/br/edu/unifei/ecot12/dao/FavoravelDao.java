package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.Bakugan;
import br.edu.unifei.ecot12.bakugan.Favoravel;

public class FavoravelDao extends BaseDaoImpl<Favoravel, Integer> {
    public FavoravelDao(ConnectionSource cs) throws SQLException {
        super(Favoravel.class);
        setConnectionSource(cs);
        initialize();
    }
}