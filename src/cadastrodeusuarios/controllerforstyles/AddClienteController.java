/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.controllerforstyles;

import cadastrodeusuarios.auxapis.ConversordeImagem;
import cadastrodeusuarios.auxapis.MascarasFX;
import cadastrodeusuarios.auxapis.PassardadosAlert;
import cadastrodeusuarios.auxapis.PositiveAlertController;
import cadastrodeusuarios.classespoo.FuncoesClient;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
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
public class AddClienteController implements Initializable {

    /**
     *
     *
     *Erro Referente as Mascaras Utilizadas Corrigir com Modificação de Caracteres Recebidos
     *
     * 
     */
    
    @FXML
    TextField ADCCtxtinome, ADCCtxtisobrenome, ADCCtxtiEmail, ADCCtxtitelefone, ADCCtxticpf, ADCCtxtirg;
    @FXML
    DatePicker ADCCdtdatadenascimento;
    @FXML
    MenuButton ADCCmbSexo;
    @FXML
    TextField ADCCtxticep, ADCCtxticidade, ADCCtxtibairro, ADCCtxtinumerocasa, ADCCtxticomplemento, ADCCtxtiuf, ADCCtxtinacionalidade;
    @FXML
    Button ADCCbtncarregarimagem;
    @FXML
    Button ADCCbtnVoltar, ADCCbtnadicionarCliente;
    @FXML
    ImageView imageUser;
    @FXML
    private TextField ADCCtxtiRua;
    @FXML
    private AnchorPane ACbarArrastForm;
    @FXML
    private AnchorPane ACpanePCP;
    @FXML
    private ImageView ACImgCliente;
    
    private byte[] imgArray;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ADCCtxtinome.setText("Carlos");
        ADCCtxtisobrenome.setText("Eduardo");
        ADCCtxtiEmail.setText("teste");
        ADCCtxtitelefone.setText("123456789");
        ADCCtxticpf.setText("1234566");
        ADCCtxtirg.setText("MG1456568");
        ADCCtxticep.setText("1241456");
        ADCCtxticidade.setText("Campos Gerais");
        ADCCtxtiRua.setText("Padre Ancheita");
        ADCCtxtibairro.setText("Baixao");
        ADCCtxtinumerocasa.setText("123");
        ADCCtxticomplemento.setText("nenhum");
        ADCCtxtiuf.setText("MG");
        ADCCtxtinacionalidade.setText("brasileira");
    }    
    
    //acao do botao adicionar
    @FXML
    private void actionBtnAdicionar(){
        String nome = ADCCtxtinome.getText();
        String sobrenome = ADCCtxtisobrenome.getText();
        String email = ADCCtxtiEmail.getText();
        String telefone = ADCCtxtitelefone.getText();
        String cpf = ADCCtxticpf.getText();
        String rg = ADCCtxtirg.getText();
        LocalDate data = ADCCdtdatadenascimento.getValue();
        String cep = ADCCtxticep.getText();
        String cidade = ADCCtxticidade.getText();
        String rua = ADCCtxtiRua.getText();
        String bairro = ADCCtxtibairro.getText();
        String sexo = ADCCmbSexo.getText();
        String numero = ADCCtxtinumerocasa.getText();
        String complemento = ADCCtxticomplemento.getText();
        String uf = ADCCtxtiuf.getText();
        String nacionalidade = ADCCtxtinacionalidade.getText();
        
        FuncoesClient cliente = new FuncoesClient();
        
        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
        cliente.setSexo(sexo);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setRG(rg);
        cliente.setDatadenascimento(data);
        cliente.setCep(cep);
        cliente.setCidade(cidade);
        cliente.setBairro(bairro);
        cliente.setRua(rua);
        cliente.setNumero(numero);
        cliente.setComplemento(complemento);
        cliente.setUf(uf);
        cliente.setNacionalidade(nacionalidade);
        cliente.setImageUser(imgArray);
        
        Boolean adicionado = cliente.AdicionarCliente();
        
        if(adicionado){
            //Colocar aviso que foi adicionado com sucesso
            PassardadosAlert.titulo = "Cliente Adicionado com Sucesso!";
            PassardadosAlert.texto = "A base de dados será atualizada em instantes!";
            PassardadosAlert.icone = new File("src/cadastrodeusuarios/img/checkedicon.png");
            PassardadosAlert.imgpcp = new File("src/cadastrodeusuarios/img/clientaddsucess.png");
            PassardadosAlert.btncenter = true;
            PositiveAlertController pac =  new PositiveAlertController();
            pac.abredialogd();
        }else{
            //Colocar Aviso Erro ao adicionar
            PassardadosAlert.titulo = "ERRO!";
            PassardadosAlert.texto = "Ocorreu um Erro ao Adicionar um Cliente!";
            PassardadosAlert.icone = new File("src/cadastrodeusuarios/img/iconfail.png");
            PassardadosAlert.imgpcp = new File("src/cadastrodeusuarios/img/removeuser.png");
            PassardadosAlert.btncenter = true;
            PositiveAlertController pac =  new PositiveAlertController();
            pac.abredialogd();
        }
    }
    
    @FXML
    void ACbtnLoadImage(MouseEvent event) {
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
                ACImgCliente.setImage(null);
                
                //cria um buffer de imagem lendo o file
                BufferedImage bufferedImage = ImageIO.read(file);
                
                //diminuo a imagem, chamando função especifica
                bufferedImage = ConversordeImagem.scale(bufferedImage, 250, 300);            
                
                //converte o buffer para image, para eu colocar no componente
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                
                //adiciono a imagem no componente
                ACImgCliente.setImage(image);
                
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", stream);
                
                imgArray = stream.toByteArray();
            } catch (IOException ex) {
                System.out.println("Erro ao Carregar Imagem! Detalhes do Erro: "+ex);
            }  
            }
    }
    
    @FXML
    private void maskEmail(){
        MascarasFX.mascaraEmail(ADCCtxtiEmail);
    }
    
    @FXML
    private void MaskTelefone(){
        MascarasFX.mascaraTelefone(ADCCtxtitelefone);
    }
    
    @FXML
    private void MaskCPF(){
        MascarasFX.mascaraCPF(ADCCtxticpf);
    }
    
    @FXML
    private void MaskRG(){
        //fazer
    }
    @FXML
    private void MaskDataNascimento(){
        MascarasFX.mascaraData(ADCCdtdatadenascimento);
    }
    @FXML
    private void MaskCEP(){
        MascarasFX.mascaraCEP(ADCCtxticep);
    }
    
    //config menuButton
    @FXML
    private void Masculino(){
        ADCCmbSexo.setText("Masculino");
    }
    
    @FXML
    private void Feminino(){
        ADCCmbSexo.setText("Feminino");
    }
    
    @FXML
    void ACbtnClose(MouseEvent event) {
        ACpanePCP.getScene().getWindow().hide();
    }
}
