package Controller;

import GUI.Theme;
import Shape.Shape;
import Shape.SingleBlock;
import Shape.Shape_I;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Game{
    // innerBlocks 是指已经死定了的方块，实际上的图层有两个：
    // 1. 灰黑色死掉的方块
    // 2. 正在运行的方块
    int[][] innerBlocks = new int[20][10];
    public SingleBlock[][] guiBlocks = new SingleBlock[20][10];
    Shape currentShape = null;
    Shape nextShape;

    public static Game theGame;
    public Stage theStage;

    public Game(Stage theStage){
        // TODO: 临时调试，只生成 I
        this.nextShape = new Shape_I();
        this.theStage = theStage;
        theGame = this;
    }

    public boolean creatCurrentShape(){
        Shape.randomState(this.nextShape);
        nextShape.extendBlocks();
        for(SingleBlock block : nextShape.blocks){
            if(this.innerBlocks[block.position.i][block.position.j] == 1){
                return false;
            }
        }
        this.currentShape = nextShape;
        // TODO: 临时调试，只生成 I
        this.nextShape = new Shape_I();
        return true;
    }

    public void drawBlocks(){
        String urlBlack = "file:src/Resource/Images/Black.png";
        String urlGrey = "file:src/Resource/Images/Grey.png";
        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 10; j++){
                if(this.innerBlocks[i][j] == 1){
                    this.guiBlocks[i][j].setStyle(
                        "-fx-background-image: " +
                                "url(" + urlBlack + ");"
                    );
                }else{
                    this.guiBlocks[i][j].setStyle(
                            "-fx-background-image: " +
                                    "url(" + urlGrey + ");"
                    );
                }
            }
        }
        for(SingleBlock block : currentShape.blocks){
            int x = block.position.i;
            int y = block.position.j;
            String urlStyle = "file:src/Resource/Images/"
                    + Shape.getColor(currentShape)
                    + "_"
                    + Shape.getThemeID(currentShape)
                    + ".png";
            this.guiBlocks[x][y].setStyle(
                    "-fx-background-image: " +
                            "url(" + urlStyle + ");"
            );
        }
    }

    boolean isEmpty(int i, int j){
        return this.innerBlocks[i][j] == 0;
    }

    boolean isEmpty(Position position){
        return isEmpty(position.i, position.j);
    }

    public void play(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(theGame.currentShape == null){
                    if(theGame.creatCurrentShape()){
                        theGame.drawBlocks();
                    }else{
                        System.out.println("Game Over");
                        this.cancel();
                    }
                }else{
                    theGame.action();
                }

            }
        }, 0,1000);
    }

    public void action(){
        System.out.println("物体下落");
    }


    public void setTheme(Theme theme){

    }

    public void reStart(){}

    public static void createSave(Game game){}
}