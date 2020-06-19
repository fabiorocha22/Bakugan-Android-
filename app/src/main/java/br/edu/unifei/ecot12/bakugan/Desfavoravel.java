/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.ecot12.bakugan;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author fabio
 */
@DatabaseTable (tableName = "desfavoravel")
public class Desfavoravel extends Efeito{

    public Desfavoravel(){}

    @Override
    public int efeito(){
        return -(super.getValor());
    }
}
