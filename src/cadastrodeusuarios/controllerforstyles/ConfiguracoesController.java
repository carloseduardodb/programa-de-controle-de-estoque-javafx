/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Carlos-ED
 */
public class ConfiguracoesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void CFGbtnvoltar(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("home");
    }
    
    @FXML
    void CFGbtnhelp(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("help");
    }
    
}
