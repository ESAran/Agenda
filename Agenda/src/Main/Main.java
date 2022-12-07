package Main;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        
        uConexao con = new uConexao();
        Query_Banco q = new Query_Banco();
        int login,opcao,opedit;
        String user;
        String password1;
       
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("1 - Logar\n2 - Se cadastrar\n3 - Usuários cadastrados\n4 - Deletar Usuário\n5- Sair");
            System.out.print("Digite um número: ");
            login = sc.nextInt();
            
            switch(login){
                case 1: {
                    System.out.print("Digite seu usuário: ");
                    user = sc.next();
                    System.out.print("Digite sua senha: ");
                    password1 = sc.next();
                                       
                    con.setUsuario(user);
                    con.setSenha(password1);
                    con.getConnection();
                    if (con.getConexao()) {
                        System.out.print("\nConectado com o usuário: \n");
                        q.query_buscarUsuario("SELECT * FROM agenda.user WHERE nome = \'"+user+"\'");
                    }
                                        
                    if (con.getConexao() == true) {
                        do{
                            System.out.print(  "\n1 - Adicionar contato"
                                           + "\n2 - Editar contato"
                                           + "\n3- Excluir contato"
                                           + "\n7- Fechar\n"); 
                            
                            System.out.print("Digite um número: ");
                            opcao = sc.nextInt();
                            
                            switch(opcao){
                                case 1:{
                                    System.out.print("Nome do contato: ");
                                    String nome = sc.next();
                                    System.out.print("Número do contato: ");
                                    String numero = sc.next();
                                    System.out.print("Cep: ");
                                    String cep = sc.next();
                                    q.query_gravarContato("INSERT INTO agenda.contatos (nome,numero,cep,user) "
                                                 + "VALUES(?,?,?,?)",nome,numero,cep,user);
                                    break;
                                }   
                                case 2:{
                                    System.out.print("Digite o id do contato: ");
                                    int id = sc.nextInt();
                                    System.out.print("Digite o nome do contato: ");
                                    String nome = sc.next();
                                
                                    q.query_buscarContato("SELECT * FROM agenda.contatos WHERE nome = \'"+nome+"\' AND id = "+id);
                                    if (q.query_executada()) {
                                        do {
                                            System.out.print("1 - Alterar nome"
                                                           + "\n2 - Alterar número"
                                                           + "\n3 - Alterar cep"
                                                           + "\n4 - Sair\n");
                                            System.out.print("Digite uma número: ");
                                            opedit = sc.nextInt();
                                            switch(opedit){
                                                case 1:{
                                                    System.out.print("Digite um novo nome: ");
                                                    String novonome = sc.next();
                                                    q.query_editar("UPDATE agenda.contatos SET nome = \'"+novonome+"\' WHERE id ="+id);
                                                }
                                                case 2:{ 
                                                    System.out.print("Digite um novo número: ");
                                                    String novonum = sc.next();
                                                    q.query_editar("UPDATE agenda.contatos SET nome = \'"+novonum+"\' WHERE id ="+id);
                                                }
                                                case 3:{
                                                    System.out.print("Digite um novo nome: ");
                                                    String novocep = sc.next();
                                                    q.query_editar("UPDATE agenda.contatos SET nome = \'"+novocep+"\' WHERE id ="+id);
                                                }
                                            }
                                            
                                        }while(opedit != 4);  
                                    }
                                    break;
                                }
                                case 3:{
                                    System.out.print("Digite o id do usuário que deseja excluir: ");
                                    int id = sc.nextInt();
                                    q.query_gravarDelete("DELETE FROM agenda.contatos WHERE id = ?", id);
                                    break;
                                }
/*                                case 4:{
                                    q.query_buscarContato("SELECT * FROM agenda.contatos WHERE user=\'"+user+"@localhost\' ORDER BY id");
                                    break;
                                }
                                case 5:{
                                    System.out.print("Digite o id do usuário que você deseja adicionar na lista de favoritos: ");
                                    int idfav = sc.nextInt();
                                    q.query_buscarContato("SELECT * FROM agenda.contatos WHERE id = "+idfav+" AND user = \'"+user+"@localhost\' ORDER BY id");
                                    q.query_gravar("INSERT INTO agenda.favoritos (codigo,nome,numero,dt_favoritou,user)"
                                                 + "VALUES ("+idfav+",\'"+q.Inf_Contato().get(0)+"\',\'"+q.Inf_Contato().get(1)+"\',\'"+q.getDateTime()+"\',\'"+user+"@localhost\')");
                                    if (q.query_executada()) {
                                        System.out.println("\nContato adicionado na lista de favoritos");    
                                    }
                                    break;
                                }
                                case 6:{
                                    q.query_buscarFavorito("SELECT * FROM agenda.favoritos WHERE user = \'"+user+"@localhost\' ORDER BY id");
                                    break;
                                }*/
                            }
                        }while(opcao != 7);
                    }
                    break;
                }
                case 2: {
                    
                    con.setUsuario("root");
                    con.setSenha("");
                    con.getConnection();
                    
                    System.out.println("Digite um nome para o usuário: ");
                    user = sc.next();
                    
                    System.out.print("Digite sua senha: ");
                    password1 = sc.next();
                        
                    q.query_gravar("CREATE USER \'"+user+"\'@\'localhost\' IDENTIFIED BY \'"+password1+"\'");
                    q.query_gravar(" GRANT ALL PRIVILEGES ON * . * TO \'"+user+"\'@\'localhost\';");
                    q.query_gravar(" INSERT INTO agenda.user (nome, senha, dt_inclusao) VALUES (\'"+user+"\',\'"+password1+"\',\'"+q.getDateTime()+"\')");
                    
                    if (q.query_executada()) {
                        System.out.println("Usuário cadastrado com sucesso");    
                    }
                    
                    q.query_buscarUsuario("SELECT * FROM agenda.user WHERE nome = \'"+user+"\' ORDER BY id");
                    break;
                }
                case 3:{
                    con.setUsuario("root");
                    con.setSenha("");
                    con.getConnection();
                    
                    q.query_buscarUsuario("SELECT * FROM agenda.user ORDER BY id");
                    break;
                }
                case 4:{
                    con.setUsuario("root");
                    con.setSenha("");
                    con.getConnection();
                    
                    System.out.print("Digite o nome do usuário que deseja excluir: ");
                    String nome = sc.next();
                    System.out.print("Tem certeza que deseja excluir o usuário "+nome+" (T/F): ");
                    String perg = sc.next();
                    if (perg.equals("T")) {
                        q.query_gravar("DROP USER \'"+nome+"\'@\'localhost\';");
                        q.query_gravar("DELETE FROM agenda.user WHERE nome = \'"+nome+"\'");
                        System.out.println("Usuário deletado");
                    }else{
                        System.out.println("Operação cancelada!");
                    }
                    break;
                }
            }
        } while (login != 5);
    }
}
