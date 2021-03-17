/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.classespoo;

import cadastrodeusuarios.interfacespoo.InterfaceProdutos;


/**
 *
 * @author Carlos
 */
public final class FazerumavendaActions extends Produtos implements InterfaceProdutos{

    public FazerumavendaActions(String descricao, String categoria, Double precodecusto, Double precodevenda, String datadecadastro, Integer ID, String nome, String marca, Integer quantidade) {
        super(descricao, categoria, precodecusto, precodevenda, datadecadastro, ID, nome, marca, quantidade);
    }

    public FazerumavendaActions(String descricao, String categoria, Double precodevenda, String datadecadastro, Integer ID, String nome, String marca, Integer quantidade) {
        super(descricao, categoria, precodevenda, datadecadastro, ID, nome, marca, quantidade);
    }

    public FazerumavendaActions() {
    }
    
    /*
    @Override
    public void AtualizaProdutos(Integer ID, String caracteristica, Object o){
        //Irá atualizar a quantidade de produtos de acordo com a quantidade vendida
         conexaoSQLite.conectar();
        PreparedStatement attpreparedStatement = null;
        
        String sql = "UPDATE produtos"
                        + " SET "
                        + " quantidade = ?"
                        + " WHERE id = ?";
                try{
                attpreparedStatement = conexaoSQLite.criarPreparedStatement(sql);
                attpreparedStatement.setInt(1, (int) o);
                attpreparedStatement.setInt(2, ID);
                
                attpreparedStatement.executeUpdate();
                }catch(SQLException e){
                    
                }
    }*/

    @Override
    public Object VerProdutosVendidos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
