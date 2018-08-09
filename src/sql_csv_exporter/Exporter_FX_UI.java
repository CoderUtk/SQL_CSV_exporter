package sql_csv_exporter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Exporter_FX_UI extends Application {

    public void launch_fxml(){
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Exporter_FX_UI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("UTK");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
