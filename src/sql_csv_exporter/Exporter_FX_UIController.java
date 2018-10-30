package sql_csv_exporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

public class Exporter_FX_UIController {

    //Connections connection = new Connections();
    SQL_exporter sql_exporter = new SQL_exporter();
    FileChooser fileChooser = new FileChooser();
    File selectedFile;
    Stage controllerStage = new Stage();
    ToggleGroup radioGroup = new ToggleGroup();
    @FXML
    private Label mainLabel = new Label();
    @FXML
    private RadioButton selectConnection = new RadioButton();
    @FXML
    private RadioButton addConection = new RadioButton();
    @FXML
    private Pane selectConnectionPane = new Pane();
    @FXML
    private Pane addConnectionPane = new Pane();
    @FXML
    ToggleGroup connectionToggleGroup = new ToggleGroup();
    @FXML
    RadioButton selectConnectionRbtn = new RadioButton();
    @FXML
    RadioButton addConnectionRbtn = new RadioButton();
    @FXML
    ToggleGroup sqlToggleGroup = new ToggleGroup();
    @FXML
    RadioButton addSQLRbtn = new RadioButton();
    @FXML
    RadioButton writeSQLRbtn = new RadioButton();
    @FXML
    TextArea queryTextArea = new TextArea();
    @FXML
    Pane queryPane = new Pane();
    @FXML
    Pane addFilePane = new Pane();
    @FXML
    Button sqlBrowseButton = new Button();
    @FXML
    Label selectedFileLabel = new Label();
    @FXML
    Button exportBtn = new Button();
    @FXML
    ChoiceBox delimeterChoicebox = new ChoiceBox();
    @FXML
    ChoiceBox connectionChoicebox = new ChoiceBox();
    @FXML
    TextField connectionName = new TextField();
    @FXML
    TextField port = new TextField();
    @FXML
    TextField host = new TextField();
    @FXML
    TextField sid = new TextField();
    @FXML
    TextField username = new TextField();
    @FXML
    TextField password = new TextField();
    @FXML
    TextField fileName = new TextField();
    @FXML
    Label onComplete = new Label();
    @FXML
    ToggleGroup exportToggleGroup = new ToggleGroup();
    @FXML
    RadioButton export_to_xlsx = new RadioButton();
    @FXML
    RadioButton export_to_csv = new RadioButton();
    @FXML
    Pane delimeterPane = new Pane();
    @FXML
    public void initialize() {
        try {
            sql_exporter.get_connections();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        delimeterChoicebox.setItems(FXCollections.observableArrayList("Comma: , ", "Pipe: | "));
        delimeterChoicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println(delimeterChoicebox.getItems().get((Integer) number2));
                sql_exporter.delimeter = (String) delimeterChoicebox.getItems().get((Integer) number2);
            }
        });
        connectionChoicebox.setItems(FXCollections.observableArrayList(sql_exporter.db_names));
        connectionChoicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println(connectionChoicebox.getItems().get((Integer) number2));
                sql_exporter.current_db = (String) connectionChoicebox.getItems().get((Integer) number2);
            }
        });
        
    }

    public void open_file_chooser(ActionEvent e) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SQL Files", "*.sql"));
        selectedFile = fileChooser.showOpenDialog(controllerStage);
        controllerStage.setTitle("Choose an SQL File");
        selectedFileLabel.setText(selectedFile.toString());
    }

    public void add_new_connection(ActionEvent e) {
        selectConnectionRbtn.setToggleGroup(connectionToggleGroup);
        addConnectionRbtn.setToggleGroup(connectionToggleGroup);
        selectConnectionPane.setVisible(false);
        addConnectionPane.setVisible(true);
    }

    public void select_connection(ActionEvent e) {
        selectConnectionRbtn.setToggleGroup(connectionToggleGroup);
        addConnectionRbtn.setToggleGroup(connectionToggleGroup);
        selectConnectionPane.setVisible(true);
        addConnectionPane.setVisible(false);
    }

    public void export_data_to_excel(ActionEvent e){
        export_to_csv.setToggleGroup(exportToggleGroup);
        export_to_xlsx.setToggleGroup(exportToggleGroup);
        delimeterPane.setVisible(false);
    }
    
    public void export_data_to_csv(ActionEvent e){
        export_to_csv.setToggleGroup(exportToggleGroup);
        export_to_xlsx.setToggleGroup(exportToggleGroup);
        delimeterPane.setVisible(true);        
    }
    public void add_sql_file(ActionEvent e) {
        addSQLRbtn.setToggleGroup(sqlToggleGroup);
        writeSQLRbtn.setToggleGroup(sqlToggleGroup);
        queryPane.setVisible(false);
        addFilePane.setVisible(true);
    }

    public void write_query(ActionEvent e) {
        addSQLRbtn.setToggleGroup(sqlToggleGroup);
        writeSQLRbtn.setToggleGroup(sqlToggleGroup);
        queryPane.setVisible(true);
        addFilePane.setVisible(false);
    }

    public void export(ActionEvent e) throws IOException, FileNotFoundException, ParseException {
        onComplete.setText("");
        if (addConnectionRbtn.isSelected()) {
            try {
                System.out.println(connectionName.getText());
                sql_exporter.add_connection(connectionName.getText(), host.getText(), port.getText(), sid.getText(), username.getText(), password.getText());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        if (selectConnectionRbtn.isSelected()) {
            sql_exporter.set_connections();
        }
        
        if (writeSQLRbtn.isSelected()) {
            sql_exporter.query = queryTextArea.getText();
            System.out.println(sql_exporter.query);
        }
        if (addSQLRbtn.isSelected()) {
            System.out.println("dede " + selectedFile.toString());
            sql_exporter.read_query_from_file(selectedFile);
        }
        if (!fileName.getText().trim().equals("")) {
            sql_exporter.fileName = fileName.getText();
        }
        try {
            if (export_to_csv.isSelected()) {
                sql_exporter.delimeter = sql_exporter.delimeter.contains("Comma") ? "," : "|";
                sql_exporter.export();
            } else {
                sql_exporter.export_to_excel();
            }
        } catch (ClassNotFoundException | SQLException sqle) {
            sqle.printStackTrace();
        }
        if (sql_exporter.isComplete) {
            onComplete.setText("FINISHED !!");
        }
    }

}
