package br.edu.unifei.ecot12.bakugan;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author fabio
 */
@DatabaseTable(tableName = "humano")
public class Humano {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private int idade;
    @DatabaseField
    private String imagem;
    @DatabaseField(foreign = true)
    private Time time = new Time();
    @ForeignCollectionField
    private Collection<CartaDeHabilidade> cartasDeHabilidade;
    @ForeignCollectionField
    private Collection<CampoDeBatalha> camposDeBatalha;
    @ForeignCollectionField
    private Collection<Bakugan> bakugans;
    @DatabaseField(foreign = true)
    private Pontuacao pontuacao
            = new Pontuacao();

    public Humano(){}

    public Humano(String nome, String imagem){
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pontuacao getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Pontuacao pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Collection<CartaDeHabilidade> getCartasDeHabilidade() {
        return cartasDeHabilidade;
    }

    public void setCartasDeHabilidade(Collection<CartaDeHabilidade> cartasDeHabilidade) {
        this.cartasDeHabilidade = cartasDeHabilidade;
    }

    public Collection<CampoDeBatalha> getCamposDeBatalha() {
        return camposDeBatalha;
    }

    public void setCamposDeBatalha(Collection<CampoDeBatalha> camposDeBatalha) {
        this.camposDeBatalha = camposDeBatalha;
    }

    public Collection<Bakugan> getBakugans() {
        return bakugans;
    }

    public void setBakugans(Collection<Bakugan> bakugans) {
        this.bakugans = bakugans;
    }
    
    protected void notificar(boolean rounds[]){
        pontuacao.atualizar(rounds);
    }
    
    public int getVitorias(){
        return pontuacao.getVitorias();
    }
    
    public int getDerrotas(){
        return pontuacao.getDerrotas();
    }

    @Override
    public String toString(){
        return "ID: " + id + " Nome: " + nome;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
