package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.CartaDeHabilidade;

public class CartadeHabilidadeDao extends BaseDaoImpl<CartaDeHabilidade, Integer> {
    public CartadeHabilidadeDao(ConnectionSource cs) throws SQLException {
        super(CartaDeHabilidade.class);
        setConnectionSource(cs);
        initialize();
    }
}