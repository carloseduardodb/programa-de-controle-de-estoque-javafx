/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.classespoo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Carlos
 */
public abstract class Produto {
    private SimpleIntegerProperty ID;
    private SimpleStringProperty nome;
    private SimpleStringProperty marca;
    private SimpleIntegerProperty quantidade;

    public Produto(Integer ID, String nome, String marca, Integer quantidade) {
        this.ID = new SimpleIntegerProperty(ID);
        this.nome = new SimpleStringProperty(nome);
        this.marca = new SimpleStringProperty(marca);
        this.quantidade = new SimpleIntegerProperty(quantidade);
    }
    
    public Produto(){
    
    }
    
    public Integer getID(){
        return ID.get();
    }
    
    public void setID(Integer ID){
        this.ID = new SimpleIntegerProperty(ID);
    }
    
    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca = new SimpleStringProperty(marca);
    }

    public Integer getQuantidade() {
        return quantidade.get();
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = new SimpleIntegerProperty(quantidade);
    }
    
    
}
