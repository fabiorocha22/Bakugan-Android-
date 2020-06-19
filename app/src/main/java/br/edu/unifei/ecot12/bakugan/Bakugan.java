package br.edu.unifei.ecot12.bakugan;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author fabio
 */
@DatabaseTable(tableName = "bakugan")
public class Bakugan extends  ColaboradorBakugan{
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private int ataque;
    @DatabaseField
    private int defesa;
    @DatabaseField
    private AtributoEnum atributo;
    @DatabaseField
    private int vidaGPower;
    //@DatabaseField(foreign = true)
    private Ataque poder = new Ataque();
    //@DatabaseField(foreign = true)
    private Estado estado = new Vivo();
    @DatabaseField(foreign = true)
    private Humano humano;
    @DatabaseField(foreign = true)
    private Combatente combatente;

    private int imagem;

    public Bakugan(){}

    public Bakugan(String nome, int ataque, int defesa, AtributoEnum atributo,
        int vidaGPower, Humano humano){
        super.setNome(nome);
        this.ataque = ataque;
        this.defesa = defesa;
        this.atributo = atributo;
        this.vidaGPower = vidaGPower;
        this.humano = humano;
        //this.imagem = imagem;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public Humano getHumano() {
        return humano;
    }

    public void setHumano(Humano humano) {
        this.humano = humano;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public AtributoEnum getAtributo() {
        return atributo;
    }

    public void setAtributo(AtributoEnum atributo) {
        this.atributo = atributo;
    }

    public int getVidaGPower() {
        return vidaGPower;
    }

    public void setVidaGPower(int vidaGPower) {
        this.vidaGPower = vidaGPower;
    }

    public Ataque getPoder() {
        return poder;
    }

    public void setPoder(Ataque poder) {
        this.poder = poder;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public void atacar(){
        super.chamarAcao();
    }

    public Combatente getCombatente() {
        return combatente;
    }

    public void setCombatente(Combatente combatente) {
        this.combatente = combatente;
    }
}
