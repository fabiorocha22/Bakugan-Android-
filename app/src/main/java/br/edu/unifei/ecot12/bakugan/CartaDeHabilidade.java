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
@DatabaseTable (tableName = "cartaDeHabilidade")
public class CartaDeHabilidade extends Carta{
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private PropriedadeEnum propriedadeAlvo;
    @DatabaseField
    private int incremento;
    @DatabaseField(foreign = true)
    private Humano humano;
    @DatabaseField(foreign = true)
    private Combatente combatente;

    public CartaDeHabilidade(){}

    public CartaDeHabilidade(String nome, AtributoEnum alvo, int inc, Humano humano){
        super.setNome(nome);
        super.setAtributoAlvo(alvo);
        this.incremento = inc;
        this.humano = humano;
    }

    public PropriedadeEnum getPropriedadeAlvo() {
        return propriedadeAlvo;
    }

    public void setPropriedadeAlvo(PropriedadeEnum propriedadeAlvo) {
        this.propriedadeAlvo = propriedadeAlvo;
    }

    public int getIncremento() {
        return incremento;
    }

    public void setIncremento(int incremento) {
        this.incremento = incremento;
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
