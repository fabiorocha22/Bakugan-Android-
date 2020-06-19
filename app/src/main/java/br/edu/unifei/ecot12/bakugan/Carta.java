package br.edu.unifei.ecot12.bakugan;

import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author fabio
 */
public abstract class Carta extends ColaboradorBakugan{
    @DatabaseField
    private AtributoEnum atributoAlvo;

    public AtributoEnum getAtributoAlvo() {
        return atributoAlvo;
    }

    public void setAtributoAlvo(AtributoEnum atributoAlvo) {
        this.atributoAlvo = atributoAlvo;
    }
    
    public void abrirCarta(){
        super.chamarAcao();
    }
}
