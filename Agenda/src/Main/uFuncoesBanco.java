package Main;

import java.util.ArrayList;

public abstract class uFuncoesBanco {
   
    public abstract void query_buscarContato(String SQL);
    public abstract void query_buscarFavorito(String SQL);
    public abstract void query_buscarUsuario(String SQL);
    public abstract void query_gravarContato(String SQL,String nome, String numero, String cep, String user);
    public abstract void query_gravarFavorito(String SQL, int codigo, String nome, String numero, String date, String user);
    public abstract void query_gravarDelete(String SQL, int id);
    public abstract void query_editar(String SQL);
    public abstract void query_gravar(String SQL);
    public abstract boolean query_executada();
    public abstract ArrayList Inf_Contato();
    public abstract String getDateTime();
}
