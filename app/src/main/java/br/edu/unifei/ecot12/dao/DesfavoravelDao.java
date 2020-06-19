package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.Desfavoravel;

public class DesfavoravelDao extends BaseDaoImpl<Desfavoravel, Integer> {
    public DesfavoravelDao(ConnectionSource cs) throws SQLException {
        super(Desfavoravel.class);
        setConnectionSource(cs);
        initialize();
    }
}