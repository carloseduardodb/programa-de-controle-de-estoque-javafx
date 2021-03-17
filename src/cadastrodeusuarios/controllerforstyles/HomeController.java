/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.CriarBancoSQLite;
import cadastrodeusuarios.Moldes.AutenticateLogin;
import cadastrodeusuarios.auxapis.ConversordeImagem;
import cadastrodeusuarios.auxapis.MoverForm;
import cadastrodeusuarios.classespoo.Usuario;
import conexoes.ConexaoSQLite;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Carlos-ED
 */
public class HomeController implements Initializable {

    private byte[] imgArray;
    private final ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
    private final CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
    private final Usuario us = new Usuario();
    
    @FXML
    private AnchorPane HMPaneTop;
    
    @FXML
    private ImageView HMimgUser;
    
    Map<String, Object> mpUser = new HashMap<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarBancoSQLite.criaTabelaPessoa();
        
        CadastrodeUsuarios.addOnChangeScreenListener((String newScreen, Object objectData) -> {
            if (newScreen.equals("home")) {
               mpUser = (Map<String, Object>) objectData;
               
               BufferedImage img;
                try {
                    img = ImageIO.read(new ByteArrayInputStream((byte[]) mpUser.get("imagem")));
                    Image image = SwingFXUtils.toFXImage(img, null);
                    HMimgUser.setImage(image);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
    }    
    
    @FXML
    private void abreVerCliente(){
        CadastrodeUsuarios.changeScreen("configandviewCDCliente");
    }
    
    @FXML
    private void abreVereAdicionarNovoProduto(){
        CadastrodeUsuarios.changeScreen("vereadicionarnovoproduto");
    }
    
    @FXML
    private void abreFazerUmaVenda(){
        CadastrodeUsuarios.changeScreen("fazerumavenda");
    }
    
    @FXML
    private void abreRelatoriodeVendas(){
        CadastrodeUsuarios.changeScreen("relatoriodevendas");
    }
    
    @FXML
    private void abreGerenciarUsuários(){
        CadastrodeUsuarios.changeScreen("gerenciarusuarios");
    }
    
    @FXML
    private void Sair(){
        System.exit(1);
    }
    
    @FXML
    void HMLoadImg(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterJPG = 
                    new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            FileChooser.ExtensionFilter extFilterjpg = 
                    new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg);
            fileChooser.titleProperty().set("Selecione uma Imagem:");
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            if(file != null){
            try {
                //anula imagem anterior do componente
                HMimgUser.setImage(null);
                
                //cria um buffer de imagem lendo o file
                BufferedImage bufferedImage = ImageIO.read(file);
                
                //diminuo a imagem, chamando função especifica
                bufferedImage = ConversordeImagem.scale(bufferedImage, 250, 300);            
                
                //converte o buffer para image, para eu colocar no componente
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                
                //adiciono a imagem no componente
                HMimgUser.setImage(image);
                
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", stream);
                
                imgArray = stream.toByteArray();
                
                us.setImageUser(imgArray);
                
                //vou mandar a imagem pro banco aqui
                us.AlteraFoto((int) mpUser.get("id"));
                
            } catch (IOException ex) {
                System.out.println("Erro ao Carregar Imagem! Detalhes do Erro: "+ex);
            }  
            }
    }
    
    @FXML
    void HMbtnconfig(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("configuracoes");
    }
    
    @FXML
    void HMarrasttoForm(MouseEvent event) {
        MoverForm.MoveP(HMPaneTop);
    }
    
    @FXML
    void HMbtnExit(MouseEvent event) {
        System.exit(0);
    }
    
    @FXML
    void Minimizarbtn(MouseEvent event) {
        CadastrodeUsuarios.getStage().setIconified(true);
    }
    
    Boolean maximizado = false;
    
    @FXML
    void maximizarbtn(MouseEvent event) {
        if(!maximizado){
            CadastrodeUsuarios.getStage().centerOnScreen();
            CadastrodeUsuarios.getStage().setMaximized(true);
            maximizado = true;
        }else{
            CadastrodeUsuarios.getStage().centerOnScreen();
            CadastrodeUsuarios.getStage().setMaximized(false);
            maximizado = false;
        }
    }
}
