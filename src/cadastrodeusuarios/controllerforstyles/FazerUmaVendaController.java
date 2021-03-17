/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.auxapis.MoverForm;
import cadastrodeusuarios.classespoo.FazerumavendaActions;
import cadastrodeusuarios.classespoo.Produtos;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FazerUmaVendaController implements Initializable {

    @FXML
    private Button FVbtnVoltar;

    @FXML
    private Button FVbtnabreCalculadora;

    @FXML
    private Button FVbtnAbreRelatorio;

    @FXML
    private ComboBox<String> FVcbProduto;
    
    @FXML
    private Button FVbtnAbreCalendario;

    @FXML
    public Text FVtxthoradoSistema;

    @FXML
    private Spinner<Integer> FVspQuantidade;

    @FXML
    private ComboBox<String> FVcbFormadePagamento;

    @FXML
    private TextArea FVtxtaInformacoes;

    @FXML
    private TextField FVtxtfCodigo;

    @FXML
    private ComboBox<String> FVcbMarca;
    
    @FXML
    private Button FVbtnAdicionaraoCarrinho;

    @FXML
    private Button FVbtnVoltaraoProdutoAnterior;

    @FXML
    private Text FVtxtPreco;
    
    @FXML
    private Text FVqtprodutosEmEstoque;

    @FXML
    private Button FVbtnVender;

    @FXML
    private Text FVtxtTotaldaVenda;

    @FXML
    private TextField FVtxtfValorPago;

    @FXML
    private Text FVtxtDesconto;

    @FXML
    private Text FVtxtTroco;
    
    @FXML
    private Text FVtxtnumproductadd;
    
    @FXML
    private AnchorPane FUVAnchorPCP;
     
    //todas variaveis do form
    private int codigo;
    private String marca;
    private String produto;
    private int quantidade;
    private String formapagamento;
    private String descricao;
    private Double totaldavenda = 0.0;
    private Double precoproduto = 0.0;
    private DecimalFormat df = new DecimalFormat("##,###.00");
    
    //o tamanho da lista ficava mudando e bugando o programa se definido static ele nao muda
    private static int tamlist;
    
    private final FazerumavendaActions myclassController = new FazerumavendaActions();
    
    List<String> lt = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FVbtnVoltaraoProdutoAnterior.setVisible(false);
        //chama relogio para iniciar na atividade
        Relogio();
        
        //crio uma lista chamando da classe Produtos a funcao que retorna os dados do banco em uma lista observavel
        ObservableList dados = myclassController.VerProdutos();
        
        //Para saber o tamanho da lista eu precisava converter esses dados
        List<String> listaMarcas = new ArrayList<>();
        ObservableList<String> dadosList = FXCollections.observableArrayList();
        dadosList = dados;
        Integer n = dadosList.size();
        tamlist = n;
        
        //passa valores para uma lista
        /*
         * apos iniciar a atividade eu percorro o banco de dados 
         * atraz de marcas cadastradas e removo as marcas repetidas!
         */
         
        for(int i =0; i<=n; i++){
            listaMarcas.add(myclassController.VerProdutos().get(i).getMarca());
        }
        lt = listaMarcas;
        
        //comparo os valores e elimino os iguais
        Set<String> listaMarcassemDuplicidade = new TreeSet<>(listaMarcas);
        
        //adiciono item no combobox
        FVcbMarca.getItems().addAll(listaMarcassemDuplicidade);
        dadosList.clear();
        
        //adiciono dois itens de pagamento Dinheiro e Cartão no ComboBox
        FVcbFormadePagamento.getItems().add("Dinheiro");
        FVcbFormadePagamento.getItems().add("Cartão"); 
    }
    
    @FXML
    void ActionacordMarca(ActionEvent event) {
        /**
         * quando eu clico na marca o evento tem referenciar produto então tecnicamente este evento é do produto
        **/
        
        List<String> listaMarcas = new ArrayList<>();
        FVcbProduto.getItems().clear();
        
        List<String> listaProdutos = new ArrayList<>();
        listaProdutos.clear();
        
        //percorri toda a lista procurando produtos relacionados a marca
        for(int i =0; i<=tamlist; i++){
            listaMarcas.add(myclassController.VerProdutos().get(i).getMarca());
            if(myclassController.VerProdutos().get(i).getMarca().equals(FVcbMarca.getSelectionModel().getSelectedItem())){
                listaProdutos.add(myclassController.VerProdutos().get(i).getNome());
            }
        }
        FVcbProduto.getItems().addAll(listaProdutos);
    }
    
    @FXML
    void ActionProductSelection(ActionEvent event) {
        Map mp = retornaDadosProdutos(FVcbMarca.getSelectionModel().getSelectedItem(), FVcbProduto.getSelectionModel().getSelectedItem());
        //quando eu clico em produto eu pego todos os dados referentes ao mesmo e adiciono nos campos
        if(mp!=null){
            FVspQuantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
            1, (int) mp.get("quantidade"), Integer.parseInt("1")));
            FVspQuantidade.setEditable(false);
            FVtxtPreco.setText("R$ "+mp.get("preco"));
            FVtxtfCodigo.setText(""+mp.get("codigo"));
            FVtxtaInformacoes.setText(""+mp.get("descricao"));
            precoproduto = (Double) mp.get("preco");
            double preconconvert = (double) mp.get("preco");
            FVtxtPreco.setText("R$ "+df.format(preconconvert));
            FVqtprodutosEmEstoque.setText(""+mp.get("quantidade"));
        }
    }
    
    String pd[][] = new String[100][8];
    Integer tamAdicionado = 1;
    Integer quantidadett[] = new Integer[100];
    
    @FXML
    void ActionFVbtnfinalizarVenda(ActionEvent event) {
        
        /***
         * usar a lista dessa atividade para procurar pela quantidade de acordo com item
         * apos isso a finalização da lista esta feita
         * */
        
        for (int i = 1; i < tamAdicionado; i++) {
        Integer FNLcodigo = Integer.parseInt(pd[i][1]);
        String FNLmarca = pd[i][2];
        String FNLnomeprodutos = pd[i][3];
        Integer FNLquantidade = Integer.parseInt(pd[i][4]);
        String FNLformapagamento = pd[i][5];
        Double FNLprecoproduto = Double.parseDouble(pd[i][7]);
        
        //aqui eu instancio e passo valores para a classe
        Produtos FNprodutos = new Produtos(FNLcodigo, FNLnomeprodutos, FNLmarca, FNLquantidade, FNLprecoproduto);
        
        //aqui eu chamo a funcao para gravar os valores no banco
        FNprodutos.AdcVendadeProdutos(FNLformapagamento);
        

        //preciso remover a quantidade do produto do banco de dados
        FNprodutos.AtualizaProdutos(FNLcodigo, "quantidade", quantidadett[i]-FNLquantidade);
            System.out.println("----------------------Valor total de quantidade: "+quantidadett[i]);
    }
        
    }

    private Map<String, Object> retornaDadosProdutos(String marca, String nomeproduto){
        Map<String, Object> mapa = new HashMap<>();
        
        for(int y = 0; y<=tamlist; y++){
            if(myclassController.VerProdutos().get(y).getNome().equals(nomeproduto) && myclassController.VerProdutos().get(y).getMarca().equals(marca)){
                System.out.println("passou");
                mapa.put("codigo", myclassController.VerProdutos().get(y).getID());
                mapa.put("quantidade", myclassController.VerProdutos().get(y).getQuantidade());
                mapa.put("descricao", myclassController.VerProdutos().get(y).getDescricao());
                mapa.put("preco", myclassController.VerProdutos().get(y).getPrecodevenda());
                return mapa;
            }
        }
        return null;
    }
    
    @FXML
    void ActionAdicionarCarrinho(ActionEvent event) {
        FVbtnVoltaraoProdutoAnterior.setVisible(true);
        ATTpuxaDadosAtuais();
        totaldavenda = totaldavenda + (precoproduto * quantidade);
        FVtxtTotaldaVenda.setText("R$ "+df.format(totaldavenda));
        
        FVtxtnumproductadd.setText(""+tamAdicionado);
        pd[tamAdicionado][1] = ""+codigo;
        pd[tamAdicionado][2] = ""+marca;
        pd[tamAdicionado][3] = ""+produto;
        pd[tamAdicionado][4] = ""+quantidade;
        pd[tamAdicionado][5] = ""+formapagamento;
        pd[tamAdicionado][6] = descricao;
        pd[tamAdicionado][7] = ""+precoproduto;
        quantidadett[tamAdicionado] = Integer.parseInt(FVqtprodutosEmEstoque.getText());
        zeraValoresInterface();
        
        tamAdicionado ++;
    }
    
    @FXML
    void FVadcarrinhoVoltar(ActionEvent event) {
        //try{
        if(tamAdicionado>=1){
        tamAdicionado --;
        
        FVtxtnumproductadd.setText(""+(tamAdicionado));
        
        FVtxtfCodigo.setText(""+pd[tamAdicionado][1]);
        
        FVcbMarca.getSelectionModel().select(pd[tamAdicionado][2]);
        
        FVcbProduto.getSelectionModel().select(pd[tamAdicionado][3]);
        
        FVspQuantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
            1, Integer.parseInt(pd[tamAdicionado][4]), Integer.parseInt("1")));
        
        FVcbFormadePagamento.getSelectionModel().select(pd[tamAdicionado][5]);
        
        FVtxtaInformacoes.setText(pd[tamAdicionado][6]);
        
        totaldavenda = totaldavenda - (precoproduto*Integer.parseInt(pd[tamAdicionado][4]));
        
        FVtxtTotaldaVenda.setText("R$ "+df.format(totaldavenda));
        
        if(tamAdicionado == 1){
            FVbtnVoltaraoProdutoAnterior.visibleProperty().set(false);
            FVtxtnumproductadd.setText(""+(tamAdicionado-1));
            zeraValoresInterface();
            System.out.println("Não podemos voltar");
        }
        }
    }
    
    @FXML
    void eventclickkeyValorPago() {
        Double valorquepaga = Double.parseDouble(FVtxtfValorPago.getText());
        Double VPtotaldavenda = totaldavenda;
        FVtxtTroco.setText("R$ "+df.format((valorquepaga - VPtotaldavenda)));
    }
    
    private void zeraValoresInterface(){
        //são valores da interface na qual eu preciso tirar para ocorrer conflito
        FVtxtfCodigo.setText("");
        FVcbMarca.getSelectionModel().select(null);
        FVcbProduto.getSelectionModel().select(null);
        FVspQuantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
            0, 0, Integer.parseInt("1")));
        FVtxtaInformacoes.setText("");
        FVtxtPreco.setText("");
    }
    
    private void Relogio(){
        Thread th;
        th = new Thread(() -> {
            while(true) { //roda indefinidamente
                Date date = Calendar.getInstance().getTime(); //pega a hora do sistema
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String today = formatter.format(date);
                FVtxthoradoSistema.setText(today);
                try {
                    Thread.sleep(1000); //espera 1 segundo para fazer a nova evolução
                } catch(InterruptedException ex){
                    //é algo terrível a se fazer mas pelo jeito a API medonha do Java exige
                }
            }
        } //cria uma thread
        );
        th.start();
    }    

    public void ATTpuxaDadosAtuais(){
        codigo = Integer.parseInt(FVtxtfCodigo.getText());
        marca = FVcbMarca.getSelectionModel().getSelectedItem();
        produto = FVcbProduto.getSelectionModel().getSelectedItem();
        quantidade = FVspQuantidade.getValue();
        formapagamento = FVcbFormadePagamento.getSelectionModel().getSelectedItem();
        descricao = FVtxtaInformacoes.getText();
    }
    
    @FXML
    void FUVbtnExit(MouseEvent event) {
        System.exit(0);
    }

    private Boolean maximizado = false;
    
    @FXML
    void FUVbtnMaximize(MouseEvent event) {
        if(!maximizado){
            CadastrodeUsuarios.getStage().setMaximized(true);
            maximizado = true;
        }else{
            CadastrodeUsuarios.getStage().setMaximized(false);
            maximizado = false;
        }
    }

    @FXML
    void FUVbtnMinimize(MouseEvent event) {
        CadastrodeUsuarios.getStage().setIconified(true);
    }
    
    @FXML
    void FUVpressendtopForm(MouseEvent event) {
        MoverForm.MoveP(FUVAnchorPCP);
    }
    
    @FXML
    void FVbtnhelpaction(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("help");
    }
    
    @FXML
    void EventabreCalculadora(ActionEvent event) {
        try{ 
            Runtime.getRuntime().exec("calc"); //assim 
        }catch(Exception e){ 
            System.err.println("Erro ao abrir a calculadora!"); 
        }
    }
    
    @FXML
    void ActionFVbtnVoltar(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("home");
    }
    
    @FXML
    void EventAbreCalendario(ActionEvent event) {
         
    }

    @FXML
    void EventAbreRelatorio(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("relatoriodevendas");
    }
    
}