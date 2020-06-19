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
@DatabaseTable(tableName = "campoDeBatalha")
public class CampoDeBatalha extends Carta{
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(foreign = true)
    private Humano humano;
    @DatabaseField(foreign = true)
    private Combatente combatente;

    public CampoDeBatalha(){}

    public CampoDeBatalha(AtributoEnum alvo, Humano humano){
        super.setAtributoAlvo(alvo);
        this.humano = humano;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Humano getHumano() {
        return humano;
    }

    public void setHumano(Humano humano) {
        this.humano = humano;
    }

    public Combatente getCombatente() {
        return combatente;
    }

    public void setCombatente(Combatente combatente) {
        this.combatente = combatente;
    }
}
