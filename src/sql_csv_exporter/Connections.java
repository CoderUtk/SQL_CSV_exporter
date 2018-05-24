package sql_csv_exporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Connections {
    
    String[][] db_details = new String[20][6];
    String[] names = new String[15];
    String delimeter;
    String query;
    String current_db;
    public String host;
    public String sid;
    public String port;
    public String username;
    public String password;
    
     public Connections() throws IOException{
      read_connections();
     }
    public void read_connections() throws FileNotFoundException, IOException {
        File file = new File("connections.csv");
        int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                db_details[index] = line.split(",");
                index++;
            }
        }
            System.out.println(db_details[1][0]);
            System.out.println("");
            for (int i = 0; i < index-1; i++) 
                names[i] = db_details[i+1][0];
        get_input();
        for( int i=0; i < index; i++)
            if (db_details[i][0].equals(current_db)){
                host = db_details[i][1];
                port = db_details[i][2];
                sid = db_details[i][3];
                username = db_details[i][4];
                password = db_details[i][5];
            }
    }
    
    public void get_input(){
        JTextField query_textfield = new JTextField();
        JComboBox delimeter_combobox = new JComboBox(new String[]{",","|"});
        JComboBox db_selector = new JComboBox(names);
        Object[] message = {"Choose DB:",db_selector,"Delimeter",delimeter_combobox,"Query:",query_textfield};       
        JOptionPane.showConfirmDialog(null,message,"Enter Details",JOptionPane.OK_CANCEL_OPTION);
        query = query_textfield.getText();
        delimeter = delimeter_combobox.getItemAt(delimeter_combobox.getSelectedIndex()).toString();
        current_db = db_selector.getItemAt(db_selector.getSelectedIndex()).toString();
        
        System.out.println(query);
        System.out.println(delimeter);
        System.out.println(current_db);
       
    }
}
