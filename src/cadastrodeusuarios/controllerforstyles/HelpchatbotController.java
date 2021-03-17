package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.CadastrodeUsuarios;
import cadastrodeusuarios.classespoo.Message;
import cadastrodeusuarios.classespoo.memorybot;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import java.io.File;
import java.io.InputStream;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.alicebot.ab.*;

public class HelpchatbotController implements Initializable {

    @FXML
    private ListView<Message> lvChatWindow;
    @FXML
    private TextField tfUser;
    @FXML
    private TreeView<String> DVDperguntasfrequentes;
    
    memorybot memoria = new memorybot();
    String resourcesPath = getResourcesPath();
    ObservableList<Message> chatMessages = FXCollections.observableArrayList();//create observablelist for listview
    //Bot bot = new Bot("super", resourcesPath);
    //Chat chatSession = new Chat(bot);
    int tamstringuser;
    int tamstringbot;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //funcao para chat
        //codeBot();
        //inicializaDuvidasFrequentes();
    }
    
    //metodo para quando o botao enviar é acionado
    @FXML
    private void handleUserSubmitMessage() {
        chatMessages.add(new Message(tfUser.getText(), "Usuário"));//get 2nd user's text from his/her textfield and add message to observablelist
        Map mp =  memoria.acessarMemoria();
        tamstringuser = tfUser.getText().length();
        //Message resposta = new Message(chatSession.multisentenceRespond(tfUser.getText()), "Bot");
        //programo o chatbot para responder
        try {
            
        if(!mp.get(""+tfUser.getText()).equals("")){
            chatMessages.add(new Message((String) mp.get(""+tfUser.getText()), "Bot"));
        }
        
        } catch (Exception e) {
            //seguimentar resposta
            //tamstringbot = resposta.getText().length();
            //chatMessages.add(resposta);
        }
        tfUser.setText("");
    }
    
    @FXML
    void TDclicktokeymsg() {
        tfUser.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    handleUserSubmitMessage();
                }
            }
        });
    }
    
    @FXML
    void TDeventbtnvoltar(ActionEvent event) {
        CadastrodeUsuarios.changeScreen("home");
    }
    
    //metodo pra acabar com essa coisa chata do java de setar diretorio e nao funcionar
    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }
    
    private void codeBot(){
        //create to background for listView user and bot
        BackgroundFill myBF1 = new BackgroundFill(Color.BLUEVIOLET, new CornerRadii(10),
                new Insets(10,220,0,0));
        
        BackgroundFill myBF2 = new BackgroundFill(Color.BLUEVIOLET, new CornerRadii(10),
                new Insets(10,0,0,220));
        
        Image IMAGE_BOT = new Image("https://img.icons8.com/office/40/000000/robot-2.png");
        Image IMAGE_USER = new Image("https://img.icons8.com/cotton/40/000000/name--v2.png");
        
        //preciso modificar as celulas de acordo com o que eu quero colocar
        lvChatWindow.setItems(chatMessages);
        lvChatWindow.setCellFactory(param -> {
            ListCell<Message> cell = new ListCell<Message>() {
                @Override
                protected void updateItem(Message item, boolean empty) {
                    super.updateItem(item, empty);
                    
                        //instanciar o elemento grafico
                        TextArea lblTextLeft = new TextArea();
                        TextArea lblTextRight = new TextArea();
                    
                        ImageView img1 = new ImageView();
                        ImageView img2 = new ImageView();
                    
                        img1.setImage(IMAGE_BOT);
                        img2.setImage(IMAGE_USER);
                        
                        //caixa pra adicionar os elementos
                        HBox hbForTextArea1 = new HBox();
                        HBox hbForTextArea2 = new HBox();
                        
                        //desativando opcao de ser editavel
                        lblTextLeft.editableProperty().set(false);
                        lblTextRight.editableProperty().set(false);
                        
                        //fazendo o texto mudar de linha
                        lblTextLeft.setWrapText(true);
                        lblTextRight.setWrapText(true);
                        
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        System.out.println(item.getUser());
                        if (item.getUser().equals("Bot")) {
                            lblTextLeft.setStyle("-fx-control-inner-background:#d3d8de; -fx-font-family: Consolas; -fx-highlight-fill: #c7c9c7; -fx-highlight-text-fill: #000000; -fx-text-fill: #454545;");
                            lblTextLeft.setPrefWidth(200);
                            lblTextLeft.setPrefHeight(40);
                            lblTextLeft.setBackground(new Background(myBF1));
                            lblTextLeft.setText("   "+item.getUser() + ":");
                            lblTextLeft.setText(item.getText());
                            
                            hbForTextArea1.getChildren().addAll(img1, lblTextLeft);
                            
                            setGraphic(hbForTextArea1);
                        } else {
                            lblTextRight.setStyle("-fx-control-inner-background:#53a5f5; -fx-font-family: Consolas; -fx-highlight-fill: #c7c9c7; -fx-highlight-text-fill: #000000; -fx-text-fill: #FFFFFF; -fx-border-radius: 10 10 10 10;");
                            lblTextRight.setPrefWidth(200);
                            lblTextRight.setPrefHeight(40);
                            lblTextRight.setBackground(new Background(myBF2));
                            lblTextRight.setText(item.getUser()+":");
                            lblTextRight.setText(item.getText()+"");
                            hbForTextArea2.alignmentProperty().set(Pos.TOP_RIGHT);
                            hbForTextArea2.getChildren().addAll(lblTextRight,img2);
                            
                            setGraphic(hbForTextArea2);
                        }
                    }
                }

            };
            return cell;
        });
    }

    private void inicializaDuvidasFrequentes() {
        //configurando perguntas
        TreeItem<String> rootItem1 = new TreeItem("Dúvidas");
        TreeItem webItem1 = new TreeItem("Ver e Adicionar Clientes");
        webItem1.getChildren().add(new TreeItem("Como adicionar um cliente?"));
        webItem1.getChildren().add(new TreeItem("Adicionei um cliente e não apareceu. O que eu faço?"));
        webItem1.getChildren().add(new TreeItem("Os clientes não aparecem. O que fazer?"));
        webItem1.getChildren().add(new TreeItem("Não consigo remover os clientes"));
        webItem1.getChildren().add(new TreeItem("Como faço para editar um cliente?"));
        
        
        TreeItem webItem2 = new TreeItem("Ver e Adicionar Produtos");
        webItem2.getChildren().add(new TreeItem("Como adicionar um produto?"));
        webItem2.getChildren().add(new TreeItem("Adicionei um produto e não apareceu. O que eu faço?"));
        webItem2.getChildren().add(new TreeItem("Os produtos não aparecem. O que fazer?"));
        webItem2.getChildren().add(new TreeItem("Não consigo remover os produtos"));
        webItem2.getChildren().add(new TreeItem("Como faço para editar um produto?"));
        webItem2.getChildren().add(new TreeItem("Não consigo editar a categoria do produto. Porque?"));
        
        TreeItem webItem4 = new TreeItem("Fazer uma Venda");
        webItem4.getChildren().add(new TreeItem("Como adicionar um produto ao carrinho?"));
        webItem4.getChildren().add(new TreeItem("Adicionei um produto ao carrinho e não apareceu no contador. O que eu faço?"));
        webItem4.getChildren().add(new TreeItem("Os produtos não aparecem. O que fazer?"));
        webItem4.getChildren().add(new TreeItem("Não consigo finalizar a compra"));
        webItem4.getChildren().add(new TreeItem("A calculadora não aparece o que fazer?"));
        webItem4.getChildren().add(new TreeItem("As categorias estão repetidas. Porque?"));
        
        TreeItem webItem5 = new TreeItem("Relatório de Vendas");
        webItem5.getChildren().add(new TreeItem("Os graficos estão certos?"));
        webItem5.getChildren().add(new TreeItem("Não estopu conseguindo imprimir o relatório!"));
        webItem5.getChildren().add(new TreeItem("Como eu apago os dados do relatório?"));
        
        TreeItem webItem6 = new TreeItem("Gerenciar Usuários");
        webItem6.getChildren().add(new TreeItem("Porque eu não consigo gerenciar os usuários?"));
        webItem6.getChildren().add(new TreeItem("Editei os dados, como salva-los?"));
        webItem6.getChildren().add(new TreeItem("Os produtos não aparecem. O que fazer?"));
        webItem6.getChildren().add(new TreeItem("Porque eu não consigo visualizar as senhas dos usuários?"));
        
        TreeItem webItem7 = new TreeItem("Configurações");
        webItem7.getChildren().add(new TreeItem("Quais são os formatos suportados para importar dados?"));
        webItem7.getChildren().add(new TreeItem("Qual o intervalo de tempo que posso fazer backup dos dados?"));
        webItem7.getChildren().add(new TreeItem("Posso importar dados de outro aplicativo?"));
        
        rootItem1.getChildren().add(webItem1);
        rootItem1.getChildren().add(webItem2);
        rootItem1.getChildren().add(webItem4);
        rootItem1.getChildren().add(webItem5);
        rootItem1.getChildren().add(webItem6);
        rootItem1.getChildren().add(webItem7);
        
        DVDperguntasfrequentes.setRoot(rootItem1);
    }
}
