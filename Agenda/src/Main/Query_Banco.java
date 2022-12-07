package Main;

import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Query_Banco extends uFuncoesBanco {
    uConexao con = new uConexao();
    private boolean executou;
    private ArrayList lista = new ArrayList();
    
    public void query_buscarContato(String SQL){
        try{
            Statement smt = con.getConnection().createStatement();
            PreparedStatement ps = con.getConnection().prepareStatement(SQL);
            ResultSet res = smt.executeQuery(SQL);
            while( res.next() ){
                                System.out.println("\nId: " + res.getInt("id") );
                                System.out.println("Nome: " + res.getString("nome"));
                                System.out.println("Número: " + res.getString("numero"));
                                System.out.println("Cep: " + res.getString("cep"));
                                System.out.println("Cadastrado por: " + res.getString("user")+"\n");
            
                lista.add(res.getString("nome"));
                lista.add(res.getString("numero")); 
                executou = true;
           }
        } catch (SQLException e) {
             System.out.println("SQL ERROR: "+ SQL + e.getMessage());
        }    
    }
    
    public void query_buscarFavorito(String SQL){
        try{
            Statement smt = con.getConnection().createStatement();
            ResultSet res = smt.executeQuery(SQL);
            while( res.next() ){
                                System.out.println("\nId: " + res.getInt("id") );
                                System.out.println("Código do contato: " + res.getInt("codigo"));
                                System.out.println("Nome: " + res.getString("nome"));
                                System.out.println("Número: " + res.getString("numero")+"\n");
            executou = true;
           }
        } catch (SQLException e) {
             System.out.println("SQL ERROR:"+ e.getMessage());
        }    
    }
    
        public void query_buscarUsuario(String SQL){
        try{
            Statement smt = con.getConnection().createStatement();
            ResultSet res = smt.executeQuery(SQL);
            while( res.next() ){
                                System.out.println("\nId: " + res.getInt("id") );
                                System.out.println("Nome: " + res.getString("nome"));
                                System.out.println("Senha: " + res.getString("senha"));
                                System.out.println("Data de inclusão: " + res.getDate("dt_inclusao")+"\n");
            executou = true;
           }
        } catch (SQLException e) {
             System.out.println("SQL ERROR:"+ e.getMessage());
        }    
    }
    
    public void query_gravarContato(String SQL, String nome, String numero, String cep, String user){
        try{
                PreparedStatement ps = con.getConnection().prepareStatement(SQL);
                ps.setString(1, nome);
                ps.setString(2, numero);
                ps.setString(3, cep);
                ps.setString(4, user+"@localhost");
                ps.execute();
                System.out.println( "Contato gravado com sucesso!" );
        } catch (SQLException e) {
             System.out.println("SQL ERROR:\n" + SQL +"\n"+ e.getMessage());
        }                                    
    }
        public void query_gravarFavorito(String SQL, int codigo, String nome, String numero, String date, String user){
        try{
                PreparedStatement ps = con.getConnection().prepareStatement(SQL);
                ps.setInt(1, codigo);
                ps.setString(2, nome);
                ps.setString(3, numero);
                ps.setString(4, getDateTime());
                ps.setString(4, user+"@localhost");
                ps.execute();
                System.out.println( "Contato adicionado aos favoritos com sucesso!" );
        } catch (SQLException e) {
             System.out.println("SQL ERROR:\n" + SQL +"\n"+ e.getMessage());
        }                                    
    }
    public void query_gravarDelete(String SQL,int id){
    try{
            PreparedStatement ps = con.getConnection().prepareStatement(SQL);
            ps.setInt(1, id);
            ps.execute();
            System.out.println( "Contato Deletado com sucesso!" );
    } catch (SQLException e) {
         System.out.println("SQL ERROR:\n" + SQL + "\n"+ e.getMessage());
        }                                    
    }
    
    public void query_editar(String SQL){
    try{
            PreparedStatement ps = con.getConnection().prepareStatement(SQL);
            ps.execute(SQL);
            System.out.println( "Contato alterado com sucesso!" );
    } catch (SQLException e) {
         System.out.println("SQL ERROR:\n" + SQL + "\n"+ e.getMessage());
        }                                    
    }
    
    public void query_gravar(String SQL){
    try{
            PreparedStatement ps = con.getConnection().prepareStatement(SQL);
            ps.execute(SQL);
            executou = true;
    } catch (SQLException e) {
         System.out.println("SQL ERROR:\n" + SQL + "\n"+ e.getMessage());
        }                                    
    }
    
    public boolean query_executada(){
        return this.executou;
    }
    
    public String getDateTime() {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	return dateFormat.format(date);
    }
    
    public ArrayList Inf_Contato(){
        if (this.lista.size() > 0) {
            this.executou = true;
            return this.lista;
        }else executou = false;
        return null;
    }
}
