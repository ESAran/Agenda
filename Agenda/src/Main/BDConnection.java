package Main;

import java.sql.Connection;

public interface BDConnection {
   
    void setUsuario(String user);
    void setSenha(String password);
    void setConexao();
    Connection getConnection();
    boolean getConexao();
}
