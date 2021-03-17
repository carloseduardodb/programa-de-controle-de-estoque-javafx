/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.auxapis.MascarasFX;
import cadastrodeusuarios.auxapis.PassardadosAlert;
import cadastrodeusuarios.auxapis.PositiveAlertController;
import cadastrodeusuarios.classespoo.FuncoesClient;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ModificarClienteController implements Initializable {

    @FXML
    private AnchorPane ADaPanetoPCP;
    
    @FXML
    private TextField ADtxtiemail;

    @FXML
    private TextField ADtxtitelefone;

    @FXML
    private TextField ADtxticep;

    @FXML
    private TextField ADtxticidade;

    @FXML
    private TextField ADtxtibairro;

    @FXML
    private TextField ADtxtinumero;

    @FXML
    private TextField ADtxticomplemento;

    @FXML
    private TextField ADtxtiuf;

    @FXML
    private TextField ADtxtirg;

    @FXML
    private TextField ADtxticpf;

    @FXML
    private Button ADbtnatualizar;

    @FXML
    private Button ADbtnvoltar;
    
    @FXML
    private TextField ADtxtiRua;
    
    Integer id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CadastrodeUsuarios.addOnChangeScreenListener((String newScreen, Object objectData) -> {
            if (newScreen.equals("modificarcliente")) {
                Map<String, String> mapDados;
                mapDados = (Map<String, String>) objectData;
                ADtxtiemail.setText(mapDados.get("email"));
                ADtxtitelefone.setText(mapDados.get("telefone"));
                ADtxticep.setText(mapDados.get("cep"));
                ADtxticidade.setText(mapDados.get("cidade"));
                ADtxticomplemento.setText(mapDados.get("complemento"));
                ADtxtinumero.setText(mapDados.get("numero"));
                ADtxtibairro.setText(mapDados.get("bairro"));
                ADtxtiRua.setText(mapDados.get("rua"));
                ADtxticpf.setText(mapDados.get("cpf"));
                ADtxtirg.setText(mapDados.get("rg"));
                ADtxtiuf.setText(mapDados.get("uf"));
                
                id = Integer.parseInt(mapDados.get("id")); 
            }
        });
    }

    @FXML
    void ADbtnAtualizarClick(ActionEvent event) {
        FuncoesClient cl = new FuncoesClient();
        
        cl.setEmail(ADtxtiemail.getText());
        cl.setTelefone(ADtxtitelefone.getText());
        cl.setCep(ADtxticep.getText());
        cl.setCidade(ADtxticidade.getText());
        cl.setComplemento(ADtxticomplemento.getText());
        cl.setNumero(ADtxtinumero.getText());
        cl.setBairro(ADtxtibairro.getText());
        cl.setRua(ADtxtiRua.getText());
        cl.setCpf(ADtxticpf.getText());
        cl.setRG(ADtxtirg.getText());
        cl.setUf(ADtxtiuf.getText());
        cl.setId(id);
        
        Boolean bl = cl.modifyClient();
        
        if(bl){
            PassardadosAlert.titulo = "Sucesso!";
            PassardadosAlert.texto = "Dados do Cliente Alterados com Exito!";
            PassardadosAlert.icone = new File("src/cadastrodeusuarios/img/checkedicon.png");
            PassardadosAlert.imgpcp = new File("src/cadastrodeusuarios/img/altsucessfull.png");
            PassardadosAlert.btncenter = true;
            PositiveAlertController pac =  new PositiveAlertController();
            pac.abredialogd();
        }else{
            PassardadosAlert.titulo = "ERRO!";
            PassardadosAlert.texto = "Ocorreu um Erro ao Modificar o Cliente!";
            PassardadosAlert.icone = new File("src/cadastrodeusuarios/img/iconfail.png");
            PassardadosAlert.imgpcp = new File("src/cadastrodeusuarios/img/iconfail.png");
            PassardadosAlert.btncenter = true;
            PositiveAlertController pac =  new PositiveAlertController();
            pac.abredialogd();
        }
    }
    @FXML
    void ADbtnExit(MouseEvent event) {
        ADaPanetoPCP.getScene().getWindow().hide();
    }
    
    
    @FXML
    void ADmaskCPF(KeyEvent event) {
        MascarasFX.mascaraCPF(ADtxticpf);
    }

    @FXML
    void ADmaskEmail(KeyEvent event) {
        MascarasFX.mascaraEmail(ADtxtiemail);
    }

    @FXML
    void ADmaskTelefone(KeyEvent event) {
        MascarasFX.mascaraTelefone(ADtxtitelefone);
    }
    
}