import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Start extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Test");
        Scene scene = new Scene(new Button("SSS"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
