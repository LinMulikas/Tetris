package Shape;

import Controller.Position;
import GUI.Theme;

import java.util.Random;

import static Shape.Direction.*;

public abstract class Shape{
    public Position p = new Position(1, 3);

    Theme theme;
    public SingleBlock[] blocks;
    private int velocity = 1;
    public Direction state = Direction.O;

    public SingleBlock[] downBlocks(){
        SingleBlock[] ans = new SingleBlock[this.blocks.length];
        for(int i = 0; i < ans.length; i++){
            ans[i] = new SingleBlock();
        }

        for(int i = 0; i < ans.length; i++){
            ans[i].position = this.blocks[i].position.down();
        }
        return ans;
    }

    public SingleBlock[] leftBlocks(){
        SingleBlock[] ans = new SingleBlock[this.blocks.length];
        for(int i = 0; i < ans.length; i++){
            ans[i] = new SingleBlock();
        }

        for(int i = 0; i < ans.length; i++){
            ans[i].position = this.blocks[i].position.left();
        }
        return ans;
    }

    public abstract void leftRotate();


    public SingleBlock[] rightBlocks(){
        SingleBlock[] ans = new SingleBlock[this.blocks.length];
        for(int i = 0; i < ans.length; i++){
            ans[i] = new SingleBlock();
        }

        for(int i = 0; i < ans.length; i++){
            ans[i].position = this.blocks[i].position.right();
        }
        return ans;
    }

    public abstract void rightRotate();

    public abstract void extendBlocks();

    public SingleBlock[] getBlocks(){
        return this.blocks;
    }

    public SingleBlock[] leftRotatedBlocks(){
        Shape result = this.cloneShape();
        result.leftRotate();
        return result.blocks;
    }

    public SingleBlock[] rightRotatedBlocks(){
        Shape result = this.cloneShape();
        result.rightRotate();
        return result.blocks;
    }

    public abstract Shape cloneShape();


    public abstract void setTheme(Theme theme);

    public static String getColor(Shape shape){
        String str = shape.getClass().getTypeName();
        switch(shape.getClass().getName()){
            case "Shape.Shape_I":
                return "Cyan";
            case "Shape.Shape_J":
                return "Blue";
            case "Shape.Shape_L":
                return "Orange";
            case "Shape.Shape_O":
                return "Yellow";
            case "Shape.Shape_S":
                return "Green";
            case "Shape.Shape_T":
                return "Purple";
            case "Shape.Shape_Z":
                return "Red";
        }
        return null;
    }

    public static String getThemeID(Shape shape){
        switch(shape.theme){
            case theme1:
                return "1";
            case theme2:
                return "2";
            case theme3:
                return "3";
        }
        return null;
    }

    public void speedUp(){
        this.velocity = 2;
    }

    public void speedDown(){
        this.velocity = 1;
    }

    public int getSize(){
        return this.blocks.length;
    }

    public int getIof(int i){
        return this.blocks[i].position.getI();
    }

    public int getJof(int i){
        return this.blocks[i].position.getJ();
    }

    // 强制下降
    public void moveDown(){
        for(SingleBlock block : this.blocks){
            block.position = block.position.down();
        }
    }

    public static Shape randomShape(){
        Random random = new Random();
        int id = random.nextInt(7);
        switch(id){
            case 0:
                return new Shape_I();
            case 1:
                return new Shape_J();
            case 2:
                return new Shape_L();
            case 3:
                return new Shape_O();
            case 4:
                return new Shape_S();
            case 5:
                return new Shape_T();
            case 6:
                return new Shape_Z();
        }
        return null;
    }

    public static void randomState(Shape shape){
        Random random = new Random();
        int state = random.nextInt(4);
        switch(state){
            case 0:
                shape.state = O;
                break;
            case 1:
                shape.leftRotate();
                break;
            case 2:
                shape.rightRotate();
                break;
            case 3:
                shape.leftRotate();
                shape.leftRotate();
        }
    }

    public static void main(String[] args){
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            System.out.println(random.nextInt(6));
        }
    }
}


