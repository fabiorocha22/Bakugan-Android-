package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.CampoDeBatalha;

public class CampoDeBatalhaDao extends BaseDaoImpl<CampoDeBatalha, Integer> {
    public CampoDeBatalhaDao(ConnectionSource cs) throws SQLException {
        super(CampoDeBatalha.class);
        setConnectionSource(cs);
        initialize();
    }
}
