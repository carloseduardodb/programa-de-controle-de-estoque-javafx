/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.classespoo;

import cadastrodeusuarios.interfacespoo.InterfaceVenda;
import java.util.Date;

/**
 *
 * @author Carlos
 */
public final class Venda extends Produto implements InterfaceVenda{
    private Date datadavenda;

    public Venda(Integer ID, String nome, String marca, Integer quantidade) {
        super(ID, nome, marca, quantidade);
    }

    public Date getDatadavenda() {
        return datadavenda;
    }

    public void setDatadavenda(Date datadavenda) {
        this.datadavenda = datadavenda;
    }

    @Override
    public void FazerumaVenda() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void GerarRelatorio() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
