import Controller.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import GUI.iScene;

public class Start extends Application{
    public static void main(String[] args){
        launch(args);
    }
    public static Game theGame;
    public static Stage theStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        theStage = primaryStage;
        theGame = new Game(primaryStage);
        primaryStage.setTitle("Tetris");
        Scene scene = iScene.welcomeScene;
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
