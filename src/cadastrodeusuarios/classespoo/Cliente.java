/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.classespoo;

import java.time.LocalDate;

/**
 *
 * @author Carlos
 */
public class Cliente extends PessoaMae{
    private String telefone;
    private String cpf;
    private String RG;
    private LocalDate datadenascimento;
    private String sexo;
    private String cep;
    private String cidade;
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private String uf;
    private String nacionalidade;
    private byte [] imageClient;

    public Cliente(int id, String nome, String sobrenome, String email, LocalDate datadenascimento, byte [] imageclient, String telefone, String cpf, String rg, String sexo, String cep, String cidade, String bairro, String numerocasa, String complemento, String uf, String nacionalidade, String rua) {
        super(id, nome, sobrenome, email);
        this.datadenascimento = datadenascimento;
        this.imageClient = imageclient;
        this.telefone = telefone;
        this.cpf = cpf;
        this.RG = rg;
        this.sexo = sexo;
        this.cep = cep;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numerocasa;
        this.complemento = complemento;
        this.uf = uf;
        this.nacionalidade = nacionalidade;
        this.rua = rua;
    }

    public Cliente() {
    }
    
    public byte[] getImageUser() {
        return imageClient;
    }

    public void setImageUser(byte [] imageClient) {
        this.imageClient = imageClient;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public LocalDate getDatadenascimento() {
        return datadenascimento;
    }

    public void setDatadenascimento(LocalDate datadenascimento) {
        this.datadenascimento = datadenascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
}
