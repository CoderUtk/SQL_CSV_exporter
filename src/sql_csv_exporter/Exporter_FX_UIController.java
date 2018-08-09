package sql_csv_exporter;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Exporter_FX_UIController{

    FileChooser fileChooser = new FileChooser();
    Stage controllerStage = new Stage();
    TextArea queryText = new TextArea();
    @FXML
    private Label mainLabel = new Label();
    public void open_file_chooser(ActionEvent e) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SQL Files", "*.sql"));
        fileChooser.showOpenDialog(controllerStage);
        controllerStage.setTitle("Choose an SQL File");
        queryText.setEditable(false);
    }
  
    public void add_new_connection(ActionEvent e){
      
    }
    
    public void launch_new_connection_ui(){
        
    }
    
    

}
