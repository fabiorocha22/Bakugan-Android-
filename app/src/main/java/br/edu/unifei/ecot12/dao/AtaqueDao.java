package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.Ataque;

public class AtaqueDao extends BaseDaoImpl<Ataque, Integer> {
    public AtaqueDao(ConnectionSource cs) throws SQLException {
        super(Ataque.class);
        setConnectionSource(cs);
        initialize();
    }
}
