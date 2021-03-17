/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios;

import conexoes.ConexaoSQLite;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Carlos-ED
 */

public class CadastrodeUsuarios extends Application {

    private static Stage stage;
    public static Stage sac;
    
    private static Scene login;
    private static Scene cfgandviewcdclienteScene;
    private static Scene addCliente;
    private static Scene sceneHome;
    private static Scene vereadicionarnovoproduto;
    private static Scene fazerumavenda;
    private static Scene relatoriodevendas;
    private static Scene gerenciarusuarios;
    private static Scene modificarcliente;
    private static Scene adicionarprodutos;
    private static Scene scenehelp;
    private static Scene configuracoes;
    private static Scene detalhesclient;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        
        primaryStage.setTitle("Cadastro de Clientes");
        
        //referente a login
        Parent FXMLlogin = FXMLLoader.load(getClass().getResource("styles/FXMLDocument.fxml"));
        login = new Scene(FXMLlogin);
        
        //referente a adicionar novo cliente
        Parent FXMLcliente = FXMLLoader.load(getClass().getResource("styles/addCliente.fxml"));
        addCliente = new Scene(FXMLcliente);
        
        //referente a home após login
        Parent FXMLhome = FXMLLoader.load(getClass().getResource("styles/Home.fxml"));
        sceneHome = new Scene(FXMLhome);
        
        //referente a visualizar e editar e adionar novo cliente
        Parent FXMLcfgandviewcliente = FXMLLoader.load(getClass().getResource("styles/ConfigAndViewCDCliente.fxml"));
        cfgandviewcdclienteScene = new Scene(FXMLcfgandviewcliente);
        
        //referente a ver e adicionar novo produto
        Parent FXMLvereadicionarnovoproduto = FXMLLoader.load(getClass().getResource("styles/VereAdicionarNewProduct.fxml"));
        vereadicionarnovoproduto = new Scene(FXMLvereadicionarnovoproduto);
        
        //referente a fazer uma venda
        Parent FXMLfazerumavenda = FXMLLoader.load(getClass().getResource("styles/FazerUmaVenda.fxml"));
        fazerumavenda = new Scene(FXMLfazerumavenda);
        
        //referente a Relatório de vendas
        Parent FXMLrelatoriodevendas = FXMLLoader.load(getClass().getResource("styles/RelatoriodeVendas.fxml"));
        relatoriodevendas = new Scene(FXMLrelatoriodevendas);
        
        //referente a gerenciar usuários
        Parent FXMLgerenciarusuarios = FXMLLoader.load(getClass().getResource("styles/GerenciarUsuarios.fxml"));
        gerenciarusuarios = new Scene(FXMLgerenciarusuarios);
        
        //referente a gerenciar clientes
        Parent FXMLmodificarcliente = FXMLLoader.load(getClass().getResource("styles/ModificarCliente.fxml"));
        modificarcliente = new Scene(FXMLmodificarcliente);
        
        //referente a adicionar usuarios
        Parent FXMLadicionarprodutos = FXMLLoader.load(getClass().getResource("styles/AdicionarProdutos.fxml"));
        adicionarprodutos = new Scene(FXMLadicionarprodutos);
        
        //referente a help chatbot
        Parent FXMLhelp = FXMLLoader.load(getClass().getResource("styles/helpchatbot.fxml"));
        scenehelp = new Scene(FXMLhelp);
        
        //referente a configuracoes
        Parent FXMLconfig  = FXMLLoader.load(getClass().getResource("styles/Configuracoes.fxml"));
        configuracoes = new Scene(FXMLconfig);
        
        //referente a detalhes do cliente
        Parent FXMLdetail = FXMLLoader.load(getClass().getResource("styles/DetailsforClient.fxml"));
        detalhesclient = new Scene(FXMLdetail);
        
        primaryStage.setScene(login);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
    
    public static void changeScreen(String scr, Object userData){
        switch(scr){
            case "home":{
                stage.setScene(sceneHome);
                stage.centerOnScreen();
                notifyAllListenners("home", userData);
                break;
            }
            
            case "configandviewCDCliente":{
                stage.setScene(cfgandviewcdclienteScene);
                stage.centerOnScreen();
                notifyAllListenners("configandviewCDCliente", userData);
                break;
            }
            case "login":{
                stage.setScene(login);
                stage.centerOnScreen();
                notifyAllListenners("login", userData);
                break;
            }
            case "vereadicionarnovoproduto":{
                stage.setScene(vereadicionarnovoproduto);
                stage.centerOnScreen();
                notifyAllListenners("vereadicionarproduto", userData);
                break;
            }
            case "fazerumavenda":{
                stage.setScene(fazerumavenda);
                stage.centerOnScreen();
                notifyAllListenners("fazerumavenda", userData);
                break;
            }
            case "relatoriodevendas":{
                stage.setScene(relatoriodevendas);
                stage.centerOnScreen();
                notifyAllListenners("relatoriodevendas", userData);
                break;
            }
            case "gerenciarusuarios":{
                stage.setScene(gerenciarusuarios);
                stage.centerOnScreen();
                notifyAllListenners("gerenciarusuarios", userData);
                break;
            }
            case "help":{
                stage.setScene(scenehelp);
                stage.centerOnScreen();
                notifyAllListenners("help", userData);
                break;
            }
            
            case "configuracoes":{
                stage.setScene(configuracoes);
                stage.centerOnScreen();
                notifyAllListenners("configuracoes", userData);
                break;
            }
            
            //telas independentes do stagePCP
            case "addCliente":{
                sac = new Stage();
                sac.initOwner(getStage());
                sac.initModality(Modality.APPLICATION_MODAL);
                sac.initStyle(StageStyle.UNDECORATED);
                sac.setScene(addCliente);
                sac.show();
                notifyAllListenners("addCliente", userData);
                break;
            }
            case "modificarcliente":{
                sac = new Stage();
                sac.initOwner(getStage());
                sac.initModality(Modality.APPLICATION_MODAL);
                sac.initStyle(StageStyle.UNDECORATED);
                sac.setScene(modificarcliente);
                sac.show();
                //stage.setScene(modificarcliente);
                //stage.centerOnScreen();
                notifyAllListenners("modificarcliente", userData);
                break;
            }
            case "detalhesCliente":{
                sac = new Stage();
                sac.initOwner(getStage());
                sac.initModality(Modality.APPLICATION_MODAL);
                sac.initStyle(StageStyle.UNDECORATED);
                sac.setScene(detalhesclient);
                sac.show();
                notifyAllListenners("detalhesCliente", userData);
                break;
            }
            
            case "adicionarprodutos":{
                sac = new Stage();
                sac.initOwner(getStage());
                sac.initModality(Modality.APPLICATION_MODAL);
                sac.initStyle(StageStyle.UNDECORATED);
                sac.setScene(adicionarprodutos);
                sac.show();
                notifyAllListenners("adicionarprodutos", userData);
                break;
            }
        }
    }
    
    public static void changeScreen(String scr){
        changeScreen(scr, null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        CriarBancoSQLite criarBancoSQLite = new CriarBancoSQLite(conexaoSQLite);
        criarBancoSQLite.criaTabelaPessoa();
    }
    
    //interface 
    //notificar
    //listenner
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
    
    public static interface OnChangeScreen{
        void onScreenChange(String newScreen, Object objectData);
    }
    
    public static void addOnChangeScreenListener(OnChangeScreen newListenner){
        listeners.add(newListenner);
    }
    
    private static void notifyAllListenners(String newScreen, Object objectData){
        for(OnChangeScreen l : listeners)
        l.onScreenChange(newScreen, objectData);
    }
    
    @Override
    public void stop(){
        System.exit(0);
    }
    
    public static Stage getStage() {
        return stage;
    }
}
