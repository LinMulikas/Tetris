package Shape;

import GUI.Theme;

import java.util.Random;

import static Shape.Direction.*;

public abstract class Shape{
    Theme theme;
    public SingleBlock[] blocks;
    private int velocity = 1;
    public Direction state = Direction.O;

    public abstract void leftRotate();

    public abstract void rightRotate();

    public abstract void extendBlocks();

    public abstract SingleBlock[] getBlocks();

    public abstract void setTheme(Theme theme);

    public static String getColor(Shape shape){
        switch(shape.getClass().getName()){
            case "Shape_I":
                return "Cyan";
            case "Shape_J":
                return "Blue";
            case "Shape_L":
                return "Orange";
            case "Shape_O":
                return "Yellow";
            case "Shape_S":
                return "Green";
            case "Shape_T":
                return "Purple";
            case "Shape_Z":
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
