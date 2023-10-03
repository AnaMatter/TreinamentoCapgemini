
package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;




public class TaskTableModel extends AbstractTableModel{// metodos abstratos da tablemodel obrigatorios
    
    String [] columns = {"Nome", "Descriçao", "Prazo", "Tarefa Concluida", "Editar", "Excluir"};
    List<Task> tasks = new ArrayList();

    @Override
    public int getRowCount() { // vai dizer qts tarefas eu ja tenho
     return tasks.size(); //pega a lista de tarefas criadas e pede pra retornar as tarefas
    }

    @Override
    public int getColumnCount() { // qts colunas
        return columns.length;
        
    }
    @Override
    public String getColumnName(int columnIndex){
        return columns [columnIndex];
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex){
       return columnIndex==3;
       
      
    }
    
    @Override
    public Class<?> getColumnClass (int columnIndex){ // retorna qual a classe do componente que esta em determinada coluna se é string boolean etc
       if (tasks.isEmpty()){ //verifica se a lista de tarefas é vazia se for da um retorno object nao faz diferenca
           return Object.class;
       } 
       return this.getValueAt(0, columnIndex).getClass();// chama o metodo getvalueat  e retorna se a tarefa está conlcuida ou nao/ retorna a classe desse dado string.. boolean, date ... só pra dizer o tipo de formacao exibido na coluna
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { // qual valor deve ser exibido em determina linha e coluna
         
        switch (columnIndex){
            case 0:
                return tasks.get(rowIndex).getName();// se a coluna for 1 retorna o nome da tarefa com base na linha
                
            case 1 :
               return tasks.get(rowIndex).getDescription();
             case 2:
                 SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
                 return dateFormat.format(tasks.get(rowIndex).getDeadline());
            case 3 :
                return tasks.get(rowIndex).completed();
            case 4:
                return "";
             case 5:
                return "";
            default:
                return "Dados não encontrados";
    }
    
    
}
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){ 
        tasks.get(rowIndex).setcompleted((boolean)aValue) ;
    }

    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    
}
