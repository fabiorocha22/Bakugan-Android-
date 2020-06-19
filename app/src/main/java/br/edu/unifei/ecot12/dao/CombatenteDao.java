package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.Combatente;

public class CombatenteDao extends BaseDaoImpl<Combatente, Integer> {
    public CombatenteDao(ConnectionSource cs) throws SQLException {
        super(Combatente.class);
        setConnectionSource(cs);
        initialize();
    }
}
