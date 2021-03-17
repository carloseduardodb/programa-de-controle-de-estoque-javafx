/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.interfacespoo;

/**
 *
 * @author Carlos
 */
public interface InterfaceUsuario {
    public void ApagarUsuario(Integer ID);
    public Object VerUsuarios();
    public void AlterarDadosdoUsuario(Integer ID);
    public Boolean AdicionaUsuario();
}