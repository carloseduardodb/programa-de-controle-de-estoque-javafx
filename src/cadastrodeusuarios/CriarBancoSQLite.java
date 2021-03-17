/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios;

import conexoes.ConexaoSQLite;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos-ED
 */
public class CriarBancoSQLite {
    
    private final ConexaoSQLite conexaoSQLite;
    
    public CriarBancoSQLite(ConexaoSQLite pConexaoSQLite){
        this.conexaoSQLite = pConexaoSQLite;
    }
    
    public void criaTabelaPessoa(){
        String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuarios"
                + "("
                + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "nome text NOT NULL," 
                + "sobrenome text NOT NULL,"
                + "email text NOT NULL,"
                + "usuario text NOT NULL,"
                + "senha text NOT NULL,"
                + "imagem BLOB"
                + ");";
        
        String sqlCliente = "CREATE TABLE IF NOT EXISTS clientes"
                +"("
                +"id integer PRIMARY KEY AUTOINCREMENT,"
                +"nome text NOT NULL,"
                +"sobrenome text NOT NULL,"
                +"email text NOT NULL,"
                +"telefone text NOT NULL,"
                +"cpf text NOT NULL,"
                +"rg text NOT NULL,"
                +"datadenascimento date NOT NULL,"
                +"sexo text NOT NULL,"
                +"cep text NOT NULL,"
                +"cidade text NOT NULL,"
                +"bairro text NOT NULL,"
                +"rua text NOT NULL,"
                +"numerocasa text NOT NULL,"
                +"complemento text NOT NULL,"
                +"uf text NOT NULL,"
                +"nacionalidade text NOT NULL,"
                +"imageclient BLOB"
                +");";
        
        String sqlProdutos = "CREATE TABLE IF NOT EXISTS produtos"
                + "("
                + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "nome text NOT NULL,"
                + "marca text NOT NULL,"
                + "quantidade integer NOT NULL,"
                + "descricao text NOT NULL,"
                + "categoria text NOT NULL,"
                + "precocusto double,"
                + "precovenda double,"
                + "datacadastro date"
                + ");";
        
        String sqlVenda = "CREATE TABLE IF NOT EXISTS venda"
                + "("
                + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "nomedoproduto text NOT NULL,"
                + "marca text NOT NULL,"
                + "quantidade decimal NOT NULL,"
                + "valorvenda decimal NOT NULL,"
                + "datadavenda date NOT NULL,"
                + "formadepagamento text NOT NULL"
                + ");";
        
        String sqlChatBot = "CREATE TABLE IF NOT EXISTS memoria"
                + "("
                + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "pergunta text NOT NULL,"
                + "resposta text NOT NULL"
                + ");";
        
        //EXECUTANDO O SQL DE CRIAR TABELAS
        
        boolean conectou = false;
        
        try{
            conectou = this.conexaoSQLite.conectar();
            Statement stmt = this.conexaoSQLite.criarStatement();
            stmt.execute(sqlUsuario);
            stmt.execute(sqlCliente);
            stmt.execute(sqlProdutos);
            stmt.execute(sqlVenda);
            stmt.execute(sqlChatBot);
        }catch(SQLException e){
            //mensagem de erro na criacao da tabela
        } finally{
            if(conectou){
                this.conexaoSQLite.desconectar();
            }
        }
    }
    
}
