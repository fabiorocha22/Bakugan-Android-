package br.edu.unifei.ecot12.bakugan;

import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author fabio
 */

public abstract class ColaboradorBakugan {
    @DatabaseField
    private String nome;
    private Mediador mediador;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Mediador getMediador() {
        return mediador;
    }

    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }
    
    public void chamarAcao(){
        mediador.acao();
    }
}
