package Shape;

import GUI.Theme;

public class Shape_T extends Shape{


    @Override
    public SingleBlock[] downBlocks(){
        return new SingleBlock[0];
    }

    @Override
    public SingleBlock[] leftBlocks(){
        return new SingleBlock[0];
    }

    @Override
    public void leftRotate(){

    }

    @Override
    public SingleBlock[] rightBlocks(){
        return new SingleBlock[0];
    }

    @Override
    public void rightRotate(){

    }

    @Override
    public void extendBlocks(){
    }

    @Override
    public SingleBlock[] getBlocks(){
        return new SingleBlock[0];
    }

    @Override
    public SingleBlock[] rightRotatedBlocks(){
        return new SingleBlock[0];
    }

    @Override
    public void setTheme(Theme theme){

    }

    @Override
    public Shape cloneShape(){
        return null;
    }

    @Override
    public SingleBlock[] leftRotatedBlocks(){
        return new SingleBlock[0];
    }
}
