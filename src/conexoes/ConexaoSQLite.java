/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carlos-ED
 */

//conecta ao banco de dados ou cria se ele nao existir
public class ConexaoSQLite {
    
    public static int crud = 0;
    
    private Connection conexao;
    
    public boolean conectar(){
        try{
            String url = "jdbc:sqlite:banco_de_dados/bdlocal.db";
            
            this.conexao = DriverManager.getConnection(url);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean desconectar(){
        crud = 1;
        try{
            if(this.conexao.isClosed()==false){
                this.conexao.close();
            }
        }catch(SQLException e){
        System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    //Cria os statments para os sql's serem executados
    public Statement criarStatement(){
        try{
            return this.conexao.createStatement();
        }catch(SQLException e){
            return null;
        }
    }
    
    public PreparedStatement criarPreparedStatement(String sql){
        try{
            return this.conexao.prepareStatement(sql);
        }catch(SQLException e){
            return null;
        }
    }
    
    public Connection getConexao(){
        return conexao;
    }
    
}
