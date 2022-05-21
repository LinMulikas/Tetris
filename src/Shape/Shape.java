package Shape;

import Controller.Position;
import GUI.Theme;

import java.util.Random;

import static Shape.Direction.*;

public abstract class Shape{
    Position p = new Position(1, 3);
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

    public void leftRotate(){
        switch(state){
            case O:
                this.state = L;
                this.p.i += 2;
                this.p.j += 1;
                this.extendBlocks();
                return;
            case R:
                this.state = O;
                this.p.i += 1;
                this.p.j -= 2;
                this.extendBlocks();
                return;
            case D:
                this.state = R;
                this.p.i -= 2;
                this.p.j -= 1;
                this.extendBlocks();
                break;
            case L:
                this.state = D;
                this.p.i -= 1;
                this.p.j += 2;
                this.extendBlocks();
        }
    }

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

    public void rightRotate(){
        switch(state){
            case O:
                this.state = R;
                this.p.i -= 1;
                this.p.j += 2;
                this.extendBlocks();
                return;
            case R:
                this.state = D;
                this.p.i += 2;
                this.p.j += 1;
                this.extendBlocks();
                return;
            case D:
                this.state = L;
                this.p.i += 1;
                this.p.j -= 2;
                this.extendBlocks();
                return;
            case L:
                this.state = O;
                this.p.i -= 2;
                this.p.j -= 1;
                this.extendBlocks();
        }
    }

    public abstract void extendBlocks();

    public SingleBlock[] getBlocks(){
        return this.blocks;
    }

    public SingleBlock[] leftRotatedBlocks(){
        Shape result = this.cloneShape();
        switch(result.state){
            case O:
                result.state = L;
                result.p.i += 2;
                result.p.j += 1;
                result.extendBlocks();
                return result.blocks;
            case R:
                result.state = O;
                result.p.i += 1;
                result.p.j -= 2;
                result.extendBlocks();
                return result.blocks;
            case D:
                result.state = R;
                result.p.i -= 2;
                result.p.j -= 1;
                result.extendBlocks();
                return result.blocks;
            case L:
                result.state = D;
                result.p.i -= 1;
                result.p.j += 2;
                result.extendBlocks();
                return result.blocks;
        }
        return null;
    }

    public SingleBlock[] rightRotatedBlocks(){
        Shape result = this.cloneShape();
        switch(result.state){
            case O:
                result.state = R;
                result.p.i -= 1;
                result.p.j += 2;
                result.extendBlocks();
                return result.blocks;
            case R:
                result.state = D;
                result.p.i += 2;
                result.p.j += 1;
                result.extendBlocks();
                return result.blocks;
            case D:
                result.state = L;
                result.p.i += 1;
                result.p.j -= 2;
                result.extendBlocks();
                return result.blocks;
            case L:
                result.state = O;
                result.p.i -= 2;
                result.p.j -= 1;
                result.extendBlocks();
                return result.blocks;
        }

        return null;
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
