/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.auxapis.PassardadosAlert;
import cadastrodeusuarios.auxapis.PositiveAlertController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import cadastrodeusuarios.classespoo.Produtos;
import java.io.File;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class AdicionarProdutosController implements Initializable {

    @FXML
    private TextField APtxtiNome;

    @FXML
    private TextField APtxtiMarca;

    @FXML
    private TextField APtxtiQuantidade;

    @FXML
    private TextArea APtxtiDescricao;

    @FXML
    private TextField APtxtiCategoria;

    @FXML
    private TextField APtxtiPrecoVenda;

    @FXML
    private TextField APtxtiPrecoCusto;

    @FXML
    private Button APbtnAdicionar;

    @FXML
    private AnchorPane APpanePCP;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void Actionbtnadd(ActionEvent event) {
        Produtos produtos = new Produtos();
        
        produtos.setNome(APtxtiNome.getText());
        produtos.setMarca(APtxtiMarca.getText());
        produtos.setCategoria(APtxtiCategoria.getText());
        produtos.setQuantidade(Integer.parseInt(APtxtiQuantidade.getText()));
        produtos.setDescricao(APtxtiDescricao.getText());
        produtos.setPrecodecusto(Double.parseDouble(APtxtiPrecoCusto.getText()));
        produtos.setPrecodevenda(Double.parseDouble(APtxtiPrecoVenda.getText()));        
        
        boolean a = produtos.AdicionarProdutos();
        
        if(a){
             //Colocar aviso que foi adicionado com sucesso
            PassardadosAlert.titulo = "Produto Adicionado com Sucesso!";
            PassardadosAlert.texto = "A base de dados será atualizada em instantes!";
            PassardadosAlert.icone = new File("src/cadastrodeusuarios/img/checkedicon.png");
            PassardadosAlert.imgpcp = new File("src/cadastrodeusuarios/img/clientaddsucess.png");
            PassardadosAlert.btncenter = true;
            PositiveAlertController pac =  new PositiveAlertController();
            pac.abredialogd();
        }else{
            //Colocar Aviso Erro ao adicionar
            PassardadosAlert.titulo = "ERRO!";
            PassardadosAlert.texto = "Ocorreu um Erro ao Adicionar um Produto!";
            PassardadosAlert.icone = new File("src/cadastrodeusuarios/img/iconfail.png");
            PassardadosAlert.imgpcp = new File("src/cadastrodeusuarios/img/removeuser.png");
            PassardadosAlert.btncenter = true;
            PositiveAlertController pac =  new PositiveAlertController();
            pac.abredialogd();
        }
    }
    
    @FXML
    void APbtnFechar(MouseEvent event) {
        APpanePCP.getScene().getWindow().hide();
    }
    
}
