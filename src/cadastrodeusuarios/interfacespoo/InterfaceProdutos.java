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
public interface InterfaceProdutos {
    public Object VerProdutos();
    public Object VerProdutosVendidos();
    public void ApagarProdutos(Integer ID);
    public boolean AdicionarProdutos();
    public void AtualizaProdutos(Integer ID, String caracteristica, Object valor);
    public void AdcVendadeProdutos(String formadepagamento);
}
