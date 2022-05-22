package Controller;

import GUI.Theme;
import Shape.Shape;
import Shape.SingleBlock;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import Shape.Shape_Z;

import java.util.Timer;
import java.util.TimerTask;

public class Game{
    // innerBlocks 是指已经死定了的方块，实际上的图层有两个：
    // 1. 灰黑色死掉的方块
    // 2. 正在运行的方块
    int[][] innerBlocks = new int[20][10];
    public SingleBlock[][] guiBlocks = new SingleBlock[20][10];
    public Shape currentShape = null;
    public Shape nextShape;
    public TextArea infoArea;
    public boolean isGaming = true;
    public long score = 0;

    public static Game theGame;
    public Stage theStage;

    public Game(Stage theStage){
        this.nextShape = new Shape_Z();
//        this.nextShape = Shape.randomShape();
//        Shape.randomState(nextShape);
        this.theStage = theStage;
        theGame = this;
    }

    public boolean createCurrentShape(){
        Shape.randomState(this.nextShape);
        nextShape.extendBlocks();
        if(isLegal(nextShape.blocks)){
            this.currentShape = nextShape;
            this.nextShape = new Shape_Z();
//            this.nextShape = Shape.randomShape();
//            Shape.randomState(nextShape);
            return true;
        }else{
            return false;
        }
    }

    public void drawBlocks(){
        String urlBlack = "file:src/Resource/Images/Black.png";
        String urlGrey = "file:src/Resource/Images/Grey.png";
        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 10; j++){
                if(this.innerBlocks[i][j] == 0){
                    this.guiBlocks[i][j].setStyle(
                        "-fx-background-image: " +
                                "url(" + urlGrey + ");"
                    );
                }else{
                    this.guiBlocks[i][j].setStyle(
                            "-fx-background-image: " +
                                    "url(" + urlBlack + ");"
                    );
                }
            }
        }
        if(this.currentShape != null){
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
                    if(theGame.createCurrentShape()){
                        theGame.drawBlocks();
                    }else{
                        System.out.println("Game Over");
                        theGame.isGaming = false;
                        this.cancel();
                    }
                }else{
                    if(theGame.tryDown()){
                        theGame.shapeMoveDown();
                        theGame.drawBlocks();
                    }else{
                        theGame.meltShape();
                        theGame.drawBlocks();
                        try{
                            theGame.checkRows();
                        }
                        catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, 0,200);
    }

    public void checkRows() throws InterruptedException{
        int[] count = new int[20];
        for(int i = 19; i >= 0; i--){
            int number = 0;
            for(int j = 0; j < 10; j++){
                if(this.innerBlocks[i][j] == 1){
                    number++;
                }
            }
            if(number == 10){
                this.killRow(i);
                this.drawBlocks();
                this.upperMoveDown(i);
                this.drawBlocks();
                i++;
                Thread.sleep(300);
            }
        }
    }


    // 因消去第 row 行，第 row 行（包含）上方的方块下移
    public void upperMoveDown(int row){
        for(int i = row; i > 0; i--){
            for(int j = 0; j < 10; j++){
                this.innerBlocks[i][j] = this.innerBlocks[i - 1][j];
            }
        }
        for(int j = 0; j < 10; j++){
            this.innerBlocks[0][j] = 0;
        }
    }

    public void killRow(int row){
        for(int j = 0; j < 10; j ++){
            this.innerBlocks[row][j] = 0;
        }
    }

    public boolean isBoundary(int x, int y){
        return (x >= 0 && x <= 19) && (y >= 0 && y <= 9);
    }

    // move and action
    public boolean tryDown(){
        SingleBlock[] blocks = this.currentShape.downBlocks();
        return isLegal(blocks);
    }

    // 方块确认要死亡把当前的方块融合进背景图
    public void meltShape(){
        for(SingleBlock block : this.currentShape.blocks){
            this.innerBlocks[block.position.i][block.position.j] = 1;
        }
        this.currentShape = null;
    }

    public void shapeMoveDown(){
        for(SingleBlock block : this.currentShape.blocks){
            block.position.goDown();
        }
        this.drawBlocks();
    }

    public void shapeMoveLeft(){
        for(SingleBlock block : this.currentShape.blocks){
            block.position.goLeft();
        }
        this.drawBlocks();
    }

    public void shapeMoveRight(){
        for(SingleBlock block : this.currentShape.blocks){
            block.position.goRight();
        }
        this.drawBlocks();
    }

    public void shapeRotateLeft(){
        this.currentShape.leftRotate();
        this.drawBlocks();
    }

    public void shapeRotateRight(){
        this.currentShape.rightRotate();
        this.drawBlocks();
    }

    public boolean tryLeftRotate(){
        SingleBlock[] blocks = this.currentShape.leftRotatedBlocks();
        return isLegal(blocks);
    }

    public boolean tryRightRotate(){
        SingleBlock[] blocks = this.currentShape.rightRotatedBlocks();
        return isLegal(blocks);
    }

    public boolean tryMoveLeft(){
        SingleBlock[] blocks = this.currentShape.leftBlocks();
        return isLegal(blocks);
    }

    public boolean isLegal(SingleBlock[] blocks){
        for(SingleBlock block : blocks){
            if(!isBoundary(block.position.i, block.position.j)
                    || this.innerBlocks[block.position.i][block.position.j] == 1){
                return false;
            }
        }
        return true;
    }

    public boolean tryMoveRight(){
        SingleBlock[] blocks = this.currentShape.rightBlocks();
        return isLegal(blocks);
    }

    public void setTheme(Theme theme){

    }

    public void putMessage(String str){
        this.infoArea.appendText(str);
    }

    public void reStart(){}

    public static void createSave(Game game){}
}