/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.classespoo.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class GerenciarUsuariosController implements Initializable {
  
    @FXML
    private TableView<Usuario> GUtvUsuarios;

    @FXML
    private TableColumn<Usuario, String> GUtcNome;

    @FXML
    private TableColumn<Usuario, String> GUtcEmail;
  
    @FXML
    private TableColumn<Usuario, Integer> GUtcID;
    
    @FXML
    private TextField GUtxtiPesquisar;

    @FXML
    private TextField GUtxtiNome;

    @FXML
    private TextField GUtxtiSobrenome;

    @FXML
    private TextField GUtxtiUsuario;

    @FXML
    private PasswordField GUtxtiSenha;

    @FXML
    private PasswordField GUtxtiRPSenha;

    @FXML
    private TextField GUtxtiEmail;

    @FXML
    private Button GUbtnSalvar;

    @FXML
    private Button GUbtnRemover;

    @FXML
    private Button GUbtnVoltar;
    
    Usuario usuario = new Usuario();
    ObservableList<Usuario> ob =  usuario.VerUsuarios();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GUtcNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        GUtcEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        GUtcID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        GUtvUsuarios.setItems(ob);
    }
    
    @FXML
    public void clicounalista(){
          
        ob.stream().filter((u) -> (u.getId()==GUtvUsuarios.getSelectionModel().getSelectedItem().getId())).map((u) -> {
            GUtxtiNome.setText(""+u.getNome());
            return u;
        }).map((u) -> {
            GUtxtiSobrenome.setText(""+u.getSobrenome());
            return u;
        }).map((u) -> {
            GUtxtiEmail.setText(""+u.getEmail());
            return u;
        }).map((u) -> {
            GUtxtiUsuario.setText(""+u.getUsuario());
            return u;
        }).map((u) -> {
            GUtxtiSenha.setText(""+u.getSenha());
            return u;
        }).forEachOrdered((u) -> {
            GUtxtiRPSenha.setText(""+u.getSenha());
        });
    }

    @FXML
    void ActionGUbtnRemover(ActionEvent event) {
        usuario.ApagarUsuario(GUtvUsuarios.getSelectionModel().getSelectedItem().getId());
        GUtvUsuarios.getItems().removeAll(GUtvUsuarios.getSelectionModel().getSelectedItem());
    }

    @FXML
    void ActionGUbtnSalvar(ActionEvent event) {
        usuario.setNome(GUtxtiNome.getText());
        usuario.setSobrenome(GUtxtiSobrenome.getText());
        usuario.setEmail(GUtxtiEmail.getText());
        usuario.setUsuario(GUtxtiUsuario.getText());
        usuario.setSenha(GUtxtiSenha.getText());
        
        usuario.AlterarDadosdoUsuario(GUtvUsuarios.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    void ActionGUbtnVoltar(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("home");
    }
    
    @FXML
    void GUbtnhelpaction(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("help");
    }
    
}
