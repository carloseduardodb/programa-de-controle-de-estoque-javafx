/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.classespoo;

import cadastrodeusuarios.CriarBancoSQLite;
import cadastrodeusuarios.controllerforstyles.FXMLDocumentController;
import cadastrodeusuarios.interfacespoo.InterfaceProdutos;
import conexoes.ConexaoSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Carlos
 */
public class Produtos extends Produto implements InterfaceProdutos{
    private SimpleStringProperty descricao;
    private SimpleStringProperty categoria;
    private SimpleDoubleProperty precodecusto;
    private SimpleDoubleProperty precodevenda;
    private SimpleStringProperty datadecadastro;

    public Produtos(String descricao, String categoria, Double precodecusto, Double precodevenda, String datadecadastro, Integer ID, String nome, String marca, Integer quantidade) {
        super(ID, nome, marca, quantidade);
        this.descricao = new SimpleStringProperty(descricao);
        this.categoria = new SimpleStringProperty(categoria);
        this.precodecusto = new SimpleDoubleProperty(precodecusto);
        this.precodevenda = new SimpleDoubleProperty(precodevenda);
        this.datadecadastro = new SimpleStringProperty(datadecadastro);
    }
    
    public Produtos(String descricao, String categoria, Double precodevenda, String datadecadastro, Integer ID, String nome, String marca, Integer quantidade) {
        super(ID, nome, marca, quantidade);
        this.descricao = new SimpleStringProperty(descricao);
        this.categoria = new SimpleStringProperty(categoria);
        this.precodevenda = new SimpleDoubleProperty(precodevenda);
        this.datadecadastro = new SimpleStringProperty(datadecadastro);
    }    

    public Produtos(Integer ID, String nome, String marca, Integer quantidade, Double precodoproduto) {
        super(ID, nome, marca, quantidade);
        this.precodevenda = new SimpleDoubleProperty(precodoproduto);
    }
    
    public Produtos(){
        
    }

    public Produtos(Double precodevenda, String datadecadastro, Integer ID, String nome, String marca, Integer quantidade) {
        super(ID, nome, marca, quantidade);
        this.precodevenda = new SimpleDoubleProperty(precodevenda);
        this.datadecadastro = new SimpleStringProperty(datadecadastro);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao = new SimpleStringProperty(descricao);
    }

    public String getCategoria() {
        return categoria.get();
    }

    public void setCategoria(String categoria) {
        this.categoria = new SimpleStringProperty(categoria);
    }

    public Double getPrecodecusto() {
        return precodecusto.get();
    }

    public void setPrecodecusto(Double precodecusto) {
        this.precodecusto = new SimpleDoubleProperty(precodecusto);
    }

    public Double getPrecodevenda() {
        return precodevenda.get();
    }

    public void setPrecodevenda(Double precodevenda) {
        this.precodevenda = new SimpleDoubleProperty(precodevenda);
    }

    public String getDatadecadastro() {
        return datadecadastro.get();
    }

    public void setDatadecadastro(Date datadecadastro) {
        //this.datadecadastro = datadecadastro;
    }

    ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
    CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
    
    //usar interface para fazer interação com o banco de dados
    
    private final ObservableList<Produtos> ol = FXCollections.observableArrayList();
    
    @Override
    public ObservableList<Produtos> VerProdutos() {
        ResultSet resultSet;
        Statement statement;
        
        conexaoSQLite.conectar();
        
        String query = "SELECT * FROM produtos";
        
        statement = conexaoSQLite.criarStatement();
        
        try {
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                ol.add(new Produtos(resultSet.getString("descricao"), resultSet.getString("categoria"), resultSet.getDouble("precovenda"), 
                        String.valueOf(resultSet.getDate("datacadastro")), resultSet.getInt("id"), 
                        resultSet.getString("nome"), resultSet.getString("marca"), resultSet.getInt("quantidade")));
            }
        } catch (SQLException ex) {
             Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexaoSQLite.desconectar();
        }
        return ol;
    }

    @Override
    public void ApagarProdutos(Integer ID) {
        System.out.println(""+ID);
        conexaoSQLite.conectar();
        
        PreparedStatement preparedStatement = null;
        
        String sqlDelete = "DELETE FROM produtos"
                + " WHERE id = ?;";
        
        try{
            preparedStatement = conexaoSQLite.criarPreparedStatement(sqlDelete);
            preparedStatement.setInt(1, ID);
            
            preparedStatement.executeUpdate();
            
            System.out.println("Apagado com Sucesso!");
            
        }catch(SQLException e){
             System.out.println("Ocorreu um erro ao apagar o produto. Detalhes do erro: " + e);
        }finally{
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro ao apagar o produto. Detalhes do erro: " + ex);
            }
        }
        
    }

    
    //adiciona produtos no banco de dados
    @Override
    public boolean AdicionarProdutos() {
        //chama funcao para pegar a data atual do sistema e converter para o formato utilizado no banco sqlite
        java.util.Date d = new java.util.Date();
        java.sql.Date dt = new java.sql.Date (d.getTime());
       
        criarBancoSQLite.criaTabelaPessoa();
        
        conexaoSQLite.conectar();
        
        String inserttodataBDCliente = "INSERT INTO produtos("
                + "id,"
                + "nome,"
                + "marca,"
                + "quantidade,"
                + "descricao,"
                + "categoria,"
                + "precocusto,"
                + "precovenda,"
                + "datacadastro"
                + ") VALUES (?,?,?,?,?,?,?,?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(inserttodataBDCliente);
        
        try {
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, this.getNome());
            preparedStatement.setString(3, this.getMarca());
            preparedStatement.setInt(4, this.getQuantidade());
            preparedStatement.setString(5, this.getDescricao());
            preparedStatement.setString(6, this.getCategoria());
            preparedStatement.setDouble(7, this.getPrecodecusto());
            preparedStatement.setDouble(8, this.getPrecodevenda());
            preparedStatement.setDate(9, dt);
            
            int result = preparedStatement.executeUpdate();
            
            if(result == 1){
                System.out.println("Produto Cadastrado Com Sucesso!");
            }
            else{
                System.out.println("Erro ao Adicionar Produto!");
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao adicionar o cliente! (Detalhes: " + e +")");
        }finally{
            if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException ex) {
                            return false;
                        }
                    }
                    conexaoSQLite.desconectar();
        }
        return true;
    }
    
    private static int tamlist;
    
    @Override
    public void AtualizaProdutos(Integer ID, String caracteristica, Object o){
        conexaoSQLite.conectar();
        PreparedStatement attpreparedStatement = null;
        
        switch(caracteristica){
            case "nome":{
                String sql = "UPDATE produtos"
                        + " SET "
                        + " nome = ?"
                        + " WHERE id = ?";
                try{
                attpreparedStatement = conexaoSQLite.criarPreparedStatement(sql);
                attpreparedStatement.setString(1, String.valueOf(o));
                attpreparedStatement.setInt(2, ID);
                
                attpreparedStatement.executeUpdate();
                }catch(SQLException e){
                    
                }
                break;
            }
            case "marca":{
                String sql = "UPDATE produtos"
                        + " SET "
                        + " marca = ?"
                        + " WHERE id = ?";
                try{
                attpreparedStatement = conexaoSQLite.criarPreparedStatement(sql);
                attpreparedStatement.setString(1, String.valueOf(o));
                attpreparedStatement.setInt(2, ID);
                
                attpreparedStatement.executeUpdate();
                }catch(SQLException e){
                    
                }
                break;
            }
            case "descricao":{
                String sql = "UPDATE produtos"
                        + " SET "
                        + " descricao = ?"
                        + " WHERE id = ?";
                try{
                attpreparedStatement = conexaoSQLite.criarPreparedStatement(sql);
                attpreparedStatement.setString(1, String.valueOf(o));
                attpreparedStatement.setInt(2, ID);
                
                attpreparedStatement.executeUpdate();
                }catch(SQLException e){
                    
                }
                break;
            }
            case "quantidade":{
                String sql = "UPDATE produtos"
                        + " SET "
                        + " quantidade = ?"
                        + " WHERE id = ?";
                try{
                attpreparedStatement = conexaoSQLite.criarPreparedStatement(sql);
                attpreparedStatement.setInt(1, (int) o);
                attpreparedStatement.setInt(2, ID);
                
                attpreparedStatement.executeUpdate();
                }catch(SQLException e){
                    
                }
                break;
            }
            
            case "preco":{
                String sql = "UPDATE produtos"
                        + " SET "
                        + " precovenda = ?"
                        + " WHERE id = ?";
                try{
                attpreparedStatement = conexaoSQLite.criarPreparedStatement(sql);
                attpreparedStatement.setDouble(1, (double) o);
                attpreparedStatement.setInt(2, ID);
                
                attpreparedStatement.executeUpdate();
                }catch(SQLException e){
                    
                }
                break;
            }
        }
        
        try {
                attpreparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
    }
    @Override
    public void AdcVendadeProdutos(String formadepagamento){
        /**
         *fazer conexao com o banco e adicionar os produtos vendidos
         * isso vai demandar processamento colocar em thread quando chamar
         * ou status de carregamento
         */
        
        java.util.Date d = new java.util.Date();
        java.sql.Date dt = new java.sql.Date (d.getTime());
       
        criarBancoSQLite.criaTabelaPessoa();
        
        conexaoSQLite.conectar();
        
        String inserttodataBDCliente = "INSERT INTO venda("
                + "id,"
                + "nomedoproduto,"
                + "marca,"
                + "quantidade,"
                + "valorvenda,"
                + "datadavenda,"
                + "formadepagamento"
                + ") VALUES (?,?,?,?,?,?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(inserttodataBDCliente);
        
        try {
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, this.getNome());
            preparedStatement.setString(3, this.getMarca());
            preparedStatement.setInt(4, this.getQuantidade());
            preparedStatement.setDouble(5, this.getPrecodevenda());
            preparedStatement.setDate(6, dt);
            preparedStatement.setString(7, formadepagamento);
            
            int result = preparedStatement.executeUpdate();
            
            if(result == 1){
                System.out.println("Produto Cadastrado Com Sucesso!");
            }
            else{
                System.out.println("Erro ao Adicionar Produto Nas Vendas!");
            }
            
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao adicionar o produto! (Detalhes: " + e +")");
        }finally{
            if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    conexaoSQLite.desconectar();
        }
    }

    @Override
    public Object VerProdutosVendidos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
