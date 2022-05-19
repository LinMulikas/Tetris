package GUI;

import Controller.*;
import Shape.SingleBlock;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static Controller.Game.theGame;
import static GUI.Theme.*;


public class iScene{
    public static Stage theStage;
    public static Scene welcomeScene;
    public static Scene gameScene;
    public static Scene settingScene;
    public static Scene recordScene;
    public static Game theGame = Game.theGame;

    static{


        // WelcomeScene
        Button startButton = new Button("Start");
        startButton.setPrefSize(200, 80);
        startButton.setOnMouseClicked(event -> {
            Game.theGame.theStage.setTitle("游戏将在三秒后开始");
            Game.theGame.theStage.setTitle("Game Scene");
            Game.theGame.theStage.setScene(gameScene);
            Game.theGame.play();
        });

        Button settingButton = new Button("Setting");
        settingButton.setPrefSize(200, 80);
        settingButton.setOnMouseClicked(event -> {
            theGame.theStage.setScene(settingScene);
        });

        Button recordButton = new Button("Record");
        recordButton.setPrefSize(200, 80);
        recordButton.setOnMouseClicked(event -> {
            theGame.theStage.setScene(recordScene);
        });

        VBox chooseVbox = new VBox(100);
        chooseVbox.getChildren().addAll(startButton, settingButton, recordButton);

        FlowPane flowPane = new FlowPane(chooseVbox);
        flowPane.setAlignment(Pos.CENTER);

        flowPane.setPrefWidth(1280);
        flowPane.setPrefHeight(768);

        welcomeScene = new Scene(flowPane);

        // gamePane
        HBox hb_gamePane = new HBox();

        GridPane grid_GamePane = new GridPane();
        grid_GamePane.setVgap(0);
        grid_GamePane.setHgap(0);
        String url = "file:src/Resource/Images/Grey.png";
        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 10; j++){
                SingleBlock iBtn = new SingleBlock();
                theGame.guiBlocks[i][j] = iBtn;
                iBtn.setPrefSize(32, 32);
                iBtn.setBorder(null);
                iBtn.setStyle(
                        "-fx-background-image: " +
                                "url(" + url + ");"
                );
                grid_GamePane.add(iBtn, j, i);
            }
        }

        // infoPane
        VBox vb_infoPane = new VBox();
        vb_infoPane.setSpacing(20);

        GridPane grid_nextShape = new GridPane();

        TextArea ta_infoArea = new TextArea();
        ta_infoArea.setVisible(true);

        GridPane grid_ctrlPane = new GridPane();
        Button btn_ctrl1 = new Button("Control 1");
        Button btn_ctrl2 = new Button("Control 2");
        Button btn_ctrl3 = new Button("Control 3");
        grid_ctrlPane.add(btn_ctrl1, 1, 1);
        grid_ctrlPane.add(btn_ctrl2, 2, 1);
        grid_ctrlPane.add(btn_ctrl3, 1, 2);

        vb_infoPane.getChildren().addAll(ta_infoArea, grid_ctrlPane);
        hb_gamePane.getChildren().addAll(grid_GamePane, vb_infoPane);

        // Menu
        // 菜单栏
        MenuBar menuGameBar = new MenuBar();
        menuGameBar.setPrefSize(480, 20);
        // 1. "Game" 菜单
        Menu menuGame = new Menu("游戏");
        // 1.1 NewGame item
        MenuItem itemNewGame = new MenuItem("返回菜单");
        itemNewGame.setOnAction(event -> {
            theGame.theStage.setScene(welcomeScene);
        });
        // 绑定功能

        // 1.2 ReStart 选项
        MenuItem itemRestart = new MenuItem("重新开始");
        itemRestart.setOnAction(event -> {
            theGame.reStart();
        });

        // 1.3 Save 选项
        MenuItem itemSave = new MenuItem("保存游戏");
        itemSave.setOnAction(event -> {
//			System.out.println(gameStart.thisGame.getRecorder().toString());
            Game.createSave(theGame);
        });

        // 绑定到 Game
        menuGame.getItems().addAll(itemNewGame, itemRestart, itemSave);

        // 2. "Scheme" 菜单
        Menu menuScheme = new Menu("主题");

        ToggleGroup schemeChoose = new ToggleGroup();
        RadioMenuItem itemA = new RadioMenuItem("Scheme A");
        itemA.setOnAction(event -> {
            if(itemA.isSelected()){
                theGame.setTheme(theme1);
            }
        });
        RadioMenuItem itemB = new RadioMenuItem("Scheme B");
        itemB.setSelected(true);
        itemB.setOnAction(event -> {
            if(itemB.isSelected()){
                theGame.setTheme(theme2);
            }
        });
        RadioMenuItem itemC = new RadioMenuItem("Scheme C");
        itemC.setOnAction(event -> {
            if(itemC.isSelected()){
                theGame.setTheme(theme3);
            }
        });
        schemeChoose.getToggles().addAll(itemA, itemB, itemC);

        menuScheme.getItems().addAll(itemA, itemB, itemC);

        // 3. "GUI" 菜单
        Menu menuGameRule = new Menu("游戏选项");

        ToggleGroup typeChoose = new ToggleGroup();

        RadioMenuItem itemSingle = new RadioMenuItem("a");
        itemSingle.setSelected(true);
        RadioMenuItem itemSweep = new RadioMenuItem("b");

        typeChoose.getToggles().addAll(itemSingle, itemSweep);

        menuGameRule.getItems().addAll(itemSingle, itemSweep);

        menuGameBar.getMenus().addAll(menuGame, menuScheme, menuGameRule);

        VBox vb_mainGame = new VBox();
        vb_mainGame.getChildren().addAll(menuGameBar, hb_gamePane);

        gameScene = new Scene(vb_mainGame);
    }

}
