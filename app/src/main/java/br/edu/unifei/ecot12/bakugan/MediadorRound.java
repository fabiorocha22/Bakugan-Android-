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
public class MediadorRound {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(foreign = true)
    private Bakugan bakugan1;
    @DatabaseField(foreign = true)
    private Bakugan bakugan2;
    @DatabaseField(foreign = true)
    private CampoDeBatalha campoDeBatalha;
    @DatabaseField(foreign = true)
    private CartaDeHabilidade cartaDeHabilidade;
    
    public MediadorRound(){
        campoDeBatalha = new CampoDeBatalha();
        cartaDeHabilidade = new CartaDeHabilidade();
        bakugan1 = new Bakugan();
        bakugan2 = new Bakugan();
    }

    public Bakugan getBakugan1() {
        return bakugan1;
    }

    public void setBakugan1(Bakugan bakugan1) {
        this.bakugan1 = bakugan1;
    }

    public Bakugan getBakugan2() {
        return bakugan2;
    }

    public void setBakugan2(Bakugan bakugan2) {
        this.bakugan2 = bakugan2;
    }

    public CampoDeBatalha getCampoDeBatalha() {
        return campoDeBatalha;
    }

    public void setCampoDeBatalha(CampoDeBatalha campoDeBatalha) {
        this.campoDeBatalha = campoDeBatalha;
    }

    public CartaDeHabilidade getCartaDeHabilidade() {
        return cartaDeHabilidade;
    }

    public void setCartaDeHabilidade(CartaDeHabilidade cartaDeHabilidade) {
        this.cartaDeHabilidade = cartaDeHabilidade;
    }
    
    public void acao(){
        if(campoDeBatalha.getAtributoAlvo() == bakugan1.getAtributo()){
            bakugan1.setVidaGPower(bakugan1.getVidaGPower()/* + campoDeBatalha.getEfeito().efeito()*/);
        }
        
        if(campoDeBatalha.getAtributoAlvo() == bakugan2.getAtributo()){
            bakugan2.setVidaGPower(bakugan2.getVidaGPower()/* + campoDeBatalha.getEfeito().efeito()*/);
        }
        
        if(cartaDeHabilidade.getAtributoAlvo() == bakugan1.getAtributo()){
            switch(cartaDeHabilidade.getPropriedadeAlvo()){
                case ATAQUE:
                    bakugan1.setAtaque(bakugan1.getAtaque() + cartaDeHabilidade.getIncremento());
                    break;
                case DEFESA:
                    bakugan1.setDefesa(bakugan1.getDefesa() + cartaDeHabilidade.getIncremento());
                    break;
            }
        }
        
        if(cartaDeHabilidade.getAtributoAlvo() == bakugan2.getAtributo()){
            switch(cartaDeHabilidade.getPropriedadeAlvo()){
                case ATAQUE:
                    bakugan2.setAtaque(bakugan2.getAtaque() + cartaDeHabilidade.getIncremento());
                    break;
                case DEFESA:
                    bakugan2.setDefesa(bakugan2.getDefesa() + cartaDeHabilidade.getIncremento());
                    break;
            }
        }
        
        bakugan1.setVidaGPower(bakugan1.getVidaGPower() + bakugan1.getDefesa() 
                    - bakugan2.getAtaque() - bakugan2.getPoder().getDano());
        bakugan2.setVidaGPower(bakugan2.getVidaGPower() + bakugan2.getDefesa() 
                - bakugan1.getAtaque() - bakugan1.getPoder().getDano());
    }
}
