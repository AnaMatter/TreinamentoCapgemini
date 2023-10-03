

package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConectionFactory;


public class TaskController {
    
     public void save(Task task){
        String sql = "INSERT INTO tasks (idProject, "
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                 + "updatedAt) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; // campo autoincremento nao e mencionado na tabela
        
        Connection connection = null;// fora pra ter acesso dentro do finally pra fechar a conexao
        PreparedStatement statement=null;
        
               
        try {
            connection = ConectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, task.getIdProject());
            statement.setString(2,task.getName());
            statement.setString(3,task.getDescription());
            statement.setBoolean(4,task.completed());
            statement.setString(5,task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime())); //pega a tarefa busca o deadline dela o get time rerona a data num formato longo que e o que o metodo espera
            statement.setDate(7, new Date(task.getCreatedAt().getTime())); 
            statement.setDate(8, new Date(task.getUpdateAt().getTime())); 
            statement.execute();
             
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa "
                    + ex.getMessage(), ex);
        }finally{
            ConectionFactory.closeConnection(connection, statement);
            
        }
 
       
    }
    
    public void update(Task task){
        String sql = "UPDATE tasks SET "
               + "idProject = ?,"
               + "name = ?,"
               + "description = ?,"
                + "completed = ?,"
                + "notes = ?,"
                + "deadline = ?,"
               + "createdAt = ?,"
                + "updatedAt = ? "
                + "WHERE id = ?";
        
        //UPDATE tasks SET idProject = 1, name = 'teste', description = 'description teste', 
        //completed = 1, notes = 'asdf', deadline = 2023-01-20, createdAt = '2023-01-17 00:00:00', 
        //updatedAt = '2023-02-01 00:00:00' WHERE id = 9;
        
        Connection connection= null; // criar conection 
        PreparedStatement statement = null;
        try {
//                         
                // estabelecendo a conexao com o BD
            connection = ConectionFactory.getConnection();
            // preparando a query
            statement= connection.prepareStatement(sql);
            //setando os valores do statement
           statement.setInt(1,task.getIdProject());
            statement.setString(2,task.getName());
           statement.setString(3,task.getDescription());
           statement.setBoolean(4, task.completed());
           statement.setString(5,task.getNotes());
           
            statement.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new java.sql.Date(task.getUpdateAt().getTime()));
            statement.setInt(9, task.getId());
            //executando a query
           
           System.out.println(statement.toString());
            
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa"
                    + ex.getMessage(), ex);
        }finally{
            ConectionFactory.closeConnection(connection, statement);
            
        }
        
    }
    
    public void removeById (int taskId) { // vou passar pra ele o id da tarefa que quero deletar e ele vai executar o delete no BD
        String sql = "DELETE FROM tasks WHERE ID = ?"; // sql que quero que seja executado
        
        Connection connection = null; // conexao
        PreparedStatement statement = null;
        
        try {
            // criacao conexao BD
           connection = ConectionFactory.getConnection(); // pediu a conexao
           // preparando a query
           statement = connection.prepareStatement(sql); // ajuda preparar o comando sql que vai ser executado no banco
          //setando os valores
           statement.setInt(1, taskId);// esta dizendo quero setar um valor no sql acima, pra mudar o interrogacao, ou seja vai substituir pelo numero passado por esse tamanho quando o metodo for chamadp
          //executando a query
           statement.execute();// executa no banco
           
        } catch (Exception ex) {// diz qual tipo de excecao vai tratar, nesse caso trata qualquer tipo de excecao
            throw new RuntimeException("Erro ao deletar a tarefa"/*+ ex.getMessage(),ex*/);// se ocorrer algum erro vai tratar
         } finally{ // é um bloco que sempre vai ser executado no final do bloco try
            ConectionFactory.closeConnection(connection, statement);
        }
        
    }
     public List<Task> getAll (int idProject){ //vai buscar todas tarefas com base no projeto, esse metodo devolve uma lista de tarefas
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //Lista de tarefas que sera devolvida quando a chamada do metodo acontecer
        List <Task> tasks = new ArrayList<Task>();
        
        try {
            //criamos a conexa BD
            connection = ConectionFactory.getConnection();// estabelece a conexao com BD
            statement = connection.prepareStatement(sql); //conectou sql 
            //setando um valor que corresponde ao filtro de busca
            statement.setInt(1, idProject); // setou id do projeto do qual quero buscar
            //valor retornado pela execucao da query
            resultSet= statement.executeQuery(); //vai devolver um valor de resposta do select que aconteceu no BD
            // enquanto houverem valores a serem percorridos no meu result set
            
            while (resultSet.next()){ // se houver um objeto dentro do result set
                
                Task task = new Task(); // vou criar uma tarefa
                
                task.setId(resultSet.getInt("id")); // setar o id com o valor que tiver no result set
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
               task.setcompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdateAt(resultSet.getDate("updatedAt"));
                
                tasks.add(task); //colocar a tarefa dentra da lista
            }
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir tarefa" + ex.getMessage(), ex);
        }finally{
            ConectionFactory.closeConnection(connection, statement, resultSet);
        }
        //lista de tarefas que foi criada e carregada do BD
        return tasks; // devolve listas de tarefas que foi criada e carregada do BD
        
    }
}