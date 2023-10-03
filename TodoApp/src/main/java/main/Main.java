package main;

import controller.ProjectController;
import controller.TaskController;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;
import util.ConectionFactory;

public class Main {

    public static void main(String[] args) {
        
  ProjectController projectController = new ProjectController();
    
//   Project project = new Project();
//   project.setDescription("description");
//   projectController.save(project);
      
              
//    ProjectController projectController = new ProjectController(); // teste update
//    
//    Project project = new Project();
//    project.setId(10);
//    project.setName("novo nome do projeto");
//    project.setDescription("description");
//                   
//    projectController.update(project); 
      
//    List <Project> projects = projectController.getAll();
//    System.out.println("Total de projetos" + projects.size());
//      
//    projectController.removeById(3);
//       
//    TaskController taskController= new TaskController();
//    Task task = new Task();
//      
//     task.setName("Alterar telas");
//     taskController.update(task);
//     List <Task>  tasks = taskController.getAll(8);
//     System.out.println("Total de projetos" + tasks.size());
//      
//      task.setIdProject(1);
//      task.setName("Criar as telas de aplicacao");
//      task.setDescription("Devem ser criadas telas para os cadastros");
//      task.setcompleted(false);
//      task.setNotes("sem notas");
//      task.setDeadline(new Date());
//      task.setCreatedAt(new Date());
//      task.setUpdateAt(new Date());
//       
//       
//       taskController.save(task);
            
     
        
//      Connection c = ConectionFactory.getConnection(); // devolve uma conexao
//       ConectionFactory.closeConnection(c);// fecha a conexao
    }

}
