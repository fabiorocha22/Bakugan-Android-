/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.ecot12.bakugan;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author fabio
 */
@DatabaseTable (tableName = "time")
public class Time {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(foreign = true)
    private Humano lider;
    @ForeignCollectionField
    private Collection<Humano> membros;

    public Time(){}
    
    public Time(Humano lider){
        this.lider = lider;
    }

    public Humano getLider() {
        return lider;
    }

    public void setLider(Humano lider) {
        this.lider = lider;
    }

    public Collection<Humano> getMembros() {
        return membros;
    }

    public void setMembros(Collection<Humano> membros) {
        this.membros = membros;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
