/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.auxapis;

import cadastrodeusuarios.CadastrodeUsuarios;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class PositiveAlertController implements Initializable{

    @FXML
    private ImageView alertIMGicon;

    @FXML
    private Text alerttxtitle;

    @FXML
    private Text alerttxtdescription;

    @FXML
    private ImageView alertIMGPCP;

    @FXML
    private Button alertbtnNegative;

    @FXML
    private Button alertbtnPositiveCenter;

    @FXML
    private Button alertbtnPositive;
    
    public static Scene alerta;
    
    public static Stage stages = new Stage();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {   
                    try {
                        Thread.currentThread().sleep(20);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PositiveAlertController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    alerttxtitle.setText(PassardadosAlert.titulo);
                    alerttxtdescription.setText(PassardadosAlert.texto);
                    Image imgicon = new Image(PassardadosAlert.icone.toURI().toString());
                    alertIMGicon.setImage(imgicon);
                    Image imgpcp =  new Image(PassardadosAlert.imgpcp.toURI().toString());
                    alertIMGPCP.setImage(imgpcp);
                }
            }
        }).start();
        
        if(PassardadosAlert.btncenter){
            alertbtnPositive.setVisible(false);
            alertbtnNegative.setVisible(false);
        }else{
            alertbtnPositiveCenter.setVisible(false);
            alertbtnPositive.setVisible(true);
            alertbtnNegative.setVisible(true);
        }
    }
    
    public void abredialog(/*String titulo, String texto, File fileimgicon, File fileimgpcp, Boolean option*/) {
        try{
            if(stages.getModality() == Modality.NONE){
            Parent alertaLoad = FXMLLoader.load(getClass().getResource("PositiveAlert.fxml"));
            alerta = new Scene(alertaLoad);
            stages.initOwner(CadastrodeUsuarios.getStage());
            stages.initModality(Modality.APPLICATION_MODAL);
            stages.setScene(alerta);
            stages.initStyle(StageStyle.UNDECORATED);
            stages.show();
            }else{
                //tomar cuidado com os dados
                stages.show();
            }
        }catch(Exception e){
            System.out.println("Deu ruim!" + e);
        }
    }
    
    public void abredialogd(/*String titulo, String texto, File fileimgicon, File fileimgpcp, Boolean option*/) {
        try{
            if(stages.getModality() == Modality.NONE){
            Parent alertaLoad = FXMLLoader.load(getClass().getResource("PositiveAlert.fxml"));
            alerta = new Scene(alertaLoad);
            stages.initOwner(CadastrodeUsuarios.sac);
            stages.initModality(Modality.APPLICATION_MODAL);
            stages.initStyle(StageStyle.UNDECORATED);
            stages.setScene(alerta);
            stages.show();
            }else{
                //tomar cuidado com os dados
                stages.show();
            }
        }catch(Exception e){
            System.out.println("Deu ruim!" + e);
        }
    }
    
    @FXML
    void eventNegativebtn(MouseEvent event) {
        //desaparece alerta
        alerta.getWindow().hide();
    }
    
    @FXML
    void eventPositivecenterbtn(MouseEvent event) {
        //desaparece alerta
        alerta.getWindow().hide();
        stages.close();
    }

    @FXML
    void eventPositvebtn(MouseEvent event) {

    }
}
