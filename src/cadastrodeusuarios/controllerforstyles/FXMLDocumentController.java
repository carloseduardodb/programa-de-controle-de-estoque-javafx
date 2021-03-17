/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.CriarBancoSQLite;
import cadastrodeusuarios.Moldes.AutenticateLogin;
import cadastrodeusuarios.auxapis.MoverForm;
import cadastrodeusuarios.auxapis.PositiveAlertController;
import cadastrodeusuarios.auxapis.PassardadosAlert;
import cadastrodeusuarios.classespoo.Usuario;
import conexoes.ConexaoSQLite;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
/**
 *
 * @author Carlos-ED
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane PCPLogin;
    @FXML
    private Button LGbtnClose;
    
    //referente a anchor login
    @FXML
    private AnchorPane anchorLogin;
    @FXML
    private TextField lgntxtpUsuario;
    @FXML
    private PasswordField lgntxtpSenha;
    @FXML
    private Button lgnbtnEntrar;
    @FXML
    private Button lgnbtnCadastrar;
    @FXML
    private Text verificacaoanchorlg;
    @FXML
    private ProgressIndicator carregandoLGCD;

    //referente a anchorpropagLion
    @FXML
    private AnchorPane propagLion;

    //referente a anchorCadastro
    @FXML
    private AnchorPane anchorCadastroU;
    @FXML
    private TextField cdttxtfNome;
    @FXML
    private TextField cdttxtfSobrenome;
    @FXML
    private TextField cdttxtfEmail;
    @FXML
    private TextField cdttxtfUsuario;
    @FXML
    private PasswordField cdttxtfSenha;
    @FXML
    private PasswordField cdttxtfRPSenha;
    @FXML
    private Button cdtbtnCadastrar;
    @FXML
    private Text verificCamposCD;
    
    @FXML
    private AnchorPane opacityvisible;

    //referente a achorAddCliente
    ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
    CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
    AutenticateLogin al = new AutenticateLogin();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lgntxtpUsuario.setText("felipe35");
        lgntxtpSenha.setText("12345");
    }

    //acoes do btnlogin ----------- AnchorLogin
    @FXML
    public void ActionlgnbtnEntrar() {     
        if(al.autentica(lgntxtpUsuario, lgntxtpSenha)){
            carregandoLGCD.setVisible(false);
            propagLion.setVisible(false);
            anchorLogin.setVisible(false);
            CadastrodeUsuarios.changeScreen("home", al.getDadosUserLG());
        }else{
            carregandoLGCD.setVisible(false);
            verificacaoanchorlg.setText("Usuário ou senha incorretos!");
        }
    }

    //acoes do btncadastro ----------- AnchorLogin
    @FXML
    public void ActionlgnbtnCadastrar() {
        anchorLogin.setVisible(false);
        anchorCadastroU.setVisible(true);
    }

    //---------------------------------------------
    //referente a AnchorCadastro
    @FXML
    public void ActioncdtbtnVoltar() {
        emptytoCampsAll();
        anchorCadastroU.setVisible(false);
        anchorLogin.setVisible(true);
    }

    @FXML
    public void ActioncdtbtnCadastrar() {
        carregandoLGCD.setVisible(true);
        String nome = cdttxtfNome.getText();
        String sobrenome = cdttxtfSobrenome.getText();
        String email = cdttxtfEmail.getText();
        String usuario = cdttxtfUsuario.getText();
        String senha = cdttxtfSenha.getText();
        //add to user data from table, operating late to user loading interface...
        if (!cdttxtfNome.getText().isEmpty() && !cdttxtfSobrenome.getText().isEmpty() && !cdttxtfEmail.getText().isEmpty() && !cdttxtfUsuario.getText().isEmpty() && !cdttxtfSenha.getText().isEmpty() && !cdttxtfRPSenha.getText().isEmpty()) {
            Usuario pessoa = new Usuario();
            pessoa.setNome(nome);
            pessoa.setSobrenome(sobrenome);
            pessoa.setEmail(email);
            pessoa.setUsuario(usuario);
            pessoa.setSenha(senha);
            
                Boolean ps = pessoa.AdicionaUsuario();
             
                if(ps){
                    carregandoLGCD.setVisible(false);
                    opacityvisible.setVisible(true);
                    emptytoCampsAll();
                    PassardadosAlert.titulo = "Cadastrado com Sucesso!";
                    PassardadosAlert.texto = "Recomendamos que ao Fazer o Primeiro Login, Você Adicione sua Foto!";
                    PassardadosAlert.icone = new File("src/cadastrodeusuarios/img/checkedicon.png");
                    PassardadosAlert.imgpcp = new File("src/cadastrodeusuarios/img/icon1.png");
                    PassardadosAlert.btncenter = true;
                    PositiveAlertController pac =  new PositiveAlertController();
                    pac.abredialog();
                    
                    new Thread(() -> {
                        while (true) {
                            if(!PositiveAlertController.stages.isFocused() && !Thread.currentThread().isInterrupted()){
                                opacityvisible.setVisible(false);
                                Thread.currentThread().interrupt();
                            }
                        }
                    }).start();

                }else{
                    carregandoLGCD.setVisible(false);
                    System.out.println("Error!");
                }
        } else {
            carregandoLGCD.setVisible(false);
            verificCamposCD.setText("Você não preencheu todos os campos!");
        }

    }

    private void emptytoCampsAll() {
        //referente a cadastro Anchor
        cdttxtfNome.setText("");
        cdttxtfSobrenome.setText("");
        cdttxtfEmail.setText("");
        cdttxtfUsuario.setText("");
        cdttxtfSenha.setText("");
        cdttxtfRPSenha.setText("");
        verificCamposCD.setText("");

        //referente a login Anchor
        lgntxtpUsuario.setText("");
        lgntxtpSenha.setText("");
        verificacaoanchorlg.setText("");
    }

    @FXML
    void LGmover(MouseEvent event) {
        MoverForm.MoveP(propagLion);
    }
    
     @FXML
    void LGeventClose(MouseEvent event) {
        System.exit(0);
    }

}
