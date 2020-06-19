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
@DatabaseTable (tableName = "pontuacao")
public class Pontuacao extends Observador{
    @DatabaseField(generatedId = true)
    private long id;

    public Pontuacao(){}

    @Override
    public void atualizar(boolean[] rounds) {
        int vit = 0;
        int der = 0;
        for(int i = 0; i < rounds.length; i++){
            if(rounds[i])
                vit++;
            else
                der++;
        }
        if(vit > der)
            super.setVitorias(super.getVitorias() + 1);
        else
            super.setDerrotas(super.getDerrotas() + 1);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
