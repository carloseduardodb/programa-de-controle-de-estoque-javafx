package cadastrodeusuarios.classespoo;

import cadastrodeusuarios.CriarBancoSQLite;
import cadastrodeusuarios.controllerforstyles.FXMLDocumentController;
import cadastrodeusuarios.interfacespoo.InterfaceUsuario;
import conexoes.ConexaoSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Usuario extends PessoaMae implements InterfaceUsuario {
    
    private byte [] imageUser;
    private String usuario;
    private String senha;

    ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
    CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
    private final ObservableList<Usuario> ob = FXCollections.observableArrayList();

    public Usuario(int id, String nome, String sobrenome, String email, String usuario, String senha, byte[] image) {
        super(id, nome, sobrenome, email);
        this.usuario = usuario;
        this.senha = senha;
        this.imageUser = image;
    }

    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getImageUser() {
        return imageUser;
    }

    public void setImageUser(byte[] imageUser) {
        this.imageUser = imageUser;
    }

    @Override
    public void ApagarUsuario(Integer ID) {

        conexaoSQLite.conectar();

        PreparedStatement preparedStatement = null;

        String sqlDelete = "DELETE FROM usuarios"
                + " WHERE id = ?;";

        try {
            preparedStatement = conexaoSQLite.criarPreparedStatement(sqlDelete);
            preparedStatement.setInt(1, ID);

            preparedStatement.executeUpdate();

            System.out.println("Apagado com Sucesso!");

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao apagar o usuario. Detalhes do erro: " + e);
        } finally {
            try {
                preparedStatement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro ao apagar o usuario. Detalhes do erro: " + ex);
            }
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Usuario> VerUsuarios() {

        ResultSet resultSet;
        Statement statement;

        conexaoSQLite.conectar();

        String query = "SELECT * FROM usuarios";

        statement = conexaoSQLite.criarStatement();

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ob.add(new Usuario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"), resultSet.getString("email"),
                        resultSet.getString("usuario"), resultSet.getString("senha"), resultSet.getBytes("imagem")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexaoSQLite.desconectar();
        }
        return ob;
    }

    public void AlteraFoto(int id){
        conexaoSQLite.conectar();
        PreparedStatement attpreparedStatement = null;
        
        String sql = "UPDATE usuarios"
                + " SET "
                + " imagem = ?"
                + " WHERE id = ?";
        
        try {
            attpreparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            attpreparedStatement.setBytes(1, this.getImageUser());
            attpreparedStatement.setInt(2, id);
            
            attpreparedStatement.executeUpdate();
        
            System.out.println("Alterado com Sucesso!");
            
        }catch(SQLException e){
            
        }
        
        try {
            attpreparedStatement.close();
            conexaoSQLite.desconectar();
        } catch (SQLException ex) {
            System.out.println("Erro ao Alterar Dados Line 141 Class User! Details:"+ex);
        }
    }
    
    @Override
    public void AlterarDadosdoUsuario(Integer ID) {
        conexaoSQLite.conectar();
        PreparedStatement attpreparedStatement = null;

        String sql = "UPDATE usuarios"
                + " SET "
                + " nome = ?,"
                + " sobrenome = ?,"
                + " email = ?,"
                + " usuario = ?,"
                + " senha = ?"
                + " WHERE id = ?";
        try {
            attpreparedStatement = conexaoSQLite.criarPreparedStatement(sql);
            attpreparedStatement.setString(1, this.getNome());
            attpreparedStatement.setString(2, this.getSobrenome());
            attpreparedStatement.setString(3, this.getEmail());
            attpreparedStatement.setString(4, this.getUsuario());
            attpreparedStatement.setString(5, this.getSenha());
            attpreparedStatement.setInt(6, ID);

            attpreparedStatement.executeUpdate();
            
            System.out.println("Salvo com sucesso!");
        } catch (SQLException e) {

        }

        try {
            attpreparedStatement.close();
            conexaoSQLite.desconectar();
        } catch (SQLException ex) {
            System.out.println("Erro ao Alterar Dados Line 141 Class User! Details:"+ex);
        }
    }

    @Override
    public Boolean AdicionaUsuario() {
        criarBancoSQLite.criaTabelaPessoa();

        conexaoSQLite.conectar();
        //tomar cuidado com os comando de inserção do banco, ver sempre se estão corretos
        //se estiver errado error in line 125 preparedStatement
        String sqlInsert = "INSERT INTO usuarios("
                + "id,"
                + "nome,"
                + "sobrenome,"
                + "email,"
                + "usuario,"
                + "senha,"
                + "imagem"
                + ") VALUES (?,?,?,?,?,?,?)"
                + ";";

        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsert);

        try {
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, this.getNome());
            preparedStatement.setString(3, this.getSobrenome());
            preparedStatement.setString(4, this.getEmail());
            preparedStatement.setString(5, this.getUsuario());
            preparedStatement.setString(6, this.getSenha());
            preparedStatement.setBytes(7, this.getImageUser());

            int resultado = preparedStatement.executeUpdate();
            if (resultado == 1) {
                System.out.println("Pessoa Inserida com Sucesso");
                return true;
            } else {
                return false;

            }

        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao criar o usuario" + e);

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            conexaoSQLite.desconectar();
        }

        return false;
    }
}
