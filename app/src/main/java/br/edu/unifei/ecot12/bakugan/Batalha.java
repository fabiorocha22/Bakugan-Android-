/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.ecot12.bakugan;

import android.util.Log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author fabio
 */
@DatabaseTable (tableName = "batalha")
public class Batalha {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(foreign = true)
    private MediadorRound round;
    @DatabaseField(foreign = true)
    private Combatente combatente1;
    @DatabaseField(foreign = true)
    private Combatente combatente2;

    public Batalha() {
    }

    public Batalha(Combatente combatente1, Combatente combatente2){
        this.combatente1 = combatente1;
        this.combatente2 = combatente2;
        round = new MediadorRound();
    }
    
    public void batalhar(){
        List<Bakugan> listBakugan1 = new ArrayList<>(combatente1.getBakugans());
        List<CampoDeBatalha> listBatalha1 = new ArrayList<>(combatente1.getCamposDeBatalha());
        List<CartaDeHabilidade> listHabilidade1 = new ArrayList<>(combatente1.getCartasDeHabilidade());


        List<Bakugan> listBakugan2 = new ArrayList<>(combatente2.getBakugans());
        List<CampoDeBatalha> listBatalha2 = new ArrayList<>(combatente2.getCamposDeBatalha());
        List<CartaDeHabilidade> listHabilidade2 = new ArrayList<>(combatente2.getCartasDeHabilidade());


        /*Log.i("ITERATOR", "tamanho: " + combatente1.getCamposDeBatalha().size());
        for(int i = 0; i < combatente1.getCamposDeBatalha().size(); i++){
            Log.i("ITERATOR", list.get(i).getAtributoAlvo().toString());
        }*/

        if(combatente1.getBakugans().size() >= 3 && combatente2.getBakugans().size() >= 3 &&
                combatente1.getCamposDeBatalha().size() >= 3 && combatente1.getCamposDeBatalha().size() >= 3 &&
                combatente1.getCartasDeHabilidade().size() >= 3 && combatente2.getCartasDeHabilidade().size() >= 3){
            Random r = new Random(2);

            for(int i = 0; i < 3; i++){
                if(!listBakugan1.get(i).getEstado().lutar() &&
                        !listBakugan2.get(i).getEstado().lutar())
                    break;
                if(listBakugan1.get(i).getEstado().lutar() &&
                        !listBakugan2.get(i).getEstado().lutar()){
                    combatente1.setRound(i, true);
                    combatente1.setRound(i, false);
                }
                if(!listBakugan1.get(i).getEstado().lutar() &&
                        listBakugan2.get(i).getEstado().lutar()){
                    combatente1.setRound(i, false);
                    combatente1.setRound(i, true);
                }
                if(listBakugan1.get(i).getEstado().lutar() &&
                        listBakugan2.get(i).getEstado().lutar()){
                    round.setBakugan1(listBakugan1.get(i));
                    round.setBakugan2(listBakugan2.get(i));
                    if(r.nextInt() == 1){
                        round.setCartaDeHabilidade(listHabilidade1.get(i));
                        round.setCampoDeBatalha(listBatalha1.get(i));
                    }
                    if(r.nextInt() == 2){
                        round.setCartaDeHabilidade(listHabilidade2.get(i));
                        round.setCampoDeBatalha(listBatalha2.get(i));
                    }
                    round.acao();
                    if(listBakugan1.get(i).getVidaGPower() <
                            listBakugan2.get(i).getVidaGPower()){
                        combatente2.setRound(i, true);
                        combatente1.setRound(i, false);
                    }
                    else{
                        combatente2.setRound(i, false);
                        combatente1.setRound(i, true);
                    }
                }
            }

            combatente1.atualizarPontuacao();
            combatente2.atualizarPontuacao();
        }
        else{
            Log.i("COMBATENTE 1",
                    "BAKUGANS: " + listBakugan1.size() +
                        "BATALHA" + listBatalha1.size() +
                        "HABILIDADE" + listHabilidade1.size());

            Log.i("COMBATENTE 2",
                    "BAKUGANS: " + listBakugan2.size() +
                            "BATALHA" + listBatalha2.size() +
                            "HABILIDADE" + listHabilidade2.size());
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MediadorRound getRound() {
        return round;
    }

    public void setRound(MediadorRound round) {
        this.round = round;
    }

    public Combatente getCombatente1() {
        return combatente1;
    }

    public void setCombatente1(Combatente combatente1) {
        this.combatente1 = combatente1;
    }

    public Combatente getCombatente2() {
        return combatente2;
    }

    public void setCombatente2(Combatente combatente2) {
        this.combatente2 = combatente2;
    }
}
