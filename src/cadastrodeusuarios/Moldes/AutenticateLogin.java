/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.Moldes;

import cadastrodeusuarios.CriarBancoSQLite;
import conexoes.ConexaoSQLite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Carlos
 */

public class AutenticateLogin {
    public Map<String, Object> DadosUserLG = new HashMap<>();
    private final ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
    private final CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);

    public Map<String, Object> getDadosUserLG() {
        return DadosUserLG;
    }

    public void setDadosUserLG(Map<String, Object> DadosUserLG) {
        this.DadosUserLG = DadosUserLG;
    }
    
    public boolean autentica(TextField lgntxtpUsuario, PasswordField lgntxtpSenha){
        ResultSet resultSet;
        
        Statement statement;

        conexaoSQLite.conectar();

        String LGusuario = lgntxtpUsuario.getText();
        String LGsenha = lgntxtpSenha.getText();

        String query = "SELECT * FROM usuarios";

        statement = conexaoSQLite.criarStatement();

        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if (resultSet.getString("usuario").equals(LGusuario) && resultSet.getString("senha").equals(LGsenha)) {
                    DadosUserLG.put("id", resultSet.getInt("id"));
                    DadosUserLG.put("nome", resultSet.getString("nome"));
                    DadosUserLG.put("sobrenome", resultSet.getString("sobrenome"));
                    DadosUserLG.put("email", resultSet.getString("email"));
                    DadosUserLG.put("usuario", resultSet.getString("usuario"));
                    DadosUserLG.put("imagem", resultSet.getBytes("imagem"));
                    conexaoSQLite.desconectar();
                    return true;
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
