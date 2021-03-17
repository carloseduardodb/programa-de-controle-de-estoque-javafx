/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.classespoo;

import cadastrodeusuarios.CriarBancoSQLite;
import conexoes.ConexaoSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Carlos
 */
public class memorybot {
    
    ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
    CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
    
    public Map acessarMemoria() {
        ResultSet resultSet;
        Statement statement;
        String resposta="";
        conexaoSQLite.conectar();
        String query = "SELECT * FROM memoria";
        
        Map<String, Object> mapaConhecimento = new HashMap<>();
        statement = conexaoSQLite.criarStatement();
        
        try {
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                mapaConhecimento.put(resultSet.getString("pergunta"), resultSet.getString("resposta"));
                //mapaConhecimento.put("12", "doze");
            }
        } catch (SQLException ex) {
             Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexaoSQLite.desconectar();
        }
        return mapaConhecimento;
    }
    
    
    public void adicionaconhecimento(String pergunta, String resposta) {
        //chama funcao para pegar a data atual do sistema e converter para o formato utilizado no banco sqlite
        java.util.Date d = new java.util.Date();
        java.sql.Date dt = new java.sql.Date (d.getTime());
       
        criarBancoSQLite.criaTabelaPessoa();
        
        conexaoSQLite.conectar();
        
        String inserttodataBDCliente = "INSERT INTO memoria("
                + "id,"
                + "pergunta,"
                + "resposta"
                + ") VALUES (?,?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(inserttodataBDCliente);
        
        try {
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, pergunta);
            preparedStatement.setString(3, resposta);
            
            int result = preparedStatement.executeUpdate();
            
            if(result == 1){
                System.out.println("Conhecimento adicionado com sucesso!");
            }
            else{
                System.out.println("A minha memoria não está boa! Chame o Carlos para dar uma olhadinha!");
            }
            
        } catch (SQLException e) {
                System.out.println("A minha memoria não está boa! Chame o Carlos para dar uma olhadinha!");
        }finally{
            if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(memorybot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    conexaoSQLite.desconectar();
        }
    }
    
}
