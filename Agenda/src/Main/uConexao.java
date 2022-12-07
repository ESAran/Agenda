package Main;

import java.sql.*;

public class uConexao implements BDConnection {
    
    private static Connection con = null;
    private static String banco = "jdbc:mysql://localhost:3306/agenda";
    private static String usuario;
    private static String senha;
    
    public void setUsuario(String user){
        this.usuario = user;
    }
    public void setSenha(String password){
        this.senha = password;
    }
    public void setConexao(){
        try {
         con = DriverManager.getConnection(this.banco, this.usuario, this.senha);
            System.out.println("Conexão com banco concluida\n");
        } catch (SQLException e) {
            System.out.println("Erro ao abrir conexão com o Banco.");
        }
    }
    public Connection getConnection(){
        if (con == null){
            setConexao();
        }    
      return con;
    }
    
    public boolean getConexao(){
        if (con != null){
            return true;
        } else return false;
    }   
}
