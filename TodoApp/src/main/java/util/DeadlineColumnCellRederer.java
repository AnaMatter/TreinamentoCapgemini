
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;


public class DeadlineColumnCellRederer extends DefaultTableCellRenderer{ // renderizador padrao
    
   //customizacao em tempo de execucao
    @Override
    public Component getTableCellRendererComponent (JTable table, Object value, 
            boolean isSelected, boolean hasFocu, int row, int col) { // devolve um componente que vai ser rendeirizaddo na tela
        JLabel label;
        label = (JLabel)super.getTableCellRendererComponent(table,// pega o componente padrao da classe pai
                value, isSelected, hasFocu, row, col);
        
        label.setHorizontalAlignment(CENTER); // alinha o texto no centro
        
        TaskTableModel taskModel = (TaskTableModel)table.getModel();
        Task task= taskModel.getTasks().get(row);// pega a tabela da linha renderizada
        
        if(task.getDeadline().after(new Date())){ // verifica se o deadline esta depois de hoje
            label.setBackground(Color.GREEN);
        }else{
            label.setBackground(Color.RED);
        }
                
         return label;   
         
    }
           
   
     
   
}
