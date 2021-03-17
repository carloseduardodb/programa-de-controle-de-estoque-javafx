/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.auxapis.MoverForm;
import cadastrodeusuarios.auxapis.PassardadosAlert;
import cadastrodeusuarios.auxapis.PositiveAlertController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import cadastrodeusuarios.classespoo.Produtos;
import conexoes.ConexaoSQLite;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Carlos
 */

public class VereAdicionarNewProductController implements Initializable {

    @FXML
    private TableView<Produtos> VAPtblProdutos;

    @FXML
    private TableColumn<Produtos, Integer> VAPtblID;

    @FXML
    private TableColumn<Produtos, String> VAPtblNome;

    @FXML
    private TableColumn<Produtos, String> VAPtblMarca;

    @FXML
    private TableColumn<Produtos, String> VAPtblDescricao;

    @FXML
    private TableColumn<Produtos, Integer> VAPtblQuantidade;

    @FXML
    private TableColumn<Produtos, String> VAPtblCategoria;

    @FXML
    private TableColumn<Produtos, Double> VAPtblPreco;

    @FXML
    private TableColumn<Produtos, String> VAPtblDataCadastro;

    @FXML
    private Button VAPbtnAdicionar;

    @FXML
    private Button VAPbtnRemover;

    @FXML
    private TextField VAPtxtiPesquisar;

    @FXML
    private Button VAPbtnReturnforHome;
    
    @FXML
    private AnchorPane VAPpaneTop;
    
    @FXML
    private Pane VAPpaneblack;
    
    private final Produtos produto = new Produtos();
    
    private final ObservableList<Produtos> pd = produto.VerProdutos();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VAPtblID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        VAPtblNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        VAPtblMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        VAPtblDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        VAPtblQuantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
        VAPtblCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        VAPtblPreco.setCellValueFactory(new PropertyValueFactory<>("Precodevenda"));
        VAPtblDataCadastro.setCellValueFactory(new PropertyValueFactory<>("Datadecadastro"));
        
        VAPtblProdutos.setItems(pd);
        VAPtblProdutos.setEditable(true);
        
        //usando textfield para editar os itens abaixo
        VAPtblNome.setCellFactory(TextFieldTableCell.forTableColumn());
        VAPtblMarca.setCellFactory(TextFieldTableCell.forTableColumn());
        VAPtblDescricao.setCellFactory(TextFieldTableCell.forTableColumn());
        VAPtblQuantidade.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        VAPtblPreco.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        
        new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConfigAndViewCDClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(ConexaoSQLite.crud == 1){
                    VAPtblProdutos.getItems().clear();
                    VAPtblProdutos.setItems(produto.VerProdutos());
                    System.out.println("Deu Certo!");
                    ConexaoSQLite.crud = 0;
                }
            }
        }).start();
        
    }
    
    @FXML
    void onEditName(TableColumn.CellEditEvent<Produtos, String> nomeProdutosEditEvent) {
        //System.out.println("ver id: "+VAPtblProdutos.getSelectionModel().getSelectedItem().getID());
        produto.AtualizaProdutos(VAPtblProdutos.getSelectionModel().getSelectedItem().getID(), "nome", nomeProdutosEditEvent.getNewValue());
        Produtos produtos = VAPtblProdutos.getSelectionModel().getSelectedItem();
        produtos.setNome(nomeProdutosEditEvent.getNewValue());
    }
    
    @FXML
    void onEditMarca(TableColumn.CellEditEvent<Produtos, String> marcaProdutosEditEvent) {
        produto.AtualizaProdutos(VAPtblProdutos.getSelectionModel().getSelectedItem().getID(), "marca", marcaProdutosEditEvent.getNewValue());
        Produtos produtos = VAPtblProdutos.getSelectionModel().getSelectedItem();
        produtos.setMarca(marcaProdutosEditEvent.getNewValue());
    }
    
    @FXML
    void onEditDescricao(TableColumn.CellEditEvent<Produtos, String> descricaoProdutosEditEvent) {
        produto.AtualizaProdutos(VAPtblProdutos.getSelectionModel().getSelectedItem().getID(), "descricao", descricaoProdutosEditEvent.getNewValue());
        Produtos produtos = VAPtblProdutos.getSelectionModel().getSelectedItem();
        produtos.setDescricao(descricaoProdutosEditEvent.getNewValue());
    }
    
    @FXML
    void onEditQuantidade(TableColumn.CellEditEvent<Produtos, Integer> quantidadeProdutosEditEvent) {
        produto.AtualizaProdutos(VAPtblProdutos.getSelectionModel().getSelectedItem().getID(), "quantidade", quantidadeProdutosEditEvent.getNewValue());
        Produtos produtos = VAPtblProdutos.getSelectionModel().getSelectedItem();
        produtos.setQuantidade(quantidadeProdutosEditEvent.getNewValue());
    }
    
    @FXML
    void onEditPreco(TableColumn.CellEditEvent<Produtos, Double> precoProdutosEditEvent) {
        //System.out.println(""+precoProdutosEditEvent.getClass().g);
        produto.AtualizaProdutos(VAPtblProdutos.getSelectionModel().getSelectedItem().getID(), "preco", precoProdutosEditEvent.getNewValue());
        Produtos produtos = VAPtblProdutos.getSelectionModel().getSelectedItem();
        produtos.setPrecodevenda(precoProdutosEditEvent.getNewValue());
    }
    
    @FXML
    void VAPbtnAdicionarProduto(ActionEvent event) {
        VAPpaneblack.setVisible(true);
        CadastrodeUsuarios.changeScreen("adicionarprodutos");
        
        new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConfigAndViewCDClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(CadastrodeUsuarios.getStage().isFocused() && !Thread.currentThread().isInterrupted()){
                    VAPpaneblack.setVisible(false);
                    Thread.currentThread().stop();
                    }
                }
        }).start();
    }

    @FXML
    void VAPbtnRemoverProduto(ActionEvent event) {
        try { 
            produto.ApagarProdutos(VAPtblProdutos.getSelectionModel().getSelectedItem().getID());
            VAPtblProdutos.getItems().removeAll(VAPtblProdutos.getSelectionModel().getSelectedItem());
            
            //Colocar aviso que foi adicionado com sucesso
            PassardadosAlert.titulo = "Produto Removido com Sucesso!";
            PassardadosAlert.texto = "A base de dados será atualizada em instantes!";
            PassardadosAlert.icone = new File("src/cadastrodeusuarios/img/checkedicon.png");
            PassardadosAlert.imgpcp = new File("src/cadastrodeusuarios/img/clientaddsucess.png");
            PassardadosAlert.btncenter = true;
            PositiveAlertController pac =  new PositiveAlertController();
            pac.abredialogd();
            
        } catch (Exception e) {
            System.out.println("Voce precisa selecionar um produto para excluir!");
            //Colocar Aviso Erro ao adicionar
            PassardadosAlert.titulo = "ERRO!";
            PassardadosAlert.texto = "Você Precisa Clicar em um Produto Para Excluir!";
            PassardadosAlert.icone = new File("src/cadastrodeusuarios/img/iconfail.png");
            PassardadosAlert.imgpcp = new File("src/cadastrodeusuarios/img/removeuser.png");
            PassardadosAlert.btncenter = true;
            PositiveAlertController pac =  new PositiveAlertController();
            pac.abredialogd();
        }
    }

    @FXML
    void VAPbtnVoltarHome(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("home");
    }
    
    @FXML
    void VAPbtnhelp(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("help");
    }
    
    @FXML
    void VAPEventbtnFechar(MouseEvent event) {
        System.exit(0);
    }

    Boolean maximizado = false;
    
    @FXML
    void VAPEventbtnMaximizar(MouseEvent event) {
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

    @FXML
    void VAPEventbtnMinimizar(MouseEvent event) {
        CadastrodeUsuarios.getStage().setIconified(true);
    }
    
    @FXML
    void VAParrast(MouseEvent event) {
        MoverForm.MoveP(VAPpaneTop);
    }
    
    FilteredList<Produtos> filteredData = new FilteredList<>(pd, b -> true);
    
    @FXML
    void VAPseachinglistenner(KeyEvent event) {
         VAPtxtiPesquisar.textProperty().addListener((observable, oldvalue, newvalue) ->{
             filteredData.setPredicate(pers -> {
                 if(newvalue == null || newvalue.isEmpty()){
                    return true;
                 }
                 String typedText = newvalue.toLowerCase();
                 if(pers.getNome().toLowerCase().contains(typedText)){
                     return true;
                 }
                 if(pers.getCategoria().toLowerCase().contains(typedText)){
                     return true;
                 }
                 if(pers.getMarca().toLowerCase().contains(typedText)){
                     return true;
                 }
                 String data = ""+pers.getDatadecadastro();
                 if(data.toLowerCase().contains(typedText)){
                     return true;
                 }
                 return false;
             });
             SortedList<Produtos> sortedList = new SortedList<>(filteredData);
             sortedList.comparatorProperty().bind(VAPtblProdutos.comparatorProperty());
             VAPtblProdutos.setItems(sortedList);
         });
    }
}