package br.edu.unifei.ecot12.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.edu.unifei.ecot12.bakugan.Ataque;
import br.edu.unifei.ecot12.bakugan.Bakugan;
import br.edu.unifei.ecot12.bakugan.Batalha;
import br.edu.unifei.ecot12.bakugan.CampoDeBatalha;
import br.edu.unifei.ecot12.bakugan.CartaDeHabilidade;
import br.edu.unifei.ecot12.bakugan.Combatente;
import br.edu.unifei.ecot12.bakugan.Desfavoravel;
import br.edu.unifei.ecot12.bakugan.Favoravel;
import br.edu.unifei.ecot12.bakugan.Humano;
import br.edu.unifei.ecot12.bakugan.Pontuacao;
import br.edu.unifei.ecot12.bakugan.Time;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String databaseName = "bakugan.db";
    private static final int databaseVersion = 14;

    public DatabaseHelper(Context context){
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {
        try {
            TableUtils.createTableIfNotExists(cs, Ataque.class);
            TableUtils.createTableIfNotExists(cs, Bakugan.class);
            TableUtils.createTableIfNotExists(cs, Batalha.class);
            TableUtils.createTableIfNotExists(cs, CampoDeBatalha.class);
            TableUtils.createTableIfNotExists(cs, CartaDeHabilidade.class);
            TableUtils.createTableIfNotExists(cs, Combatente.class);
            TableUtils.createTableIfNotExists(cs, Desfavoravel.class);
            TableUtils.createTableIfNotExists(cs, Favoravel.class);
            TableUtils.createTableIfNotExists(cs, Humano.class);
            TableUtils.createTableIfNotExists(cs, Pontuacao.class);
            TableUtils.createTableIfNotExists(cs, Time.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sd, ConnectionSource cs, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(cs, Ataque.class, true);
            TableUtils.dropTable(cs, Bakugan.class, true);
            TableUtils.dropTable(cs, Batalha.class, true);
            TableUtils.dropTable(cs, CampoDeBatalha.class, true);
            TableUtils.dropTable(cs, CartaDeHabilidade.class, true);
            TableUtils.dropTable(cs, Combatente.class, true);
            TableUtils.dropTable(cs, Desfavoravel.class, true);
            TableUtils.dropTable(cs, Favoravel.class, true);
            TableUtils.dropTable(cs, Humano.class, true);
            TableUtils.dropTable(cs, Pontuacao.class, true);
            TableUtils.dropTable(cs, Time.class, true);
            onCreate(sd,cs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void close(){
        super.close();
    }
}
