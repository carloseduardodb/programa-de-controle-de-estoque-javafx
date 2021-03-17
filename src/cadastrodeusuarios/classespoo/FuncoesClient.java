/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.classespoo;

import cadastrodeusuarios.CriarBancoSQLite;
import cadastrodeusuarios.controllerforstyles.ConfigAndViewCDClienteController;
import cadastrodeusuarios.interfacespoo.InterfaceCliente;
import conexoes.ConexaoSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Carlos
 */
public class FuncoesClient extends Cliente implements InterfaceCliente{
    
    private final ObservableList<Cliente> olp = FXCollections.observableArrayList();
    private final ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
    
    @Override
    public ObservableList<Cliente> VerCliente(boolean pesquisa) {
        //configurar para adicionar os dados do banco de dados
        //se a pesquisa for negativa eu uso o banco de dados e o retorno
        //se positiva eu retorno apenas o que ja foi pego no banco
        
        if(!pesquisa){
        ResultSet resultSet;
        Statement statement;

        conexaoSQLite.conectar();
        
        String query = "SELECT * FROM clientes";
        
        statement = conexaoSQLite.criarStatement();
        
        try {
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
            
                olp.add(new Cliente(resultSet.getInt("id") ,resultSet.getString("nome"), resultSet.getString("sobrenome"), resultSet.getString("email"), LocalDate.parse(""+resultSet.getDate("datadenascimento")), resultSet.getBytes("imageclient"), resultSet.getString("telefone"), resultSet.getString("cpf"), resultSet.getString("rg"), resultSet.getString("sexo"), resultSet.getString("cep"), resultSet.getString("cidade"), resultSet.getString("bairro"), resultSet.getString("numerocasa"), resultSet.getString("complemento"), resultSet.getString("uf"), resultSet.getString("nacionalidade"), resultSet.getString("rua")));
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigAndViewCDClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                conexaoSQLite.desconectar();
        }
        
        return olp;
        }
        else{
            return olp;
        }
    }

    @Override
    public Map EditarCliente(int ID) {
        conexaoSQLite.conectar();
        
        ResultSet edtresultSet = null;
        PreparedStatement edtPreparedStatement = null;      
        
        String query = "SELECT * FROM clientes"
                + " WHERE id = ?;";
        
        try {
            edtPreparedStatement = conexaoSQLite.criarPreparedStatement(query);
            edtPreparedStatement.setInt(1, ID);
            
            edtresultSet = edtPreparedStatement.executeQuery();            
            
            Map<String, String> mapDados = new HashMap<>(); 
            
            mapDados.put("id", ""+ID);
            mapDados.put("email", edtresultSet.getString("email"));
            mapDados.put("telefone", ""+edtresultSet.getInt("telefone"));
            mapDados.put("cpf", "" +edtresultSet.getInt("cpf"));
            mapDados.put("rg", edtresultSet.getString("rg"));
            mapDados.put("cep", ""+edtresultSet.getInt("cep"));
            mapDados.put("cidade", edtresultSet.getString("cidade"));
            mapDados.put("bairro", edtresultSet.getString("bairro"));
            mapDados.put("rua", edtresultSet.getString("rua"));
            mapDados.put("numero", ""+edtresultSet.getInt("numerocasa"));
            mapDados.put("complemento", edtresultSet.getString("complemento"));
            mapDados.put("uf", edtresultSet.getString("uf"));
            
            return mapDados;
            
        } catch (Exception e) {
        }finally{
            try {
            edtPreparedStatement.close();
            conexaoSQLite.desconectar();
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public void ApagarCliente(int ID) {
        
        conexaoSQLite.conectar();
        
        PreparedStatement preparedStatement = null;
        
        String sql = "DELETE FROM clientes"
                + " WHERE id = ?;";
        
        try {
            preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            preparedStatement.setInt(1, ID);
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
        
        }finally{
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException ex) {
            }
        }
    }
    
    CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
    
    @Override
    public Boolean AdicionarCliente() {
        criarBancoSQLite.criaTabelaPessoa();
        conexaoSQLite.conectar();
        
        String sqlInsertTableCliente = "INSERT INTO clientes("
                + "id,"
                + "nome,"
                + "sobrenome,"
                + "email,"
                + "telefone,"
                + "cpf,"
                + "rg,"
                + "datadenascimento,"
                + "sexo,"
                + "cep,"
                + "cidade,"
                + "bairro,"
                + "rua,"
                + "numerocasa,"
                + "complemento,"
                + "uf,"
                + "nacionalidade,"
                + "imageclient"
                + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                + ";";
        
        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsertTableCliente);

            try{
                preparedStatement.setString(1, null);                
                preparedStatement.setString(2, this.getNome());
                preparedStatement.setString(3, this.getSobrenome());
                preparedStatement.setString(4, this.getEmail());
                preparedStatement.setString(5, this.getTelefone());
                preparedStatement.setString(6, this.getCpf());
                preparedStatement.setString(7, this.getRG());
                preparedStatement.setDate(8, java.sql.Date.valueOf(this.getDatadenascimento()));
                preparedStatement.setString(9, this.getSexo());
                preparedStatement.setString(10, this.getCep());
                preparedStatement.setString(11, this.getCidade());
                preparedStatement.setString(12, this.getBairro());
                preparedStatement.setString(13, this.getRua());
                preparedStatement.setString(14, this.getNumero());
                preparedStatement.setString(15, this.getComplemento());
                preparedStatement.setString(16, this.getUf());
                preparedStatement.setString(17, this.getNacionalidade());
                preparedStatement.setBytes(18, this.getImageUser());
                
                int resultado = preparedStatement.executeUpdate();
                
                if(resultado == 1){
                    System.out.println("Cliente Adicionado com sucesso!");
                    return true;
                }else{
                    System.out.println("Ocorreu um erro ao inserir o cliente!");
                    return false;
                }
                
            }catch(SQLException e){
                System.out.println("Ocorreu um erro ao adicionar o cliente! (Detalhes: " + e +")");
                return false;
            }finally {
                    if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException ex) {
                            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    conexaoSQLite.desconectar();
                }
    }
    
    @Override
    public Boolean modifyClient(){
        conexaoSQLite.conectar();
        
        PreparedStatement attpreparedStatement = null;
        
        String MDsql = "UPDATE clientes"
                + " SET "
                + " email = ?,"
                + " telefone = ?,"
                + " cep = ?,"
                + " cidade = ?,"
                + " complemento = ?,"
                + " numerocasa = ?,"
                + " bairro = ?,"
                + " rua = ?,"
                + " cpf = ?,"
                + " rg = ?,"
                + " uf = ?"
                + " WHERE id = ?";
        
        try {
            attpreparedStatement = conexaoSQLite.criarPreparedStatement(MDsql);
            
            attpreparedStatement.setString(1, this.getEmail());
            attpreparedStatement.setString(2, this.getTelefone());
            attpreparedStatement.setString(3, this.getCep());
            attpreparedStatement.setString(4, this.getCidade());
            attpreparedStatement.setString(5, this.getComplemento());
            attpreparedStatement.setString(6, this.getNumero());
            attpreparedStatement.setString(7, this.getBairro());
            attpreparedStatement.setString(8, this.getRua());
            attpreparedStatement.setString(9, this.getCpf());
            attpreparedStatement.setString(10, this.getRG());
            attpreparedStatement.setString(11, this.getUf());
            attpreparedStatement.setInt(12, this.getId());
            
            attpreparedStatement.executeUpdate();
            
            return true;
            
        } catch (SQLException e) {
            System.err.println("Erro ao Alterar o Banco de Dados!" + e);
            return false;
        }finally{
            try {
                attpreparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
}
