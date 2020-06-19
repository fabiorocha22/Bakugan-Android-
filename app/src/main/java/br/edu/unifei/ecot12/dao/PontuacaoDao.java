package br.edu.unifei.ecot12.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.Pontuacao;

public class PontuacaoDao extends BaseDaoImpl<Pontuacao, Integer> {
    public PontuacaoDao(ConnectionSource cs) throws SQLException {
        super(Pontuacao.class);
        setConnectionSource(cs);
        initialize();
    }
}