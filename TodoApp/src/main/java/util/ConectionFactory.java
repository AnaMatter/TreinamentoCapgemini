package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConectionFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver"; // driver de conexao indica qual BD vai usar nao vai mudar nunca
    public static final String URL = "jdbc:mysql://localhost:3306/todoapp"; // caminho do BD localhost quando está no computador se estiver no servidor vai o IP
    public static final String USER = "root"; // usuario e senha padrao pode ser alterada
    public static final String PASS = "";

    public static Connection getConnection() {
        try {// tratamento de excecao
            
            Class.forName(DRIVER); // carrega o driver -  class codigo de conexao bd pode ocorrer um erro, se ocorrer faz o tratamento no catch 
            return DriverManager.getConnection(URL, USER, PASS); // faz conexao usando levando em consideracao esses parametros
        } catch (Exception ex) {
            throw new RuntimeException("Erro na conexao com o banco de dados", ex); // tratamento de um eventual erro/ 
        }
    }

    public static void closeConnection(Connection connection) {// recebe como parametro a conexao que pedi
        try { // e catch para tratamento de erros
            if (connection != null) { // verifica se existe a conexao e fecha
                connection.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar o bando de dados", ex);// é executado logo apos o erro acontecer
        }
    }
    
     public static void closeConnection(Connection connection, PreparedStatement statement) {// posso ter metodos com mesmo nome, modificador de aceso e retorno mas tem que ter diferenciacao nos parametros
       try { 
           if (connection != null) { 
               connection.close();
            }
            if(statement != null){
                statement.close();
           }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar o bando de dados", ex);
       }
    }
        
    public static void closeConnection(Connection connection,
           PreparedStatement statement, ResultSet resultSet) { // pode receber a conexao, o statement e resultSet
        try { 
            if (connection != null) { 
                connection.close();
            }
           if(statement != null){
                statement.close();
           }
           if(resultSet!= null){
                resultSet.close();
            }
       } catch (Exception ex) {
           throw new RuntimeException("Erro ao fechar o bando de dados", ex);
        }
    }

}
