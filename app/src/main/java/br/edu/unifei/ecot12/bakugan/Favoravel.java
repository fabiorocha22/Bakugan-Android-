package br.edu.unifei.ecot12.bakugan;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author fabio
 */
@DatabaseTable(tableName = "favoravel")
public class Favoravel extends Efeito{

    public Favoravel(){}

    @Override
    public int efeito() {
        return super.getValor();
    }
}
