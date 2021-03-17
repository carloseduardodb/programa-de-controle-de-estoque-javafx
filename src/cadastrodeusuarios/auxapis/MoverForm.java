/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.auxapis;

import cadastrodeusuarios.CadastrodeUsuarios;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Carlos
 */
public class MoverForm {
    private static double xOffset = 0; 
    private static double yOffset = 0; 
    
    public static void MoveP(AnchorPane node){
    
    node.setOnMousePressed((MouseEvent event) -> {
        xOffset = CadastrodeUsuarios.getStage().getX() - event.getScreenX();
        yOffset = CadastrodeUsuarios.getStage().getY() - event.getScreenY();
    }); 
    
    node.setOnMouseDragged((MouseEvent event) -> {
            CadastrodeUsuarios.getStage().setX(event.getScreenX() + xOffset);
            CadastrodeUsuarios.getStage().setY(event.getScreenY() + yOffset);
    });
    
    }
}
