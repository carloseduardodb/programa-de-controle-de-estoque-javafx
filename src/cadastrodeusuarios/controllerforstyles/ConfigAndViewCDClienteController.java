/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.auxapis.MoverForm;
import cadastrodeusuarios.classespoo.Cliente;
import cadastrodeusuarios.classespoo.FuncoesClient;
import conexoes.ConexaoSQLite;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;

public class ConfigAndViewCDClienteController implements Initializable {

    //referente a anchorverCadastroC apos login
    @FXML
    private AnchorPane anchorverCadastroC;
    @FXML
    private TextField txtipesquisarCliente;
    @FXML
    private Button btnadicionarCliente;
    @FXML
    private Button CAVbtnApagar;
    
    @FXML
    private AnchorPane VACarrastform;
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente, String> tblclientesID;
    @FXML
    private TableColumn<Cliente, String> tblclientesNome;
    @FXML
    private TableColumn<Cliente, String> tblclientesSobrenome;
    @FXML
    private TableColumn<Cliente, String> tblclientesEmail;
    @FXML
    private TableColumn<Cliente, Date> tblclientesDataNascimento;
    @FXML
    private AnchorPane blackancharpane;
    @FXML
    private ImageView VACimgClient;
    
    private final ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
    //verificar veracidade
    private final FuncoesClient cliente = new FuncoesClient();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tblclientesID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tblclientesNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tblclientesSobrenome.setCellValueFactory(new PropertyValueFactory<>("Sobrenome"));
        tblclientesEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tblclientesDataNascimento.setCellValueFactory(new PropertyValueFactory<>("Datadenascimento"));
        
        tblClientes.setItems(cliente.VerCliente(false));
        
        new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConfigAndViewCDClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(ConexaoSQLite.crud == 1){
                    tblClientes.getItems().clear();
                    tblClientes.setItems(cliente.VerCliente(false));
                    System.out.println("Deu Certo!");
                    ConexaoSQLite.crud = 0;
                }
            }
        }).start();
        
    }    
    
    //pesquisa na tabela
    @FXML
    void seachingListener(KeyEvent event) {
         FilteredList<Cliente> filterData = new FilteredList<>(cliente.VerCliente(true), p -> true);
         txtipesquisarCliente.textProperty().addListener((observable, oldvalue, newvalue) ->{
             filterData.setPredicate(pers -> {
                 if(newvalue == null || newvalue.isEmpty()){
                    return true;
                 }
                 String typedText = newvalue.toLowerCase();
                 if(pers.getNome().toLowerCase().contains(typedText)){
                     return true;
                 }
                 if(pers.getSobrenome().toLowerCase().contains(typedText)){
                     return true;
                 }
                 if(pers.getEmail().toLowerCase().contains(typedText)){
                     return true;
                 }
                 String data = ""+pers.getDatadenascimento();
                 if(data.toLowerCase().contains(typedText)){
                     return true;
                 }
                 return false;
             });
             SortedList<Cliente> sortedList = new SortedList<>(filterData);
             sortedList.comparatorProperty().bind(tblClientes.comparatorProperty());
             tblClientes.setItems(sortedList);
         });
    }
    //apaga dado do banco e tira o dado da tabela
    @FXML
    void CAVbtnApagar(ActionEvent event) {
        cliente.ApagarCliente(tblClientes.getSelectionModel().getSelectedItem().getId());
        //tblClientes.getItems().removeAll(tblClientes.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void CAVbtnEditar(){
        try {
        blackancharpane.setVisible(true);
        Map mp = cliente.EditarCliente(tblClientes.getSelectionModel().getSelectedItem().getId());
        if(mp!=null){
            CadastrodeUsuarios.changeScreen("modificarcliente", cliente.EditarCliente(tblClientes.getSelectionModel().getSelectedItem().getId()));
        }else{
            blackancharpane.setVisible(false);
            System.out.println("Não existe nenhum dado no banco, ou o correu um erro na validação ds dados!");
        }
        } catch (Exception ex) {
            blackancharpane.setVisible(false);
            System.out.println("Voce nao selecionou nenhum item!");
        }
        new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConfigAndViewCDClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(CadastrodeUsuarios.getStage().isFocused() && !Thread.currentThread().isInterrupted()){
                    blackancharpane.setVisible(false);
                    Thread.currentThread().stop();
                    }
                }
        }).start();
    }
    
    @FXML
    private void OnclickAddClient(){
        blackancharpane.setVisible(true);
        CadastrodeUsuarios.changeScreen("addCliente");
        new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConfigAndViewCDClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(CadastrodeUsuarios.getStage().isFocused() && !Thread.currentThread().isInterrupted()){
                    blackancharpane.setVisible(false);
                    Thread.currentThread().stop();
                    }
                }
        }).start();
    }
    
    private static int tam;
    
    @FXML
    void VAClistClick(MouseEvent event) {
        ObservableList<Cliente> obl = cliente.VerCliente(true);
        VACimgClient.getImage().cancel();
        BufferedImage img = null;
        
        for(Cliente cl : obl){
            if(cl.getId() == tblClientes.getSelectionModel().getSelectedItem().getId()){
                try {
                    img = ImageIO.read(new ByteArrayInputStream(cl.getImageUser()));
                    Image image = SwingFXUtils.toFXImage(img, null);
                    VACimgClient.setImage(image);
                    Thread.currentThread().interrupt();
                } catch (IOException ex) {
                    Logger.getLogger(ConfigAndViewCDClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @FXML
    void VACimgClicked(MouseEvent event) {
        CadastrodeUsuarios.changeScreen("detalhesCliente", tblClientes.getSelectionModel().getSelectedItem().getId());
        blackancharpane.setVisible(true);
        
        new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConfigAndViewCDClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(CadastrodeUsuarios.getStage().isFocused() && !Thread.currentThread().isInterrupted()){
                    blackancharpane.setVisible(false);
                    Thread.currentThread().stop();
                    }
                }
        }).start();
    }
    
    @FXML
    private void btnVoltar(){
        CadastrodeUsuarios.changeScreen("home");
    }    
    
    @FXML
    void VAChelpbtnaction(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("help");
    }
    
    @FXML
    void HMbtnExit(MouseEvent event) {
        System.exit(0);
    }
    
    @FXML
    void Minimizarbtn(MouseEvent event) {
        CadastrodeUsuarios.getStage().setIconified(true);
    }
    
    @FXML
    void VACpressendform(MouseEvent event) {
        MoverForm.MoveP(VACarrastform);
    }
    
    private Boolean maximizado = false;
    
    @FXML
    void maximizarbtn(MouseEvent event) {
        if(!maximizado){
            CadastrodeUsuarios.getStage().setFullScreen(true);
            maximizado = true;
        }else{
            CadastrodeUsuarios.getStage().setFullScreen(false);
            maximizado = false;
        }
    }
}
