/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.ecot12.bakugan;

import android.util.Log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author fabio
 */
@DatabaseTable (tableName = "combatente")
public class Combatente extends Humano{
    @DatabaseField
    private boolean round1;
    @DatabaseField
    private boolean round2;
    @DatabaseField
    private boolean round3;
    @DatabaseField(foreign = true)
    private Batalha batalha;

    public Combatente(){}
    
    public Combatente(Humano h){
        super(h.getNome(), h.getImagem());
        super.setId(h.getId());
        super.setIdade(h.getIdade());
        super.setCartasDeHabilidade(h.getCartasDeHabilidade());
        super.setCamposDeBatalha(h.getCamposDeBatalha());
        super.setBakugans(h.getBakugans());
        super.setTime(h.getTime());
        round1 = false;
        round2 = false;
        round3 = false;
    }

    public boolean getRounds(int pos) {
        switch (pos){
            case 1:
                return round1;
            case 2:
                return round2;
            case 3:
                return round3;
            default:
                return false;
        }
    }

    public void setRound(int pos, boolean valor) {
        switch (pos){
            case 1:
                this.round1 = valor;
            case 2:
                this.round2 = valor;
            case 3:
                this.round3 = valor;
        }
    }
    
    protected void atualizarPontuacao(){
        boolean rounds[] = new boolean[3];
        rounds[0] = round1;
        rounds[1] = round2;
        rounds[2] = round3;
        Log.i("PONTUACAO", "ENTROU AQUI 1 : " + rounds.toString());
        super.notificar(rounds);
    }

    public boolean isRound1() {
        return round1;
    }

    public void setRound1(boolean round1) {
        this.round1 = round1;
    }

    public boolean isRound2() {
        return round2;
    }

    public void setRound2(boolean round2) {
        this.round2 = round2;
    }

    public boolean isRound3() {
        return round3;
    }

    public void setRound3(boolean round3) {
        this.round3 = round3;
    }

    public Batalha getBatalha() {
        return batalha;
    }

    public void setBatalha(Batalha batalha) {
        this.batalha = batalha;
    }
}
