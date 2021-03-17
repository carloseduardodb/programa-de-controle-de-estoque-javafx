/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.classespoo.Cliente;
import cadastrodeusuarios.classespoo.FuncoesClient;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Carlos
 */



public class DetailsforClientController implements Initializable {

    @FXML
    private Text DTLtxtNome;

    @FXML
    private Text DTLtxtSobrenome;

    @FXML
    private Text DTLtxtEmail;

    @FXML
    private Text DTLtxtTelefone;

    @FXML
    private Text DTLtxtUF;

    @FXML
    private Text DTLtxtNacionalidade;

    @FXML
    private Text DTLtxtCPF;

    @FXML
    private Text DTLtxtRG;

    @FXML
    private Text DTLtxtSexo;

    @FXML
    private Text DTLtxtDatadenascimento;

    @FXML
    private Text DTLtxtCEP;

    @FXML
    private Text DTLtxtCidade;

    @FXML
    private Text DTLtxtBairro;

    @FXML
    private Text DTLtxtRua;

    @FXML
    private Text DTLtxtNumero;

    @FXML
    private Text DTLtxtComplemento;

    @FXML
    private ImageView DTLimgFotoCliente;
    
    @FXML
    private AnchorPane DTLAnchorPanePCP;
    
    @FXML
    private WebView DTLwbvMaps;
    
    @FXML
    private WebView DTLwbvMaps1;
    
    @FXML
    private AnchorPane DTLpaneMap;
    
    @FXML
    private ProgressIndicator DTLprogressBar;
    
    @FXML
    private ProgressIndicator DTLprogressBar1;
    
    private int identificacao;
    
    private String link;
    
    private WebEngine we;
    
    private WebEngine we1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DTLprogressBar.setVisible(true);
        DTLprogressBar1.setVisible(true);
        CadastrodeUsuarios.addOnChangeScreenListener((String newScreen, Object objectData) -> {
            if (newScreen.equals("detalhesCliente")) {
                //identificacao = Integer.parseInt(mapDados.get("id")); 
                FuncoesClient fc = new FuncoesClient();
                
                ObservableList<Cliente> o = fc.VerCliente(false);
                
                for(Cliente dados : o){
                    if(dados.getId() == (int) objectData){
                        BufferedImage img = null;
                        try {
                            img = ImageIO.read(new ByteArrayInputStream(dados.getImageUser()));
                        } catch (IOException ex) {
                            Logger.getLogger(DetailsforClientController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Image image = SwingFXUtils.toFXImage(img, null);
                        
                        DTLimgFotoCliente.setImage(image);
                        DTLtxtNome.setText(dados.getNome());
                        DTLtxtSobrenome.setText(dados.getSobrenome());
                        DTLtxtEmail.setText(dados.getEmail());
                        DTLtxtTelefone.setText(dados.getTelefone());
                        DTLtxtUF.setText(dados.getUf());
                        DTLtxtNacionalidade.setText(dados.getNacionalidade());
                        DTLtxtCPF.setText(dados.getCpf());
                        DTLtxtRG.setText(dados.getRG());
                        DTLtxtSexo.setText(dados.getSexo());
                        DTLtxtDatadenascimento.setText(""+dados.getDatadenascimento());
                        DTLtxtCEP.setText(dados.getCep());
                        DTLtxtCidade.setText(dados.getCidade());
                        DTLtxtBairro.setText(dados.getBairro());
                        DTLtxtRua.setText(dados.getRua());
                        DTLtxtNumero.setText(dados.getNumero());
                        DTLtxtComplemento.setText(dados.getComplemento());
                        
                        String cidade = dados.getCidade().replace(" ", "+");
                        
                        link = "http://www.google.com/maps/search/R.+"+dados.getRua()+",+"+dados.getNumero()+"+-+"+cidade+",+"+dados.getUf()+",+"+dados.getCep()+",+Brasil";
                        
                        we = DTLwbvMaps.getEngine();
                        we1 = DTLwbvMaps1.getEngine();
                        
                        we.load(link);
                        we1.load(link);
                        
                        we.getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
                            DTLprogressBar1.setVisible(false);
                        });
                    
                        we1.getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
                            DTLprogressBar.setVisible(false);
                        });
                    }
                }
            }
        });
    }
    
    @FXML
    void DTLbtnExit(MouseEvent event) {
        we.loadContent("");
        we1.loadContent("");
        DTLprogressBar.setVisible(true);
        DTLprogressBar1.setVisible(true);
        DTLAnchorPanePCP.getScene().getWindow().hide();
    }
    
    @FXML
    void MPbtnReturn(MouseEvent event) {
        DTLpaneMap.setVisible(false);
    }
    
    @FXML
    void DTLmpbtnZomm(MouseEvent event) {
        DTLpaneMap.setVisible(true);
    }
}
