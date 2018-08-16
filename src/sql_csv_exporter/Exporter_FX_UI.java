package sql_csv_exporter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Exporter_FX_UI extends Application {

    public String db_name;
    public String current_db;
    public String[] db_names;
    public String host;
    public String sid;
    public String port;
    public String username;
    public String password;
    public String delimeter;
    JSONParser parser = new JSONParser();
    JSONObject json_object;
    public String query="";
    public String fileName = "CSV_export";
    public boolean isComplete = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Exporter_FX_UI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("ORACLE");
        primaryStage.setScene(scene);
        primaryStage.show();;
    }

}
