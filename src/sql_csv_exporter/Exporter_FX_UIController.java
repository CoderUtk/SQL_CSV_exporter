package sql_csv_exporter;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Exporter_FX_UIController {

    public Exporter_FX_UIController(){
        delimeterChoicebox.setItems(FXCollections.observableArrayList(
    "New Document", "Open ", 
    new Separator(), "Save", "Save as")
);
    }
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
    ChoiceBox delimeterChoicebox = new ChoiceBox();

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

}
