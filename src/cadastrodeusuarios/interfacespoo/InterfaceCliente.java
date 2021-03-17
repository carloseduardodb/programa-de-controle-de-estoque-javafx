/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.interfacespoo;

import java.util.Map;
import javafx.collections.ObservableList;

/**
 *
 * @author Carlos
 */
public interface InterfaceCliente {
    public ObservableList VerCliente(boolean pesquisa);
    public Map EditarCliente(int ID);
    public void ApagarCliente(int ID);
    public Boolean AdicionarCliente();
    public Boolean modifyClient();
}
