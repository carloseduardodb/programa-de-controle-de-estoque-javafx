/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.classespoo.ProdutosVendidos;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class RelatoriodeVendasController implements Initializable {
    @FXML
    private CategoryAxis RVGFlinha;

    @FXML
    private NumberAxis RVGFcoluna;
    
    @FXML
    private LineChart<?, ?> RVgfcVendasRealizadas;
    
    @FXML
    private TableView<ProdutosVendidos> RVtblVendasRealizadas;

    @FXML
    private TableColumn<ProdutosVendidos, Integer> RVtblID;

    @FXML
    private TableColumn<ProdutosVendidos, String> RVtblNomeproduto;
    
    @FXML
    private TableColumn<ProdutosVendidos, Date> RVtblDatadaVenda;

    @FXML
    private TableColumn<ProdutosVendidos, Double> RVtblValordaVenda;

    @FXML
    private TableColumn<ProdutosVendidos, Integer> RVtblNumerodeItens;

    @FXML
    private TextField RVtxtiPesquisar;

    @FXML
    private Button RVbtnImprimir;

    @FXML
    private Text RVtxtTotaldeVendas;

    @FXML
    private Text RVtxtMediadasVendas;
    
    private static int tamanho;

    private final ProdutosVendidos pdv = new ProdutosVendidos();
    
    private final ObservableList<ProdutosVendidos> productBuy = pdv.VerProdutosVendidos();
  
    private final DecimalFormat df = new DecimalFormat("##,###,###.00");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        RVtblID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        RVtblNomeproduto.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        RVtblNumerodeItens.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
        RVtblValordaVenda.setCellValueFactory(new PropertyValueFactory<>("Precodevenda"));
        RVtblDatadaVenda.setCellValueFactory(new PropertyValueFactory<>("Datadecadastro"));
        
        RVtblVendasRealizadas.setItems(pdv.VerProdutosVendidos());
 
        //preciso disso para criar algo pra armazenar os dados
        XYChart.Series series = new XYChart.Series();
        
        Double totalvenda = 0.0;
        int quantidadedproduct = 0;
        
        String[] dtpcS = new String[100000];
        
        
        
        for(ProdutosVendidos item : productBuy){
            totalvenda = totalvenda + item.getPrecodevenda();
            quantidadedproduct++;
        
            dtpcS[quantidadedproduct] = item.getDatadecadastro();
            
            if(quantidadedproduct>=1){
           
                if(!dtpcS[quantidadedproduct].equals(dtpcS[quantidadedproduct-1])){
                    series.getData().add(new XYChart.Data(""+item.getDatadecadastro(),item.getPrecodevenda()));
                }
            }
        }

        
        series.setName("Maiores Vendas Realizadas por Mês");
        RVgfcVendasRealizadas.getData().addAll(series);
        RVtxtTotaldeVendas.setText("R$ "+df.format(totalvenda));
        RVtxtMediadasVendas.setText("R$ "+df.format(totalvenda/quantidadedproduct));
    }    
    
    @FXML
    void ActionRVbtnImprimir(ActionEvent event) {
        
    }

    @FXML
    void acaobtnvoltar(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("home");
    }
    
    @FXML
    void RVbtnhelp(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("help");
    }
    
}
