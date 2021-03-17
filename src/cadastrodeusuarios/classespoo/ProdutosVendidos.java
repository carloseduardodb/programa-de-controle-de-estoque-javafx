/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.classespoo;

import cadastrodeusuarios.interfacespoo.InterfaceProdutos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Carlos
 */
public class ProdutosVendidos extends Produtos implements InterfaceProdutos{
    
    //usar interface para fazer interação com o banco de dados
    
    private final ObservableList<ProdutosVendidos> ol = FXCollections.observableArrayList();

    public ProdutosVendidos(Double precodevenda, String datadecadastro, Integer ID, String nome, String marca, Integer quantidade) {
        super(precodevenda, datadecadastro, ID, nome, marca, quantidade);
    }

    public ProdutosVendidos() {
    }
    
    @Override
    public ObservableList<ProdutosVendidos> VerProdutosVendidos() {
        ResultSet resultSet;
        Statement statement;
        
        conexaoSQLite.conectar();
        
        String query = "SELECT * FROM venda";
        
        statement = conexaoSQLite.criarStatement();
        
        try {
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                ol.add(new ProdutosVendidos(resultSet.getDouble("valorvenda"), ""+resultSet.getDate("datadavenda"), resultSet.getInt("id"),
                resultSet.getString("nomedoproduto"), resultSet.getString("marca"),resultSet.getInt("quantidade")));
            }
        } catch (SQLException ex) {
             Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexaoSQLite.desconectar();
        }
        return ol;
    }
    
    
}
