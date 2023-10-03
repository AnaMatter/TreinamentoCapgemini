
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConectionFactory;



public class ProjectController {
    public void save (Project project){
         String sql = "INSERT INTO projects (name, "
                + "description,"
                + "createdAt,"
                + "updateAt)"
                + " VALUES(?, ?, ?, ?)"; // campo autoincremento nao e mencionado na tabela
        
        Connection connection = null;// fora pra ter acesso dentro do finally pra fechar a conexao
        PreparedStatement statement=null;
        
               
        try {
            connection = ConectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
          
            statement.setString(1,project.getName());
            statement.setString(2,project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime())); 
            statement.setDate(4, new Date(project.getUpdateAt().getTime())); 
           
            statement.execute();
             
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa "
                    + ex.getMessage(), ex);
        }finally{
            ConectionFactory.closeConnection(connection, statement);
            
        }
        
    }
    public void update (Project project){
        String sql = "UPDATE projects SET name = ?, description = ?, createdAt = ?, updateAt = ? WHERE id = ?";
        
        Connection connection= null; // criar conection 
        PreparedStatement statement = null;
        
        try {
            // estabelecendo a conexao com o BD
            connection = ConectionFactory.getConnection();
            // preparando a query
            statement= connection.prepareStatement(sql);
            //setando os valores do statement
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new java.sql.Date(project.getUpdateAt().getTime()));
            statement.setInt(5, project.getId());
           
     //executando a query  
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa"
                    + ex.getMessage(), ex);
        }finally{
            ConectionFactory.closeConnection(connection, statement);
            
        }
        
    }
    
    
    public List<Project> getAll (){
         String sql = "SELECT * FROM projects";
                    
        //Lista de tarefas que sera devolvida quando a chamada do metodo acontecer
        List <Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        //classe que vai recuperar os dados do BD
        ResultSet resultSet = null;
        
        try {
            //criamos a conexa BD
            connection = ConectionFactory.getConnection();// estabelece a conexao com BD
            statement = connection.prepareStatement(sql); //conectou sql 
            //setando um valor que corresponde ao filtro de busca
           
            //valor retornado pela execucao da query/ usado sempre que houver select
            resultSet= statement.executeQuery(); //vai devolver um valor de resposta do select que aconteceu no BD
            // enquanto houverem valores a serem percorridos no meu result set
            while (resultSet.next()){ // se houver um objeto dentro do result set
                
                Project project = new Project ();
                project.setId(resultSet.getInt("id")); // setar o id com o valor que tiver no result set
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdateAt(resultSet.getDate("updateAt"));
                //add o contato recuperado a lista de contatos
                projects.add(project); //colocar a tarefa dentra da lista
            }
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir tarefa" + ex.getMessage(), ex);
        }finally{
            ConectionFactory.closeConnection(connection, statement, resultSet);
        }
        //lista de tarefas que foi criada e carregada do BD
        return projects; // devolve listas de tarefas que foi criada e carregada do BD
        
    
     }
    
     public void removeById (int idProject) {
         String sql = "DELETE FROM projects WHERE id = ?"; // sql que quero que seja executado
        Connection connection = null; // conexao
        PreparedStatement statement = null;
        
        try {
            // criacao conexao BD
           connection = ConectionFactory.getConnection(); // pediu a conexao
           // preparando a query
           statement = connection.prepareStatement(sql); // ajuda preparar o comando sql que vai ser executado no banco
          //setando os valores
           statement.setInt(1, idProject);// esta dizendo quero setar um valor no sql acima, pra mudar o interrogacao, ou seja vai substituir pelo numero passado por esse tamanho quando o metodo for chamadp
          //executando a query
           statement.execute();// executa no banco
           
        } catch (Exception ex) {// diz qual tipo de excecao vai tratar, nesse caso trata qualquer tipo de excecao
            throw new RuntimeException("Erro ao deletar a tarefa"+ ex.getMessage(),ex);// se ocorrer algum erro vai tratar
         } finally{ // é um bloco que sempre vai ser executado no final do bloco try
            ConectionFactory.closeConnection(connection, statement);
        }
     }
     
}
